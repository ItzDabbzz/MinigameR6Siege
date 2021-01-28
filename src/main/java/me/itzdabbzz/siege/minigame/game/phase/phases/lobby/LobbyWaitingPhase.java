package me.itzdabbzz.siege.minigame.game.phase.phases.lobby;

import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.utils.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The phase of a minigames where players are waiting in the lobby before the countdown
 */
public class LobbyWaitingPhase extends LobbyPhase
{
    /**
     * The minimum needed amount of players for the minigames
     */
    private final int neededPlayers;

    public LobbyWaitingPhase(Game game)
    {
        super(game);

        neededPlayers = game.getType().getMinPlayers();
    }

    @Override
    public void onUpdate()
    {
        if (game.getPlayersSize() >= neededPlayers)
        {
            game.forEachPlayer(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2F, 1F));

            endPhase();
        }
    }

    @Override
    public List<String> getSidebar(Player player)
    {
        List<String> sidebar = new ArrayList<>();

        sidebar.add(CC.green + CC.bold + "Waiting");
        writeBlank(sidebar);

        sidebar.add(CC.yellow + CC.bold + "Players");
        sidebar.add(game.getPlayersSize() + "/" + game.getType().getMaxPlayers());
        writeBlank(sidebar);

        sidebar.add(CC.blue + CC.bold + "Map");
        sidebar.add(game.getMap().getName());

        return sidebar;
    }

    @Override
    public String getActionBar(Player player)
    {
        return CC.blue + CC.bold + "Waiting for players...";
    }
}