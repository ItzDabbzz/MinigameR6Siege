package me.itzdabbzz.siege.menus;



import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.Objects.GUIItem;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.config.CfgBankWithdrawMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WithdrawMenu implements Listener {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgBankWithdrawMenu.TITLE, 3).openOnClose(BankMainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgBankWithdrawMenu.BACKGROUND);
        }
        guiBuilder.setItem(9, new GUIItem(CfgBankWithdrawMenu.REMOVE10, run -> Bukkit.dispatchCommand(player, "bank remove 10")));
        guiBuilder.setItem(10, new GUIItem(CfgBankWithdrawMenu.REMOVE100, run -> Bukkit.dispatchCommand(player, "bank remove 100")));
        guiBuilder.setItem(11, new GUIItem(CfgBankWithdrawMenu.REMOVE1000, run -> Bukkit.dispatchCommand(player, "bank remove 1000")));
        guiBuilder.setItem(12, new GUIItem(CfgBankWithdrawMenu.REMOVE10000, run -> Bukkit.dispatchCommand(player, "bank remove 10000")));
        guiBuilder.setItem(13, new GUIItem(CfgBankWithdrawMenu.REMOVE100000, run -> Bukkit.dispatchCommand(player, "bank remove 100000")));
        guiBuilder.setItem(14, new GUIItem(CfgBankWithdrawMenu.REMOVE1000000, run -> Bukkit.dispatchCommand(player, "bank remove 1000000")));
        guiBuilder.setItem(15, new GUIItem(CfgBankWithdrawMenu.REMOVE10000000, run -> Bukkit.dispatchCommand(player, "bank remove 10000000")));
        guiBuilder.setItem(16, new GUIItem(CfgBankWithdrawMenu.REMOVE100000000, run -> Bukkit.dispatchCommand(player, "bank remove 100000000")));
        guiBuilder.setItem(17, new GUIItem(CfgBankWithdrawMenu.REMOVE_ALL, run -> Bukkit.dispatchCommand(player, "bank removeall")));
        guiBuilder.setItem(22, new GUIItem(CfgBankWithdrawMenu.REMOVE_CUSTOM,
                run -> {
                    /*for (String message : Lang.BANK_WITHDRAW_MESSAGE.get()){
                        ChatUtil.sendMessage(player, message);
                    }*/
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}