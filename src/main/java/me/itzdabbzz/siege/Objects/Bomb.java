package me.itzdabbzz.siege.Objects;

import me.itzdabbzz.siege.R6Siege;
import me.itzdabbzz.siege.minigame.game.game.Game;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Bomb implements Item {

    R6Siege plugin;
    int pickupDelay;
    ItemStack bomb;
    Location loc;
    UUID uuid;

    public Bomb(PlayerDeathEvent e) {
        this.plugin = (R6Siege) Bukkit.getPluginManager().getPlugin("BombArena");
        pickupDelay = 0;
        bomb = new ItemStack(Game.getBombBlock());
        loc = e.getEntity().getPlayer().getLocation();
        uuid = UUID.randomUUID();
    }

    public Bomb(PlayerDeathEvent e, int i) {
        this.plugin = (R6Siege) Bukkit.getPluginManager().getPlugin("BombArena");
        pickupDelay = i;
        bomb = new ItemStack(Game.getBombBlock());
        loc = e.getEntity().getPlayer().getLocation();
        uuid = UUID.randomUUID();
    }


    @Override
    public ItemStack getItemStack() {
        return bomb;
    }

    @Override
    public void setItemStack(ItemStack is) {
        this.bomb = is;
    }

    @Override
    public int getPickupDelay() {
        return pickupDelay;
    }

    @Override
    public void setPickupDelay(int i) {
        pickupDelay = i;
    }

    @Override
    public Location getLocation() {
        return loc;
    }

    @Override
    public Location getLocation(Location lctn) {
        return loc;
    }

    @Override
    public void setVelocity(Vector vector) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector getVelocity() {
        return new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public boolean isOnGround() {
        return true;
    }

    @Override
    public World getWorld() {
        return loc.getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {

    }

    @Override
    public boolean teleport(Location lctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teleport(Location lctn, PlayerTeleportEvent.TeleportCause tc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teleport(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause tc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Entity> getNearbyEntities(double d, double d1, double d2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFireTicks() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMaxFireTicks() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFireTicks(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendMessage(String[] messages) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public void setPersistent(boolean persistent) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Entity getPassenger() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setPassenger(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Entity> getPassengers() {
        return null;
    }

    @Override
    public boolean addPassenger(Entity passenger) {
        return false;
    }

    @Override
    public boolean removePassenger(Entity passenger) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eject() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getFallDistance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFallDistance(float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent ede) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public int getTicksLived() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTicksLived(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void playEffect(EntityEffect ee) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityType getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInsideVehicle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean leaveVehicle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entity getVehicle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public void setGlowing(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public void setInvulnerable(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isSilent() {
        return false;
    }

    @Override
    public void setSilent(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean gravity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPortalCooldown() {
        return 0;
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> getScoreboardTags() {
        return null;
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        return false;
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        return false;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public BlockFace getFacing() {
        return null;
    }

    @Override
    public Pose getPose() {
        return null;
    }

    @Override
    public Spigot spigot() {
        return null;
    }


    @Override
    public void setMetadata(String string, MetadataValue mv) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<MetadataValue> getMetadata(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasMetadata(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeMetadata(String string, Plugin plugin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isPermissionSet(String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return false;
    }

    @Override
    public boolean hasPermission(String name) {
        return false;
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return false;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
