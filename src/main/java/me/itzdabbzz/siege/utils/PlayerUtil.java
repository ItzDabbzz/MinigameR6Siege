package me.itzdabbzz.siege.utils;

import me.itzdabbzz.siege.Managers.TitleManager;
import me.itzdabbzz.siege.Objects.ItemCompareOption;
import me.itzdabbzz.siege.Objects.MotherTitle;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.builders.ItemBuilder;
import me.itzdabbzz.siege.builders.TitleBuilder;
import me.itzdabbzz.siege.config.Lang;
import me.itzdabbzz.siege.config.SoundType;
import me.itzdabbzz.siege.config.TitleType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class PlayerUtil {

    private static Method getHandleMethod;
    private static Field pingField;

    private static final Pattern PLAYER_UUID_PATTERN = Pattern.compile(
            "\\p{XDigit}{8}-\\p{XDigit}{4}-[34]\\p{XDigit}{3}-[89ab]\\p{XDigit}{3}-\\p{XDigit}{12}"
    );

    /**
     * Returns a {@link Pattern} to check if a string is a valid Minecraft
     * player UUID.
     *
     * @return a {@link Pattern} for Minecraft player UUIDs
     */
    public static Pattern getPlayerUUIDPattern() {
        return PLAYER_UUID_PATTERN;
    }

    /**
     * Returns the index of the Minecraft inventory slot at which the given
     * inventory row and column cross.
     *
     * @param row    the row's index (starting from 0 with the topmost row)
     * @param column the column's index (starting from 0 with the leftmost row)
     * @return the inventory slot at which the given row and column cross
     */
    @SuppressWarnings("unused")
    public static int slotAt(int row, int column) {
        return (row * 9) + column;
    }


    /**
     * Gets a {@link Player}'s ping
     *
     * @param player {@link Player} to get a ping for.
     * @return a player's current ping
     */
    public static int getPing(Player player) {
        try {
            if (getHandleMethod == null) {
                getHandleMethod = player.getClass().getDeclaredMethod("getHandle");
                getHandleMethod.setAccessible(true);
            }
            Object entityPlayer = getHandleMethod.invoke(player);
            if (pingField == null) {
                pingField = entityPlayer.getClass().getDeclaredField("ping");
                pingField.setAccessible(true);
            }
            int ping = pingField.getInt(entityPlayer);

            return ping > 0 ? ping : 0;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * Returns the given player's head. The item's display name will be set to
     * the player's name.
     *
     * @param player the player to get the head of
     * @return the player's head
     */
    @SuppressWarnings("deprecation")
    public static ItemStack getPlayerHead(Player player) {
        try {
            return new ItemBuilder(Material.PLAYER_HEAD)
                    .withName(player.getName())
                    .withOwner(player.getName())
                    .create();
        } catch (NoSuchFieldError e) {
            // For compatibility with versions of Bukkit before Minecraft 1.13
            return new ItemBuilder(Material.valueOf("SKULL_ITEM"))
                    .withName(player.getName())
                    .withOwner(player.getName())
                    .create();
        }
    }

    /**
     *
     * Checks if {@link OfflinePlayer} {@param player1} is the same to {@link OfflinePlayer} {@param player2}
     *
     * @param player1 {@link OfflinePlayer} player1 to check if same to {@param plauer2}
     * @param player2 {@link OfflinePlayer} player2 to check if same to {@param player1}
     * @return
     */
    public static boolean isSame(OfflinePlayer player1, OfflinePlayer player2) {
        if(player1 == null  || player2 == null) {
            return false;
        }
        return player1.getUniqueId().equals(player2.getUniqueId());
    }

    /**
     *
     * @param player {@link OfflinePlayer} to see if online
     * @return {@link Player}
     */
    public static Player getOnlinePlayer(OfflinePlayer player) {
        if(player!=null) {
            return Bukkit.getPlayer(player.getUniqueId());
        }
        return null;
    }

    public static Player getOnlinePlayer(List<OfflinePlayer> playerList, OfflinePlayer player) {
        return getOnlinePlayer(getOfflinePlayer(playerList, player));
    }

    public static OfflinePlayer getOfflinePlayer(List<OfflinePlayer> playerList, OfflinePlayer player) {
        for(OfflinePlayer search : playerList) {
            if(search.getUniqueId().equals(player.getUniqueId())) {
                return search;
            }
        }
        return null;
    }

    public static boolean isPlayerInList(List playerList, OfflinePlayer player) {
        for(Object search: playerList) {
            if(isSame((OfflinePlayer)search,player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if input belongs to a username of {@link Player}
     *
     * @param input Username of {@link Player}
     * @return True if there is a {@link Player} with this username, false if otherwise
     */
    public static boolean isPlayer(String input){
        Player player = Bukkit.getPlayer(input);
        return player != null && player.isOnline();
    }

    /**
     * Checks if input belongs to a UUID of {@link Player}
     *
     * @param input UUID of {@link Player}
     * @return True if there is a {@link Player} with this UUID, false if otherwise
     */
    public static boolean isPlayer(UUID input){
        Player player = Bukkit.getPlayer(input);
        return player != null && player.isOnline();
    }

    /**
     * Will try to get a username of {@link OfflinePlayer}
     *
     * @param offlinePlayer {@link OfflinePlayer} that we will try to get the username of
     * @return Username of offlinePlayer, "?" otherwise
     */

    //TODO: FIX THIS WITH NEW DATABASE SYSTEM
    /*public static String getName(OfflinePlayer offlinePlayer){
        QueryManager queryManager = TheMother.getQueryManager();

        UUID playerId = offlinePlayer.getUniqueId();

        if (offlinePlayer.isOnline()){
            queryManager.savePlayerName(playerId, offlinePlayer.getName());
            return offlinePlayer.getName();
        }

        String name = queryManager.getPlayerName(playerId);
        if (name == null){
            return  "?";
        }
        return name;
    }*/

    /**
     * Will get a ArrayList of all nearby {@link Player}'s
     *
     * @param loc {@link Location} of a player to search the area around.
     * @param range Determines the range of the player search.
     * @return
     */
    public static ArrayList<Player> getNearbyPlayers( Location loc, int range){//+
        range = range*range;
        ArrayList<Player> ppl = new ArrayList<Player>();
        for( Player p : Bukkit.getServer().getOnlinePlayers()){
            if(p.getWorld().getName().equals(loc.getWorld().getName()) && p.getLocation().distanceSquared(loc) > range)
                ppl.add(p);
        }
        return ppl;
    }

    /**
     * Will teleport {@link Player} to another {@link Player} using {@link #teleport(Player, Location, boolean)}.
     *
     * @param player {@link Player} that will be teleported to target
     * @param target {@link Player} that whose location will be set as the destination
     * @param delay Determines if the teleportation should be immediate or delayed
     *
     * @see #teleport(Player, Location, boolean)
     */
    public static void teleport(Player player, Player target, boolean delay){
        teleport(player, target.getLocation(), delay);
    }

    /**
     * Will teleport {@link Player} to {@link Location} with countdown displayed by {@link MotherTitle},
     * using {@link TitleManager}.
     *
     * @param player {@link Player} that should be teleported
     * @param location {@link Location} destination player should be teleported to
     * @param delay Determines if the teleportation should be immediate or delayed
     *
     * @see MotherTitle
     * @see TitleManager
     */
    public static void teleport(Player player, Location location, boolean delay){
        if (player.hasPermission("mother.teleport.bypass")){
            player.teleport(location);
            SoundType.TELEPORT_TP.get().play(player);
            TitleManager.send(player, TitleType.TELEPORT_TELEPORT.get());
            return;
        }
        final int countdown;
        if (delay){
            countdown = 0;//CfgSettings.TELEPORT_DELAY;
        }else{
            TitleManager.send(player, Lang.TELEPORT_TITLE_TP);
            player.teleport(location);
            SoundType.TELEPORT_TP.get().play(player);
            return;
        }
        new BukkitRunnable() {
            int finalCountdown = countdown;
            final Location pLoc = player.getLocation();
            final double x = pLoc.getX();
            final double z = pLoc.getZ();
            @Override
            public void run() {
                Location currentLoc = player.getLocation();
                double currentX = currentLoc.getX();
                double currentZ = currentLoc.getZ();
                // Fail (Player moved)
                if (currentX != x || currentZ != z){
                    TitleManager.send(player, new TitleBuilder(TitleType.TELEPORT_FAIL.get())
                            .replaceTitle("{time}", finalCountdown+"")
                            .replaceSubtitle("{time}", finalCountdown+"")
                            .create());
                    SoundType.TELEPORT_FAIL.get().play(player);
                    cancel();
                    return;
                }
                // Success (Teleporting Player)
                if (finalCountdown <= 0){
                    TitleManager.send(player, new TitleBuilder(Lang.TELEPORT_TITLE_TP)
                            .replaceTitle("{time}", finalCountdown+"")
                            .replaceSubtitle("{time}", finalCountdown+"")
                            .create());
                    SoundType.TELEPORT_TP.get().play(player);
                    Bukkit.getScheduler().runTask(R6Siege.getInstance(), ()-> player.teleport(location));
                    cancel();
                    return;
                }
                // Ticking (Waiting to be teleported)
                TitleManager.send(player, new TitleBuilder(Lang.TELEPORT_TITLE_TICK)
                        .replaceTitle("{time}", finalCountdown+"")
                        .replaceSubtitle("{time}", finalCountdown+"")
                        .create());
                SoundType.TELEPORT_TICK.get().play(player);
                finalCountdown--;
            }
        }.runTaskTimerAsynchronously(R6Siege.getInstance(), 0, 20);
    }

    /**
     * Will try to give an {@link ItemStack} to inventory of {@link Player}, drops the item at target's location
     * if there is no enough space.
     *
     * @param target {@link Player} that should receive the itemStack
     * @param item {@link ItemStack} that should be given to target
     */
    public static void give(Player target, ItemStack item){
        if (target.getInventory().firstEmpty() == -1){
            target.getWorld().dropItem(target.getLocation(), item);
        }else{
            target.getInventory().addItem(item);
        }
    }

    /**
     *
     * @param player {@link Player} that would receive the items
     * @param items {@link List<ItemStack>} the items to give to the player
     *
     */
    public static void give(Player player, List<ItemStack> items) {
        for(ItemStack item: items){
            give(player,item);
        }
    }

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    public static void healPlayer(Player player) {
        player.setHealth(player.getMaxHealth());
    }

    public static void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setExhaustion(0);
    }

    public static void clearLevels(Player player) {
        player.setLevel(0);
        player.setExp(0);
    }

    public static void clearEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    private static boolean hasOffHand = true;

    public static ItemStack getItemInMainHand(Player player) {
        try {
            if(hasOffHand) {
                return player.getInventory().getItemInMainHand();
            } else {
                return player.getInventory().getItemInHand();
            }
        } catch(NoSuchMethodError e) {
            hasOffHand = false;
            return player.getInventory().getItemInHand();
        }
    }

    public static boolean containsAtLeast(PlayerInventory inventory, ItemStack compareTo, int amount, ItemCompareOption option){
        int amountFound = 0;
        amountFound += countOccurrences(inventory.getArmorContents(),compareTo,option);
        amountFound += countOccurrences(inventory.getContents(),compareTo,option);
        ConsoleUtil.debug("amount found = "+amountFound);
        return amountFound >= amount;
    }

    public static List<ItemStack> find(PlayerInventory inventory, ItemStack compareTo, ItemCompareOption option){
        List<ItemStack> found = new ArrayList<ItemStack>();
        found.addAll(find(inventory.getArmorContents(),compareTo,option));
        found.addAll(find(inventory.getContents(),compareTo,option));
        return found;
    }

    public static void remove(PlayerInventory inventory, ItemStack toRemove, ItemCompareOption option){
        int leftToRemove = toRemove.getAmount();

        ItemStack[] inv = inventory.getContents();
        for(int i=0 ; i< inv.length ; i++){
            ItemStack item = inv[i];
            if(leftToRemove > 0 && item != null && itemEquals(item, toRemove, option)){
                if(leftToRemove >= item.getAmount()){
                    leftToRemove -= item.getAmount();
                    inv[i] = null;
                }else{
                    item.setAmount(item.getAmount()-leftToRemove);
                    leftToRemove = 0;
                }
            }
        }
        inventory.setContents(inv);

        ItemStack[] armor = inventory.getArmorContents();
        for(int i=0 ; i< armor.length ; i++){
            ItemStack item = armor[i];
            if(leftToRemove > 0 && item != null && itemEquals(item, toRemove, option)){
                if(leftToRemove >= item.getAmount()){
                    leftToRemove -= item.getAmount();
                    armor[i] = new ItemStack(Material.AIR);
                }else{
                    item.setAmount(item.getAmount()-leftToRemove);
                    leftToRemove = 0;
                }
            }
        }
        inventory.setArmorContents(armor);
    }


    public static int countOccurrences(ItemStack[] contents, ItemStack compareTo, ItemCompareOption option){
        int found = 0;
        for(ItemStack item : contents){
            if(itemEquals(item, compareTo, option)){
                found += item.getAmount();
            }
        }
        return found;
    }


    public static List<ItemStack> find(ItemStack[] contents, ItemStack compareTo, ItemCompareOption option) {
        List<ItemStack> found = new ArrayList<ItemStack>();
        for(ItemStack item : contents){
            if(itemEquals(item, compareTo, option)){
                found.add(item.clone());
            }
        }
        return found;
    }

    public static boolean itemEquals(ItemStack one, ItemStack two, ItemCompareOption option){
        boolean equals = true;

        if(one == null && two == null){
            ConsoleUtil.debug("one==null && two==null");
            return true;
        }

        if( (one == null && two != null) || (one != null && two == null) ){
            ConsoleUtil.debug("one==null && two!=null || one!=null && two==null");
            return false;
        }

        ConsoleUtil.debug("comparing one="+one.getType().toString()+" to two="+two.getType().toString());

        if(option.isMaterial()){
            equals = one.getType().equals(two.getType());
            ConsoleUtil.debug("compare material="+equals);
        }

        if(equals && option.isAmount()){
            equals = one.getAmount() == two.getAmount();
            ConsoleUtil.debug("compare amount="+equals);
        }

        if(equals && option.isDamageValue()){
            equals = one.getDurability() == two.getDurability();
            ConsoleUtil.debug("compare durability="+equals);
        }

        if(equals && option.isDisplayName()){
            if(!one.hasItemMeta() && !two.hasItemMeta()){
                equals = true;
            }else if(one.hasItemMeta() && two.hasItemMeta()){
                String oneName = one.getItemMeta().getDisplayName();
                String twoName = two.getItemMeta().getDisplayName();
                if( (oneName == null && twoName == null) || (oneName != null && twoName != null && oneName.equals(twoName))){
                    equals = true;
                }else{
                    equals = false;
                }
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare display name="+equals);
        }

        if(equals && option.isLore()){
            if(!one.hasItemMeta() && !two.hasItemMeta()){
                equals = true;
            }else if(one.hasItemMeta() && two.hasItemMeta()){
                List<String> oneLore = one.getItemMeta().getLore();
                List<String> twoLore = two.getItemMeta().getLore();
                if(oneLore == null && twoLore == null){
                    equals = true;
                }else if (oneLore != null && twoLore != null){
                    if(oneLore.size() == twoLore.size()){
                        for(int i=0 ; i< oneLore.size() ; i++){
                            if(!oneLore.get(i).equals(twoLore.get(i))){
                                equals = false;
                                break;
                            }
                        }
                    }else{
                        equals = false;
                    }
                }else{
                    equals = false;
                }
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare lore="+equals);
        }

        if(equals && option.isEnchantments()){
            Map<Enchantment, Integer> oneEnch = one.getEnchantments();
            Map<Enchantment, Integer> twoEnch = two.getEnchantments();

            if(oneEnch == null && twoEnch == null){
                equals = true;
            }else if(oneEnch != null && twoEnch != null){
                if(oneEnch.size() == twoEnch.size()){
                    equals = true;
                    for(Map.Entry<Enchantment,Integer> oneEnchEntry : oneEnch.entrySet()){
                        Integer twoEnchLevel = twoEnch.get(oneEnchEntry.getKey());
                        if(twoEnchLevel == null || twoEnchLevel != oneEnchEntry.getValue()){
                            equals = false;
                            break;
                        }
                    }
                }else{
                    equals = false;
                }
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare enchantments="+equals);
        }

        if(equals && option.isUnbreakable()){
            if(!one.hasItemMeta() && !two.hasItemMeta()){
                equals = true;
            }else if(one.hasItemMeta() && two.hasItemMeta()){
                equals = one.getItemMeta().isUnbreakable() == two.getItemMeta().isUnbreakable();
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare unbreakable="+equals);
        }

        if(equals && option.isColor()){
            if(!one.hasItemMeta() && !two.hasItemMeta()){
                equals = true;
            }else if(one.hasItemMeta() && two.hasItemMeta() && one.getItemMeta() instanceof LeatherArmorMeta && two.getItemMeta() instanceof LeatherArmorMeta){
                LeatherArmorMeta oneMeta = (LeatherArmorMeta) one.getItemMeta();
                LeatherArmorMeta twoMeta = (LeatherArmorMeta) two.getItemMeta();
                if(oneMeta.getColor() == null && twoMeta.getColor() == null){
                    equals = true;
                }else if(oneMeta.getColor() != null && twoMeta.getColor() != null && oneMeta.getColor().equals(twoMeta.getColor())){
                    equals = true;
                }else{
                    equals = false;
                }
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare color="+equals);
        }

        if(option.isSkullOwner()){
            if(!one.hasItemMeta() && !two.hasItemMeta()){
                equals = true;
            }else if(one.hasItemMeta() && two.hasItemMeta() && one.getItemMeta() instanceof SkullMeta && two.getItemMeta() instanceof SkullMeta){
                SkullMeta oneMeta = (SkullMeta) one.getItemMeta();
                SkullMeta twoMeta = (SkullMeta) two.getItemMeta();
                if(oneMeta.getOwner() == null && twoMeta.getOwner() == null){
                    equals = true;
                }else if(oneMeta.getOwner() != null && twoMeta.getOwner() != null && oneMeta.getOwner().equals(twoMeta.getOwner())){
                    equals = true;
                }else{
                    equals = false;
                }
            }else{
                equals = false;
            }
            ConsoleUtil.debug("compare color="+equals);
        }


        ConsoleUtil.debug("=> compare ="+equals);

        return equals;
    }


    public static void clear(Player player) {
        player.getInventory().clear();

        //clear player armor
        ItemStack[] emptyArmor = new ItemStack[4];
        for(int i=0 ; i<emptyArmor.length ; i++){
            emptyArmor[i] = new ItemStack(Material.AIR);
        }
        player.getInventory().setArmorContents(emptyArmor);

    }

}
