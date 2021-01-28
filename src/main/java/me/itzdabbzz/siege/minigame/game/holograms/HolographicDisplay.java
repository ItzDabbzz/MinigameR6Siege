package me.itzdabbzz.siege.minigame.game.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HolographicDisplay {

    Plugin plugin;
    private final AtomicInteger ID = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, Hologram> hmap = new ConcurrentHashMap<Integer, Hologram>();

    public HolographicDisplay(Plugin plugin) {
        this.plugin = plugin;
    }

    public int createBaseHologram(Location loc) {
        Location center = loc.clone().add(0.5, 1.2, 0.5);
        Hologram hologram = HologramsAPI.createHologram(plugin, center);
        hologram.appendTextLine("Target");
        hologram.setAllowPlaceholders(true);
        int hologramID = addHologram(hologram);
        return hologramID;
    }

    public int createBombHologram(Location loc) {
        Location center = loc.clone().add(0.0, 0.4, 0.0);
        Hologram hologram = HologramsAPI.createHologram(plugin, center);
        hologram.appendTextLine("Bomb");
        hologram.setAllowPlaceholders(true);
        int hologramID = addHologram(hologram);
        return hologramID;
    }

    public void teleport(int id, Location loc) {
        Hologram temp = getHologram(id);
        if (temp != null) {
            temp.teleport(loc.clone().add(0.0, 0.4, 0.0));
        }
    }

    public void removeHologram(int id) {
        deleteHologram(id);
    }

    protected int addHologram(Hologram hologram) {
        int id = ID.incrementAndGet();
        hmap.put(id, hologram);
        return id;
    }

    protected Hologram getHologram(int id) {
        return hmap.get(id);
    }

    protected void deleteHologram(int id) {
        if (hmap.containsKey(id)) {
            Hologram hologram = hmap.get(id);
            hologram.delete();
            hmap.remove(id);
        }
    }

}