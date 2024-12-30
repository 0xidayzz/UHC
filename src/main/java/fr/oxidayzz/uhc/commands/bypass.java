package fr.oxidayzz.uhc.commands;

import fr.oxidayzz.uhc.managers.MessagesManagers;
import fr.oxidayzz.uhc.managers.PlayersManagers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bypass implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessagesManagers.getMessage("prefix", null);

        if(!(sender instanceof Player)) {
            String notPlayer = MessagesManagers.getMessage("senderIsNotPlayer", null);
            sender.sendMessage(prefix + notPlayer);
            return false;
        }

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("bypass")) {
            if(!player.hasPermission("uhc.bypass")) {
                String notPermission = MessagesManagers.getMessage("playerHaveNotPermission", null);
                player.sendMessage(prefix + notPermission);
                return false;
            }
            if(PlayersManagers.bypass.contains(player.getUniqueId())) {
                PlayersManagers.removeBypassPlayer(player);
                return true;
            }else {
                PlayersManagers.addBypassPlayer(player);
                return true;
            }
        }

        return false;
    }
}
