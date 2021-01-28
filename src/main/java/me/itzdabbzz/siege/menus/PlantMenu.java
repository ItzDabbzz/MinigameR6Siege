package me.itzdabbzz.siege.menus;

import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.Objects.GUIItem;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.config.Lang;
import me.itzdabbzz.siege.config.MenuItem;
import me.itzdabbzz.siege.minigame.game.game.PlantTimer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PlantMenu {

    public static GUI get(Player player){
        GUIBuilder builder = new GUIBuilder(Lang.OPS_MENU_TITLE.get(), 3);

        for (int i = 0; i < 3*9; i++){
            builder.setItem(i, MenuItem.PLANT_BACKGROUND.get());
        }

        PlantTimer plantTimer = new PlantTimer(R6Siege.getInstance(), 15, R6Siege.getGame().getBombLocation());
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack[] temp = new ItemStack[5];//.getItem(inv.getSize() - 1);
                temp[0] = new ItemBuilder(Material.PINK_STAINED_GLASS_PANE).withName("1").create();
                temp[1] = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).withName("2").create();
                temp[2] = new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).withName("3").create();
                temp[3] = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).withName("4").create();
                temp[4] = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).withName("5").create();

                for (int i = temp.length; i > 0; i--) {
                    if (i > 1) {
                        //inv.setItem(i - 1, inv.getItem(i - 2));
                        builder.setItem(i - 1, temp[i]);
                    } else {
                        builder.setItem(i - 1, temp[i]);
                    }
                }
            }
        }.runTaskTimer(R6Siege.getInstance(), 10, 10);

        /*WarpManager warpManager = TheMother.getWarpManager();
        int warpSlot = 0;
        List<String> warps = new ArrayList<>();

        for (MotherWarp warp : warpManager.getWarpNames().values()){
            if (!player.hasPermission("servercore.warp.use." + warp)) continue;

            ItemBuilder item = new ItemBuilder(MenuItem.WARP_ITEM.get());
            String itemName = item.getName().replace("{warp}", warp.getName());
            item.withName(itemName);
            item.replaceLore("{warp}", warp.getName());

            builder.setItem(warpSlot, new GUIItem(item.create(),
                    run -> {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warp " + warp);
                    }));

            warpSlot++;
        }*/

        return builder.create();
    }
}
