package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.chat.ChatManager;
import me.itzdabbzz.siege.chat.SubscribeMode;
import me.itzdabbzz.siege.utils.CC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handles login attempts to the server
 */
public class LoginListener implements Listener
{
    /**
     * The plugin instance
     */
    private final R6Siege manager;

    /**
     * LoginListener constructor
     * @param manager The plugin instance
     */
    public LoginListener(R6Siege manager)
    {
        this.manager = manager;
    }

    @EventHandler
    private void onChat(final AsyncPlayerChatEvent event) {
        ChatManager.__processChatEvent(event);
        /*
         * if (event.getPlayer().isOp()) event.setFormat(ChatManager.chatPlayerOp(event.getMessage(),
         * event.getPlayer())); else event.setFormat(ChatManager.chatPlayer(event.getMessage(), event.getPlayer()));
         */
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent event)
    {
        /*Game game = null;//manager.getGame();

        if (game != null)
        {
            if (!game.isLobby())
            {
                event.disallow(Result.KICK_OTHER, CC.red + "This game is already in progress");
            }
            else if (game.isFull())
            {
                event.disallow(Result.KICK_OTHER, CC.red + "This game is full");
            }
        }
        else
        {
            event.disallow(Result.KICK_OTHER, CC.red + "An error occured creating the game instance");
        }*/
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        try {
            PlayerManager.loadProfile(event.getPlayer().getUniqueId(), event.getPlayer().getAddress().getHostString());
            ChatManager.CHANNEL_GLOBAL.subscribe(event.getPlayer(), SubscribeMode.READ_WRITE);
            //ChatManager.CHANNEL_LOBBY.subscribe(event.getPlayer(), SubscribeMode.READ_WRITE);
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        //event.setJoinMessage(null);

        manager.getGame().handleJoin(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent event)
    {
        PlayerProfile player = PlayerManager.getProfiles().get(event.getPlayer().getUniqueId());

        if(player.getParty() != null) {
            player.getParty().removePlayer(event.getPlayer());
            player.setParty(null);
        }

        ChatManager.CHANNEL_GLOBAL.unsubscribe(event.getPlayer());
        ChatManager.CHANNEL_LOBBY.unsubscribe(event.getPlayer());

        player.setLastKnownName(event.getPlayer().getDisplayName());
        player.setLastLoc(event.getPlayer().getLocation());
        player.setLastIP(event.getPlayer().getAddress().toString());
        player.setLastLongin(System.currentTimeMillis());
        player.save(PlayerManager.playerProfile(event.getPlayer().getUniqueId()));
        //event.setQuitMessage(null);

        manager.getGame().handleLeave(event.getPlayer());
    }
}