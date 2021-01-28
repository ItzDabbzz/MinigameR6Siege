package me.itzdabbzz.siege.config;



import me.itzdabbzz.siege.Managers.ConfigManager;
import me.itzdabbzz.siege.Objects.MotherTitle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum Lang {
    OPS_MENU_TITLE("Operators"),
    SKULL_USAGE("§3Mother §8» §bUsage: §f/skull <name>"),
    SKULL_SUCCESS("§3Mother §8» §fSuccessfully got a skull of §b{name}§f."),
    TP_USAGE("§3Mother §8» §bUsage: §f/tp <player> <player>"),
    TP_SUCCESS("§3Mother §8» §fYou have been successfully teleported to §b{player}§f."),
    TP_SUCCESS_PLAYER("§3Mother §8» §b{player} §fhas teleported to you."),
    TP_SUCCESS_OTHERS("§3Mother §8» §fSuccessfully teleported §b{player1} §fto §b{player2}§f."),
    TP_SUCCESS_OTHERS_PLAYER1("§3Mother §8» §fYou have been teleported to §b{target} §fby §b{player}§f."),
    TP_SUCCESS_OTHERS_PLAYER2("§3Mother §8» §b{target} §fhas been teleported to you by §b{player}§f."),
    TPHERE_USAGE("§3Mother §8» §bUsage: §f/tphere <player>"),
    TPHERE_SUCCESS("§3Mother §8» §fSuccessfully teleported §b{player} §fto you."),
    TPHERE_SUCCESS_PLAYER("§3Mother §8» §fYou have been teleported to §b{player}§f."),
    TPA_USAGE("§3Mother §8» §bUsage: §f/tpa <player>"),
    TPA_SUCCESS("§3Mother §8» §fSuccessfully requested a teleportation to §b{player}§f."),
    TPA_SUCCESS_PLAYER("§3Mother §8» §b{player} §frequested a teleportation." +
            "\n<hover:show_text:'§aClick to accept'><click:run_command:'tpaccept'>§a§lAccept §a✓</click></hover>" +
            "\n<hover:show_text:'§cClick to decline'><click:run_command:'tpdeny'>§c§lDecline §c✕</click></hover>"),
    TPA_FAILURE_BLOCKED("§3Mother §8» §fYou are in the blocked list of §b{player}§f."),
    TPACCEPT_SUCCESS("§3Mother §8» §fSuccessfully accepted teleportation request from §b{player}§f."),
    TPACCEPT_SUCCESS_PLAYER("§3Mother §8» §b{player} §faccepted your teleportation request."),
    TPACCEPT_FAILURE_NO_REQUEST("§3Mother §8» §fYou don't have any teleportation requests."),
    TPDENY_SUCCESS("§3Mother §8» §fYou declined teleportation request from §b{player}§f."),
    TPDENY_SUCCESS_PLAYER("§3Mother §8» §b{player} §fdeclined your teleportation request."),
    TPDENY_FAILURE_NO_REQEUST("§3Mother §8» §fYou don't have any teleportation requests."),
    TPIGNORE_USAGE("§3Mother §8» §bUsage: §f/tpignore <player>"),
    TPIGNORE_SUCCESS_ADD("§3Mother §8» §b{player} §fhas been added to your blocked list."),
    TPIGNORE_SUCCESS_REMOVE("§3Mother §8» §b{player} §fhas been removed from your blocked list."),
    WARP_USAGE("§3Mother §8» §bUsage: §f/warp <name>"),
    WARP_SUCCESS("§3Mother §8» §fTeleporting to §b{warp}§f."),
    WARP_FAILURE_NO_WARP("§3Mother §8» §b{warp} §fis not a warp."),
    SETWARP_USAGE("§3Mother §8» §bUsage: §f/setwarp <name>"),
    SETWARP_SUCCESS("§3Mother §8» §fSuccessfully created warp §b{warp}§f." +
            "\n<hover:show_text:'§eClick to teleport'><click:run_command:'warp {warp}'>§e§lTeleport §e➹</click></hover>" +
            "\n<hover:show_text:'§cClick to remove'><click:run_command:'delwarp {warp}'>§c§lRemove §c✕</click></hover>"),
    DELWARP_USAGE("§3Mother §8» §bUsage: §f/delwarp <name>"),
    DELWARP_SUCCESS("§3Mother §8» §fSuccessfully removed warp §b{warp}§f."),
    DELWARP_FAILURE_NO_WARP("§3Mother §8» §b{warp} §fdoes not exists."),
    WARPS_SUCCESS_TITLE("§3Mother §8» §bServer warps:"),
    WARPS_SUCCESS_FORMAT("§7- §f{name} <hover:show_text:'§eClick to teleport'><click:run_command:'warp {name}'>§e➹</hover></click>"),
    WARPS_SUCCESS_FORMAT_REMOVE("§7- §f{name}" +
            "<hover:show_text:'§eClick to teleport'><click:run_command:'warp {name}'>§e➹</hover></click>" +
            "<hover:show_text:'§cClick to remove'><click:run_command:'delwarp {name}'>§c✕</hover></click>"),
    GAMEMODE_USAGE("§3Mother §8» §bUsage: §f/gamemode <type> <player>"),
    GAMEMODE_SUCCESS("§3Mother §8» §fYour gamemode has been set to §b{type}§f."),
    GAMEMODE_SUCCESS_OTHERS("§3Mother §8» §fSuccessfully set gamemode of §b{player} §fto §b{type}§f."),
    GAMEMODE_SUCCESS_OTHERS_PLAYER("§3Mother §8» §fYour gamemode has been set to §b{type} §fby §b{player}§f."),
    GAMEMODE_FAILURE_NO_GAMEMODE("§3Mother §8» §b{type} §fis not a valid gamemode!"),
    GAMEMODE_GMC_USAGE("§3Mother §8» §bUsage: §f/gmc <player>"),
    GAMEMODE_GMS_USAGE("§3Mother §8» §bUsage: §f/gms <player>"),
    GAMEMODE_GMA_USAGE("§3Mother §8» §bUsage: §f/gma <player>"),
    GAMEMODE_GMSP_USAGE("§3Mother §8» §bUsage: §f/gmsp <player>"),
    NO_PERM("&c&l! §8» §fYou don't have enough permissions to do this!"),
    NO_NUMBER("§c§l! §8» §b{arg} §fis not a number!"),
    NO_MATERIAL("§c§l! §8» §b{material} §fis not a material!"),
    OFFLINE("&c&l! §8» §e{name} §fis not on the server!"),
    TIME_FORMAT_DAYS("%d d, %02d h. %02d m. %02d s."),
    TIME_FORMAT_HOURS("%02d h. %02d m. %02d s."),
    TIME_FORMAT_MINUTES("00 h. %02d m. %02d s."),
    COMMAND_RESPONSE_PLAYER("&c&l! §8» §fYou can't use this command!"),
    BACK_MENU_TITLE("§aServerCore §8> §b/back"),
    WARP_MENU_TITLE("§aServerCore §8> §bWarps"),
    COMMAND_RESPONSE_CONSOLE("&c&l! §8» §fThis command is for in-game use only!");
    public static MotherTitle TELEPORT_TITLE_TICK;
    public static MotherTitle TELEPORT_TITLE_FAIL;
    public static MotherTitle TELEPORT_TITLE_TP;

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        TELEPORT_TITLE_TICK = config.getMotherTitle("teleport-title.ticking");
        TELEPORT_TITLE_FAIL = config.getMotherTitle("teleport-title.fail");
        TELEPORT_TITLE_TP = config.getMotherTitle("teleport-title.teleport");
        FileConfiguration fileConfig = config.getConfig();
        lang = new HashMap<>();
        for (Lang lang : values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.getDefault());
            }
            Lang.lang.put(lang, config.getString(lang.toString()));
        }
        config.save();
    }

    public String get(){
        return lang.get(this);
    }

    public String getDefault(){ return this.defaultValue; }
}
