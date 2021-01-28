package me.itzdabbzz.siege.commands.misc;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.Command;
import org.bukkit.command.CommandSender;

/**
 * A basic command that takes no arguments.
 */
public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
        super.setDescription("Pong!");
    }

    @CommandExecutor
    public void run(CommandSender sender) {
        sender.sendMessage("Pong!");
        PlayerManager.saveProfiles();

    }

}