package me.itzdabbzz.siege.config;


import me.itzdabbzz.siege.Managers.ConfigManager;
import me.itzdabbzz.siege.Objects.MotherTitle;
import me.itzdabbzz.siege.builders.TitleBuilder;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum TitleType {
    TELEPORT_TICKING(new TitleBuilder("§b§lTeleportation", "§7{time}§8s", 20, 40, 20).create()),
    TELEPORT_FAIL(new TitleBuilder("§b§lMove detected", "§cTeleportation canceled..", 20, 60, 20).create()),
    TELEPORT_TELEPORT(new TitleBuilder("§bTeleportation", "§7Teleporting..", 20, 60, 20).create());

    private static HashMap<TitleType, MotherTitle> titleType;
    private final MotherTitle defaultValue;

    TitleType(MotherTitle defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        FileConfiguration fileConfig = config.getConfig();
        titleType = new HashMap<>();
        for (TitleType title : values()){
            if (!fileConfig.isSet(title.toString())){
                config.setMotherTitle(title.toString(), title.getDefault());
            }
            TitleType.titleType.put(title, config.getMotherTitle(title.toString()));
        }
        config.save();
    }

    public MotherTitle get(){
        return titleType.get(this);
    }

    public MotherTitle getDefault(){
        return this.defaultValue;
    }
}