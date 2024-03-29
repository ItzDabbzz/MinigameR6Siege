package me.itzdabbzz.siege.minigame.game.phase.phases.lobby;

import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.minigame.game.phase.GamePhase;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * The representation of a {@link GamePhase} where players are in the lobby
 */
public class LobbyPhase extends GamePhase
{
    /**
     * LobbyPhase constructor
     * @param game The game which is starting
     */
    public LobbyPhase(Game game)
    {
        super(game, false);
    }

    /**
     * Adds a player to the game
     * @param player The player to add
     */
    public void addPlayer(Player player)
    {
        player.teleport(game.getMap().getLobbyLocation());

        player.setDisplayName(player.getName());

        player.getInventory().clear();

        player.setGameMode(GameMode.ADVENTURE);

        player.setGlowing(false);

        player.setHealth(20);

        player.setFoodLevel(20);
        player.setSaturation(20);

        player.setAllowFlight(false);

        // Fix potion effects and visibility
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));

        Bukkit.getOnlinePlayers().stream().filter(pl -> !pl.equals(player)).forEach(pl -> pl.showPlayer(player));
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        event.setBuild(false);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBurn(BlockBurnEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockDamage(BlockDamageEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntitySpawn(CreatureSpawnEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        event.setCancelled(true);
    }
}