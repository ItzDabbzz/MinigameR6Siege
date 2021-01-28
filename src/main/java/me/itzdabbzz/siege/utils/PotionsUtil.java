package me.itzdabbzz.siege.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionsUtil {


    public static void add(Player player, PotionEffectType type, int duration, int multiplier){
        player.addPotionEffect(new PotionEffect(type, duration, multiplier), false);
    }

    public static void add(Player player, PotionEffectType type, int duration, int multiplier, boolean showParticles){
        player.addPotionEffect(new PotionEffect(type, duration, multiplier, false, showParticles), false);
    }

    public static void addPermanent(Player player, PotionEffectType type, int multiplier){
        player.addPotionEffect(new PotionEffect(type, 99999999, multiplier), false);
    }

    public static void addPermanent(Player player, PotionEffectType type, int multiplier, boolean showParticles){
        player.addPotionEffect(new PotionEffect(type, 99999999, multiplier, false, showParticles), false);
    }

    public static void addForce(Player player, PotionEffectType type, int duration, int multiplier){
        player.addPotionEffect(new PotionEffect(type, duration, multiplier), true);
    }

    public static void addForce(Player player, PotionEffectType type, int duration, int multiplier, boolean showParticles){
        player.addPotionEffect(new PotionEffect(type, duration, multiplier, false, showParticles), true);
    }

    public static void addPermanentForce(Player player, PotionEffectType type, int multiplier){
        player.addPotionEffect(new PotionEffect(type, 99999999, multiplier), true);
    }

    public static void addPermanentForce(Player player, PotionEffectType type, int multiplier, boolean showParticles){
        player.addPotionEffect(new PotionEffect(type, 99999999, multiplier, false, showParticles), true);
    }

    public static void clear(Player player){
        for(PotionEffect effect : player.getActivePotionEffects()){
            remove(player,effect.getType());
        }
    }

    public static void remove(Player player, PotionEffectType type){
        if(player.hasPotionEffect(type)){
            player.removePotionEffect(type);
        }
    }

}
