package me.itzdabbzz.siege.menus;



import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.Objects.GUIItem;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.config.CfgBankMainMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BankMainMenu implements Listener {
    public static GUI get(Player player){
        PlayerProfile mPlayer = PlayerManager.getProfiles().get(player.getUniqueId());
        double amount = mPlayer.getPoints();
        int maxAmount = 10000000; //mPlayer.getBankType().getBank().getMax();

        GUIBuilder guiBuilder = new GUIBuilder(CfgBankMainMenu.TITLE, 3);
        for (int i = 0; i<27;i++){
            guiBuilder.setItem(i, CfgBankMainMenu.BACKGROUND);
        }
        guiBuilder.setItem(13, new GUIItem(
                new ItemBuilder(CfgBankMainMenu.HEAD)
                        .replaceLore("{amount}", amount + "")
                        .replaceLore("{max}", maxAmount + "")
                        .create(),
                inventoryClickEvent -> {
                    //BankMenu.get(player).open(player);
                     }));
        guiBuilder.setItem(11, new GUIItem(CfgBankMainMenu.DEPOSIT, run -> DepositMenu.get(player).open(player)));
        guiBuilder.setItem(15, new GUIItem(CfgBankMainMenu.WITHDRAW, run -> WithdrawMenu.get(player).open(player)));

        return guiBuilder.create();
    }
}