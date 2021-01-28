package me.itzdabbzz.siege.utils;

import me.itzdabbzz.siege.R6Siege;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * The type Console util.
 */
public class ConsoleUtil {


    private static Logger logger;

    private static ConsoleCommandSender console;

    private static String msgHeader = R6Siege.getMsgHeading();

    private static final String IN_GAME_DEBUG = "[" + ChatColor.AQUA + ChatColor.BOLD.toString() + msgHeader + "-DEBUG" + ChatColor.WHITE + "] ";

    private static final String IN_GAME_ERROR = "[" + ChatColor.AQUA + ChatColor.BOLD.toString() + msgHeader + "-ERROR" + ChatColor.WHITE + "] ";

    private static final String CONSOLE_DEBUG = "[" + msgHeader + "-DEBUG] ";

    private static final String CONSOLE_ERROR = "[" + msgHeader + "-ERROR] ";

    private static final String CONSOLE_LOADING = "[" + msgHeader + "] ";

    private static final String EXCEPTION_CAUGHT_PREFIX = "[" + msgHeader + "-Exception-Caught] ";

    /**
     * The constant Debug.
     */
    public static boolean Debug = R6Siege.inDebugMode();

    static{
        logger = Bukkit.getLogger();
        console = Bukkit.getServer().getConsoleSender();
    }

    /**
     * Send in game debug.
     *
     * @param player    the player
     * @param igMessage the ig message
     */
    public static void sendInGameDebug(Player player, String igMessage) { player.sendMessage(IN_GAME_DEBUG + igMessage); }

    /**
     * Debug.
     *
     * @param cMessage the c message
     */
    public static void debug(String cMessage){ if(Debug){Bukkit.getLogger().info(CONSOLE_DEBUG + ChatColor.GOLD + cMessage);} }

    /**
     * Send loaded message.
     *
     * @param cMessage the c message
     */
    public static void sendLoadedMessage(String cMessage){ if(Debug){Bukkit.getLogger().info(CONSOLE_LOADING + ChatColor.GREEN + cMessage);} }

    /**
     * Send console message.
     *
     * @param cMessage the c message
     */
    public static void sendConsoleMessage(String cMessage){ Bukkit.getLogger().info(CONSOLE_DEBUG + ChatColor.AQUA + cMessage); }

    /**
     * Console error.
     *
     * @param cMessage the c message
     */
    public static void consoleError(String cMessage){ Bukkit.getLogger().severe(CONSOLE_DEBUG + ChatColor.RED + cMessage); }

    /**
     * Console error.
     *
     * @param cMessage the c message
     */
    public static void consoleError(Exception cMessage){ Bukkit.getLogger().severe(CONSOLE_DEBUG + ChatColor.RED + cMessage.getStackTrace()); }

    /**
     * Console debug.
     *
     * @param cMessage the c message
     */
    public static void consoleDebug(String cMessage){ Bukkit.getLogger().info(CONSOLE_DEBUG + ChatColor.WHITE + cMessage); }

    /**
     * Send exception debug.
     *
     * @param eMessage the e message
     */
    public static void sendExceptionDebug(String eMessage)
    {
        Bukkit.getLogger().severe(EXCEPTION_CAUGHT_PREFIX + ChatColor.RED + eMessage);
        writeDebugFile(eMessage, R6Siege.getInstance().getDataFolder().getName());
    }

    /**
     * Gets in game debug.
     *
     * @return the in game debug
     */
    public static String getInGameDebug() {
        return IN_GAME_DEBUG;
    }

    /**
     * Gets console debug.
     *
     * @return the console debug
     */
    public static String getConsoleDebug() {
        return CONSOLE_DEBUG;
    }

    /**
     * Gets exception caught prefix.
     *
     * @return the exception caught prefix
     */
    public static String getExceptionCaughtPrefix() {
        return EXCEPTION_CAUGHT_PREFIX;
    }

    /**
     * Write debug file.
     *
     * @param content  the content
     * @param location the location
     */
    public static void writeDebugFile(String content, String location) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        try {
            String fileMessage = content;

            fileWriter = new FileWriter(location + format.format(calendar));
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Console error.
     *
     * @param stackTrace the stack trace
     */
    public static void consoleError(StackTraceElement[] stackTrace) {
        Bukkit.getLogger().severe(CONSOLE_DEBUG + ChatColor.RED + stackTrace);
    }
}
