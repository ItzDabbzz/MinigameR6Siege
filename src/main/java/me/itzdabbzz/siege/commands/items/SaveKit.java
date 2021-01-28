package me.itzdabbzz.siege.commands.items;

import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A basic command that takes no arguments.
 */
public class SaveKit extends Command {

    public SaveKit() {
        super("savekit");
        super.setDescription("Saves a kit to the kits config");
    }

    /**
     *         YamlConfiguration c = YamlConfiguration.loadConfiguration(new File(path, p.getName()+".yml"));
     *         ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
     *         p.getInventory().setArmorContents(content);
     *         content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
     *         p.getInventory().setContents(content);
     *
     * @param sender
     * @param kit
     */

    @CommandExecutor
    public void run(Player sender, String kit) {
        ItemStack[] item = sender.getInventory().getContents();
        ItemStack[] armor = sender.getInventory().getArmorContents();
        ConsoleUtil.consoleDebug("Saving Kit: " + kit);

        R6Siege.getItemManager().getConfig().set("kit."+kit, item);
        R6Siege.getItemManager().getConfig().set("kit."+kit+".armor", armor);
        R6Siege.getItemManager().save();

    }

}