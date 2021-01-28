package me.itzdabbzz.siege.minigame.game.game;

import me.itzdabbzz.siege.minigame.game.phase.phases.game.LastStandingPhase;
import me.itzdabbzz.siege.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

class TestFightPhase extends LastStandingPhase
{
    TestFightPhase(Game game)
    {
        super(game);
    }

    @Override
    public void onStart()
    {
        game.forEachPlayer(player -> player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD)));
    }

    @Override
    public String getActionBar(Player player)
    {
        return CC.gold + "Players left standing: " + CC.bold + game.getPlayersSize();
    }

    @Override
    public List<String> getSidebar(Player player)
    {
        List<String> sidebar = new ArrayList<>();

        sidebar.add(CC.gold + CC.bold + "Alive");
        sidebar.add(String.valueOf(game.getPlayersSize()));

        return sidebar;
    }
}