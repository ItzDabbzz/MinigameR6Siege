package me.itzdabbzz.siege.minigame.game.game;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import me.itzdabbzz.siege.Objects.MotherSound;
import me.itzdabbzz.siege.Objects.MotherTitle;
import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.game.phase.GamePhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.game.PregamePhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyCountdownPhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyPhase;
import me.itzdabbzz.siege.minigame.game.phase.phases.lobby.LobbyWaitingPhase;
import me.itzdabbzz.siege.minigame.game.team.TeamGame;
import me.itzdabbzz.siege.minigame.map.GameMap;
import me.itzdabbzz.siege.utils.CC;
import me.itzdabbzz.siege.utils.ConsoleUtil;
import me.itzdabbzz.siege.utils.ServerUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;


/**
 * The type Game.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Game implements Listener {
    /**
     * The constant SOLO_TEAM.
     */
    @lombok.Getter
    @lombok.Setter
    public static final String SOLO_TEAM = "Players";
    @lombok.Getter
    @lombok.Setter
    private final R6Siege manager;

    /**
     * The GameType of this game
     */
    @lombok.Getter
    @lombok.Setter
    private final GameType type;

    /**
     * The map the game is taking place on
     */
    @lombok.Getter
    @lombok.Setter
    private final GameMap map;
    /**
     * The Game mode.
     */
    @lombok.Getter
    @lombok.Setter
    public GameMode gameMode = GameMode.SURVIVAL;
    /**
     * The Countdown title.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean countdownTitle = true;
    /**
     * The Join leave messages.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean joinLeaveMessages = true;
    /**
     * The Pre game freeze.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean preGameFreeze = true;
    /**
     * The Spec chat.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean specChat = false;
    /**
     * The Spec cmd.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean specCmd = false;
    /**
     * The Nice death.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean niceDeath = true;
    /**
     * The Winner glow.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean winnerGlow = true;
    /**
     * The Death drop items.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean deathDropItems = true;
    /**
     * The Block break.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean blockBreak = false;
    /**
     * The Block place.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean blockPlace = false;
    /**
     * The Item drop.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean itemDrop = false;
    /**
     * The Entity spawn.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean entitySpawn = false;
    /**
     * The Damage.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean damage = false;
    /**
     * The Pvp.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean pvp = false;

    @lombok.Getter
    @lombok.Setter
    private boolean hunger = false;
    /**
     * The Resource pack url.
     */
    @lombok.Getter
    @lombok.Setter
    public String resourcePackUrl = null;
    /**
     * The Force resource pack.
     */
    @lombok.Getter
    @lombok.Setter
    public boolean forceResourcePack = false;

    @lombok.Getter
    @lombok.Setter
    private final AtomicInteger hologramID = new AtomicInteger();

    @lombok.Getter
    @lombok.Setter
    private int PlantTime = 6;

    @lombok.Getter
    @lombok.Setter
    private int DefuseTime = 6;

    @lombok.Getter
    @lombok.Setter
    private int DetonationTime = 40;

    @lombok.Getter
    @lombok.Setter
    private Sound TimerSound = ENTITY_EXPERIENCE_ORB_PICKUP;

    @lombok.Getter
    @lombok.Setter
    private int TimerRange = 256;

    @lombok.Getter
    @lombok.Setter
    private float TimerVolume = 2;

    @lombok.Getter
    @lombok.Setter
    private int TimerPitch = 1;

    @lombok.Getter
    @lombok.Setter
    private Sound PlantDefuseNoise = Sound.BLOCK_GRASS_BREAK;

    @lombok.Getter
    @lombok.Setter
    private int NoiseRange = 32;

    @lombok.Getter
    @lombok.Setter
    private float NoiseVolume = 2;

    @lombok.Getter
    @lombok.Setter
    private float NoisePitch = 1;

    @lombok.Getter
    @lombok.Setter
    private static Material BombBlock = Material.TNT;

    @lombok.Getter
    @lombok.Setter
    private static Location BombLocation;

    @lombok.Getter
    @lombok.Setter
    private Material BaseBlock = Material.BREWING_STAND;

    @lombok.Getter
    @lombok.Setter
    private InventoryType Baseinv;

    @lombok.Getter
    @lombok.Setter
    private int BaseRadius = 3;

    @lombok.Getter
    @lombok.Setter
    private String ChangeFakeName;

    @lombok.Getter
    @lombok.Setter
    private int MaxDamage = 50;

    @lombok.Getter
    @lombok.Setter
    private int DeltaDamage = 5;

    @lombok.Getter
    @lombok.Setter
    private int DamageRadius = 9;

    @lombok.Getter
    @lombok.Setter
    private int StartupDisplay = 5;
    /**
     * The Carrier.
     */
    @lombok.Getter
    @lombok.Setter
    protected String carrier = null;
    /**
     * The Plant timer.
     */
    @lombok.Getter
    @lombok.Setter
    protected PlantTimer plantTimer = null;
    /**
     * The Defuse timers.
     */
    @lombok.Getter
    @lombok.Setter
    protected Map<String, DefuseTimer> defuseTimers = new HashMap<String, DefuseTimer>();
    /**
     * The Saved bombs.
     */
    @lombok.Getter
    @lombok.Setter
    Collection<Location> savedBombs;

    /**
     * Game constructor
     *
     * @param manager The plugin instance
     * @param type    The {@link GameType} of this
     */
    public Game(R6Siege manager, GameType type)
    {
        this(manager, type, true);
    }


    /**
     * Game constructor
     *
     * @param manager       The plugin instance
     * @param type          The {@link GameType} of this game
     * @param defaultPhases Whether to use the default phases (waiting and countdown)
     */
    public Game(R6Siege manager, GameType type, boolean defaultPhases)
    {
        this.manager = manager;
        this.type = type;

        this.map = manager.getMapManager().getSuitableMap(type);

        if (defaultPhases)
        {
            phases = Lists.newArrayList(
                    new LobbyWaitingPhase(this),
                    new LobbyCountdownPhase(this),
                    new PregamePhase(this)
            );
        }

        assignPhase();

        ServerUtil.register(this);
    }

    /**
     * Whether the game is ending
     */
    private boolean ending = false;

    /**
     * Ends the game
     *
     * @param players The winning players
     * @see #endGame(Player...) #endGame(Player...)
     */
    public void endGame(Collection<Player> players)
    {
        endGame(players.toArray(new Player[0]));
    }

    /**
     * Ends the game by kicking all players and unloading the map world
     * Calls GameManager#restart to restart the server.
     *
     * @param winners The players who won the game
     * @see #map #map
     * @see R6Siege#restart() R6Siege#restart()
     */
    public void endGame(Player... winners)
    {
        ending = true;

        currentPhase.endPhase();

        ServerUtil.unregister(this);

        Set<Player> gameWinners = Sets.newHashSet(winners);

        for (Player player : getPlayers(false))
        {
            if (gameWinners.contains(player))
            {
                String[] winnerMsg = getWinnerTitle();

                if (winnerMsg != null && winnerMsg.length == 2)
                {
                    MotherTitle title = new MotherTitle(winnerMsg[0], winnerMsg[1], 20, 60, 20);
                    title.send(player);
                }

                Sound winnerSound = getWinnerSound();

                if (winnerSound != null)
                {
                    MotherSound sound = new MotherSound(winnerSound, 1F, 2F);
                    sound.play(player);
                    //player.playSound(player.getLocation(), winnerSound, 1F, 2F);
                }

                if (winnerGlow)
                {
                    player.setGlowing(true);
                }
            }
            else
            {
                String[] loserMsg = getLoserTitle();

                if (loserMsg != null && loserMsg.length == 2)
                {
                    MotherTitle title = new MotherTitle(loserMsg[0], loserMsg[1], 20, 60, 20);
                    title.send(player);
                }

                Sound loserSound = getLoserSound();

                if (loserSound != null)
                {
                    MotherSound sound = new MotherSound(loserSound, 1F, 2F);
                    sound.play(player);
                }
            }
        }

        resetTeams();

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                forEachPlayer(player -> player.kickPlayer(CC.green +CC.bold + "The game is over, thanks for playing!"));
                unloadWorld();

                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        manager.restart();
                    }
                }.runTaskLater(manager, 10L);
            }
        }.runTaskLater(manager, 100L);
    }

    /**
     * Clears all scoreboard teams
     */
    private void resetTeams()
    {
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(team -> team.getEntries().forEach(team::removeEntry));
    }

    /**
     * Gets the title message displayed to the winners of a game
     * <b>Must return an array with length 2</b>
     *
     * @return The title and subtitle displayed to winners
     */
    protected String[] getWinnerTitle()
    {
        return new String[] {CC.gold +CC.bold + "WINNER", CC.green + "You won the game!"};
    }

    /**
     * Gets the sound played to winners of a game
     *
     * @return The sound played
     */
    protected Sound getWinnerSound()
    {
        return Sound.ENTITY_PLAYER_LEVELUP;
    }

    /**
     * Gets the title message displayed to losers of a game
     * <b>Must return an array with length 2</b>
     *
     * @return The title and subtitle displayed to losers
     */
    protected String[] getLoserTitle()
    {
        return new String[] {CC.red +CC.bold + "LOSS", CC.gray + "You lost the game"};
    }

    /**
     * Gets the sound played to losers of a game
     *
     * @return The sound played
     */
    protected Sound getLoserSound()
    {
        return Sound.ENTITY_ENDER_DRAGON_HURT;
    }

    /**
     * Unloads the world this game is taking place on
     */
    public final void unloadWorld()
    {
        Bukkit.unloadWorld(getMap().getWorld(), false);
    }


    /**
     * Broadcasts a message in chat announcing the winners
     * @param winners The winning players
     */
    private void broadcastWinners(Set<Player> winners)
    {
        if (winners.isEmpty())
        {
            return;
        }

        StringBuilder builder = new StringBuilder(CC.gold  +CC.bold + "Winners: " + CC.green);

        winners.forEach(winner -> builder.append(winner.getDisplayName()).append(", "));

        String raw = builder.toString();
        String winnersString = raw.substring(0, raw.length() - 1);

        broadcast(winnersString);
    }

    /**
     * Whether the game is currently in progress
     *
     * @return Whether the game is in progress
     */
    public boolean inProgress()
    {
        return currentPhase.isIngame();
    }

    /**
     * Gets whether the current {@link GamePhase} is a lobby phase
     *
     * @return Whether the game is currently joinable
     */
    boolean isLobby()
    {
        return currentPhase instanceof LobbyPhase;
    }

    /**
     * Gets whether the game is currently full
     *
     * @return Whether the game is full
     */
    boolean isFull()
    {
        return getPlayersSize() >= getType().getMaxPlayers();
    }

    /**
     * Gets whether the game is currently joinable
     * @return Whether the game is currently joinable
     */
    private boolean isJoinable()
    {
        return isLobby() && !isFull();
    }

    /**
     * Handles a player joining the game
     *
     * @param player The player who is joining
     * @throws AssertionError If the game is not in a lobby phase or is full
     * @see #isLobby() #isLobby()
     * @see #isFull() #isFull()
     */
    void handleJoin(Player player)
    {
        assert isJoinable();

        players.add(player.getUniqueId());

        if (resourcePackUrl != null)
        {
            player.sendMessage(
                    forceResourcePack ?
                            CC.red +CC.bold + "This game requires the use of a custom resource pack" :
                            CC.green + "This game recommends the use of a custom resource pack"
            );

            player.setResourcePack(resourcePackUrl);
        }

        if (joinLeaveMessages)
        {
            broadcastJoinOrLeaveMessage(player, "joined");
        }

        ((LobbyPhase) currentPhase).addPlayer(player);
    }

    /**
     * Handles a player leaving the game
     *
     * @param player The player who is leaving
     */
    public final void handleLeave(Player player)
    {
        removeFromTeam(player);

        if (players.remove(player.getUniqueId()))
        {
            if (joinLeaveMessages)
            {
                if (isLobby())
                {
                    broadcastJoinOrLeaveMessage(player, "left");
                }
                else
                {
                    broadcast(CC.yellow + player.getDisplayName() + CC.green + " left the game");
                }
            }
        }
        else
        {
            spectators.remove(player.getUniqueId());
        }
    }

    /**
     * Broadcasts a "%player% joined/left the game" message
     * @param player The player joining or leaving
     * @param action The action the player performed (joined/left)
     */
    private void broadcastJoinOrLeaveMessage(Player player, String action)
    {
        assert action.equals("joined") || action.equals("left");

        broadcast(CC.yellow + player.getDisplayName() + CC.green + " " + action + " the game " + CC.yellow + "(" + getPlayersSize() + "/" + getType().getMaxPlayers() + ")");

        if (getPlayersSize() <= getType().getMinPlayers())
        {
            broadcast(CC.yellow + "Players needed: " + CC.green + (getType().getMinPlayers() - getPlayersSize()));
        }
    }

    /**
     * Eliminates a player from the game
     *
     * @param target The player to be eliminated
     */
    protected void eliminate(Player target)
    {
        eliminate(target, null);
    }

    /**
     * Removes a player from their team in the game
     * @param player The player to remove
     */
    private void removeFromTeam(Player player)
    {
        if (this instanceof TeamGame)
        {
            ((TeamGame) this).getTeam(player).removePlayer(player);
        }
    }

    /**
     * Eliminates a player from the game
     *
     * @param target The player to be eliminated
     * @param slayer The player responsible for this player's elimination
     */
    protected void eliminate(Player target, Player slayer)
    {
        if (!inProgress())
        {
            return;
        }

        removeFromTeam(target);

        UUID id = target.getUniqueId();

        players.remove(id);
        spectators.add(id);

        target.setHealth(20);

        target.getInventory().clear();

        Bukkit.getOnlinePlayers().stream().filter(pl -> !pl.equals(target)).forEach(pl -> pl.hidePlayer(target));
        forEachPlayer(other -> other.hidePlayer(target));

        target.sendTitle(CC.red +CC.bold + "Eliminated", CC.gray + "You are now spectating", 10, 30, 10);
        target.sendMessage(CC.red +CC.bold + "You were eliminated from the game");

        String broadcast = getEliminationMessage(target, slayer);

        if (broadcast != null)
        {
            broadcast(broadcast);
        }

        Sound sound = getEliminationSound();

        if (sound != null)
        {
            forEachPlayer(player -> player.playSound(player.getLocation(), sound, 1F, 2F));
        }

        target.setGameMode(GameMode.SPECTATOR);
        target.getVelocity().multiply(new Vector(0, 0.5, 0));
    }

    /**
     * Gets the message to broadcast when a player is eliminated
     *
     * @param target The player being eliminated
     * @param slayer The player eliminating the target
     * @return The elimination message
     * @see #eliminate(Player, Player) #eliminate(Player, Player)
     */
    protected String getEliminationMessage(Player target, Player slayer)
    {
        return CC.yellow + target.getDisplayName() + CC.bold + CC.red + " was eliminated" + (slayer == null ? "" : " by " + CC.yellow + slayer.getDisplayName());
    }

    /**
     * Gets the sound played to all players when a player is eliminated
     *
     * @return The elimination sound
     */
    protected Sound getEliminationSound()
    {
        return Sound.ENTITY_LIGHTNING_BOLT_IMPACT;
    }

    /**
     * Gets whether a player is playing the game by {@link UUID}
     *
     * @param id The UUID of the player
     * @return Whether the player is playing the game
     */
    public final boolean isPlaying(UUID id)
    {
        return players.contains(id);
    }

    /**
     * Gets whether a player is playing the game
     *
     * @param player The player to test
     * @return Whether the player is playing
     */
    public final boolean isPlaying(Player player)
    {
        return isPlaying(player.getUniqueId());
    }

    /**
     * Gets whether a player is spectating the game
     * @param player The player to test
     * @return Whether the player is spectating
     */
    private boolean isSpectating(Player player)
    {
        return spectators.contains(player.getUniqueId());
    }

    /**
     * The phases of this game
     */
    private List<GamePhase> phases;

    /**
     * Adds a phase to this game
     *
     * @param phase The phase to add
     */
    protected final void addPhase(GamePhase phase)
    {
        phases.add(phase);
    }

    /**
     * Moves the game onto the next phase
     */
    public final void nextPhase()
    {
        if (!ending)
        {
            phaseIndex++;

            if (phaseIndex > phases.size() - 1)
            {
                endGame();
                return;
            }

            assignPhase();
        }
    }

    /**
     * Sets the current phase
     */
    private void assignPhase()
    {
        currentPhase = phases.get(phaseIndex);

        currentPhase.startPhase();
    }

    /**
     * The index of the current game phase
     */
    private int phaseIndex = 0;

    /**
     * The current game phase
     */
    private GamePhase currentPhase;

    /**
     * Reverts this game's phase to a lobby phase
     * <b>Only to be used when a countdown is cancelled after a player leaves</b>
     */
    public final void revertToLobby()
    {
        phaseIndex = 0;

        assignPhase();
    }

    /**
     * The players in the game
     */
    private final Set<UUID> players = new HashSet<>();

    /**
     * Gets the amount of players in the game
     *
     * @return The amount of players
     */
    public final int getPlayersSize()
    {
        return players.size();
    }

    /**
     * The players spectating the game
     */
    private final Set<UUID> spectators = new HashSet<>();

    /**
     * Broadcast with title wo spectators.
     *
     * @param topMessage    the top message
     * @param bottomMessage the bottom message
     */
    public void broadcastWithTitleWOSpectators(String topMessage, String bottomMessage) {
        broadcastWithTitle(topMessage, bottomMessage, 20, 60, 20, false);
    }

    /**
     * Broadcast with title w spectators.
     *
     * @param topMessage    the top message
     * @param bottomMessage the bottom message
     */
    public void broadcastWithTitleWSpectators(String topMessage, String bottomMessage) {
        broadcastWithTitle(topMessage, bottomMessage, 20, 60, 20, true);
    }

    /**
     * Broadcast's a title to all players and spectators in the game
     *
     * @param topMessage    Top of the Title message
     * @param bottomMessage Bottom of the Title message
     * @param in            Time for message to come in
     * @param stay          Time for message to stay
     * @param out           Time for message to leave
     * @param spectators    Whether to send the {@link MotherTitle} to spectators
     */
    public void broadcastWithTitle(String topMessage, String bottomMessage, int in, int stay, int out, boolean spectators) {
        MotherTitle title = new MotherTitle(topMessage, bottomMessage, in, stay, out);
        Set<Player> players = getPlayers(spectators);

        players.forEach(player -> title.send(player));
    }

    /**
     * Broadcasts a message to all players and spectators in the game
     *
     * @param msg The message to broadcast
     */
    public void broadcast(String msg)
    {
        broadcast(msg, true);
    }

    /**
     * Broadcasts a message to all players and spectators in the game
     *
     * @param msg        The message to broadcast
     * @param spectators Whether to send the message to spectators
     */
    public void broadcast(String msg, boolean spectators)
    {
        Set<Player> players = getPlayers(spectators);

        players.forEach(player -> player.sendMessage(msg));
    }

    /**
     * Executes an action for every player in the game
     *
     * @param action The consumer to run
     */
    public void forEachPlayer(Consumer<Player> action)
    {
        forEachPlayer(action, true);
    }

    /**
     * Executes an action for every player in the game
     *
     * @param consumer   The consumer to run
     * @param spectators Whether to execute the action on spectators
     */
    public void forEachPlayer(Consumer<Player> consumer, boolean spectators)
    {
        Set<Player> players = getPlayers(spectators);

        players.forEach(consumer::accept);
    }

    /**
     * Gets a set of players in the game
     *
     * @param spectators Whether to include spectators in the set
     * @return The set of players in the game
     */
    public Set<Player> getPlayers(boolean spectators)
    {
        Set<Player> players;

        players =
                spectators ?
                        ServerUtil.onlinePlayerSet(this.players, this.spectators) :
                        ServerUtil.onlinePlayerSet(this.players);

        return players;
    }

    /**
     * On bomb spawn.
     *
     * @param e the e
     */
    @EventHandler
    public void onBombSpawn(ItemSpawnEvent e) {
        Material material = e.getEntity().getItemStack().getType();
        if(material != getBombBlock()) {
            return;
        }
        if(carrier == null) {
            e.setCancelled(true);
            ConsoleUtil.sendConsoleMessage("Bomb Despawn Cancelled");
        }else {
            ConsoleUtil.consoleError("Bomb Despawn Allowed because " + carrier + " has the bomb");
        }
        broadcastWithTitleWSpectators("Bomb Has Spawned", "Game Has Started!");

        createBombHologram(e.getLocation());
    }

    /**
     * On chat.
     *
     * @param event the event
     */
    /* Events */
    @EventHandler (priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();

        if (isSpectating(player) && !player.isOp() && !specChat)
        {
            event.setCancelled(true);
            player.sendMessage(CC.gray + "Spectators may not talk");
        }
    }

    /**
     * On command process.
     *
     * @param event the event
     */
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCommandProcess(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].substring(1);

        if (isSpectating(player) && !specCmd)
        {
            if (!player.isOp() && !command.equalsIgnoreCase("leave"))
            {
                event.setCancelled(true);

                player.sendMessage(CC.gray + "You cannot use this command as a spectator");
                player.sendMessage(CC.gray + "Type " + CC.green + "/leave " + CC.gray + " to leave the game");

                return;
            }

            player.kickPlayer(CC.green +CC.bold + "Thanks for playing!");
        }
    }

    /**
     * On death damage.
     *
     * @param event the event
     */
    @EventHandler
    public void onDeathDamage(EntityDamageEvent event)
    {
        if (niceDeath && event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();

            if (player.getHealth() - event.getDamage() <= 0.0D)
            {
                // The player would die from this hit
                event.setDamage(0);

                Player killer = null;

                // Eliminate the player and drop their items
                if (event instanceof EntityDamageByEntityEvent)
                {
                    EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) event;

                    if (edbe.getDamager() instanceof Player)
                    {
                        killer = (Player) edbe.getDamager();
                    }
                }

                if (deathDropItems)
                {
                    spawnItems(player);
                }

                eliminate(player, killer);
            }
        }
    }

    /**
     * Spawns a player's items on death naturally
     * @param player The player
     */
    private void spawnItems(Player player)
    {
        World world = player.getWorld();
        Location deathLoc = player.getLocation();
        ItemStack[] items = player.getInventory().getContents();

        Arrays.stream(items).filter(Objects::nonNull).forEach(item -> world.dropItemNaturally(deathLoc, item));
    }

    /**
     * Gets the title of the sidebar to send to players
     *
     * @return The sidebar title
     */
    public String getSidebarTitle()
    {
        return CC.aqua + CC.bold + getType().toString();
    }

    /**
     * Gets the sidebar to display to a player
     *
     * @param player The player
     * @return The sidebar to display
     */
    public List<String> getSidebar(Player player)
    {
        return currentPhase.getSidebar(player);
    }

    /**
     * Resets number of blank lines on the sidebar
     */
    public void resetBlanks()
    {
        currentPhase.resetBlanks();
    }

    /**
     * Remove holograms.
     */
    public void removeHolograms() {
        R6Siege.holographicDisplay().removeHologram(hologramID.get());
        this.hologramID.set(0);
    }

    /**
     * Create base hologram.
     *
     * @param loc the loc
     */
    protected void createBaseHologram(Location loc) {
        Set<Location> set = new HashSet<Location>();
        set.add(loc);
        createBaseHologram(set);
    }

    /**
     * Create base hologram.
     *
     * @param locations the locations
     */
    protected void createBaseHologram(Set<Location> locations) {
        for (Location loc : locations) {
            removeHolograms();
            int id = R6Siege.holographicDisplay().createBaseHologram(loc);
            this.hologramID.set(id);
        }
    }

    /**
     * Create bomb hologram atomic integer.
     *
     * @param loc the loc
     * @return the atomic integer
     */
    protected AtomicInteger createBombHologram(Location loc) {
        removeHolograms();
        int id = R6Siege.holographicDisplay().createBombHologram(loc);
        hologramID.set(id);
        return hologramID;
    }

    /**
     * Used to find a nearby BaseBlock. <br/><br/>
     * <p>
     * Used by assignBases() and setbase() command. <br/><br/>
     *
     * @param loc Scan blocks near this location.
     * @return Does not return null: If no block is found, the original loc param is returned.
     */
    public Location getExactLocation(Location loc) {
        int length = 5;
        Location base_loc = null;
        ConsoleUtil.debug("Location loc = " + loc.toString());

        int x1 = loc.getBlockX() - length;
        int y1 = loc.getBlockY() - length;
        int z1 = loc.getBlockZ() - length;

        int x2 = loc.getBlockX() + length;
        int y2 = loc.getBlockY() + length;
        int z2 = loc.getBlockZ() + length;

        World world = loc.getWorld();
        ConsoleUtil.debug("World world = " + world.getName());

        // Loop over the cube in the x dimension.
        for (int xPoint = x1; xPoint <= x2; xPoint++) {
            // Loop over the cube in the y dimension.
            for (int yPoint = y1; yPoint <= y2; yPoint++) {
                // Loop over the cube in the z dimension.
                for (int zPoint = z1; zPoint <= z2; zPoint++) {
                    // Get the block that we are currently looping over.
                    Block currentBlock = world.getBlockAt(xPoint, yPoint, zPoint);
                    // Set the block to type 57 (Diamond block!)
                    if (currentBlock.getType() == this.BaseBlock) {
                        base_loc = new Location(world, xPoint, yPoint, zPoint);
                        ConsoleUtil.debug("base_loc = " + base_loc.toString());
                        return base_loc;
                    }
                }
            }
        }
        return loc;
    } // END OF getExactLocation()

    /**
     * Gets plant time.
     *
     * @return the plant time
     */
    public int getPlantTime() {
        return PlantTime;
    }

    /**
     * Sets plant time.
     *
     * @param PlantTime the plant time
     */
    public void setPlantTime(int PlantTime) {
        this.PlantTime = PlantTime;
    }

    /**
     * Gets detonation time.
     *
     * @return the detonation time
     */
    public int getDetonationTime() {
        return DetonationTime;
    }

    /**
     * Sets detonation time.
     *
     * @param DetonationTime the detonation time
     */
    public void setDetonationTime(int DetonationTime) {
        this.DetonationTime = DetonationTime;
    }

    /**
     * Gets defuse time.
     *
     * @return the defuse time
     */
    public int getDefuseTime() {
        return DefuseTime;
    }

    /**
     * Sets defuse time.
     *
     * @param DefuseTime the defuse time
     */
    public void setDefuseTime(int DefuseTime) {
        this.DefuseTime = DefuseTime;
    }

    /**
     * Gets bomb block.
     *
     * @return the bomb block
     */
    public static Material getBombBlock() {
        return BombBlock;
    }

    /**
     * Sets bomb block.
     *
     * @param type the type
     */
    public void setBombBlock(Material type) {
        this.BombBlock = type;
    }

    /**
     * Gets base block.
     *
     * @return the base block
     */
    public Material getBaseBlock() {
        return BaseBlock;
    }

    /**
     * Sets base block.
     *
     * @param type the type
     */
    public void setBaseBlock(Material type) {
        this.BaseBlock = type;
    }

    /**
     * Gets baseinv.
     *
     * @return the baseinv
     */
    public InventoryType getBaseinv() {
        return Baseinv;
    }

    /**
     * Sets baseinv.
     *
     * @param type the type
     */
    public void setBaseinv(InventoryType type) {
        this.Baseinv = type;
    }

    /**
     * Gets fake name.
     *
     * @return the fake name
     */
    public String getFakeName() {
        return "Bombs Planted Defused";
    }

    /**
     * Gets max damage.
     *
     * @return the max damage
     */
    public int getMaxDamage() {
        return MaxDamage;
    }

    /**
     * Sets max damage.
     *
     * @param max the max
     */
    public void setMaxDamage(int max) {
        this.MaxDamage = max;
    }

    /**
     * Gets delta damage.
     *
     * @return the delta damage
     */
    public int getDeltaDamage() {
        return DeltaDamage;
    }

    /**
     * Sets delta damage.
     *
     * @param delta the delta
     */
    public void setDeltaDamage(int delta) {
        this.DeltaDamage = delta;
    }

    /**
     * Gets damage radius.
     *
     * @return the damage radius
     */
    public int getDamageRadius() {
        return DamageRadius;
    }

    /**
     * Sets damage radius.
     *
     * @param radius the radius
     */
    public void setDamageRadius(int radius) {
        this.DamageRadius = radius;
    }

    /**
     * Gets startup display.
     *
     * @return the startup display
     */
    public int getStartupDisplay() {
        return StartupDisplay;
    }

    /**
     * Sets startup display.
     *
     * @param num the num
     */
    public void setStartupDisplay(int num) {
        this.StartupDisplay = num;
    }

    /**
     * Gets base radius.
     *
     * @return the base radius
     */
    public double getBaseRadius() {
        return this.BaseRadius;
    }

    /**
     * Gets timer sound.
     *
     * @return the timer sound
     */
    public Sound getTimerSound() {
        return TimerSound;
    }

    /**
     * Sets timer sound.
     *
     * @param sound the sound
     */
    public void setTimerSound(Sound sound) {
        this.TimerSound = sound;
    }

    /**
     * Gets plant defuse noise.
     *
     * @return the plant defuse noise
     */
    public Sound getPlantDefuseNoise() {
        return PlantDefuseNoise;
    }

    /**
     * Sets plant defuse noise.
     *
     * @param sound the sound
     */
    public void setPlantDefuseNoise(Sound sound) {
        this.PlantDefuseNoise = sound;
    }

    /**
     * Play timer sound.
     */
    public void playTimerSound() {
        Sound sound = getTimerSound();

        if (sound != null)
        {
            forEachPlayer(player -> player.playSound(player.getLocation(), sound, 1F, 2F));
        }
    }

    /**
     * Play plant defuse noise.
     *
     * @param loc the loc
     */
    public void playPlantDefuseNoise(Location loc) {
        Sound sound = getPlantDefuseNoise();

        if (sound != null)
        {
            forEachPlayer(player -> player.playSound(loc, sound, 1F, 2F));
        }
    }

    /**
     * On death.
     *
     * @param event the event
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        //Bukkit.broadcastMessage(event.getEntity().getDisplayName() + " Has Died By ");

        if (!deathDropItems)
        {
            event.setKeepInventory(true);
        }

        event.setDeathMessage(null);
    }

    /**
     * On block place.
     *
     * @param event the event
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        if (!blockPlace)
        {
            event.setBuild(false);
        }
    }

    /**
     * On block break.
     *
     * @param event the event
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        if (!blockBreak)
        {
            event.setCancelled(true);
        }
    }

    /**
     * On item drop.
     *
     * @param event the event
     */
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event)
    {
        if (!itemDrop)
        {
            event.setCancelled(true);
        }
    }

    /**
     * On damage.
     *
     * @param event the event
     */
    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (!damage)
        {
            event.setCancelled(true);
        }
    }

    /**
     * On damage.
     *
     * @param event the event
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if(event.getEntity().isDead()) {
            broadcast(event.getDamager().getCustomName() + " Has Killed " + event.getEntity().getCustomName());
        }

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player)
        {
            if (!pvp)
            {
                event.setCancelled(true);
            }
        }
    }

    /**
     * On entity spawn.
     *
     * @param event the event
     */
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event)
    {
        if (!entitySpawn)
        {
            event.setCancelled(true);
        }
    }

    /**
     * On food level change.
     *
     * @param event the event
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        if (!hunger)
        {
            event.setCancelled(true);
        }
    }

    /**
     * On resource pack status.
     *
     * @param event the event
     */
    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event)
    {
        Player player = event.getPlayer();
        Status status = event.getStatus();

        if (status == Status.ACCEPTED)
        {
            //player.sendMessage(CC.green + "Sending you the resource pack...");
            MotherTitle title = new MotherTitle(CC.green + "Sending you the required resource pack..", "Please download to play!", 20, 60, 20);
            title.send(player);
        }
        else if (status == Status.SUCCESSFULLY_LOADED)
        {
            player.sendMessage(CC.green + "The resource pack was loaded successfully!");
        }
        else if (status == Status.DECLINED && forceResourcePack)
        {
            player.kickPlayer(CC.red + "You must accept the resource pack to play this game");
        }
        else if (status == Status.FAILED_DOWNLOAD && forceResourcePack)
        {
            player.kickPlayer(CC.red + "There was an error downloading the required resource pack");
        }
    }
}
