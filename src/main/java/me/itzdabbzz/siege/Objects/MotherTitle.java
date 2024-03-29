package me.itzdabbzz.siege.Objects;


import me.itzdabbzz.siege.Managers.TitleManager;
import org.bukkit.entity.Player;

public class MotherTitle {
    private final String title;
    private final String subtitle;
    private final int in;
    private final int stay;
    private final int out;

    public MotherTitle(String title, String subtitle, int in, int stay, int out){
        this.title = title;
        this.subtitle = subtitle;
        this.in = in;
        this.stay = stay;
        this.out = out;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getIn() {
        return in;
    }

    public int getStay() {
        return stay;
    }

    public int getOut() {
        return out;
    }

    public void send(Player player){
        TitleManager.send(player, this);
    }

    public void broadcast(){
        TitleManager.broadcast(this);
    }
}
