package me.itzdabbzz.siege.minigame.map;

import com.google.common.collect.Lists;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.game.game.GameType;
import me.itzdabbzz.siege.minigame.map.maps.TestMap;
import me.itzdabbzz.siege.utils.CC;
import me.itzdabbzz.siege.utils.ServerUtil;
import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages {@link GameMap}s
 */
public class MapManager
{
    /**
     * The plugin instance
     */
    private final R6Siege manager;

    /**
     * MapManager constructor
     * @param manager The plugin instance
     */
    public MapManager(R6Siege manager)
    {
        this.manager = manager;

        //addMap(new TestMap());
    }

    /**
     * All the possible maps for a game
     */
    private final Set<GameMap> maps = new HashSet<>();

    /**
     * Adds a {@link GameMap} to the set of maps
     * @see #maps
     * @param map The map to add
     */
    private void addMap(GameMap map)
    {
        maps.add(map);
    }

    /**
     * Gets a random {@link GameMap} for a game
     * @param type The {@link GameType} to get a map for
     * @return A suitable GameMap for the game
     */
    public GameMap getSuitableMap(GameType type)
    {
        List<GameMap> valid = Lists.newArrayList();

        for (GameMap map : maps)
        {
            if (type != map.getGameType() || !map.isValid())
            {
                continue;
            }

            valid.add(map);
        }

        if (valid.size() == 0)
        {
            // No valid maps, restart server
            Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(CC.red + "No maps are available for this game"));

            manager.restart();
            return null;
        }

        // Get random map
        return valid.get(ServerUtil.nextInt(valid.size()));
    }
}