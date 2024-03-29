package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.R6Siege;

import java.lang.reflect.InvocationTargetException;

/**
 * The types of game
 */
public enum GameType
{
    TEST_GAME("Test Game", 10, 10, TestGame.class);

    /**
     * GameType constructor
     * @param niceName The "nice" name of the GameType
     * @param minPlayers The minimum amount of players for this GameType
     * @param maxPlayers The maximum amount of players for this GameType
     * @param clazz The class containing the implementation of this GameType
     */
    GameType(String niceName, int minPlayers, int maxPlayers, Class<? extends Game> clazz)
    {
        this.niceName = niceName;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.clazz = clazz;
    }

    private final String niceName;
    private final int minPlayers;
    private final int maxPlayers;
    private final Class<? extends Game> clazz;

    @Override
    public String toString()
    {
        return niceName;
    }

    /**
     * Gets the minimum players for this GameType
     * @return The minimum amount of players required
     */
    public int getMinPlayers()
    {
        return minPlayers;
    }

    /**
     * Gets the maximum players for this GameType
     * @return The maximum amount of players allowed
     */
    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    /**
     * Gets a new {@link Game} instance for this GameType
     * @return A new game instance
     */
    public Game newInstance(R6Siege manager)
    {
        try
        {
            return clazz.getDeclaredConstructor(R6Siege.class).newInstance(manager);
        }
        catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException  ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}