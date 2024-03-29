package me.itzdabbzz.siege.utils;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.config.CfgSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.List;

public class HologramsUtil {
    /**
     * Creates a {@link Hologram} for specified time using HolographicDisplays API.
     *
     * @param location {@link Location}, where the Hologram should be created
     * @param text Lines of Hologram
     * @param ticks For how long the Hologram should last
     */
    public static void create(Location location, List<String> text, int ticks){
        if (!CfgSettings.HOLOGRAMS){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PLJRApi: Tried to create a HolographicDisplays Hologram while disabled in config!");
            return;
        }
        Hologram hologram = HologramsAPI.createHologram(R6Siege.getInstance(), location);
        for (String line : text){
            hologram.appendTextLine(line);
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(R6Siege.getInstance(), hologram::delete, ticks);
    }
}
