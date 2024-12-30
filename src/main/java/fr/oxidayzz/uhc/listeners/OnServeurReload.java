package fr.oxidayzz.uhc.listeners;

import fr.oxidayzz.uhc.functions.lobby.TeleportLobbyPlayer;
import fr.oxidayzz.uhc.managers.GameStateManager;
import fr.oxidayzz.uhc.managers.MessagesManagers;
import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class OnServeurReload implements Listener {

    @EventHandler
    public void onReloadServeur(PluginEnableEvent e) {
        MessagesManagers.loadMessages();
        for(Player player : Bukkit.getOnlinePlayers()) {
            PlayersManagers.addPlayerGameList(player);
            if(GameStateManager.isGame(GameStateManager.LOBBY)) {
                TeleportLobbyPlayer.teleportLobbyPlayer(player);
            }
        }
    }

}
