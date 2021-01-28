package me.itzdabbzz.siege.Objects;

import me.itzdabbzz.siege.Managers.ActionBarManager;
import org.bukkit.entity.Player;

public class MotherActionBar {
    private final String message;
    private final long duration;

    public MotherActionBar(String message, long duration){
        this.message = message;
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }
    public long getDuration() {
        return duration;
    }

    public void send(Player player){
        ActionBarManager.send(player, this);
    }

    public void broadcast(){
        ActionBarManager.broadcast(this);
    }
}
