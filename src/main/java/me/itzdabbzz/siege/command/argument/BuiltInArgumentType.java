package me.itzdabbzz.siege.command.argument;


import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

/**
 * A built-in {@link ArgumentType}.
 */
public enum BuiltInArgumentType {

    /**
     * Represents a {@link Player}.
     * <p>
     * Accepts exact usernames and UUIDs of online players, case-insensitively.
     * <p>
     * Suggests matching usernames of online players for argument completion. If
     * the command sender is a player, they will only be suggested usernames of
     * online players that they can see (see {@link Player#canSee(Player)}).
     */
    PLAYER(
            Player.class,
            new ArgumentType((ArgumentResolver<Player>) argument -> {
                String value = argument.getValue();

                if (PlayerUtil.getPlayerUUIDPattern().matcher(value).matches()) {
                    return Bukkit.getPlayer(UUID.fromString(value));
                }

                return Bukkit.getPlayerExact(value);
            }, argument -> {
                String value = argument.getValue();
                CommandSender sender = argument.getSender();
                List<String> completions = new ArrayList<>();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    boolean shouldBeSuggested = StringUtil.startsWithIgnoreCase(player.getName(), value)
                            && (!(sender instanceof Player) || ((Player) sender).canSee(player));

                    if (shouldBeSuggested) {
                        completions.add(player.getName());
                    }
                }

                completions.sort(String.CASE_INSENSITIVE_ORDER);
                return completions;
            })
    ),
    /**
     * Represents an {@link OfflinePlayer}.
     * <p>
     * Accepts exact usernames of online players and UUIDs of players, whether
     * online or offline, case-insensitively.
     * <p>
     * Uses {@link BuiltInArgumentType#PLAYER}'s {@link ArgumentCompleter}.
     */
    OFFLINE_PLAYER(
            OfflinePlayer.class,
            new ArgumentType((ArgumentResolver<OfflinePlayer>) argument -> {
                String value = argument.getValue();

                if (PlayerUtil.getPlayerUUIDPattern().matcher(value).matches()) {
                    return Bukkit.getOfflinePlayer(UUID.fromString(value));
                }

                return Bukkit.getPlayerExact(value);
            }, PLAYER.getArgumentType().getCompleter())
    ),
    /**
     * Represents a {@link World}.
     * <p>
     * Accepts names of server worlds.
     * <p>
     * Suggests matching world names for argument completion.
     */
    WORLD(
            World.class,
            new ArgumentType(
                    (ArgumentResolver<World>) argument -> Bukkit.getWorld(argument.getValue()),
                    argument -> {
                        List<String> completions = new ArrayList<>();

                        for (World world : Bukkit.getWorlds()) {
                            if (StringUtil.startsWithIgnoreCase(world.getName(), argument.getValue())) {
                                completions.add(world.getName());
                            }
                        }

                        return completions;
                    }
            )
    ),
    /**
     * Represents a {@link String}.
     * <p>
     * Returns the raw argument as it was provided by the command sender.
     * <p>
     * Uses {@link BuiltInArgumentType#PLAYER}'s {@link ArgumentCompleter}.
     */
    STRING(
            String.class,
            new ArgumentType(
                    (ArgumentResolver<String>) Argument::getValue,
                    PLAYER.getArgumentType().getCompleter()
            )
    ),
    /**
     * Represents an {@link Enum}.
     * <p>
     * Accepts the name of the enum constants of the type of the parameter that
     * represents the argument in the command's executor method.
     * <p>
     * Suggests matching enum constant names for argument completion.
     */
    @SuppressWarnings("unchecked")
    ENUM(
            Enum.class,
            new ArgumentType((ArgumentResolver<Enum<?>>) argument -> {
                Class<? extends Enum<?>> enumClass =
                        (Class<? extends Enum<?>>) R6Siege.getParameterType(argument.getParameter());

                for (Enum<?> constant : enumClass.getEnumConstants()) {
                    if (argument.getValue().equals(constant.name())) {
                        return constant;
                    }
                }

                return null;
            }, argument -> {
                Class<? extends Enum<?>> enumClass =
                        (Class<? extends Enum<?>>) R6Siege.getParameterType(argument.getParameter());
                List<String> completions = new ArrayList<>();

                for (Enum<?> constant : enumClass.getEnumConstants()) {
                    if (StringUtil.startsWithIgnoreCase(constant.name(), argument.getValue())) {
                        completions.add(constant.name());
                    }
                }

                completions.sort(String.CASE_INSENSITIVE_ORDER);
                return completions;
            })
    ),
    /**
     * Represents a {@link Boolean}.
     * <p>
     * Accepts {@code true} and {@code false}, case-insensitively.
     * <p>
     * Suggests those same values for argument completion, if they match.
     */
    BOOLEAN(
            Boolean.class,
            new ArgumentType((ArgumentResolver<Boolean>) argument -> {
                if (argument.getValue().equalsIgnoreCase("true")) {
                    return true;
                }

                if (argument.getValue().equalsIgnoreCase("false")) {
                    return false;
                }

                return null;
            }, argument -> {
                String value = argument.getValue().toLowerCase();

                if ("".equals(value)) {
                    return Arrays.asList("true", "false");
                }

                if ("true".startsWith(value)) {
                    return Collections.singletonList("true");
                }

                if ("false".startsWith(value)) {
                    return Collections.singletonList("false");
                }

                return Collections.emptyList();
            })
    ),
    /**
     * Represents a {@link Byte}.
     * <p>
     * Accepts any value that {@link Byte#parseByte(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    BYTE(
            Byte.class,
            new ArgumentType((ArgumentResolver<Byte>) argument -> {
                try {
                    return Byte.parseByte(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    ),
    /**
     * Represents a {@link Short}.
     * <p>
     * Accepts any value that {@link Short#parseShort(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    SHORT(
            Short.class,
            new ArgumentType((ArgumentResolver<Short>) argument -> {
                try {
                    return Short.parseShort(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    ),
    /**
     * Represents an {@link Integer}.
     * <p>
     * Accepts any value that {@link Integer#parseInt(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    INTEGER(
            Integer.class,
            new ArgumentType((ArgumentResolver<Integer>) argument -> {
                try {
                    return Integer.parseInt(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    ),
    /**
     * Represents a {@link Long}.
     * <p>
     * Accepts any value that {@link Long#parseLong(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    LONG(
            Long.class,
            new ArgumentType((ArgumentResolver<Long>) argument -> {
                try {
                    return Long.parseLong(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    ),
    /**
     * Represents a {@link Float}.
     * <p>
     * Accepts any value that {@link Float#parseFloat(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    FLOAT(
            Float.class,
            new ArgumentType((ArgumentResolver<Float>) argument -> {
                try {
                    return Float.parseFloat(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    ),
    /**
     * Represents a {@link Double}.
     * <p>
     * Accepts any value that {@link Double#parseDouble(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    DOUBLE(
            Double.class,
            new ArgumentType((ArgumentResolver<Double>) argument -> {
                try {
                    return Double.parseDouble(argument.getValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            })
    );

    private final Class<?> parameterType;
    private final ArgumentType argumentType;

    BuiltInArgumentType(Class<?> parameterType, ArgumentType argumentType) {
        this.parameterType = parameterType;
        this.argumentType = argumentType;
    }

    /**
     * Returns the type of command executor method parameters to which the
     * {@link ArgumentType} should be bound.
     *
     * @return the type of command executor method parameters
     */
    public Class<?> getParameterType() {
        return parameterType;
    }

    /**
     * Returns the actual {@link ArgumentType}.
     *
     * @return the {@link ArgumentType}
     */
    public ArgumentType getArgumentType() {
        return argumentType;
    }

}