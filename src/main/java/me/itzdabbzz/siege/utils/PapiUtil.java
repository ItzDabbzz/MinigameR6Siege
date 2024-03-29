package me.itzdabbzz.siege.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.itzdabbzz.siege.config.CfgSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PapiUtil {
    /**
     * Parses {@link PlaceholderAPI} placeholders in String.
     *
     * @param player {@link Player} by whom the placeholders should be parsed
     * @param input String that should be parsed
     * @return Input with parsed strings (If enabled in configuration, unparsed input otherwise)
     */
    public static String setPlaceholders(Player player, String input){
        if (CfgSettings.PLACEHOLDERS){
            return PlaceholderAPI.setPlaceholders(player, input);
        }
        if (CfgSettings.DISABLED_MESSAGES){
            ConsoleUtil.sendConsoleMessage(ChatColor.RED + "Tried to parse PAPI Placeholders, while disabled in config!");
        }
        return input;
    }
}
