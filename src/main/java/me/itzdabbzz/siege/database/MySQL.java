package me.itzdabbzz.siege.database;

import me.itzdabbzz.siege.Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class MySQL {


    protected final Connection connection;

    private MySQL(Connection connection) {
        this.connection = connection;
    }

    public void createTables() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE `playerData` (\n" +
                        "\t`playerId` INT NOT NULL AUTO_INCREMENT,\n" +
                        "\t`uuid` VARCHAR(42) unique,\n" +
                        "\t`name` VARCHAR(32),\n" +
                        "\t`lastIp` VARCHAR(40),\n" +
                        "\t`lastLogin` DATETIME,\n" +
                        "\t`onlineTime` BIGINT,\n" +
                        "\t`firstLogin` DATETIME,\n" +
                        "\t`KD` INT DEFAULT '0',\n" +
                        "\t`Kills` INT DEFAULT '0',\n" +
                        "\t`Deaths` INT DEFAULT '0',\n" +
                        "\t`Wins` INT DEFAULT '0',\n" +
                        "\t`Losses` INT DEFAULT '0',\n" +
                        "\t`Revives` INT DEFAULT '0',\n" +
                        "\t`attack` TEXT DEFAULT 'No Op',\n" +
                        "\t`defense` TEXT DEFAULT 'No Op',\n" +
                        "\tPRIMARY KEY (`playerId`)\n" +
                        ");"
        );

        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) as counter FROM player_data");
        rs.next();
        if (rs.getInt("counter") == 0) {
            // Console is UUID 00000000-0000-0000-0000-000000000000
            connection.createStatement().execute("INSERT INTO player_data(uuid, last_name, last_ip) VALUES ('00000000-0000-0000-0000-000000000000', '*Console*', '127.0.0.1');");
        }
    }

    public void updatePlayer(UUID player, String lastName, String ip) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) as count FROM player_data WHERE uuid = ?");
        ps.setString(1, player.toString());
        int count;
        try (ResultSet set = ps.executeQuery()) {
            set.next();
            count = set.getInt("count");
        }

        if (count == 0) {
            // Insert.
            ps = connection.prepareStatement("insert into player_data(uuid, last_name, last_seen, last_ip) VALUES (?, ?, ? ,?);");
            ps.setString(1, player.toString());
            ps.setString(2, lastName);
            ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            ps.setString(4, ip);
            ps.executeUpdate();
        } else {
            // Update.
            ps = connection.prepareStatement("update player_data set last_name = ?, last_seen = ?, last_ip = ? where uuid = ?;");
            ps.setString(4, player.toString());
            ps.setString(1, lastName);
            ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            ps.setString(3, ip);
            ps.executeUpdate();
        }
    }

    public User getPlayerInfo(UUID uuid) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT uuid, last_name, last_ip, last_seen FROM player_data WHERE uuid = ?");
        ps.setString(1, uuid.toString());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new User(uuid, rs.getString("last_name"), rs.getString("last_ip"), new Date(rs.getTimestamp("last_seen").getTime()));
            }

            return null;
        }
    }

    private Integer getIdForPlayerFromUUID(UUID uuid) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT player_id FROM player_data WHERE uuid = ?");
        ps.setString(1, uuid.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("player_id");
        }

        throw new Exception("No player with that UUID exists.");
    }

    /**
     * Closes the connection. Implements java.lang.AutoClosable.
     * @throws Exception
     */
    public void close() throws Exception {
        if (!connection.isClosed()) {
            if (!connection.getAutoCommit()) {
                this.rollbackTransaction();
            }

            connection.close();
        }
    }

    private java.sql.Timestamp getCurrentDate() {
        return new java.sql.Timestamp(new Date().getTime());
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
