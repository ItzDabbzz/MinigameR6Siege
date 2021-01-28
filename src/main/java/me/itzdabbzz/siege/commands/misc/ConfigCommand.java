package me.itzdabbzz.siege.commands.misc;

import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.argument.Optional;
import me.itzdabbzz.siege.command.argument.Rest;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A basic command that takes no arguments.
 */
public class ConfigCommand extends Command {

    public ConfigCommand() {
        super("config");
        super.setDescription("Configure Shit!");
    }

    @CommandExecutor
    public void run(Player sender, String type, @Optional String type2, @Optional String type3, @Optional String type4, @Rest String... type5) {
        sender.sendMessage("Configured the shit...");

        if(type.equalsIgnoreCase("menu")) {
            if(type2.equalsIgnoreCase("time-menu")) {
                if(type3.equalsIgnoreCase("itemstack")) {
                    R6Siege.getMenuManager().setSimpleItemStack( "time-menu."+type4, sender.getInventory().getItemInMainHand());
                    sender.sendMessage("Configured the shit... " + sender.getInventory().getItemInMainHand().getType());
                }else if(type3.equalsIgnoreCase("string")) {
                    R6Siege.getMenuManager().getConfig().set("time-menu."+type4, type5);
                    sender.sendMessage("Configured the shit... " + type5);
                }else if(type3.equalsIgnoreCase("int")) {
                    R6Siege.getMenuManager().getConfig().set("time-menu."+type4, Integer.parseInt(type5[0]));
                    sender.sendMessage("Configured the shit... " + type5);
                } else if(type3.equalsIgnoreCase("stringlist")) {
                    R6Siege.getMenuManager().getConfig().set("time-menu."+type4, type5);
                    sender.sendMessage("Configured the shit... " + type5);
                }
            }
        }else if(type.equalsIgnoreCase("config")) {

        }else if(type.equalsIgnoreCase("kit")) {
           // KitManager kitManager = R6Siege.getKitManager();
            //if(type2.equalsIgnoreCase())
        }
    }

}