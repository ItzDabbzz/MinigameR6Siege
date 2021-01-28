package me.itzdabbzz.siege.chat;

import org.bukkit.entity.Player;

public class PlayerChannelSubscriber implements ChannelSubscriber {
    private final SubscribeMode mode;
    private final Player player;

    /**
     * Creates new instance of player channel subscriber.
     *
     * @param player
     *            player
     * @param mode
     *            subscribe mode
     */
    public PlayerChannelSubscriber(final Player player, final SubscribeMode mode) {
        this.mode = mode;
        this.player = player;
    }

    /**
     * Returns subscription mode.
     *
     * @return subscription mode
     */
    @Override
    public SubscribeMode getMode() {
        return this.mode;
    }

    /**
     * Sends message to this subscriber.
     *
     * @param message
     *            message to send
     */
    @Override
    public void sendMessage(final String message) {
        this.player.sendMessage(message);
    }

    @Override
    public boolean isOnline() {
        return this.player.isOnline();
    }

    /**
     * Returns the player to which is this subscriber associated.
     *
     * @return player object
     */
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String getName() {
        return this.player.getName();
    }
}