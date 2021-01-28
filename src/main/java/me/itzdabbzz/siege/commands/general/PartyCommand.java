package me.itzdabbzz.siege.commands.general;

import me.itzdabbzz.siege.Managers.PlayerManager;
import me.itzdabbzz.siege.Objects.Party;
import me.itzdabbzz.siege.Objects.PlayerProfile;
import me.itzdabbzz.siege.chat.ChatManager;
import me.itzdabbzz.siege.command.Command;
import me.itzdabbzz.siege.command.CommandExecutor;
import me.itzdabbzz.siege.command.argument.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A basic command that takes no arguments.
 */
public class PartyCommand extends Command {

    public PartyCommand() {
        super("party");
        super.setDescription("Party System!!");
    }

    private Player findPlayer(final String name) {
        for (Player p : Bukkit.getOnlinePlayers())
            if (p.getName().equalsIgnoreCase(name))
                return p;
        return null;
    }

    public void leaveParty(PlayerProfile player, Player sender) {
        if (player.getParty() != null) {
            Party party = player.getParty();
            party.removePlayer(sender);
            sender.sendMessage(ChatManager.success("You have left party!"));
        }
        else {
            sender.sendMessage(ChatManager.error("You are not in party!"));
        }
    }

    public void joinParty(PlayerProfile player, Player sender, PlayerProfile inviteep, Player invitee) {
        if (player.getParty() != null) {
            if (player.getParty().isOwner(sender)) {
                Player players = this.findPlayer(invitee.getName());
                if (players != null) {
                    if (player.getParty() == null) {
                        sender.sendMessage(ChatManager.success("Adding player "
                                + players.getDisplayName() + " to party!"));
                        player.getParty().addPlayer(
                                players);
                        inviteep.setParty(
                                player.getParty());
                    }
                    else {
                        sender.sendMessage(ChatManager.error("Specified player is in another party!"));
                    }
                }
                else {
                    sender.sendMessage(ChatManager.error("Player not found!"));
                }
            }
            else {
                sender.sendMessage(ChatManager.error("Only party owner can invite players."));
            }
        }
        else {
            this.createParty(player, sender);
            this.joinParty(player, sender, inviteep, invitee);
        }
    }

    public void createParty(PlayerProfile player, Player sender) {
        if (player.getParty() == null) {
            player.setParty(new Party(sender));
            player.getParty().addPlayer(sender);
        }
        else {
            sender.sendMessage(ChatManager.error("You have to create party first! Type /party create!"));
        }
    }

    @CommandExecutor
    public void run(Player sender, String type,@Optional Player p2) {
        PlayerProfile player = PlayerManager.getProfiles().get(sender.getUniqueId());
        PlayerProfile player2 = PlayerManager.getProfiles().get(p2.getUniqueId());


        if(type.equals("join")) {
            joinParty(player, sender, player2, p2);
        } else if(type.equals("leave")) {
            this.leaveParty(player, sender);
        }else if(type.equals("create")) {
            createParty(player, sender);
        }else if(type.equals("kick")) {
                if (player.getParty() != null) {
                    Party p = player.getParty();
                    if (p.isOwner(sender)) {
                        Player p3 = this.findPlayer(p2.getName());
                        if (p3 != null) {
                            if (p.contains(p3)) {
                                p3.sendMessage(ChatManager.success("You have been kicked from the party!"));
                                sender.sendMessage(ChatManager.success("Player "
                                        + p3.getName() + " has been kicked!"));
                                p.removePlayer(p3);
                            }
                            else {
                                sender.sendMessage(ChatManager.error("Player not found in this party!"));
                            }
                        }
                        else {
                            sender.sendMessage(ChatManager.error("Player not found!"));
                        }
                    }
                    else {
                        sender.sendMessage(ChatManager.error("You are not owner of this party!"));
                    }
                }
                else {
                    sender.sendMessage(ChatManager.error("You are not in party!"));
                }
            }
    }

}