package me.itzdabbzz.siege.config;

import me.itzdabbzz.siege.Managers.ConfigManager;
import org.bukkit.inventory.ItemStack;


public class CfgBankDepositMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack ADD10;
    public static ItemStack ADD100;
    public static ItemStack ADD1000;
    public static ItemStack ADD10000;
    public static ItemStack ADD100000;
    public static ItemStack ADD1000000;
    public static ItemStack ADD10000000;
    public static ItemStack ADD100000000;
    public static ItemStack ADD_ALL;
    public static ItemStack ADD_CUSTOM;

    public static void load(ConfigManager config){
        CfgBankDepositMenu.TITLE = config.getString("deposit-menu.title");
        CfgBankDepositMenu.BACKGROUND = config.getSimpleItemStack("deposit-menu.background");
        CfgBankDepositMenu.ADD10 = config.getSimpleItemStack("deposit-menu.add-10");
        CfgBankDepositMenu.ADD100 = config.getSimpleItemStack("deposit-menu.add-100");
        CfgBankDepositMenu.ADD1000 = config.getSimpleItemStack("deposit-menu.add-1000");
        CfgBankDepositMenu.ADD10000 = config.getSimpleItemStack("deposit-menu.add-10000");
        CfgBankDepositMenu.ADD100000 = config.getSimpleItemStack("deposit-menu.add-100000");
        CfgBankDepositMenu.ADD1000000 = config.getSimpleItemStack("deposit-menu.add-1000000");
        CfgBankDepositMenu.ADD10000000 = config.getSimpleItemStack("deposit-menu.add-10000000");
        CfgBankDepositMenu.ADD100000000 = config.getSimpleItemStack("deposit-menu.add-100000000");
        CfgBankDepositMenu.ADD_ALL = config.getSimpleItemStack("deposit-menu.add-all");
        CfgBankDepositMenu.ADD_CUSTOM = config.getSimpleItemStack("deposit-menu.add-custom");
    }
}