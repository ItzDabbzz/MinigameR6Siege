package me.itzdabbzz.siege.config;


import com.cryptomorin.xseries.XSound;
import me.itzdabbzz.siege.Managers.ConfigManager;
import me.itzdabbzz.siege.Objects.MotherSound;
import me.itzdabbzz.siege.builders.SoundBuilder;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum SoundType {
    COMMAND_FAIL(new SoundBuilder(XSound.ENTITY_VILLAGER_NO, 10, 1).create()),
    TELEPORT_TICK(new SoundBuilder(XSound.UI_BUTTON_CLICK, 10, 1).create()),
    TELEPORT_FAIL(new SoundBuilder(XSound.ENTITY_VILLAGER_HURT, 10, 1).create()),
    TELEPORT_TP(new SoundBuilder(XSound.ENTITY_CAT_PURR, 10, 1).create());

    private static HashMap<SoundType, MotherSound> soundType;
    private final MotherSound defaultValue;

    SoundType(MotherSound defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        FileConfiguration fileConfig = config.getConfig();
        soundType = new HashMap<>();
        for (SoundType soundType : values()){
            if(!fileConfig.isSet(soundType.toString())) {
                config.setMotherSound(soundType.toString(), soundType.getDefault());
            }
            SoundType.soundType.put(soundType, config.getMotherSound(soundType.toString()));
        }
    }

    public MotherSound get(){
        return soundType.get(this);
    }

    public MotherSound getDefault(){
        return this.defaultValue;
    }
}
