package me.itzdabbzz.siege.config;

import com.cryptomorin.xseries.XMaterial;

import me.itzdabbzz.siege.Managers.ConfigManager;
import me.itzdabbzz.siege.builders.ItemBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum MenuItem {
    // Back Menu
    BACK_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("§0").create()),
    BACK_LAST_LOCATION(new ItemBuilder(XMaterial.GLOWSTONE_DUST)
            .withName("§eLast location")
            .withLore("§fClick to teleport to your last location.")
            .create()),
    BACK_DEATH_LOCATION(new ItemBuilder(XMaterial.GLOWSTONE_DUST)
            .withName("§cDeath location")
            .withLore("§fClick to teleport to your death location.")
            .create()),

    PLANT_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND1(new ItemBuilder(XMaterial.BLUE_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND2(new ItemBuilder(XMaterial.CYAN_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND4(new ItemBuilder(XMaterial.PURPLE_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND5(new ItemBuilder(XMaterial.YELLOW_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND6(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("§0").create()),
    PLANT_BACKGROUND7(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("§0").create()),

    // Warp Menu
    WARP_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("§0").create()),
    WARP_ITEM(new ItemBuilder(XMaterial.PAPER)
            .withName("§e{warp}")
            .withLore("§fClick to be teleported to", "§e{warp}§f.")
            .create());

    private static HashMap<MenuItem, ItemStack> menuItem;
    private final ItemStack defaultValue;

    MenuItem(ItemStack defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        FileConfiguration fileConfig = config.getConfig();
        menuItem = new HashMap<>();
        for (MenuItem menuItem : values()){
            if (!fileConfig.isSet(menuItem.toString())){
                config.setSimpleItemStack(menuItem.toString(), menuItem.getDefault());
            }
            MenuItem.menuItem.put(menuItem, config.getSimpleItemStack(menuItem.toString()));
        }
        config.save();
    }

    public ItemStack get(){
        return menuItem.get(this);
    }

    public ItemStack getDefault(){
        return this.defaultValue;
    }
}