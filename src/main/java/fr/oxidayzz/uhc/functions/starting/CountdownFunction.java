package fr.oxidayzz.uhc.functions.starting;

import fr.oxidayzz.uhc.Main;
import fr.oxidayzz.uhc.functions.lobby.TeleportLobbyPlayer;
import fr.oxidayzz.uhc.managers.GameStateManager;
import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownFunction {
    static void startCountdown() {
        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                for (Player player : PlayersManagers.gameList) {
                    if (!PlayersManagers.gameList.contains(player)) {
                        this.cancel();
                        Bukkit.broadcastMessage("§cUn joueur a quitté la partie, partie annulé !");
                        TeleportLobbyPlayer.teleportLobbyPlayer(player);
                        return;
                    }
                }

                for (Player player: PlayersManagers.gameList) {
                    if (player != null) {
                        player.setExp((float) countdown / 5);
                        player.setLevel(countdown);

                        if (countdown <= 3) {
                            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
                            player.sendTitle("§cStarting :", "§6" + countdown + " secondes");
                        }
                    }
                }

                countdown--;

                if (countdown < 0) {
                    this.cancel();

                    for (Player player : PlayersManagers.gameList) {
                        if (player != null) {
                            player.sendTitle("§aStart !", "");
                            player.getInventory().clear();
                            player.playSound(player.getLocation(), Sound.GLASS, 1, 1);
                            player.setExp(0);
                            player.setLevel(0);
                            GameStateManager.setCurrentPhase(GameStateManager.INGAME);
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }

}
