package me.itzdabbzz.siege.Objects;

import me.itzdabbzz.siege.Managers.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class MotherScoreboard {
    private final String title;
    private final List<String> lines;

    public MotherScoreboard(MotherScoreboard scoreboard){
        this.title = scoreboard.getTitle();
        this.lines = scoreboard.getLines();
    }

    public MotherScoreboard(String title, List<String> lines){
        this.title = title;
        this.lines = lines;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLines() {
        return lines;
    }

    public Scoreboard parseScoreboard(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(title, "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);

        int slot = 16;
        for (String line : lines){
            Score score = objective.getScore(line);
            score.setScore(slot);
            slot--;
        }

        return scoreboard;
    }

    public void send(Player player){
        ScoreboardManager.send(player, this);
    }

    public void broadcast(){
        ScoreboardManager.broadcast(this);
    }
}
