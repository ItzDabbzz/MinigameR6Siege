package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.game.phase.phases.game.ActionPhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.game.DronePhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.game.PregamePhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyCountdownPhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyPhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyWaitingPhase;
import me.itzdabbzz.siege.minigame.game.team.Team;
import me.itzdabbzz.siege.minigame.game.team.TeamGame;
import me.itzdabbzz.siege.minigame.game.team.TeamSide;
import org.bukkit.ChatColor;
import org.bukkit.Color;

public class TestGame extends TeamGame
{

    private static Team teamOrange = new Team("Orange", Color.ORANGE, "Attackers", TeamSide.ATTACK);
    private static Team teamBlue = new Team("Blue", Color.BLUE, "Defenders", TeamSide.DEFENSE);

    public TestGame(R6Siege manager)
    {
        super(manager, GameType.TEST_GAME, 5, teamBlue, teamOrange);

        itemDrop = false;
        deathDropItems = false;

        damage = true;
        pvp = true;

       // addPhase(new TestFightPhase(this));
        addPhase(new LobbyWaitingPhase(this));
        addPhase(new LobbyPhase(this));
        addPhase(new LobbyCountdownPhase(this));
        addPhase(new PregamePhase(this));
        addPhase(new DronePhase(this));
        addPhase(new ActionPhase(this));
    }
}