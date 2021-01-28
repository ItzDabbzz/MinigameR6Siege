package me.itzdabbzz.siege.minigame.game.game.siege;

import me.itzdabbzz.siege.kits.Kit;

public class Operator {

    private static String name;

    private final Kit kit;

    private static String description;

    private Side side;

    private static Speed speed;

    private static Armor armor;

    private static Ability ability;

    public Operator(String name, Kit kit, String description, Side side, Speed speed, Armor armor, Ability ability) {
      this.name = name;
      this.kit = kit;
      this.description = description;
      this.side = side;
      this.speed = speed;
      this.armor = armor;
      this.ability = ability;
    }
}
