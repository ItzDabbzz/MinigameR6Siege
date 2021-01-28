package me.itzdabbzz.siege.commands.general;

import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.menus.BackMenu;
import me.itzdabbzz.siege.menus.BankMainMenu;
import me.itzdabbzz.siege.menus.BankMenu;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class BankCommand extends Command {

    public BankCommand() {
        super("back");
        super.setDescription("back!");
    }

    @CommandExecutor
    public void run(Player sender) {

        BankMainMenu.get(sender);

    }

}