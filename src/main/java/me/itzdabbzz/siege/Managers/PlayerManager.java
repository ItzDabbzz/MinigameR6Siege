package me.itzdabbzz.siege.Managers;

import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager {
    private static final Map<UUID, PlayerProfile> profiles = new HashMap<UUID, PlayerProfile>();
    private static boolean initialized = false;

    public static String playerProfile(final UUID uuid) {
        return R6Siege.getInstance().getDataFolder().getAbsolutePath()+ "/profiles/" + uuid + ".yml";
    }

    public static Map<UUID, PlayerProfile> getProfiles() {
        return profiles;
    }

    /**
     * Initializes static obeject of storage engine.
     *
     * @param core
     *            R6Siege
     */
    public static void initialize(final R6Siege core) {
        if (!initialized)
            initialized = true;
    }
    /**
     * Returns UUIDs of player's friends.
     *
     * @param player
     *            player
     * @return lsit of friends
     */
    public static List<UUID> getFriends(final Player player) {
        return profiles.get(player.getUniqueId()).getFriends();
    }

    /**
     * Returns UUIDs of player's foes.
     *
     * @param player
     * @return
     */
    public static List<UUID> getFoes(final Player player) {
        return profiles.get(player.getUniqueId()).getFoes();
    }

    /**
     * Saves player's profile to file.
     *
     * @param uniqueId
     */
    public static void saveProfile(final UUID uniqueId) {
        ConsoleUtil.sendConsoleMessage("Saving profile for " + uniqueId.toString() + " to disk...");
        profiles.get(uniqueId).save(playerProfile(uniqueId));
    }


    /**
     * Loads player profile from disk or creates an empty one.
     *
     * @param uniqueId
     */
    public static void loadProfile(final UUID uniqueId) {
        File f = new File(playerProfile(uniqueId));
        if (f.exists()) {
            ConsoleUtil.sendConsoleMessage("Loading profile for " + uniqueId + "...");
            profiles.put(uniqueId, PlayerProfile.load(R6Siege.getInstance().getDataFolder().getAbsolutePath()+ "/profiles/" + uniqueId + ".yml"));
        }
        else {
            ConsoleUtil.sendConsoleMessage("Creating new profile for " + uniqueId.toString());
            profiles.put(uniqueId, new PlayerProfile(uniqueId));
        }
    }

    /**
     * Loads player profile from disk or creates an empty one.
     *
     * @param uniqueId
     */
    public static void loadProfile(final UUID uniqueId, String IP) {
        File f = new File(playerProfile(uniqueId));
        if (f.exists()) {
            ConsoleUtil.sendConsoleMessage("Loading profile for " + uniqueId + "...");
            profiles.put(uniqueId, PlayerProfile.load(R6Siege.getInstance().getDataFolder().getAbsolutePath()+ "/profiles/" + uniqueId + ".yml"));
        }
        else {
            ConsoleUtil.sendConsoleMessage("Creating new profile for " + uniqueId.toString());
            profiles.put(uniqueId, new PlayerProfile(uniqueId, IP));
        }
    }

    public static void saveProfiles() {
        for (PlayerProfile profile : profiles.values()) {
            profile.saveXML(playerProfile(profile.getUniqueId()));
            ConsoleUtil.consoleDebug("Saving Profile For " + profile.getLastKnownName());
        }
    }
}
