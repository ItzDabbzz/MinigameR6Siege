package me.itzdabbzz.siege.Objects;

import me.itzdabbzz.siege.Managers.PlayerManager;
import org.bukkit.entity.Player;

/**
 * All settings.
 *
 * @author Mato Kormuth
 *
 */
public enum Settings {
    /**
     * If the music would be played at the end of music.
     */
    ENDROUND_MUSIC,
    /**
     * Chat has sounds, when player name is in it.
     */
    CHAT_SOUNDS;

    public boolean hasEnabled(final Player player) {
        return PlayerManager.getProfiles().get(player.getUniqueId()).getSetting(this);
    }
}