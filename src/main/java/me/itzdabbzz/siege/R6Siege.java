package me.itzdabbzz.siege;

import lombok.Getter;
import lombok.Setter;
import me.itzdabbzz.siege.Managers.ConfigManager;
import me.itzdabbzz.siege.Managers.FireworkManager;
import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.command.CommandHandler;
import me.itzdabbzz.siege.commands.chat.ChannelCommand;
import me.itzdabbzz.siege.commands.general.*;
import me.itzdabbzz.siege.commands.items.SaveItem;
import me.itzdabbzz.siege.commands.items.SaveKit;
import me.itzdabbzz.siege.commands.misc.ConfigCommand;
import me.itzdabbzz.siege.commands.misc.GenMapCommand;
import me.itzdabbzz.siege.commands.misc.PingCommand;
import me.itzdabbzz.siege.config.*;
import me.itzdabbzz.siege.config.MenuItem;
import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.minigame.game.game.GameType;
import me.itzdabbzz.siege.minigame.game.game.LoginListener;
import me.itzdabbzz.siege.minigame.game.holograms.HolographicDisplay;
import me.itzdabbzz.siege.minigame.map.MapManager;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import me.itzdabbzz.siege.utils.Lag;
import me.itzdabbzz.siege.utils.ServerUtil;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.*;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

public final class R6Siege extends JavaPlugin {
    public static R6Siege instance;
    private static BukkitAudiences bukkitAudiences;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static String msgHeading = "R6Siege";
    private static Boolean debugMode = false;
    public static String pluginFolder;
    private MapManager mapManager;
    private PlayerManager playerManager;


    /**
     * The ongoing game
     */
    @Getter
    @Setter
    private static Game game;

    private static ConfigManager configManager;
    private static ConfigManager itemManager;
    private static ConfigManager langManager;
    private static ConfigManager titleTypeManager;
    private static ConfigManager soundTypeManager;
    @Getter
    @Setter
    private static ConfigManager menuManager;

    private static HolographicDisplay holographicDisplay;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupMiniMessage();
        setupConfig();
        PlayerManager.initialize(this);
        mapManager = new MapManager(this);

        Bukkit.getPluginManager().registerEvents(FireworkManager.getInstance(), this);

        CommandHandler commandHandler = new CommandHandler();
        ServerUtil.register(new LoginListener(this));
        commandHandler.registerCommand(new PingCommand());
        commandHandler.registerCommand(new ProfileCommand());
        commandHandler.registerCommand(new FriendsCommand());
        commandHandler.registerCommand(new FoeCommand());
        commandHandler.registerCommand(new PartyCommand());
        commandHandler.registerCommand(new ChannelCommand());
        commandHandler.registerCommand(new SaveItem());
        commandHandler.registerCommand(new SaveKit());
        commandHandler.registerCommand(new GenMapCommand());
        commandHandler.registerCommand(new ConfigCommand());
        commandHandler.registerCommand(new BankCommand());
        commandHandler.registerCommand(new BackCommand());

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
    }


    /**
     * Initiates the game by getting a new instance
     */
    private void initGame()
    {
        GameType type = EnumUtils.getEnum(GameType.class, getConfigManager().getString("game"+GameType.values()[0].name()));

        if (type != null)
        {
            Game gameInst = type.newInstance(this);

            if (gameInst != null)
            {
                game = gameInst;

                ConsoleUtil.sendConsoleMessage("Loading game of type " + type.toString() + " [" + type.name() + "]");
                return;
            }

            ConsoleUtil.consoleError("Game failed to load: Error creating game instance");
            return;
        }

        ConsoleUtil.consoleError("Game failed to load: Config game setting not valid");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PlayerManager.saveProfiles();
    }

    public void setupConfig() {
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(instance, "config.yml");
        langManager = new ConfigManager(instance, "lang.yml");
        titleTypeManager = new ConfigManager(instance, "titles.yml");
        itemManager = new ConfigManager(instance, "items.yml");
        soundTypeManager = new ConfigManager(instance, "sounds.yml");
        menuManager = new ConfigManager(instance, "menus.yml");
        MenuItem.load(menuManager);
        CfgTimeMenu.load(menuManager);
        CfgBankDepositMenu.load(menuManager);
        CfgBankMainMenu.load(menuManager);
        CfgBankWithdrawMenu.load(menuManager);
        Lang.load(langManager);
        SoundType.load(soundTypeManager);
        TitleType.load(titleTypeManager);
        CfgSettings.load(configManager);
        CfgBlockedBlocks.load(configManager);
    }

    public static R6Siege getInstance() {
        return instance;
    }

    public void restart() { this.getServer().spigot().restart(); }

    public static ConfigManager getConfigManager() { return configManager; }

    public static ConfigManager getItemManager() { return itemManager; }

    public static Boolean inDebugMode() { return debugMode; }

    public static String getMsgHeading() { return msgHeading; }

    public static HolographicDisplay holographicDisplay() { return holographicDisplay;}
    /**
     * Gets the current ongoing game
     * @see #game
     * @return The current game
     */
    public static Game getGame() { return game; }
    /**
     * Gets the MapManager instance
     * @return The map manager
     */
    public MapManager getMapManager() { return mapManager; }

    public @NonNull
    static BukkitAudiences getBukkitAudiences()
    {
        if(bukkitAudiences == null)  {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return bukkitAudiences;
    }


    private void setupMiniMessage(){ bukkitAudiences = BukkitAudiences.create(instance); }

    /**
     * Returns the given method parameter's type if it is a regular parameter,
     * or its component type if the parameter represents variable arguments
     * (varargs).
     *
     * @param param the parameter to get the type of
     * @return the parameter's type, or its component type if the parameter
     *         represents variable arguments
     */
    public static Class<?> getParameterType(Parameter param) {
        if (param.isVarArgs()) {
            return param.getType().getComponentType();
        } else {
            return param.getType();
        }
    }
}
