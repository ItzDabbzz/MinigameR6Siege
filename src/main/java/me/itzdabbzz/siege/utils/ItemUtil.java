package me.itzdabbzz.siege.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemUtil {

    /**
     * Returns specified colored lether armor.
     *
     * @param material
     * @param color
     * @return
     */
    public static ItemStack coloredLetherArmor(final Material material, final Color color) {
        ItemStack larmor = new ItemStack(material, 1);
        LeatherArmorMeta lam = (LeatherArmorMeta) larmor.getItemMeta();
        lam.setColor(color);
        larmor.setItemMeta(lam);
        return larmor;
    }
}
