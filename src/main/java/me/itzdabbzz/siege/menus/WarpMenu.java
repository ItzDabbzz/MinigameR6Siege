package me.itzdabbzz.siege.menus;

import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.config.Lang;
import me.itzdabbzz.siege.config.MenuItem;
import org.bukkit.entity.Player;


public class WarpMenu {

    public static GUI get(Player player){
        GUIBuilder builder = new GUIBuilder(Lang.WARP_MENU_TITLE.get(), 6);

        for (int i = 0; i < 6*9; i++){
            builder.setItem(i, MenuItem.WARP_BACKGROUND.get());
        }

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
