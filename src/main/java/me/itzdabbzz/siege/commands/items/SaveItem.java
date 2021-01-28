package me.itzdabbzz.siege.commands.items;

import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import me.itzdabbzz.siege.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A basic command that takes no arguments.
 */
public class SaveItem extends Command {

    public SaveItem() {
        super("saveitem");
        super.setDescription("Saves a item to the items config");
    }

    @CommandExecutor
    public void run(Player sender) {
        ItemStack item = sender.getInventory().getItemInMainHand();
        ConsoleUtil.consoleDebug("Saving Item: " + item.getType());
        R6Siege.getItemManager().setSimpleItemStack("item_"+item.getType(), item);
        R6Siege.getItemManager().save();

    }

}