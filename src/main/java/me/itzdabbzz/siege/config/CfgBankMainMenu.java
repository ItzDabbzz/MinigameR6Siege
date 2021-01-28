package me.itzdabbzz.siege.config;

import me.itzdabbzz.siege.Managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgBankMainMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack HEAD;
    public static ItemStack WITHDRAW;
    public static ItemStack DEPOSIT;

    public static void load(ConfigManager config){
        CfgBankMainMenu.TITLE = config.getString("main-menu.title");
        CfgBankMainMenu.BACKGROUND = config.getSimpleItemStack("main-menu.background");
        CfgBankMainMenu.HEAD = config.getSimpleItemStack("main-menu.head");
        CfgBankMainMenu.WITHDRAW = config.getSimpleItemStack("main-menu.withdraw");
        CfgBankMainMenu.DEPOSIT = config.getSimpleItemStack("main-menu.deposit");
    }
}