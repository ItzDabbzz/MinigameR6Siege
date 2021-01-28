package me.itzdabbzz.siege.minigame.game.phase.phases.game;

import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.minigame.game.phase.GamePhase;
import me.itzdabbzz.siege.minigame.game.team.Team;
import me.itzdabbzz.siege.minigame.game.team.TeamGame;
import me.itzdabbzz.siege.utils.CC;
import me.itzdabbzz.siege.utils.ServerUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The phase in a game before the game starts
 */
public class DronePhase extends GamePhase
{
    public DronePhase(Game game)
    {
        super(game, true);
    }

    @Override
    public void onStart()
    {
        game.broadcast(CC.blue + CC.bold + "The game will begin in 45 seconds");

        /*if (game instanceof TeamGame)
        {
            ((TeamGame) game).initTeams();
        }

        spawnPlayers();*/
        runCountdown();
    }

    /**
     * Runs the pre-game countdown
     */
    private void runCountdown()
    {
        AtomicInteger i = new AtomicInteger(45);

        runRepeatingTask(() ->
        {
            if (i.decrementAndGet() == 0)
            {
                game.forEachPlayer(player -> player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 2F, 2F));

                game.broadcast(CC.gold + "The game has begun!");

                endPhase();
            }
            else
            {
                game.forEachPlayer(player ->
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 2F)
                );
            }
        }, 45, 0L, 20L);
    }

    /**
     * Spawns the players on the map
     */
    private void spawnPlayers()
    {
        if (game instanceof TeamGame)
        {
            TeamGame tGame = (TeamGame) game;

            for (Team team : tGame.getTeams())
            {
                List<Location> spawns = game.getMap().getSpawns(team.getName());
                Set<Integer> used = new HashSet<>();

                team.getPlayers().forEach(player -> spawnPlayer(player, spawns, used));
            }
        }
        else
        {
            List<Location> spawns = game.getMap().getSpawns(Game.SOLO_TEAM);
            Set<Integer> used = new HashSet<>();

            game.forEachPlayer(player -> spawnPlayer(player, spawns, used), false);
        }
    }

    /**
     * Spawns a player on the map
     * @param player The player
     * @param spawns The possible spawns
     * @param used The indexes of used spawns
     */
    private void spawnPlayer(Player player, List<Location> spawns, Set<Integer> used)
    {
        player.setGameMode(game.gameMode);

        int i;

        do
        {
            i = ServerUtil.nextInt(spawns.size());
        } while (used.contains(i));

        player.teleport(spawns.get(i));
        used.add(i);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if (game.preGameFreeze && game.isPlaying(event.getPlayer()))
        {
            TeamGame tGame = (TeamGame) game;

            for (Team team : tGame.getTeams())
            {
                List<Location> spawns = game.getMap().getSpawns(team.getName());
                Set<Integer> used = new HashSet<>();

                team.getPlayers().forEach(player -> spawnPlayer(player, spawns, used));
            }
            event.setTo(event.getFrom());
        }
    }
}