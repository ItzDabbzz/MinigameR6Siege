package me.itzdabbzz.siege.chat;

/**
 * Chat channel subscription mode.
 *
 * @author Mato Kormuth
 *
 */
public enum SubscribeMode {
    /**
     * Player can only read (receive) messages from channel. (default)
     */
    READ,
    /**
     * Player can read (receive) and write (send) messages to channel.
     */
    READ_WRITE;
}
