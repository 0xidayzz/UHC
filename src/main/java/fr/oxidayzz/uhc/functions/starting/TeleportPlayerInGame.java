package fr.oxidayzz.uhc.functions.starting;

import fr.oxidayzz.uhc.Main;
import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TeleportPlayerInGame {

    public static void teleportPlayersInGame(Player player) {

        if(Bukkit.getWorld("Game") == null) {
            Bukkit.createWorld(new WorldCreator("Game"));
            teleportPlayersInGame(player);
        }
        World world = Bukkit.getWorld("Game");

        if(PlayersManagers.gameList.contains(player)) {
            Random random = new Random();
            double x = random.nextDouble() * world.getWorldBorder().getSize() - world.getWorldBorder().getSize() / 2;
            double z = random.nextDouble() * world.getWorldBorder().getSize() - world.getWorldBorder().getSize() / 2;

            Location randomLocation = new Location(world, x+0.5, 150, z+0.5);
            player.teleport(randomLocation);
            CageFunction.createCage(randomLocation, world, player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    CageFunction.destroyCage(randomLocation, world);
                }
            }.runTaskLater(Main.getInstance(), 20 * 5);

        }else if(PlayersManagers.spectators.contains(player)){
            player.setGameMode(GameMode.SPECTATOR);
            Location location = new Location(world, 0, 100, 0);
            player.teleport(location);
        }else if(PlayersManagers.moderators.contains(player.getUniqueId())) {
            player.setGameMode(GameMode.SPECTATOR);
            Location location = new Location(world, 0, 100, 0);
            player.teleport(location);
        }else if(PlayersManagers.bypass.contains(player.getUniqueId())) {
            PlayersManagers.removeBypassPlayer(player);
        }

    }
}
