package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.Objects.MotherActionBar;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.map.GameMap;
import me.itzdabbzz.siege.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DefuseTimer extends BukkitRunnable {

    R6Siege plugin;
    int duration;
    GameMap map;
    InventoryOpenEvent event;
    Player player;
    Location BOMB_LOCATION;


    @Override
    public void run() {
        duration = duration - 1;
        R6Siege.getGame().playPlantDefuseNoise(BOMB_LOCATION);

        if(duration <= 0) {
            R6Siege.getGame().getPlayers(false).forEach(player1 -> {
                MotherActionBar bar = new MotherActionBar("Bomb being defused... " + duration, duration);
                bar.send(player1);
            });
            run();
        }
    }
}
