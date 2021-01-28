package me.itzdabbzz.siege.config;

import com.cryptomorin.xseries.XMaterial;
import it.unimi.dsi.fastutil.Hash;
import me.itzdabbzz.siege.Managers.BlockedBlocksManager;
import me.itzdabbzz.siege.Managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class CfgBlockedBlocks {

    public static List<Material> blockedBlocks;

    public static void load(ConfigManager config) {
        ConfigurationSection blockedBs = config.getConfigurationSection("settings.blocklist");
        if(blockedBlocks != null) {
            for(String block : blockedBs.getKeys(false)) {
                Material mat = config.getMaterial("settings.blocklist."+block);
                blockedBlocks.add(XMaterial.matchXMaterial(mat).parseMaterial());
            }
        }
    }

    public static List<Material> getBlockedBlocks() {
        return blockedBlocks;
    }
}
