package fr.oxidayzz.uhc.managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayersManagers {

    public static ArrayList<Player> gameList = new ArrayList<>();
    public static ArrayList<Player> spectators = new ArrayList<>();
    public static ArrayList<UUID> moderators = new ArrayList<>();
    public static ArrayList<UUID> bypass = new ArrayList<>();


    public static void addPlayerGameList(Player player) {
        gameList.add(player);
        String prefix = MessagesManagers.getMessage("prefix", null);
        String joinGameMessageServer = MessagesManagers.getMessage("joinGameMessageServer", player);
        Bukkit.broadcastMessage(prefix + joinGameMessageServer);
        String joinGameMessagePlayer = MessagesManagers.getMessage("joinGameMessagePlayer", null);
        player.sendMessage(prefix + joinGameMessagePlayer);
    }
    public static void removePlayerGameList(Player player) {
        gameList.remove(player);
        String prefix = MessagesManagers.getMessage("prefix", null);
        String quitGameMessageServer = MessagesManagers.getMessage("quitGameMessageServer", player);
        Bukkit.broadcastMessage(prefix + quitGameMessageServer);
        String quitGameMessagePlayer = MessagesManagers.getMessage("quitGameMessagePlayer", null);
        player.sendMessage(prefix + quitGameMessagePlayer);
    }
    public static void addSpectatorPlayer(Player player) {
        spectators.add(player);
        String prefix = MessagesManagers.getMessage("prefix", null);
        String joinSpectatorMessageServer = MessagesManagers.getMessage("joinSpectatorMessageServer", player);
        Bukkit.broadcastMessage(prefix + joinSpectatorMessageServer);
        String joinSpectatorMessagePlayer = MessagesManagers.getMessage("joinSpectatorMessagePlayer", null);
        player.sendMessage(prefix + joinSpectatorMessagePlayer);
    }
    public static void removeSpectatorPlayer(Player player) {
        spectators.remove(player);
        String prefix = MessagesManagers.getMessage("prefix", null);
        String quitSpectatorMessageServer = MessagesManagers.getMessage("quitSpectatorMessageServer", player);
        Bukkit.broadcastMessage(prefix + quitSpectatorMessageServer);
        String quitSpectatorMessagePlayer = MessagesManagers.getMessage("quitSpectatorMessagePlayer", null);
        player.sendMessage(prefix + quitSpectatorMessagePlayer);
    }
    public static void addModeratorPlayer(Player player) {
        moderators.add(player.getUniqueId());
        String prefix = MessagesManagers.getMessage("prefix", null);
        String joinModeratorMessageServer = MessagesManagers.getMessage("joinModeratorMessageServer", player);
        Bukkit.broadcastMessage(prefix + joinModeratorMessageServer);
        String joinModeratorMessagePlayer = MessagesManagers.getMessage("joinModeratorMessagePlayer", null);
        player.sendMessage(prefix + joinModeratorMessagePlayer);
        player.playSound(player.getLocation(), Sound.ENDERMAN_HIT, 1, 1);
    }
    public static void removeModeratorPlayer(Player player) {
        moderators.remove(player.getUniqueId());
        String prefix = MessagesManagers.getMessage("prefix", null);
        String quitModeratorMessageServer = MessagesManagers.getMessage("quitModeratorMessageServer", player);
        Bukkit.broadcastMessage(prefix + quitModeratorMessageServer);
        String quitModeratorMessagePlayer = MessagesManagers.getMessage("quitModeratorMessagePlayer", null);
        player.sendMessage(prefix + quitModeratorMessagePlayer);
        player.playSound(player.getLocation(), Sound.ENDERMAN_HIT, 1, 1);
    }
    public static void addBypassPlayer(Player player) {
        bypass.add(player.getUniqueId());
        String prefix = MessagesManagers.getMessage("prefix", null);
        String joinBypassMessagePlayer = MessagesManagers.getMessage("joinBypassMessagePlayer", null);
        player.sendMessage(prefix + joinBypassMessagePlayer);
        player.playSound(player.getLocation(), Sound.CREEPER_HISS, 1, 1);
    }
    public static void removeBypassPlayer(Player player) {
        bypass.remove(player.getUniqueId());
        String prefix = MessagesManagers.getMessage("prefix", null);
        String quitBypassMessagePlayer = MessagesManagers.getMessage("quitBypassMessagePlayer", null);
        player.sendMessage(prefix + quitBypassMessagePlayer);
        player.playSound(player.getLocation(), Sound.CREEPER_HISS, 1, 1);
    }

}
