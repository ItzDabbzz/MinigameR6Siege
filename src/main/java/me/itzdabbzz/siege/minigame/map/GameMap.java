package me.itzdabbzz.siege.minigame.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.itzdabbzz.siege.minigame.game.game.GameType;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryType;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

/**
 * The representation of a map for a game
 */
@SuppressWarnings("unused")
public class GameMap
{
    /**
     * The name of this map
     */
    private final String name;

    /**
     * Gets the name of this map
     * @return The map name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * The GameType this map will run on
     */
    private final GameType type;

    /**
     * Gets the {@link GameType} of this map
     * @return This type of game this map will be used for
     */
    final GameType getGameType()
    {
        return type;
    }


    /**
     * GameMap constructor
     * @param name The name of this map
     * @param worldName The name of the world containing this map
     * @param type The GameType this map will run
     */
    public GameMap(String name, String worldName, GameType type)
    {
        this.name = name;
        this.type = type;

        configureWorld(worldName);
    }

    /**
     * Configures the world options
     * @param worldName The name of the world
     */
    private void configureWorld(String worldName)
    {
        world = new WorldCreator(worldName).createWorld();
        Bukkit.getWorlds().add(world);

        world.setTime(100L);
        world.setAutoSave(false);
        world.setGameRuleValue("DO_MOB_SPAWNING", "false");
        world.setGameRuleValue("MOB_GRIEFING", "false");
        world.setGameRuleValue("DO_DAYLIGHT_CYCLE", "false");
    }

    /**
     * Whether the map has a valid world, lobby location and at least one spawnpoint
     * @return Whether the map is valid
     */
    final boolean isValid()
    {
        return world != null && lobbyLocation != null && !spawns.isEmpty();
    }

    /**
     * The world for the GameMap
     */
    private World world;

    /**
     * Gets the world for this GameMap
     * @see #world
     * @return The world
     */
    public final World getWorld()
    {
        return world;
    }

    /**
     * The location of the map's lobby
     */
    private Location lobbyLocation;

    /**
     * Sets the lobby location
     * @param x The 'x' coordinate of the location
     * @param y The 'y' coordinate of the location
     * @param z The 'z' coordinate of the location
     * @param yaw The yaw position of the location
     * @param pitch The pitch position of the location
     */
    protected final void setLobbyLocation(int x, int y, int z, float yaw, float pitch)
    {
        lobbyLocation = new Location(world, x + 0.5, y, z + 0.5, yaw, pitch);
    }

    /**
     * Gets the lobby location
     * @see #lobbyLocation
     * @return The lobby location
     */
    public final Location getLobbyLocation()
    {
        return lobbyLocation;
    }

    /**
     * The spawn locations
     */
    private final Map<String, Set<Location>> spawns = Maps.newHashMap();

    /**
     * Gets the spawnpoints for a team
     * @param team The team
     * @return The team's spawns
     */
    public final List<Location> getSpawns(String team)
    {
        return Lists.newArrayList(spawns.get(team));
    }

    /**
     * Adds a spawnpoint to the map
     * @param team The team the spawn is for
     * @param x The 'x' coordinate of the spawn
     * @param y The 'y' coordinate of the spawn
     * @param z The 'z' coordinate of the spawn
     * @param yaw The yaw position of the spawn
     * @param pitch The pitch position of the spawn
     */
    protected final void addSpawn(String team, int x, int y, int z, float yaw, float pitch)
    {
        spawns.putIfAbsent(team, new HashSet<>());

        spawns.get(team).add(new Location(world, x + 0.5, y, z + 0.5, yaw, pitch));
    }

    /**
     * The data points
     */
    private final Map<String, Set<Location>> dataPoints = Maps.newHashMap();

    /**
     * Adds a data point to the map
     * @param name The name of the data point
     * @param x The 'x' coordinate of the point
     * @param y The 'y' coordinate of the point
     * @param z The 'z' coordinate of the point
     * @param yaw The yaw position of the spawn
     * @param pitch The pitch position of the spawn
     */
    protected final void addData(String name, int x, int y, int z, float yaw, float pitch)
    {
        dataPoints.putIfAbsent(name, new HashSet<>());

        dataPoints.get(name).add(new Location(world, x + 0.5, y, z + 0.5, yaw, pitch));
    }

    private final Set<Location> savedBases = new CopyOnWriteArraySet<Location>();

    protected final boolean addBase(Location loc) {
        return savedBases.add(loc);
    }

    protected final boolean removeBase(Location loc) {
        return savedBases.remove(loc);
    }

    public void clearSavedBases() {
        savedBases.clear();
    }

    protected Set<Location> getSavedBases() {
        return savedBases;
    }
}