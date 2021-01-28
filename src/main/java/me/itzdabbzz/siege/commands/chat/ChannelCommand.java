package me.itzdabbzz.siege.commands.chat;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.chat.ChatChannel;
import me.itzdabbzz.siege.chat.ChatManager;
import me.itzdabbzz.siege.chat.PlayerChannelSubscriber;
import me.itzdabbzz.siege.chat.SubscribeMode;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.argument.Optional;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class ChannelCommand extends Command {

    public ChannelCommand() {
        super("channel");
        super.setDescription("Allows you to see or join more Chat Channels!");
    }

    @CommandExecutor
    public void run(Player sender, @Optional String type, @Optional String channelName) {
        if(type.equals("channels")) {
            sender.sendMessage(ChatColor.GOLD + "Your channels: ");
            for (ChatChannel channel : ChatManager.getChannelsByPlayer(sender)) {
                sender.sendMessage(channel.getName());
            }
            sender.sendMessage(ChatColor.AQUA + "/channel help - Displays help information.");
        }else if(type.equals("join")) {
            if (ChatManager.getChannel(channelName) != null)
                if (!ChatManager.getChannel(channelName).isSubscribed(sender))

                    ChatManager.getChannel(channelName).subscribe(
                            new PlayerChannelSubscriber(sender, SubscribeMode.READ));
                else
                    sender.sendMessage(ChatManager.error("You are already in that channel!"));
            else
                sender.sendMessage(ChatManager.error("That channel does not exists!"));
        }else if(type.equals("leave")) {
            if (ChatManager.getChannel(channelName).isSubscribed(sender))
                ChatManager.getChannel(channelName).unsubscribe(sender);
            else
                sender.sendMessage(ChatManager.error("You are not in that channel!"));
        }else {
            sender.sendMessage(ChatColor.GREEN + "Available channels: ");
            for (ChatChannel channel : ChatManager.getChannelsByPlayer(sender)) {
                if (channel.isPublic())
                    sender.sendMessage(channel.getName());
            }
        }

    }

}