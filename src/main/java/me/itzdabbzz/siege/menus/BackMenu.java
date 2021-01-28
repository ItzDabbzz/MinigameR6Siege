package me.itzdabbzz.siege.menus;


import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.GUI;
import me.itzdabbzz.siege.Objects.GUIItem;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.builders.GUIBuilder;
import me.itzdabbzz.siege.config.Lang;
import me.itzdabbzz.siege.config.MenuItem;
import me.itzdabbzz.siege.utils.PlayerUtil;
import org.bukkit.entity.Player;


public class BackMenu {

    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(Lang.BACK_MENU_TITLE.get(), 3);
        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, MenuItem.BACK_BACKGROUND.get());
        }


        PlayerProfile mPlayer = PlayerManager.getProfiles().get(player.getUniqueId());

        if (player.hasPermission("core.back.use")){
            guiBuilder.setItem(12, new GUIItem(MenuItem.BACK_LAST_LOCATION.get(),
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, mPlayer.getLastLoc(), true);
                    }));
        }
        if (player.hasPermission("core.back.use.death")){
            guiBuilder.setItem(14, new GUIItem(MenuItem.BACK_LAST_LOCATION.get(),
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, mPlayer.getDeathLoc(), true);
                    }));
        }
        return guiBuilder.create();
    }
}
