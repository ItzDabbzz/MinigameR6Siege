package me.itzdabbzz.siege.Objects;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

/**
 * Object for storing player's friends and unfriends.
 */
@XmlRootElement
public class PlayerProfile {
    /**
     * Player's UUID.
     */
    @XmlID
    @XmlAttribute
    protected final UUID uuid;
    /**
     * Player's friends.
     */
    @XmlAttribute
    protected final List<UUID> friends = new ArrayList<UUID>();
    /**
     * Player's foes.
     */
    @XmlAttribute
    protected final List<UUID> foes = new ArrayList<UUID>();
    /**
     * Player's settings.
     */
    @XmlAttribute
    protected final Map<Settings, Boolean> settings = new HashMap<Settings, Boolean>();

    /**
     * Spectating status.
     */
    protected transient boolean spectating = false;

    /**
     * Last known name of this player.
     */
    @XmlAttribute
    protected String lastKnownName = "";
    /**
     * Amount of player's points (probably in-game currency or whatever).
     */
    @XmlAttribute
    protected int points = 0;
    @XmlAttribute
    protected int warnCount = 0;

    /**
     * Represents party, that player is currently in. If is player not in any party, it is null.
     */
    protected transient Party party;

    protected Location lastLoc;

    protected Location deathLoc;

    private String lastIP;

    private long lastLongin;

    private long firstLogin;

    private long onlineTime;

    private double kd;

    private long kills;

    private long deaths;

    private long killAssists;

    private int wins;

    private int losses;

    private int revives;

    private String atk;

    private String def;

    /**
     * Creates player profile from Player object.
     *
     * @param player
     */
    public PlayerProfile(final Player player) {
        this.uuid = player.getUniqueId();
    }

    /**
     * Creates player profile from UUID.
     *
     * @param player
     */
    public PlayerProfile(final UUID player) {
        this.uuid = player;
        this.lastIP = "0.0.0.0";
        this.spectating = false;
        this.firstLogin = System.currentTimeMillis();
        this.lastLongin = System.currentTimeMillis();
        this.onlineTime = 0;
        this.kd = 0;
        this.kills = 0;
        this.deaths = 0;
        this.killAssists = 0;
        this.wins = 0;
        this.losses = 0;
        this.revives = 0;
        this.atk = "No OP";
        this.def = "No OP";
    }

    /**
     * Creates player profile from UUID and a IP.
     *
     * @param player
     */
    public PlayerProfile(final UUID player, String IP) {
        this.uuid = player;
        this.lastIP = IP;
        this.spectating = false;
        this.firstLogin = System.currentTimeMillis();
        this.lastLongin = System.currentTimeMillis();
        this.onlineTime = 0;
        this.kd = 0;
        this.kills = 0;
        this.deaths = 0;
        this.killAssists = 0;
        this.wins = 0;
        this.losses = 0;
        this.revives = 0;
        this.atk = "No OP";
        this.def = "No OP";
    }

    /**
     * Adds friend.
     *
     * @param player
     */
    public void addFriend(final UUID player) {
        this.friends.add(player);
    }

    /**
     * Removes friend.
     *
     * @param player
     */
    public void removeFriend(final UUID player) {
        this.friends.remove(player);
    }

    public void addFoe(final UUID player) {
        this.foes.add(player);
    }

    public void removeFoe(final UUID player) {
        this.foes.remove(player);
    }

    /**
     * Returns UUID of profile.
     *
     * @return
     */
    public UUID getUniqueId() {
        return this.uuid;
    }

    /**
     * Return list of player's friends.
     *
     * @return
     */
    public List<UUID> getFriends() {
        return ImmutableList.copyOf(this.friends);
    }

    public List<UUID> getFoes() {
        return ImmutableList.copyOf(this.foes);
    }

    /**
     * Returns whatever is this player friend with the specified.
     *
     * @param uniqueId
     * @return
     */
    public boolean isFriend(final UUID uniqueId) {
        return this.friends.contains(uniqueId);
    }

    public boolean isFoe(final UUID uniqueId) {
        return this.foes.contains(uniqueId);
    }

    /**
     * Returns setting value.
     *
     * @param setting
     * @return
     */
    public boolean getSetting(final Settings setting) {
        if (this.settings.containsKey(setting))
            return this.settings.get(setting);
        else
            return true;
    }

    /**
     * Set players settings.
     *
     * @param setting
     * @param value
     */
    public void setSetting(final Settings setting, final boolean value) {
        this.settings.put(setting, value);
    }

    /**
     * Return's whether is player spectating.
     *
     * @return
     */
    public boolean isSpectating() {
        return this.spectating;
    }

    /**
     * Sets player's spectating state.
     *
     * @param spectating
     */
    public void setSpectating(final boolean spectating) {
        this.spectating = spectating;
    }

    public String getLastKnownName() {
        return lastKnownName;
    }

    public void setLastKnownName(String lastKnownName) {
        this.lastKnownName = lastKnownName;
    }

    public Location getLastLoc() {
        return lastLoc;
    }

    public void setLastLoc(Location lastLoc) {
        this.lastLoc = lastLoc;
    }

    public Location getDeathLoc() {
        return deathLoc;
    }

    public void setDeathLoc(Location deathLoc) {
        this.deathLoc = deathLoc;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public long getLastLongin() {
        return lastLongin;
    }

    public void setLastLongin(long lastLongin) {
        this.lastLongin = lastLongin;
    }

    public long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getKillAssists() {
        return killAssists;
    }

    public void setKillAssists(long killAssists) {
        this.killAssists = killAssists;
    }

    public int getWins() { return wins; }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getRevives() {
        return revives;
    }

    public void setRevives(int revives) {
        this.revives = revives;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    /**
     * Saves player's profile to file.
     *
     * @param path
     *            path to save
     */
    public void save(final String path) {
        YamlConfiguration yaml = new YamlConfiguration();

        yaml.set("player.uuid", this.uuid.toString());
        yaml.set("player.points", this.points);
        yaml.set("player.warnCount", this.warnCount);
        yaml.set("player.lastKnownName", this.lastKnownName);
        yaml.set("player.lastLoc.world", this.lastLoc.getWorld().toString());
        yaml.set("player.lastLoc.x", this.lastLoc.getBlockX());
        yaml.set("player.lastLoc.y", this.lastLoc.getBlockY());
        yaml.set("player.lastLoc.z", this.lastLoc.getBlockZ());
        yaml.set("player.lastLoc.pitch", this.lastLoc.getPitch());
        yaml.set("player.lastLoc.yaw", this.lastLoc.getYaw());
        yaml.set("player.kd", this.kd);
        yaml.set("player.kills", this.kills);
        yaml.set("player.deaths", this.deaths);
        yaml.set("player.killassists", this.killAssists);
        yaml.set("player.attack", this.atk);
        yaml.set("player.defense", this.def);
        yaml.set("player.wins", this.wins);
        yaml.set("player.losses", this.losses);
        yaml.set("player.firstlogin", this.firstLogin);
        yaml.set("player.lastlogin", this.lastLongin);
        yaml.set("player.lastip", this.lastIP);
        yaml.set("player.onlinetime", this.onlineTime);

        if(deathLoc != null)
        {
            yaml.set("player.deathLoc.world", this.deathLoc.getWorld().toString());
            yaml.set("player.deathLoc.x", this.deathLoc.getBlockX());
            yaml.set("player.deathLoc.y", this.deathLoc.getBlockY());
            yaml.set("player.deathLoc.z", this.deathLoc.getBlockZ());
            yaml.set("player.deathLoc.pitch", this.deathLoc.getPitch());
            yaml.set("player.deathLoc.yaw", this.deathLoc.getYaw());
        }else {
            yaml.set("player.deathLoc", "null");
        }

        yaml.set("player.friends", this.friends);
        yaml.set("player.foes", this.foes);

        try {
            yaml.save(new File(path));
        } catch (IOException e) {
            ConsoleUtil.consoleError("Can't save player profile: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Loads player profile from specified path.
     *
     * @param path
     * @return
     */
    public static PlayerProfile load(final String path) {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(path));
        UUID uuid = UUID.fromString(yaml.getString("player.uuid"));

        PlayerProfile profile = new PlayerProfile(uuid);

        profile.points = yaml.getInt("player.points");
        profile.lastKnownName = yaml.getString("player.lastKnownName");
        profile.lastLoc = new Location(Bukkit.getWorld(yaml.getString("player.lastLoc.world")), yaml.getDouble("player.lastLoc.x"), yaml.getDouble("player.lastLoc.y"), yaml.getDouble("player.lastLoc.y"), yaml.getInt("player.lastLoc.yaw"), yaml.getInt("player.lastLoc.pitch"));
        profile.kd = yaml.getDouble("player.kd");
        profile.kills = yaml.getInt("player.kills");
        profile.killAssists = yaml.getInt("player.killAssists");
        profile.deaths = yaml.getInt("player.deaths");
        profile.wins = yaml.getInt("player.wins");
        profile.losses = yaml.getInt("player.losses");
        profile.atk = yaml.getString("player.attack");
        profile.def = yaml.getString("player.defense");
        profile.firstLogin = yaml.getLong("player.firstlogin");
        profile.lastLongin = yaml.getLong("player.lastLogin");
        profile.lastIP = yaml.getString("player.lastip");
        profile.onlineTime = yaml.getLong("player.onlinetime");

        if(yaml.getString("player.deathLoc").equals("null")) {

        }else {
            profile.deathLoc = new Location(Bukkit.getWorld(yaml.getString("player.deathLoc.world")), yaml.getDouble("player.deathLoc.x"), yaml.getDouble("player.deathLoc.y"), yaml.getDouble("player.deathLoc.y"), yaml.getInt("player.deathLoc.yaw"), yaml.getInt("player.deathLoc.pitch"));
        }



        List<?> friends = yaml.getList("player.friends");
        List<?> foes = yaml.getList("player.foes");

        for (Object obj : friends)
            profile.addFriend(UUID.fromString(obj.toString()));
        for (Object obj : foes)
            profile.addFoe(UUID.fromString(obj.toString()));

        return profile;
    }

    /**
     * Return the number of points.
     *
     * @return
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Add specified amount of points.
     *
     * @param points
     */
    public void addPoints(final int points) {
        this.points += points;
    }

    public void saveXML(final String profilePath) {
        //TODO: Not yet implemented
    }

    public Party getParty() {
        return this.party;
    }

    public void setParty(final Party party) {
        this.party = party;
    }
}