package me.itzdabbzz.siege.minigame.map.maps;

import me.itzdabbzz.siege.minigame.game.game.Game;
import me.itzdabbzz.siege.minigame.game.game.GameType;
import me.itzdabbzz.siege.minigame.map.GameMap;

public class TestMap extends GameMap
{
    public TestMap()
    {
        super("Testing Map", "TestGameWorld", GameType.TEST_GAME);

        setLobbyLocation(8, 20, 8, 90, 0);

        addSpawn(Game.SOLO_TEAM, 23, 4, -6, 45, 0);
        addSpawn(Game.SOLO_TEAM, 23, 4, 23, 135, 0);
        addSpawn(Game.SOLO_TEAM, -6, 4, 23, 225, 0);
        addSpawn(Game.SOLO_TEAM, -6, 4, -6, 315, 0);
    }
}