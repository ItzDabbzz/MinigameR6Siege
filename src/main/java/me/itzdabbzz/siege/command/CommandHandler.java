package me.itzdabbzz.siege.command;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.command.argument.ArgumentType;
import me.itzdabbzz.siege.command.argument.BuiltInArgumentType;
import me.itzdabbzz.siege.utils.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles {@link Command}s.
 */
public class CommandHandler implements Listener {

    private final Map<Class<?>, ArgumentType> argumentTypes = new HashMap<>();
    private String playerOnlyMessage = ChatColor.RED + "This command can only be run by players.";
    private String usageMessagePrefix = ChatColor.RED + "Usage: ";
    private boolean playerCompleterEnabled = true;

    /**
     * Constructs a new {@code CommandHandler}. Its map of argument types will
     * be initialized to contain all constants of the
     * {@link BuiltInArgumentType} enum.
     */
    public CommandHandler() {
        for (BuiltInArgumentType type : BuiltInArgumentType.values()) {
            this.argumentTypes.put(type.getParameterType(), type.getArgumentType());
        }

        if (ServerUtil.isAsyncTabCompletionSupported()) {
            Bukkit.getPluginManager().registerEvents(this, R6Siege.getInstance());
        }
    }

    /**
     * Returns the command handler's argument types, mapped by the type of
     * executor method parameter to which they are bound. New entries
     * (class-{@code ArgumentType} pairs) can be put into the map to add support
     * for more parameter types. Existing entries can be replaced, if a
     * {@link BuiltInArgumentType} doesn't meet your needs for example, and they
     * can also be deleted, in order to remove support for a parameter type.
     *
     * @return the command handler's argument types
     */
    @SuppressWarnings("WeakerAccess")
    public Map<Class<?>, ArgumentType> getArgumentTypes() {
        return argumentTypes;
    }

    /**
     * Returns the message to send to non-player command senders when they try
     * to call a player-only command that is handled by this command handler.
     *
     * @return the command handler's player-only message
     */
    @SuppressWarnings("WeakerAccess")
    public String getPlayerOnlyMessage() {
        return playerOnlyMessage;
    }

    /**
     * Sets the message to send to non-player command senders when they try to
     * call a player-only command that is handled by this command handler.
     *
     * @param playerOnlyMessage the command handler's player-only message
     */
    @SuppressWarnings("unused")
    public void setPlayerOnlyMessage(String playerOnlyMessage) {
        this.playerOnlyMessage = playerOnlyMessage;
    }

    /**
     * Returns the prefix to add to the usage message of commands that are
     * handled by this command handler, when sending it to a command sender that
     * did not provide all of the command's required arguments, or provided an
     * invalid value for an argument of a type that does not have a custom error
     * message set.
     *
     * @return the command handler's usage message prefix
     */
    @SuppressWarnings("WeakerAccess")
    public String getUsageMessagePrefix() {
        return usageMessagePrefix;
    }

    /**
     * Sets the prefix to add to the usage message of commands that are handled
     * by this command handler, when sending it to a command sender that did not
     * provide all of the command's required arguments, or provided an invalid
     * value for an argument of a type that does not have a custom error message
     * set.
     *
     * @param usageMessagePrefix the command handler's usage message prefix
     */
    @SuppressWarnings("unused")
    public void setUsageMessagePrefix(String usageMessagePrefix) {
        this.usageMessagePrefix = usageMessagePrefix;
    }

    /**
     * Returns {@code true} if {@link BuiltInArgumentType#PLAYER}'s
     * {@code ArgumentCompleter} is enabled for commands that are handled by
     * this command handler, and {@code false} otherwise.
     *
     * @return if {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}
     *         is enabled for commands that are handled by this command handler
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isPlayerCompleterEnabled() {
        return playerCompleterEnabled;
    }

    /**
     * Sets if {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}
     * is enabled for commands that are handled by this command handler or not.
     * Setting this property to {@code false} will also disable tab-completion
     * for arguments of other types that also use
     * {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}.
     * <p>
     * By default, this property is set to {@code true}.
     *
     * @param playerCompleterEnabled if {@link BuiltInArgumentType#PLAYER}'s
     *                               {@code ArgumentCompleter} is enabled for
     *                               commands that are handled by this command
     *                               handler
     */
    @SuppressWarnings("unused")
    public void setPlayerCompleterEnabled(boolean playerCompleterEnabled) {
        this.playerCompleterEnabled = playerCompleterEnabled;
    }

    /**
     * Registers a command to the server's command map and sets the command's
     * handler to this {@code CommandHandler}.
     *
     * @param command the command to register
     */
    @SuppressWarnings({ "unused", "WeakerAccess" })
    public void registerCommand(Command command) {
        ServerUtil.getCommandMap().register(R6Siege.getInstance().getName(), command);
        command.setHandler(this);
    }

    @EventHandler
    public void onAsyncTabComplete(AsyncTabCompleteEvent event) {
        // Making sure that the command sender is tab-completing a command argument
        if (!event.isCommand() || !event.getBuffer().contains(" ")) {
            return;
        }

        // `.substring(1)` removes the slash at the start of the chat message
        // A negative limit argument is used for the `split` method to prevent
        // it from removing trailing empty strings
        String[] splitBuffer = event.getBuffer().substring(1).split(" +", -1);
        String commandAlias = splitBuffer[0];
        org.bukkit.command.Command command = Bukkit.getPluginCommand(commandAlias);

        if (!(command instanceof Command)) {
            return;
        }

        Command MotherCommand = (Command) command;

        if (MotherCommand.getHandler() != this) {
            return;
        }

        // splitBuffer[0] is the command alias, all elements after it are command arguments
        String[] args = Arrays.copyOfRange(splitBuffer, 1, splitBuffer.length);

        event.setCompletions(MotherCommand.tabComplete(event.getSender(), commandAlias, args));
        event.setHandled(true);
    }

}