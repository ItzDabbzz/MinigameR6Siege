package me.itzdabbzz.siege.utils;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.R6Siege;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides utility methods related to the server that is running the plugin.
 */
public class ServerUtil {


    private static CommandMap commandMap;
    private static boolean paper;
    private static boolean asyncTabCompletionSupported;

    private static R6Siege instance = R6Siege.getInstance();
    /**
     * @see ThreadLocalRandom#nextInt(int)
     */
    public static int nextInt(int bound)
    {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    /**
     * Registers a {@link Listener}
     * @param listener The listener to register
     */
    public static void register(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, instance);
    }

    /**
     * Unregisters a {@link Listener}
     * @param listener The listener to unregister
     */
    public static void unregister(Listener listener)
    {
        HandlerList.unregisterAll(listener);
    }

    /**
     * Returns a set of online players from an array of Set<UUID>s
     * @param sets The sets to check for online players
     * @return The online players
     */
    @SafeVarargs
    public static Set<Player> onlinePlayerSet(Set<UUID>... sets)
    {
        Set<Player> target = new HashSet<>();

        for (Set<UUID> set : sets)
        {
            set.forEach(uuid ->
            {
                Player player = Bukkit.getPlayer(uuid);

                if (player != null)
                {
                    target.add(player);
                }
            });
        }

        return target;
    }

    /**
     * Returns a set of online players from an array of Set<UUID>s
     * @param sets The sets to check for online players
     * @return The online players
     */
    @SafeVarargs
    public static Set<PlayerProfile> onlinePlayerProfileSet(Set<UUID>... sets)
    {
        Set<PlayerProfile> target = new HashSet<>();

        for (Set<UUID> set : sets)
        {
            set.forEach(uuid ->
            {
                PlayerProfile player = PlayerManager.getProfiles().get(uuid);

                if (player != null)
                {
                    target.add(player);
                }
            });
        }

        return target;
    }

    /* Restart Util */
    static
    {
        if(R6Siege.inDebugMode()) {
            //setRestartScript();
        }
    }

    /**
     * Assigns the script for restarting the server
     * Allows the server to restart automatically once a game ends
     */
    private static void setRestartScript()
    {
        //setupConfig();

        File directory = new File(".");

        if (directory.isDirectory())
        {
            File[] files = directory.listFiles();

            if (files != null)
            {
                File scriptFile = Arrays.stream(files).filter(
                        file -> !file.isDirectory() && hasExtension(file, "bat", "sh", "bash") && file.getName().toLowerCase().contains("start")
                ).findFirst().orElse(null);

                if (scriptFile != null)
                {
                    ConsoleUtil.sendConsoleMessage("Found restart script: " + scriptFile.getName());
                    Bukkit.getServer().shutdown();
                    //SpigotConfig.restartScript = "./" + scriptFile.getName();
                    return;
                }

                ConsoleUtil.consoleError("Could not find restart script");
            }
        }
    }

    /**
     * Gets whether a file has one of the required extensions
     * @param file The file to check
     * @param extensions The extensions tested for
     * @return Whether the file has a required extension
     */
    private static boolean hasExtension(File file, String... extensions)
    {
        for (String ext : extensions)
        {
            if (getExtension(file).equals(ext.toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the extension of a file
     * @param file The file to get the extension of
     * @return The file's extension
     */
    private static String getExtension(File file)
    {
        String[] split = file.getName().split("\\.");

        if (split.length > 0)
        {
            return split[split.length - 1].toLowerCase();
        }

        return "";
    }

    static {
        try {
            Bukkit.class.getMethod("getCommandMap");
            commandMap = (CommandMap) Bukkit.getCommandAliases();
        } catch (NoSuchMethodException e) {
            // For Bukkit/Spigot servers (only Paper exposes the server's
            // command map with a getter method)
            exposeCommandMap();
        }

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            paper = true;
        } catch (ClassNotFoundException e) {
            paper = false;
        }

        try {
            Class.forName("com.destroystokyo.paper.event.server.AsyncTabCompleteEvent");
            asyncTabCompletionSupported = true;
        } catch (ClassNotFoundException e) {
            asyncTabCompletionSupported = false;
        }
    }

    private static void exposeCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            ConsoleUtil.consoleError("Could not expose the server's command map");
            e.printStackTrace();
        }
    }

    /**
     * Returns the server's command map.
     *
     * @return the server's command map
     */
    public static CommandMap getCommandMap() {
        return commandMap;
    }

    /**
     * Returns {@code true} if the plugin is running on a PaperMC server, and
     * {@code false} otherwise.
     *
     * @return if the plugin is running on a PaperMC server
     */
    @SuppressWarnings("unused")
    public static boolean isPaper() {
        return paper;
    }

    /**
     * Returns {@code true} if the plugin is running on a version of the PaperMC
     * server that supports asynchronous tab-completion, and {@code false}
     * otherwise.
     *
     * @return if the plugin is running on a version of the PaperMC server that
     *         supports asynchronous tab-completion
     */
    public static boolean isAsyncTabCompletionSupported() {
        return asyncTabCompletionSupported;
    }

}