package fr.oxidayzz.uhc.commands;

import fr.oxidayzz.uhc.Main;
import fr.oxidayzz.uhc.functions.lobby.TeleportLobbyPlayer;
import fr.oxidayzz.uhc.functions.starting.StartFunction;
import fr.oxidayzz.uhc.managers.GameStateManager;
import fr.oxidayzz.uhc.managers.MessagesManagers;
import fr.oxidayzz.uhc.managers.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class uhc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = MessagesManagers.getMessage("prefix", null);

        if(!(sender instanceof Player)) {
            String notPlayer = MessagesManagers.getMessage("senderIsNotPlayer", null);
            sender.sendMessage(prefix + notPlayer);
            return false;
        }
        Player player = (Player) sender;
        if(label.equalsIgnoreCase("uhc")) {
            if(!player.hasPermission("uhc.administration")) {
                String notPermission = MessagesManagers.getMessage("playerHaveNotPermission", null) ;
                player.sendMessage(prefix + notPermission);
                return false;
            }
            if(args.length < 1) {
                String utilisation = MessagesManagers.getMessage("utilisationUHCCommand", null);
                player.sendMessage(prefix + utilisation);
                return false;
            }
            if(args[0].equalsIgnoreCase("start")) {
                GameStateManager.setCurrentPhase(GameStateManager.STARTING);
                int borderDimension = SettingsManager.getInt("borderDimension");
                StartFunction.start(borderDimension);
                String startGameMessageServer = MessagesManagers.getMessage("startGameMessageServer", player);
                Bukkit.broadcastMessage(prefix + startGameMessageServer);
                String startGameMessagePlayer = MessagesManagers.getMessage("startGameMessagePlayer", null);
                player.sendMessage(prefix + startGameMessagePlayer);
                return true;
            }else if(args[0].equalsIgnoreCase("stop")) {
                if(GameStateManager.isGame(GameStateManager.INGAME)) {
                    String stopGameMessageServer = MessagesManagers.getMessage("stopGameMessageServer", player);
                    Bukkit.broadcastMessage(prefix + stopGameMessageServer);
                    String stopGameMessagePlayer = MessagesManagers.getMessage("stopGameMessagePlayer", null);
                    player.sendMessage(prefix + stopGameMessagePlayer);
                    GameStateManager.setCurrentPhase(GameStateManager.LOBBY);
                    for(Player player1 : Bukkit.getOnlinePlayers()) {
                        player1.playSound(player1.getLocation(), Sound.VILLAGER_DEATH, 1,1);
                        TeleportLobbyPlayer.teleportLobbyPlayer(player1);
                    }
                    return true;
                }else {
                    String errorStopGame = MessagesManagers.getMessage("ErrorStopGame", null);
                    player.sendMessage(prefix + errorStopGame);
                    return false;
                }
            }
        }

        return false;
    }
}