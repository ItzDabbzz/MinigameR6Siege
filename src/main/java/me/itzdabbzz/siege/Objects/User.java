package me.itzdabbzz.siege.Objects;

import me.itzdabbzz.siege.R6Siege;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class User {

    private int id;
    private UUID uuid;
    public String name;
    private String lastIP;
    private long lastLongin;
    private long firstLogin;
    private long onlineTime;
    public Set<Integer> invites;
    private double kd;
    private long kills;
    private long deaths;
    private long killAssits;
    private int wins;
    private int losses;
    private int revives;
    private int headshots;
    private String atk;
    private String def;

    public User(UUID uuid, String name, String ip, long lastLongin, long firstLogin, long onlineTime, Set<Integer> invites, double kd, long kills, long deaths, long killAssits, int wins, int losses, int revives) {
        this.id = -1;
        this.uuid = uuid;
        this.name = name;
        this.lastIP = ip;
        this.lastLongin = lastLongin;
        this.firstLogin = firstLogin;
        this.onlineTime = onlineTime;
        this.invites = invites;
        this.kd = kd;
        this.kills = kills;
        this.deaths = deaths;
        this.killAssits = killAssits;
        this.wins = wins;
        this.losses = losses;
        this.revives = revives;
        this.headshots = 0;
        this.atk = "No Op";
        this.def = "No Op";
    }

    public User(int id, UUID uuid, String name, String lastIP, long lastLongin, long firstLogin, long onlineTime, Set<Integer> invites, double kd, long kills, long deaths, long killAssits, int wins, int losses, int revives, int headshots, String atk, String def) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.lastIP = lastIP;
        this.lastLongin = lastLongin;
        this.firstLogin = firstLogin;
        this.onlineTime = onlineTime;
        this.invites = invites;
        this.kd = kd;
        this.kills = kills;
        this.deaths = deaths;
        this.killAssits = killAssits;
        this.wins = wins;
        this.losses = losses;
        this.revives = revives;
        this.headshots = headshots;
        this.atk = atk;
        this.def = def;
    }

    public User(UUID uuid, String name, String ip) {
        this.id = -1;
        this.uuid = uuid;
        this.name = name;
        this.lastIP = ip;
        this.lastLongin = System.currentTimeMillis();
        this.firstLogin = System.currentTimeMillis();
        this.onlineTime = 0L;
        this.invites = Collections.singleton(0);
        this.kd = 00.00;
        this.kills = 0;
        this.deaths = 0;
        this.killAssits = 0;
        this.wins = 0;
        this.losses = 0;
        this.revives = 0;
        this.headshots = 0;
        this.atk = "No Op";
        this.def = "No Op";
    }

    public User(UUID uuid, String name, String ip, Date lastSeen) {
        this.id = -1;
        this.uuid = uuid;
        this.name = name;
        this.lastIP = ip;
        this.lastLongin = lastSeen.getTime();
        this.firstLogin = getFirstLogin();
        this.onlineTime = getOnlineTime();
        this.invites = Collections.singleton(0);
        this.kd = getKd();
        this.kills = getKills();
        this.deaths = getDeaths();
        this.killAssits = getKillAssits();
        this.wins = getWins();
        this.losses = getLosses();
        this.revives = getRevives();
        this.headshots = getHeadshots();
        this.atk = getAtk();
        this.def = getDef();
    }

    public User(Player player) {
        this.id = -1;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.lastIP = player.getAddress().toString();
        this.lastLongin = System.currentTimeMillis();
        this.firstLogin = System.currentTimeMillis();
        this.onlineTime = 0L;
        this.invites = Collections.singleton(0);
        this.kd = 00.00;
        this.kills = 0;
        this.deaths = 0;
        this.killAssits = 0;
        this.wins = 0;
        this.losses = 0;
        this.revives = 0;
        this.headshots = 0;
        this.atk = "No Op";
        this.def = "No Op";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public long getLastLongin() {
        return lastLongin;
    }

    public void setLastLongin(long lastLongin) {
        this.lastLongin = lastLongin;
    }

    public long getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(long firstLogin) {
        this.firstLogin = firstLogin;
    }

    public long getOnlineTime() {
        return getOnlineTime(true);
    }

    public long getOnlineTime(boolean live) {
        if(live) {
            User player = getOnlinePlayer();
            if(player != null) {
                long yet = (System.currentTimeMillis()-lastLongin);
                if(yet > 0) return  onlineTime+yet;
            }
        }
        return onlineTime;
    }

    /**
     *
     * @return if this player is online or not
     */
    public boolean isOnline(){
        return getOnlinePlayer() != null;
    }

    /**
     *
     * @return The online player which the same uuid
     */
    public User getOnlinePlayer(){
        //return R6Siege.getInstance().getUserManager().getOnlinePlayer(this.uuid);
        return null;
    }

    public void setOnlineTime(long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Set<Integer> getInvites() {
        return invites;
    }

    public void setInvites(Set<Integer> invites) {
        this.invites = invites;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getKillAssits() {
        return killAssits;
    }

    public void setKillAssits(long killAssits) {
        this.killAssits = killAssits;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getRevives() {
        return revives;
    }

    public void setRevives(int revives) {
        this.revives = revives;
    }

    public int getHeadshots() {
        return headshots;
    }

    public void setHeadshots(int headshots) {
        this.headshots = headshots;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
