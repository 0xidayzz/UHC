package fr.oxidayzz.uhc.listeners;

import fr.oxidayzz.uhc.functions.lobby.TeleportLobbyPlayer;
import fr.oxidayzz.uhc.managers.GameStateManager;
import fr.oxidayzz.uhc.managers.MessagesManagers;
import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinPlayer implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        if(GameStateManager.isGame(GameStateManager.LOBBY)) {
            TeleportLobbyPlayer.teleportLobbyPlayer(player);
            PlayersManagers.addPlayerGameList(player);
        }
    }

}
