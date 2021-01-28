package me.itzdabbzz.siege.Managers;



import me.itzdabbzz.siege.Objects.MotherScoreboard;
import me.itzdabbzz.siege.builders.ScoreboardBuilder;
import me.itzdabbzz.siege.utils.PapiUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardManager {
    /**
     * Sends {@link MotherScoreboard} to {@link Player}
     *
     * @param player {@link Player} that should receive the scoreboard
     * @param scoreboard {@link MotherScoreboard} that should be sent to player
     */
    public static void send(Player player, MotherScoreboard scoreboard){
        ScoreboardBuilder builder = new ScoreboardBuilder(scoreboard);
        List<String> linesWithPAPI = new ArrayList<>();
        for (String line : builder.getLines()){
            linesWithPAPI.add(PapiUtil.setPlaceholders(player, line));
        }
        builder.withLines(linesWithPAPI);
        player.setScoreboard(builder.create().parseScoreboard());
    }

    /**
     * Broadcasts {@link MotherScoreboard} to all players.
     *
     * @param scoreboard {@link MotherScoreboard} that should be broadcasted
     *
     * @see #send(Player, MotherScoreboard)
     */
    public static void broadcast(MotherScoreboard scoreboard){
        for (Player player : Bukkit.getOnlinePlayers()){
            send(player, scoreboard);
        }
    }
}
