package fr.oxidayzz.uhc.functions.starting;

import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.entity.Player;

public class StartFunction {

    public static void start(int tailleBorder) {
        CountdownFunction.startCountdown();
        for(Player player : PlayersManagers.gameList) {
            TeleportPlayerInGame.teleportPlayersInGame(player);
        }
        BorderFunction.setupBorder(tailleBorder);
    }

}
