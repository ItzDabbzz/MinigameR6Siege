package me.itzdabbzz.siege.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import me.itzdabbzz.siege.Managers.TitleManager;
import me.itzdabbzz.siege.Objects.Settings;
import me.itzdabbzz.siege.builders.TitleBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Class specifing chat channel.
 *
 * @author Mato Kormuth
 *
 */
public class ChatChannel {
    public static final String UNSUBCRIBE_MSG = ChatColor.LIGHT_PURPLE
            + "You have left '%name%' chat channel!";
    public static final String SUBCRIBE_MSG = ChatColor.LIGHT_PURPLE
            + "You have joined '%name%' chat channel with mode %mode% !";

    //Last "random" channel ID.
    private static AtomicLong randomId = new AtomicLong(0L);

    /**
     * Name of channel.
     */
    private final String name;
    /**
     * List of subscribers.
     */
    private final List<ChannelSubscriber> subscribers = new ArrayList<ChannelSubscriber>();
    /**
     * Prefix of this channel.
     */
    private String prefix = "";
    /**
     * Date of last activity in this channel.
     */
    private long lastActivity = Long.MAX_VALUE;
    /**
     * Specifies if channel is visible to everyone.
     */
    private boolean isPublic = true;
    /**
     * Specifies if the channel is temporary
     */
    private boolean isTemp = false;
    /**
     * Specifies the permission for the channel if wanted
     */
    private String permission;

    /**
     * Creates new chat channel with specified name.
     *
     * @param name
     *            name of channel
     */
    public ChatChannel(final String name) {
        this.name = name;
        ChatManager.registerChannel(this);
    }

    public ChatChannel(final String name, final String prefix) {
        this.name = name;
        this.prefix = prefix;
        ChatManager.registerChannel(this);
    }

    public ChatChannel(final String name, final String prefix, boolean isTemp) {
        this.name = name;
        this.prefix = prefix;
        this.isTemp = isTemp;
        ChatManager.registerChannel(this);
    }

    public ChatChannel(final String name, final String prefix, boolean isTemp, String customPerm) {
        this.name = name;
        this.prefix = prefix;
        this.isTemp = isTemp;
        this.permission = customPerm;
        ChatManager.registerChannel(this);
    }

    public ChatChannel(final String name, boolean cPerm, String customPerm, boolean isTemp) {
        this.name = name;
        if(cPerm) {
            this.permission = customPerm;
        }else {
            this.permission = "r6siege.channel."+name;
        }
        this.isTemp = isTemp;
        ChatManager.registerChannel(this);
    }

    /**
     * Subscribes player to this chat channel.
     *
     * @param player
     *            player
     */
    public void subscribe(final Player player, final SubscribeMode mode) {
        this.subscribers.add(new PlayerChannelSubscriber(player, mode));

        player.sendMessage(ChatChannel.SUBCRIBE_MSG.replace("%name%", this.getName()).replace(
                "%mode%", mode.toString()));
    }

    /**
     * Subscribes subscriber to this chat channel.
     *
     * @param subscriber
     *            subscriber to be added to this channel
     */
    public void subscribe(final ChannelSubscriber subscriber) {
        this.subscribers.add(subscriber);

        subscriber.sendMessage(ChatChannel.SUBCRIBE_MSG.replace("%name%", this.getName()).replace(
                "%mode%", subscriber.getMode().toString()));
    }

    /**
     * Unsubscribes player from this chat channel.
     *
     * @param player
     *            specified player
     */
    public void unsubscribe(final Player player) {
        for (Iterator<ChannelSubscriber> iterator = this.subscribers.iterator(); iterator.hasNext();) {
            ChannelSubscriber subscriber = iterator.next();
            if (subscriber instanceof PlayerChannelSubscriber)
                if (((PlayerChannelSubscriber) subscriber).getPlayer() == player)
                    iterator.remove();
        }

        player.sendMessage(ChatChannel.UNSUBCRIBE_MSG.replace("%name%", this.getName()));
    }

    /**
     * Unsubscribes specified subscriber from this channel. If the subscriber did not subscribed to this channel,
     * nothing happens.
     *
     * @param subscriber
     *            subscrber to be unregistered
     */
    public void unsubscribe(final ChannelSubscriber subscriber) {
        this.subscribers.remove(subscriber);

        subscriber.sendMessage(ChatChannel.UNSUBCRIBE_MSG.replace("%name%",
                this.getName()));
    }

    /**
     * Retruns true, if specified subscriber is subscribed.
     *
     * @param subscriber
     *            subscriber to check
     */
    public boolean isSubscribed(final ChannelSubscriber subscriber) {
        return this.subscribers.contains(subscriber);
    }

    /**
     * Returns whether player can read messages from this channel.
     *
     * @param player
     *            player to check
     * @return true if player can read
     */
    public boolean canRead(final Player player) {
        for (ChannelSubscriber subscriber : this.subscribers)
            if (subscriber instanceof PlayerChannelSubscriber)
                if (((PlayerChannelSubscriber) subscriber).getPlayer() == player)
                    return this.canRead(subscriber);
        return false;
    }

    /**
     * Returns whether subscriber can read messages from this channel.
     *
     * @param subscriber
     *            player to check
     * @return true if player can read
     */
    public boolean canRead(final ChannelSubscriber subscriber) {
        return this.subscribers.contains(subscriber);
    }

    /**
     * Sends message to all subscribers.
     *
     * @param message
     *            message to be send
     */
    public void broadcastMessage(String message) {
        this.lastActivity = System.currentTimeMillis();
        // Support for colored messages
        message = ChatColor.translateAlternateColorCodes("&".toCharArray()[0], message);
        for (Iterator<ChannelSubscriber> iterator = this.subscribers.iterator(); iterator.hasNext();) {
            ChannelSubscriber p = iterator.next();
            if (p.isOnline())
                if (message.toLowerCase().contains(p.getName().toLowerCase()) && !message.startsWith(p.getName().toLowerCase()))
                {
                    p.sendMessage(this.prefix + ChatColor.BLUE + message);
                    if (p instanceof PlayerChannelSubscriber)
                        if (Settings.CHAT_SOUNDS.hasEnabled(((PlayerChannelSubscriber) p).getPlayer()))
                            ((PlayerChannelSubscriber) p).getPlayer().playSound(((PlayerChannelSubscriber) p).getPlayer().getLocation(), Sound.valueOf("BLOCK_NOTE_PLING"), 0.5F, 1);
                }
                else {
                    p.sendMessage(this.prefix + message);
                }
            else
                iterator.remove();
        }
    }

    /**
     * Sends out a title to all subscribers of a channel
     *
     * @param topMessage Message on top of title
     * @param bottomMessage Message on bottom of title
     * @param in time to take for title to come in
     * @param stay time for title to stay
     * @param out time for title to go away
     */
    public void broadcastTitle(String topMessage, String bottomMessage, int in, int stay, int out) {
        topMessage = ChatColor.translateAlternateColorCodes("&".toCharArray()[0], topMessage);
        bottomMessage = ChatColor.translateAlternateColorCodes("&".toCharArray()[0], bottomMessage);
        TitleBuilder titleBuilder = new TitleBuilder(topMessage, bottomMessage, in, stay, out);
        this.lastActivity = System.currentTimeMillis();

        for (Iterator<ChannelSubscriber> iterator = this.subscribers.iterator(); iterator.hasNext();) {
            ChannelSubscriber p = iterator.next();
            if (p.isOnline()) {
                if (p instanceof PlayerChannelSubscriber) {
                    TitleManager.send(((PlayerChannelSubscriber) p).getPlayer(), titleBuilder.create());
                    if (Settings.CHAT_SOUNDS.hasEnabled(((PlayerChannelSubscriber) p).getPlayer())) {
                        ((PlayerChannelSubscriber) p).getPlayer().playSound(((PlayerChannelSubscriber) p).getPlayer().getLocation(), Sound.valueOf("BLOCK_NOTE_PLING"), 0.5F, 1);
                    }
                }
            }
            else
                iterator.remove();
        }
    }

    /**
     * Returns name of channel.
     *
     * @return the name of channel
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns if player can write to this channel.
     *
     * @param player
     * @return true if player can write
     */
    public boolean canWrite(final Player player) {
        for (ChannelSubscriber subscriber : this.subscribers)
            if (subscriber instanceof PlayerChannelSubscriber)
                if (((PlayerChannelSubscriber) subscriber).getPlayer() == player)
                    return this.canWrite(subscriber);
        return false;
    }

    /**
     * Returns if player can write to this channel.
     *
     * @param subscriber
     *            specified subscriber
     * @return true if player can write
     */
    public boolean canWrite(final ChannelSubscriber subscriber) {
        if (!this.subscribers.contains(subscriber))
            return false;
        else {
            return subscriber.getMode() == SubscribeMode.READ_WRITE;
        }
    }

    /**
     * Returns random new channel, without specified name and stuff.
     *
     * @return random chat channel
     */
    public static ChatChannel createRandom() {
        return new ChatChannel("r" + ChatChannel.randomId.getAndIncrement());
    }

    /**
     * Set's channels prefix.
     */
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return the lastActivity
     */
    public long getLastActivity() {
        return this.lastActivity;
    }

    /**
     * @param player
     */
    public boolean isSubscribed(final Player player) {
        for (ChannelSubscriber subscriber : this.subscribers)
            if (subscriber instanceof PlayerChannelSubscriber)
                if (((PlayerChannelSubscriber) subscriber).getPlayer() == player)
                    return true;
        return false;
    }

    /**
     * Returns whether this channel is public.
     */
    public boolean isPublic() {
        return this.isPublic;
    }

    /**
     * @param isPublic
     *            the isPublic to set
     */
    public void setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
    }
}