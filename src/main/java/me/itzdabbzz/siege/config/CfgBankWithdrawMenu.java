package me.itzdabbzz.siege.config;

import me.itzdabbzz.siege.Managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgBankWithdrawMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack REMOVE10;
    public static ItemStack REMOVE100;
    public static ItemStack REMOVE1000;
    public static ItemStack REMOVE10000;
    public static ItemStack REMOVE100000;
    public static ItemStack REMOVE1000000;
    public static ItemStack REMOVE10000000;
    public static ItemStack REMOVE100000000;
    public static ItemStack REMOVE_ALL;
    public static ItemStack REMOVE_CUSTOM;

    public static void load(ConfigManager config){
    	CfgBankWithdrawMenu.TITLE = config.getString("withdraw-menu.title");
    	CfgBankWithdrawMenu.BACKGROUND = config.getSimpleItemStack("withdraw-menu.background");
    	CfgBankWithdrawMenu.REMOVE10 = config.getSimpleItemStack("withdraw-menu.remove-10");
    	CfgBankWithdrawMenu.REMOVE100 = config.getSimpleItemStack("withdraw-menu.remove-100");
        CfgBankWithdrawMenu.REMOVE1000 = config.getSimpleItemStack("withdraw-menu.remove-1000");
        CfgBankWithdrawMenu.REMOVE10000 = config.getSimpleItemStack("withdraw-menu.remove-10000");
        CfgBankWithdrawMenu.REMOVE100000 = config.getSimpleItemStack("withdraw-menu.remove-100000");
        CfgBankWithdrawMenu.REMOVE1000000 = config.getSimpleItemStack("withdraw-menu.remove-1000000");
        CfgBankWithdrawMenu.REMOVE10000000 = config.getSimpleItemStack("withdraw-menu.remove-10000000");
        CfgBankWithdrawMenu.REMOVE100000000 = config.getSimpleItemStack("withdraw-menu.remove-100000000");
        CfgBankWithdrawMenu.REMOVE_ALL = config.getSimpleItemStack("withdraw-menu.remove-all");
        CfgBankWithdrawMenu.REMOVE_CUSTOM = config.getSimpleItemStack("withdraw-menu.remove-custom");
    }
}