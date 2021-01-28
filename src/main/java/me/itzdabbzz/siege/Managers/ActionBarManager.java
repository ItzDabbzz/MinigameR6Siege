package me.itzdabbzz.siege.Managers;

import com.cryptomorin.xseries.messages.ActionBar;
import me.itzdabbzz.siege.Objects.MotherActionBar;
import me.itzdabbzz.siege.R6Siege;
import org.bukkit.entity.Player;

public class ActionBarManager {
    private static final R6Siege r6Siege = R6Siege.getInstance();

    /**
     * Will send a {@link MotherActionBar} to {@link Player} using {@link ActionBar}.
     *
     * @param player {@link Player} that should receive the actionBar
     * @param actionBar {@link  MotherActionBar} that should be send to player
     */
    public static void send(Player player, MotherActionBar actionBar){
        ActionBar.sendActionBar(r6Siege, player, actionBar.getMessage(), actionBar.getDuration());
    }

    /**
     * Will broadcast a {@link MotherActionBar} to all online players using {@link ActionBar}.
     *
     * @param actionBar {@link MotherActionBar} that should be broadcasts
     */
    public static void broadcast(MotherActionBar actionBar){
        ActionBar.sendPlayersActionBar(actionBar.getMessage());
    }

    /**
     * Clears an ActionBar of {@link Player} using {@link ActionBar}.
     *
     * @param player {@link Player} that should have the ActionBar cleared
     */
    public static void clear(Player player){
        ActionBar.clearActionBar(player);
    }

    /**
     * Clears an ActionBar of all online player using {@link ActionBar}
     */
    public static void clearAll(){
        ActionBar.clearPlayersActionBar();
    }
}
