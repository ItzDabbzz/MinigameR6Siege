package me.itzdabbzz.siege.utils;

import com.google.common.base.Strings;
import me.itzdabbzz.siege.R6Siege;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatUtil {

    public static final String DARK_STAR = "★";
    public static final String WHITE_STAR = "☆";
    public static final String CIRCLE_BLANK_STAR = "✪";
    public static final String BIG_BLOCK = "█";
    public static final String SMALL_BLOCK = "▌";
    public static final String SMALL_DOT = "•";
    public static final String LARGE_DOT = "●";
    public static final String HEART = "♥";
    public static final String SMALL_ARROWS_RIGHT = "»";
    public static final String SMALL_ARROWS_LEFT = "«";
    public static final String ALERT = "⚠";
    public static final String RADIOACTIVE = "☢";
    public static final String BIOHAZARD = "☣";
    public static final String PLUS = "✚";
    public static final String BIG_HORIZONTAL_LINE = "▍";
    public static final String SMALL_HORIZONTAL_LINE = "▏";
    public static final String PLAY = "▶";
    public static final String GOLD_ICON = "❂";
    public static final String CROSS = "✖";
    public static final String SLIM_CROSS = "✘";
    public static final String BOXED_CROSS = "☒";
    public static final String CHECKMARK = "✔";
    public static final String BOXED_CHECKMARK = "☑";
    public static final String LETTER = "✉";
    public static final String BLACK_CHESS_KING = "♚";
    public static final String BLACK_CHESS_QUEEN = "♛";
    public static final String SKULL_AND_CROSSBONES = "☠";
    public static final String WHITE_FROWNING_FACE = "☹";
    public static final String WHITE_SMILING_FACE = "☺";
    public static final String BLACK_SMILING_FACE = "☻";
    public static final String PICK = "⛏";
    public static final String SEPARATOR = "▬";
    public static final String AEROPLANE = "✈";
    public static final String TIMER = "⌛";
    public static final String LIGHTNING_BOLT = "⚡";
    public static final String SNOWFLAKE_1 = "❆";
    public static final String SNOWFLAKE_2 = "❅";
    public static final String NUKE = "☢";

    /**
     * Returns a string of hearts compared to the {@param paramHealth}
     *
     * @param paramHealth
     * @return
     */
    public static String getHealthString(double paramHealth) {
        int health = (int) Math.round(paramHealth);
        health /= 2;
        int healthAway = 10 - health;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < health; i++) {
            builder.append(ChatColor.RED).append("♥");
        }
        for (int i = 0; i < healthAway; i++) {
            builder.append(ChatColor.GRAY).append("♥");
        }
        return builder.toString();
    }

    /**
     * Translates the '&' character into its respective colour code.
     *
     * @param message - The original string to be converted.
     * @return string - Translated with Colour Codes
     */
    public static String formatMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> formatMessages(List<String> messages) {
        List<String> buffered = new ArrayList<>();
        for (String message : messages){
            buffered.add(formatMessage("&r" + message));
        }
        return buffered;
    }

    /**
     * Capitalizes the First Letter of any String.
     *
     * @param original - This is the original non-capitalised string.
     * @return result - Will return the String instead with the first letter capitalised.
     */
    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    /**
     * Sends a message with parsed {@link MiniMessage} tags and PAPI Placeholders to {@link OfflinePlayer}, if online.
     *
     * @param player {@link OfflinePlayer} that we will try to send the message to
     * @param message String that will be send to player, if online
     *
     * @see #sendMessage(Player, String)
     */
    public static void sendMessage(OfflinePlayer player, String message){
        if (!player.isOnline()) return;
        sendMessage(player.getPlayer(), message);
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link OfflinePlayer}, if online.
     *
     * @param player {@link OfflinePlayer} that we will try to send the message to
     * @param message String that will be send to player, if online
     *
     * @see #sendMessageClean(Player, String)
     */
    public static void sendMessageClean(OfflinePlayer player, String message){
        if (!player.isOnline()) return;
        sendMessageClean(player.getPlayer(), message);
    }

    /**
     * Sends a message with parsed {@link MiniMessage} tags and PAPI Placeholders to {@link Player}.
     *
     * @param player {@link Player} that will receive the message
     * @param message String that will be send to the player
     */
    public static void sendMessage(Player player, String message){
        R6Siege.getBukkitAudiences().player(player).sendMessage(MiniMessage.get().parse(PapiUtil.setPlaceholders(player, message)));
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link Player}.
     *
     * @param player {@link Player} that will receive the message
     * @param message String that will be send to the player
     */
    public static void sendMessageClean(Player player, String message){
        player.sendMessage(PapiUtil.setPlaceholders(player, message));
    }

    /**
     * Sends a message with parsed {@link MiniMessage} tags and PAPI Placeholders to {@link CommandSender}.
     *
     * @param target {@link CommandSender} that will receive the message
     * @param message String that will be send to the target
     *
     * @see #sendMessage(Player, String)
     */
    public static void sendMessage(CommandSender target, String message){
        if (target instanceof Player){
            sendMessage((Player) target, message);
            return;
        }
        R6Siege.getBukkitAudiences().sender(target).sendMessage(MiniMessage.get().parse(message));
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link CommandSender}.
     *
     * @param target {@link CommandSender} that will receive the message
     * @param message String that will be send to the target
     *
     * @see #sendMessageClean(Player, String)
     */
    public static void sendMessageClean(CommandSender target, String message){
        if (target instanceof Player){
            sendMessageClean((Player) target, message);
            return;
        }
        target.sendMessage(message);
    }

    /**
     * Broadcasts a message with parsed {@link MiniMessage} tags and PAPI Placeholders to either Bukkit or Bungee.
     *
     * @param message String that will be send to all Players and ConsoleSender
     * @param perm Permission that is required to receive the message
     * @param bungee Determines if message should be send to Bungee or not
     *
     * @see #sendMessage(Player, String)
     * @see #sendMessage(CommandSender, String)
     */
    public static void broadcast(String message, String perm, boolean bungee){
        if (bungee){
            BungeeUtil.broadcastMessage(message, perm);
        }else{
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.hasPermission(perm) || perm.equals("")){
                    sendMessage(player, message);
                }
            }
        }
        sendMessage(Bukkit.getConsoleSender(), message);
    }

    /**
     * Broadcasts a list of messages with parsed {@link MiniMessage} tags and PAPI Placeholders to either Bukkit or Bungee.
     *
     * @param messages ArrayList that will be send to all Players and ConsoleSender
     * @param perm Permission that is required to receive the messages
     * @param bungee Determines if messages should be send to Bungee or not
     *
     * @see #broadcast(String, String, boolean)
     */
    public static void broadcast(List<String> messages, String perm, boolean bungee){
        for (String line : messages){
            broadcast(line, perm, bungee);
        }
    }

    /**
     * Broadcasts a message with parsed PAPI Placeholders to either Bukkit or Bungee.
     *
     * @param message String that will be send to all Players and ConsoleSender
     * @param perm Permission that is required to receive the message
     * @param bungee Determines if message should be send to Bungee or not
     *
     * @see #sendMessage(Player, String)
     * @see #sendMessageClean(CommandSender, String)
     */
    public static void broadcastClean(String message, String perm, boolean bungee){
        if (bungee){
            BungeeUtil.broadcastMessage(message, perm);
        }else{
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.hasPermission(perm) || perm.equals("")){
                    sendMessageClean(player, message);
                }
            }
        }
        sendMessageClean(Bukkit.getConsoleSender(), message);
    }

    /**
     * Broadcasts a list of messages with parsed PAPI Placeholders to either Bukkit or Bungee.
     *
     * @param messages ArrayList that will be send to all Players and ConsoleSender
     * @param perm Permission that is required to receive the messages
     * @param bungee Determines if messages should be send to Bungee or not
     *
     * @see #broadcastClean(List, String, boolean)
     */
    public static void broadcastClean(List<String> messages, String perm, boolean bungee){
        for (String line : messages){
            broadcastClean(line, perm, bungee);
        }
    }
    
	/**
	 * To send a progress bar do something like so
	 *  player.sendMessage("�e�lYOUR PROGRESS: �8[�r" + ProgressBar.getProgressBar(20, 100, 40, "|", "�e", "�7") + "�8]");
	 * 
	 * @param current
	 * @param max
	 * @param totalBars
	 * @param symbol
	 * @param completedColor
	 * @param notCompletedColor
	 * @return 
	 */
    public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor) 
    {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + completedColor + symbol, progressBars)
                + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
    }
}
