package me.itzdabbzz.siege.commands.general;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.menus.BackMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class BackCommand extends Command {

    public BackCommand() {
        super("back");
        super.setDescription("back!");
    }

    @CommandExecutor
    public void run(Player sender) {

        BackMenu.get(sender);

    }

}