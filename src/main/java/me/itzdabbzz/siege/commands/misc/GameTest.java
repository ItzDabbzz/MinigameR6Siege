package me.itzdabbzz.siege.commands.misc;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.minigame.game.game.GameType;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import org.bukkit.command.CommandSender;

/**
 * A basic command that takes no arguments.
 */
public class GameTest extends Command {

    public GameTest() {
        super("game");
        super.setDescription("Game Dev Test!");
    }

    @CommandExecutor
    public void run(CommandSender sender) {

        GameType type = GameType.TEST_GAME;

        if (type != null)
        {
            Game gameInst = type.newInstance(R6Siege.getInstance());

            if (gameInst != null)
            {
                R6Siege.setGame(gameInst);

                ConsoleUtil.sendConsoleMessage("Loading game of type " + type.toString() + " [" + type.name() + "]");
                return;
            }

            ConsoleUtil.consoleError("Game failed to load: Error creating game instance");
            return;
        }

    }

}