package me.itzdabbzz.siege.Managers;

import com.cryptomorin.xseries.XMaterial;
import me.itzdabbzz.siege.config.CfgBlockedBlocks;
import me.itzdabbzz.siege.config.CfgSettings;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

public class BlockedBlocksManager {

    public static List<Material> blockedBlocks;

    public static void setBlockedBlocks(List<Material> blockedBlocks) {
        for(int i = 0; i < CfgBlockedBlocks.blockedBlocks.size(); i++) {
            Optional<XMaterial> xMaterialOptional = Optional.of(XMaterial.matchXMaterial(CfgBlockedBlocks.blockedBlocks.get(i)));
            ConsoleUtil.sendConsoleMessage("Loading " + CfgBlockedBlocks.blockedBlocks.size() + " Blocks To Set To Be Non Explodable");
            if(xMaterialOptional.isPresent() && xMaterialOptional.get().parseMaterial() != null) {
                blockedBlocks.add(xMaterialOptional.get().parseMaterial());
            }
            //blockedBlocks = XMaterial.matchXMaterial(CfgSettings.blockList.get(i));
        }
    }

    public static List<Material> getBlockedBlocks() {
        return blockedBlocks;
    }
}
