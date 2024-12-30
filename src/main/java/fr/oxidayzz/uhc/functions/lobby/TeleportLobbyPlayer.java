package fr.oxidayzz.uhc.functions.lobby;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class TeleportLobbyPlayer {

    public static void teleportLobbyPlayer(Player player) {
        if(Bukkit.getWorld("Lobby") == null) {
            Bukkit.createWorld(new WorldCreator("Lobby"));
            teleportLobbyPlayer(player);
        }
        World worldPlayer = player.getWorld();
        World worldLobby = Bukkit.getWorld("Lobby");
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
        if(worldPlayer == worldLobby) {
            Location location = new Location(worldLobby, 0.5, 65, 0.5, -180, 1);
            player.teleport(location);
        }else {
            Location location = new Location(worldLobby, 0.5, 65, 0.5, -180, 1);
            player.teleport(location);
        }
    }

    public static void teleportLobbyAllPlayer() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            teleportLobbyPlayer(player);
        }
    }

}
