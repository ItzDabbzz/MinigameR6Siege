package me.itzdabbzz.siege.commands.general;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.chat.ChatManager;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.argument.Optional;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class FoeCommand extends Command {

    public FoeCommand() {
        super("foe");
        super.setDescription("Add foes or remove em!");
    }

    @CommandExecutor
    public void run(Player sender, @Optional String type, @Optional Player friend) {
        PlayerProfile player = PlayerManager.getProfiles().get(sender.getUniqueId());
        if(type.equals("add")) {
            boolean success = false;
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getName().equalsIgnoreCase(friend.getName())) {
                    player.getFoes().add(friend.getUniqueId());
                    sender.sendMessage(ChatManager.success("Player '" + p.getName() + "' has been ADDED to your foe list!"));
                    p.sendMessage(ChatManager.success("Player '" + sender.getName()
                            + "' added you to his/her foe! Add him too! /foe "
                            + sender.getName()));
                    success = true;
                }
            }

            if (!success)
                sender.sendMessage(ChatManager.error("Player not found! Player must be online!"));
        }else if(type.equals("remove")) {
            boolean success = false;
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getName().equalsIgnoreCase(friend.getName())) {
                    player.getFoes().remove(friend.getUniqueId());
                    sender.sendMessage(ChatManager.success("Player '" + p.getName() + "' has been REMOVED from your foe list!"));
                    success = true;
                }
            }

            if (!success)
                sender.sendMessage(ChatManager.error("Player not found! Player must be online!"));

        }
    }

}