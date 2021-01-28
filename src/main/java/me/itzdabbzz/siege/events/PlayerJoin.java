package me.itzdabbzz.siege.events;

import me.itzdabbzz.siege.Managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        PlayerManager.loadProfile(event.getPlayer().getUniqueId());
    }

}
