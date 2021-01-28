package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.Objects.MotherActionBar;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.map.GameMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlantTimer extends BukkitRunnable {

    R6Siege plugin;
    int duration;
    GameMap map;
    Location BOMB_LOCATION;


    public PlantTimer(R6Siege r6Siege, int duration, Location BOMB_LOCATION) {
        this.plugin = r6Siege;
        this.duration = duration;
        this.BOMB_LOCATION = BOMB_LOCATION;
    }

    @Override
    public void run() {
        duration = duration - 1;
        R6Siege.getGame().playPlantDefuseNoise(BOMB_LOCATION);

        if(duration <= 0) {
            R6Siege.getGame().getPlayers(true).forEach(player1 -> {
                MotherActionBar bar = new MotherActionBar("Bomb being planted... " + duration, duration);
                bar.send(player1);
            });
            run();
        }
    }
}
