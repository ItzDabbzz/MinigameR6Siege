package me.itzdabbzz.siege.command;

import com.google.common.primitives.Primitives;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.command.argument.*;
import me.itzdabbzz.siege.utils.ChatUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command extends org.bukkit.command.Command implements PluginIdentifiableCommand {


    private final Map<String, Command> subCommands = new HashMap<>();
    private boolean playerOnly = false;
    private Method executor;
    private CommandHandler handler;
    private int requiredArgs = 0;
    private Command parent;

    protected Command(String name) {
        super(name);
        this.detectExecutorMethod();

        if (this.executor != null) {
            this.detectRequiredArguments();
        }
    }


    private void detectExecutorMethod() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(CommandExecutor.class)) {
                if (this.executor != null) {
                    throw new IllegalStateException("Command has more than one executor method");
                }

                method.setAccessible(true);
                this.executor = method;
            }
        }
    }

    private void detectRequiredArguments() {
        Parameter[] params = this.executor.getParameters();

        for (int i = 1; i < params.length; i++) {
            Parameter param = params[i];
            boolean optional = param.isAnnotationPresent(Optional.class) ||
                    param.isAnnotationPresent(Default.class);

            if (optional) {
                break;
            }

            this.requiredArgs++;
        }
    }


    /**
     * Returns the Bukkit plugin that owns the command.
     *
     * @return the Bukkit plugin that owns the command
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public Plugin getPlugin() {
        return R6Siege.getInstance();
    }

    /**
     * Returns {@code true} if the command can only be called by players or
     * {@code false} if it can also be called by other types of command senders,
     * such as the server console and command blocks.
     *
     * @return if the command is player-only
     */
    @SuppressWarnings("unused")
    public boolean isPlayerOnly() {
        return playerOnly;
    }

    /**
     * Sets if the command can only be called by players and not other types of
     * command senders, such as the server console and command blocks, or not.
     *
     * @param playerOnly if the command is player-only
     */
    @SuppressWarnings("WeakerAccess")
    public void setPlayerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
    }

    /**
     * Returns the command's subCommands, mapped by alias.
     *
     * @return the command's subCommands
     */
    @SuppressWarnings("unused")
    public Map<String, Command> getSubCommands() {
        return subCommands;
    }

    /**
     * Adds a subcommand to the command. Command senders will be able to call
     * the subcommand by typing {@code /<command> <subcommand> [arguments...]},
     * where {@code <command>} is an alias of the command and
     * {@code <subcommand>} is an alias of the subcommand.
     *
     * @param subcommand the subcommand to add to the command
     */
    @SuppressWarnings("unused")
    public void addSubcommand(Command subcommand) {
        subcommand.parent = this;
        this.subCommands.put(subcommand.getName(), subcommand);

        for (String alias : subcommand.getAliases()) {
            this.subCommands.put(alias, subcommand);
        }
    }

    /**
     * Returns the command's command handler
     *
     * @return the command's command handler
     */
    @SuppressWarnings("WeakerAccess")
    public CommandHandler getHandler() {
        return handler;
    }

    /**
     * Sets the command's command handler.
     *
     * @param handler the command's command handler
     */
    @SuppressWarnings("WeakerAccess")
    public void setHandler(CommandHandler handler) {
        this.handler = handler;

        for (Command subcommand : this.subCommands.values()) {
            subcommand.setHandler(this.handler);
        }
    }

    /**
     * Returns the command's parent command: the command of which it is a
     * subcommand.
     *
     * @return the command's parent command, or {@code null} if the command is
     *         not the subcommand of any command
     */
    @SuppressWarnings("unused")
    public Command getParent() {
        return parent;
    }

    /**
     * Sets the command's aliases. This method is a wrapper for
     * {@link org.bukkit.command.Command#setAliases(List)} that takes varargs instead of a
     * {@link List}.
     *
     * @param aliases the command's aliases
     */
    @SuppressWarnings("unused")
    public void setAliases(String... aliases) {
        super.setAliases(Arrays.asList(aliases));
    }

    /**
     * Returns the command's usage message, with occurrences of the
     * {@code <command>} macro replaced with the given alias.
     *
     * @param alias the alias to replace the {@code <command>} macro with
     * @return the command's usage message
     */
    @SuppressWarnings("WeakerAccess")
    public String getUsage(String alias) {
        return super.usageMessage.replace("<command>", alias);
    }

    /**
     * Sends the command's usage message to a {@link CommandSender}, with the
     * usage message prefix of the command's handler.
     *
     * @param sender the {@link CommandSender} to send the usage message to
     * @param alias  the alias of the command that was used by the
     *               {@link CommandSender}
     */
    @SuppressWarnings("WeakerAccess")
    public void sendUsage(CommandSender sender, String alias) {
        if (super.usageMessage.length() > 0) {
            sender.sendMessage(this.handler.getUsageMessagePrefix() + this.getUsage(alias));
        }
    }

    private boolean executeSubcommand(CommandSender sender, String[] args) {
        String subcommandAlias = args[0];
        String[] subcommandArgs = Arrays.copyOfRange(args, 1, args.length);
        Command subcommand = this.subCommands.get(subcommandAlias);
        return subcommand.execute(sender, subcommandAlias, subcommandArgs);
    }

    private ArgumentType getArgumentType(Class<?> paramType) {
        Class<?> superParamType = paramType;
        ArgumentType argType;

        do {
            argType = this.handler.getArgumentTypes().get(Primitives.wrap(superParamType));
            superParamType = superParamType.getSuperclass();
        } while (argType == null && superParamType != null);

        if (argType == null) {
            throw new IllegalStateException("Command handler of command '" + super.getLabel() +
                    "' has no argument type bound to " + paramType + " or any of its superclasses");
        }

        return argType;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] resolveVarArgs(Parameter param, Deque<String> args, CommandSender sender,
                                   ArgumentResolver<?> resolver) {
        Class<?> paramType = R6Siege.getParameterType(param);
        T[] array = (T[]) Array.newInstance(Primitives.wrap(paramType), args.size());

        for (int i = 0; !args.isEmpty(); i++) {
            array[i] = (T) resolver.resolve(new Argument(param, args.getFirst(), sender));

            if (array[i] == null) {
                return null;
            }

            args.pop();
        }

        return array;
    }

    private String resolveRestToString(Deque<String> args) {
        StringBuilder builder = new StringBuilder(args.pop());

        while (!args.isEmpty()) {
            builder.append(" ").append(args.pop());
        }

        return builder.toString();
    }

    private Object resolveArgument(CommandSender sender, String alias, Parameter param,
                                   Deque<String> args) {
        Class<?> paramType = R6Siege.getParameterType(param);
        ArgumentType argType = this.getArgumentType(paramType);
        ArgumentResolver<?> resolver = argType.getResolver();
        Object resolved;

        if (param.isVarArgs()) {
            resolved = this.resolveVarArgs(param, args, sender, resolver);
        } else if (paramType == String.class && param.isAnnotationPresent(Rest.class)) {
            resolved = this.resolveRestToString(args);
        } else {
            resolved = resolver.resolve(new Argument(param, args.getFirst(), sender));
        }

        if (resolved == null) {
            if (argType.getErrorMessage() == null) {
                this.sendUsage(sender, alias);
            } else {
                sender.sendMessage(argType.getErrorMessage().replace("{value}", args.getFirst()));
            }
        }

        if (!args.isEmpty()) {
            args.pop();
        }

        return resolved;
    }

    private Object[] makeExecutorArguments(CommandSender sender, String alias, Deque<String> args) {
        Parameter[] params = this.executor.getParameters();
        Object[] executorArgs = new Object[params.length];
        executorArgs[0] = sender;

        // Resolving command arguments into executor method arguments
        for (int i = 1; i < params.length; i++) {
            Parameter param = params[i];

            if (args.isEmpty()) {
                if (param.isAnnotationPresent(Optional.class)) {
                    break;
                } else if (param.isAnnotationPresent(Default.class)) {
                    args.push(param.getAnnotation(Default.class).value());
                }
            }

            executorArgs[i] = this.resolveArgument(sender, alias, param, args);

            if (executorArgs[i] == null) {
                // Argument could not be resolved
                return null;
            }
        }

        return executorArgs;
    }

    private void tryToInvokeExecutor(String alias, Object... params) {
        try {
            this.executor.invoke(this, params);
        } catch (IllegalAccessException e) {
            throw new CommandException("Cannot access executor method of command '" + alias +
                    "' in plugin " + this.getPlugin().getDescription().getFullName(), e);
        } catch (InvocationTargetException e) {
            throw new CommandException("Unhandled exception executing command '" + alias +
                    "' in plugin " + this.getPlugin().getDescription().getFullName(), e);
        }
    }

    /**
     * Executes the command.
     *
     * @param sender the command sender that issued the command call
     * @param alias  the alias of the command that was used
     * @param args   the arguments passed to the command
     * @return if the command's execution succeeded
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (this.playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(this.handler.getPlayerOnlyMessage());
            return true;
        }

        if (!super.testPermission(sender)) {
            return true;
        }

        if (args.length > 0 && this.subCommands.containsKey(args[0])) {
            return this.executeSubcommand(sender, args);
        }

        if (this.executor == null) {
            return true;
        }

        Deque<String> argsDeque = new ArrayDeque<>(Arrays.asList(args));

        if (argsDeque.size() < this.requiredArgs) {
            this.sendUsage(sender, alias);
            return false;
        }

        Object[] executorArgs = this.makeExecutorArguments(sender, alias, argsDeque);

        if (executorArgs == null) {
            return false;
        }

        this.tryToInvokeExecutor(alias, executorArgs);
        return true;
    }

    private List<String> tabCompleteSubcommand(String value) {
        List<String> completions = new ArrayList<>();

        for (String subcommandName : this.subCommands.keySet()) {
            if (StringUtil.startsWithIgnoreCase(subcommandName, value)) {
                completions.add(subcommandName);
            }
        }

        completions.sort(String.CASE_INSENSITIVE_ORDER);
        return completions;
    }

    private List<String> tabCompleteSubcommandArgument(CommandSender sender, String[] args) {
        String subcommandAlias = args[0];
        Command subcommand = this.subCommands.get(subcommandAlias);

        if (subcommand == null) {
            return Collections.emptyList();
        }

        String[] subcommandArgs = Arrays.copyOfRange(args, 1, args.length);
        return subcommand.tabComplete(sender, subcommandAlias, subcommandArgs);
    }

    private List<String> tabCompleteArgument(CommandSender sender, String[] args) {
        Parameter[] executorParams = this.executor.getParameters();
        // The executor method parameter that corresponds to the argument to complete
        Parameter param;

        if (args.length >= executorParams.length) {
            param = executorParams[executorParams.length - 1];
            boolean isRestAnnotatedString = param.getType() == String.class &&
                    param.isAnnotationPresent(Rest.class);

            if (!param.isVarArgs() && !isRestAnnotatedString) {
                // Unexpected argument, cannot suggest completions
                return Collections.emptyList();
            }
        } else {
            param = executorParams[args.length];
        }

        Class<?> paramType = R6Siege.getParameterType(param);
        ArgumentCompleter completer = this.getArgumentType(paramType).getCompleter();
        ArgumentCompleter playerCompleter = BuiltInArgumentType.PLAYER
                .getArgumentType().getCompleter();

        if (completer.equals(playerCompleter) && !this.handler.isPlayerCompleterEnabled()) {
            return Collections.emptyList();
        }

        return completer.complete(new Argument(param, args[args.length - 1], sender));
    }

    /**
     * Suggests completions for a command argument.
     *
     * @param sender the command sender that issued the tab-completion request
     * @param alias  the alias of the command that was used
     * @param args   the arguments that the command sender currently wrote
     * @return a list of completions for the last argument; never {@code null}
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (this.subCommands.size() > 0) {
            if (args.length > 1) {
                return this.tabCompleteSubcommandArgument(sender, args);
            } else {
                return this.tabCompleteSubcommand(args[0]);
            }
        }

        return this.tabCompleteArgument(sender, args);
    }

    /**
     * Sends a List of messages with parsed MiniMessage tags and PAPI Placeholders to {@link CommandSender}.
     *
     * @param sender {@link CommandSender} that should receive the messages
     * @param messages List of messages that should be send
     *
     * @see #sendMessage(Player, String)
     * @see #sendMessage(CommandSender, String)
     */
    public void sendMessage(CommandSender sender, List<String> messages){
        if (sender instanceof Player){
            Player player = (Player) sender;
            for (String line : messages){
                sendMessage(player, line);
            }
            return;
        }
        for (String line : messages){
            sendMessage(sender, line);
        }
    }

    /**
     * Sends a List of messages with parsed PAPI Placeholders to {@link CommandSender}.
     *
     * @param sender {@link CommandSender} that should receive the messages
     * @param messages List of messages that should be send
     *
     * @see #sendMessage(Player, String)
     * @see #sendMessage(CommandSender, String)
     */
    public void sendMessageClean(CommandSender sender, List<String> messages){
        if (sender instanceof Player){
            Player player = (Player) sender;
            for (String line : messages){
                sendMessageClean(player, line);
            }
            return;
        }
        for (String line : messages){
            sendMessageClean(sender, line);
        }
    }

    /**
     * Sends a message with parsed MiniMessage tags and PAPI Placeholders to {@link OfflinePlayer}, if online.
     *
     * @param player {@link OfflinePlayer} that should receive the message
     * @param message String that should be send to player
     *
     * @see #sendMessage(Player, String)
     */
    public void sendMessage(OfflinePlayer player, String message){
        if (!player.isOnline()) return;
        sendMessage((Player)player, message);
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link OfflinePlayer}, if online.
     *
     * @param player {@link OfflinePlayer} that should receive the message
     * @param message String that should be send to player
     *
     * @see #sendMessage(Player, String)
     */
    public void sendMessageClean(OfflinePlayer player, String message){
        if (!player.isOnline()) return;
        sendMessageClean((Player)player, message);
    }

    /**
     * Sends a message with parsed MiniMessage tags and PAPI Placeholders to {@link Player} using {@link ChatUtil}.
     *
     * @param player {@link Player} that should receive the message
     * @param message String that will be send to player
     *
     * @see ChatUtil
     */
    public void sendMessage(Player player, String message){
        ChatUtil.sendMessage(player, message);
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link Player} using {@link ChatUtil}.
     *
     * @param player {@link Player} that should receive the message
     * @param message String that will be send to player
     *
     * @see ChatUtil
     */
    public void sendMessageClean(Player player, String message){
        ChatUtil.sendMessageClean(player, message);
    }

    /**
     * Sends a message with parsed MiniMessage tags and PAPI Placeholders to {@link CommandSender} using {@link ChatUtil}.
     *
     * @param sender {@link CommandSender} that should receive the message
     * @param message String that will be send to sender
     *
     * @see ChatUtil
     */
    public void sendMessage(CommandSender sender, String message){
        ChatUtil.sendMessage(sender, message);
    }

    /**
     * Sends a message with parsed PAPI Placeholders to {@link CommandSender} using {@link ChatUtil}.
     *
     * @param sender {@link CommandSender} that should receive the message
     * @param message String that will be send to sender
     *
     * @see ChatUtil
     */
    public void sendMessageClean(CommandSender sender, String message){
        ChatUtil.sendMessageClean(sender, message);
    }
}
