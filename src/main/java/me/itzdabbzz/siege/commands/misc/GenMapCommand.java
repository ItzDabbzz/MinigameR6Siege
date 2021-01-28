package me.itzdabbzz.siege.commands.misc;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.argument.Rest;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import me.itzdabbzz.siege.utils.Location;
import me.itzdabbzz.siege.utils.ReflectionUtils;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;


import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.itzdabbzz.siege.utils.GeneralUtil.generateVoidWorld1_8__1_12;

/**
 * A basic command that takes no arguments.
 */
public class GenMapCommand extends Command {

    public GenMapCommand() {
        super("genmap");
        super.setDescription("Generates a empty map and loads it.");
        super.setUsage("/genmap <world>");
    }

    @CommandExecutor
    public void run(Player sender,@Rest String world) {

        generateVoidWorld1_8__1_12(sender, world);

    }

}