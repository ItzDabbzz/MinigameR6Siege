package me.itzdabbzz.siege.menus;


import me.clip.placeholderapi.util.TimeUtil;
import me.itzdabbzz.siege.Managers.GUIManager;
import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.config.CfgTimeMenu;
import me.itzdabbzz.siege.utils.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TimeMenu implements Listener {

    public static void open(Player player, OfflinePlayer requested){
        String requestedName = requested.getName();
        //TimeUtil.update(player);

        PlayerProfile corePlayer = PlayerManager.getProfiles().get(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(player, 3*9, CfgTimeMenu.title);
        GUIManager.ClickRunnable clickRunnable = run -> {};

        for (int i = 0; i<27; i++){
            inventory.setItem(i, CfgTimeMenu.background);
        }

        inventory.setItem(4, new ItemBuilder(CfgTimeMenu.yesterday).withOwner(requestedName).replaceLore("{player}", requestedName+"").create());
        inventory.setItem( 11, new ItemBuilder(CfgTimeMenu.all).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getOnlineTime() / 1000)).create());
       // inventory.setItem(11, new ItemBuilder(CfgTimeMenu.yesterday).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getYesterday() / 1000)).create());
       // inventory.setItem(12, new ItemBuilder(CfgTimeMenu.daily).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getDaily() / 1000)).create());
        //inventory.setItem(13, new ItemBuilder(CfgTimeMenu.all).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getAll() / 1000)).create());
       // inventory.setItem(14, new ItemBuilder(CfgTimeMenu.weekly).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getWeekly() / 1000)).create());
        //inventory.setItem(15, new ItemBuilder(CfgTimeMenu.monthly).replaceLore("{time}", FormatUtil.formatTime(corePlayer.getMonthly() / 1000)).create());


        player.openInventory(inventory);
    }

    @EventHandler
    private static void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgTimeMenu.title)) event.setCancelled(true);
    }
}