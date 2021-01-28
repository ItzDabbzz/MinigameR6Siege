package me.itzdabbzz.siege.minigame.game.team;

import me.itzdabbzz.siege.chat.ChatChannel;
import me.itzdabbzz.siege.chat.SubscribeMode;
import me.itzdabbzz.siege.utils.ItemUtil;
import me.itzdabbzz.siege.utils.ServerUtil;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * The representation of a team in a game
 */
public class Team {

    /**
     * List of player in team.
     */
    private final List<Player> players = new ArrayList<Player>();

    /**
     * List of player in team.
     */
    private final Set<UUID> playerIds = new HashSet<>();
    /**
     * The name of the team
     */
    private final String name;

    /**
     * The colour of the team
     */
    private final Color color;

    /**
     * The team's prefix
     */
    private final String prefix;

    /**
     * The team's current side
     */
    private final TeamSide side;

    /**
     * Chat channel of the team.
     */
    private final ChatChannel teamchat = ChatChannel.createRandom();
    /**
     * Maximum amount of players in this team.
     */
    private final int maxPlayers;

    /**
     * Constructor for a team
     *
     * @param name   The team name
     * @param colour The team colour
     */
    public Team(String name, Color colour) {
        this(name, colour, colour.toString(), TeamSide.NO_SIDE);
    }

    public Team(String name, Color colour, String prefix, int maxPlayers) {
        this.name = name;
        this.color = colour;
        this.prefix = prefix;
        this.side = TeamSide.NO_SIDE;
        this.maxPlayers = maxPlayers;
    }

    public Team(String name, Color colour, String prefix, TeamSide side) {
        this.name = name;
        this.color = colour;
        this.prefix = prefix;
        this.side = side;
        this.maxPlayers = 5;
    }

    public Team(String name, Color colour, String prefix, TeamSide side, int maxPlayers) {
        this.name = name;
        this.color = colour;
        this.prefix = prefix;
        this.side = side;
        this.maxPlayers = maxPlayers;
    }

    /**
     * Teleports all players.
     *
     * @param loc location, to teleport to players
     */
    public void teleportAll(final Location loc) {
        for (Player p : this.players)
            p.teleport(loc);
    }

    /**
     * Applies dyed armor to all players.
     */
    public void applyArmorAll() {
        for (Player p : this.players)
            this.applyArmor(p);
    }

    /**
     * Adds player to team.
     *
     * @param p player to add
     */
    public void addPlayer(final Player p) {
        p.sendMessage(ChatColor.GREEN + "You have joined team '" + this.name + "'!");
        this.players.add(p);
        this.playerIds.add(p.getUniqueId());
        this.teamchat.subscribe(p, SubscribeMode.READ_WRITE);
    }

    /**
     * Adds players to team.
     *
     * @param players players to add
     */
    public void addPlayers(final Player... players) {
        for (Player p : players) {
            this.playerIds.add(p.getUniqueId());
            this.addPlayer(p);
        }
    }

    /**
     * Removes player from the team.
     *
     * @param p player to remove
     */
    public void removePlayer(final Player p) {
        p.sendMessage(ChatColor.GREEN + "You have left team '" + this.name + "'!");
        this.players.remove(p);
        this.playerIds.remove(p.getUniqueId());
        this.teamchat.unsubscribe(p);
    }

    /**
     * Return's this team chat channel.
     *
     * @return team chat channel
     */
    public ChatChannel getTeamChannel() {
        return this.teamchat;
    }

    /**
     * Gets the team's name
     *
     * @return The team name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the team's colour
     *
     * @return The team colour
     */
    public Color getColour() {
        return color;
    }

    /**
     * Gets the team's prefix
     *
     * @return The team prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets the Team's side
     *
     * @return The Teams Side
     */
    public TeamSide getSide() {
        return side;
    }

    /**
     * Retruns list of players.
     *
     * @return list of players in this team
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets the {@link UUID}s of players on the team
     * @return The team's players
     */
    public Set<UUID> getPlayerIds()
    {
        return playerIds;
    }

    /**
     * Returns if this team contains specified player.
     *
     * @param p
     *            specified player
     * @return true or false
     */
    public boolean contains(final Player p) {
        return this.players.contains(p);
    }

    /**
     * Returns player count.
     *
     * @return amount of players
     */
    public int getPlayerCount() {
        return this.players.size();
    }

    /**
     * Get maximum number of players in team.
     *
     * @return amount of players
     */
    public int getMaximumPlayers() {
        return this.maxPlayers;
    }

    /**
     * Returns whether one player can join this team.
     *
     * @return boolean, if player can join
     */
    public boolean canJoin() {
        return this.players.size() < this.maxPlayers;
    }

    /**
     * Returns whether specified amount of players can join this team.
     *
     * @param amount
     *            amount of players
     * @return true, if they can, else false
     */
    public boolean canJoin(final int amount) {
        return this.players.size() + amount < this.maxPlayers;
    }

    /**
     * Applies colored armor to specified player.
     *
     * @param p
     *            specified player
     */
    public void applyArmorTo(final Player p) {
        this.applyArmor(p);
    }
    /**
     * Applies armor to specified player.
     *
     * @param player
     *            player to apply armor to
     */
    public void applyArmor(final Player player) {
        player.getInventory().setHelmet(
                ItemUtil.coloredLetherArmor(Material.LEATHER_HELMET, this.color));
        player.getInventory().setChestplate(
                ItemUtil.coloredLetherArmor(Material.LEATHER_CHESTPLATE, this.color));
        player.getInventory().setLeggings(
                ItemUtil.coloredLetherArmor(Material.LEATHER_LEGGINGS, this.color));
        player.getInventory().setBoots(
                ItemUtil.coloredLetherArmor(Material.LEATHER_BOOTS, this.color));
    }

}