package me.itzdabbzz.siege.commands.general;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class ProfileCommand extends Command {

    public ProfileCommand() {
        super("profile");
        super.setDescription("Check your profile out!");
    }

    @CommandExecutor
    public void run(Player sender) {
        //sender.sendMessage("Pong!");

        PlayerProfile player = PlayerManager.getProfiles().get(sender.getUniqueId());

        sender.sendMessage("Points: " + player.getPoints());
        sender.sendMessage("Last Username: " + player.getLastKnownName());
        sender.sendMessage("Atk: " + player.getAtk());
        sender.sendMessage("Def: " + player.getDef());

    }

}