package me.itzdabbzz.siege.menus;

import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.Objects.GUIItem;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.config.CfgBankDepositMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DepositMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgBankDepositMenu.TITLE, 3).openOnClose(BankMainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgBankDepositMenu.BACKGROUND);
        }
        guiBuilder.setItem(9, new GUIItem(CfgBankDepositMenu.ADD10, run -> Bukkit.dispatchCommand(player, "bank add 10")));
        guiBuilder.setItem(10, new GUIItem(CfgBankDepositMenu.ADD100, run -> Bukkit.dispatchCommand(player, "bank add 100")));
        guiBuilder.setItem(11, new GUIItem(CfgBankDepositMenu.ADD1000, run -> Bukkit.dispatchCommand(player, "bank add 1000")));
        guiBuilder.setItem(12, new GUIItem(CfgBankDepositMenu.ADD10000, run -> Bukkit.dispatchCommand(player, "bank add 10000")));
        guiBuilder.setItem(13, new GUIItem(CfgBankDepositMenu.ADD100000, run -> Bukkit.dispatchCommand(player, "bank add 100000")));
        guiBuilder.setItem(14, new GUIItem(CfgBankDepositMenu.ADD1000000, run -> Bukkit.dispatchCommand(player, "bank add 1000000")));
        guiBuilder.setItem(15, new GUIItem(CfgBankDepositMenu.ADD10000000, run -> Bukkit.dispatchCommand(player, "bank add 10000000")));
        guiBuilder.setItem(16, new GUIItem(CfgBankDepositMenu.ADD100000000, run -> Bukkit.dispatchCommand(player, "bank add 100000000")));
        guiBuilder.setItem(17, new GUIItem(CfgBankDepositMenu.ADD_ALL, run -> Bukkit.dispatchCommand(player, "bank addall")));
        guiBuilder.setItem(22, new GUIItem(CfgBankDepositMenu.ADD_CUSTOM,
                run -> {
                    /*for (String message : Lang.BANK_DEPOSIT_MESSAGE){
                        ChatUtil.sendMessage(player, message);
                    }*/
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}