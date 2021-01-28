package me.itzdabbzz.siege.Managers;

import com.cryptomorin.xseries.messages.Titles;
import me.itzdabbzz.siege.Objects.MotherTitle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleManager {
    /**
     * Will send a {@link MotherTitle} to {@link Player} using {@link Titles}.
     *
     * @param player {@link Player} that should receive the title
     * @param title {@link MotherTitle} that should be send to player
     */
    public static void send(Player player, MotherTitle title){
        Titles.sendTitle(player, title.getIn(), title.getStay(), title.getOut(), title.getTitle(), title.getSubtitle());
    }

    /**
     * Will broadcast a {@link MotherTitle} to all online players.
     *
     * @param title {@link MotherTitle} that should be broadcasts
     *
     * @see #send(Player, MotherTitle)
     */
    public static void broadcast(MotherTitle title){
        for (Player player : Bukkit.getOnlinePlayers()){
            send(player, title);
        }
    }

    /**
     * Clears a Title of {@link Player} using {@link Titles}.
     *
     * @param player {@link Player} that should get the Title cleared
     */
    public static void clear(Player player){
        Titles.clearTitle(player);
    }
}
