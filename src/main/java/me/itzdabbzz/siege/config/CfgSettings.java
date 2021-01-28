package me.itzdabbzz.siege.config;

import com.cryptomorin.xseries.XMaterial;
import com.mongodb.MongoCredential;
import me.itzdabbzz.siege.Managers.BlockedBlocksManager;
import me.itzdabbzz.siege.Managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import scala.Char;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CfgSettings {
    public static boolean SOUNDS;
    public static int TELEPORT_DELAY;
    public static boolean VAULT;
    public static boolean HOLOGRAMS;
    public static boolean PLACEHOLDERS;
    public static boolean DISABLED_MESSAGES;


    public static void load(ConfigManager config){
        CfgSettings.SOUNDS = config.getBoolean("settings.sounds");
        CfgSettings.TELEPORT_DELAY = config.getInt("settings.teleport-delay");
        CfgSettings.VAULT = config.getBoolean("settings.vault");
        CfgSettings.HOLOGRAMS = config.getBoolean("settings.holograms");
        CfgSettings.PLACEHOLDERS = config.getBoolean("settings.placeholders");
        CfgSettings.DISABLED_MESSAGES = config.getBoolean("settings.disabled-messages");


    }
}
