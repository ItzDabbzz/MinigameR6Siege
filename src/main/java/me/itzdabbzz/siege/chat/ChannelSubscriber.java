package me.itzdabbzz.siege.chat;

/**
 * Interface definating the chat channel subscriber.
 *
 * @author ItzDabbzz
 *
 */
public interface ChannelSubscriber {
    /**
     * Sends message to this subscriber.
     *
     * @param message
     *            message to be send
     */
    public void sendMessage(String message);

    /**
     * Returns this subscriber's mode.
     *
     * @return subscribe mode
     */
    public SubscribeMode getMode();

    /**
     * Returns if is this subscriber online/active.
     *
     * @return whether the player is online or not
     */
    public boolean isOnline();

    /**
     * Returns name of channel subscriber.
     *
     * @return name of subscriber
     */
    public String getName();
}