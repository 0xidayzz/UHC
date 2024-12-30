package fr.oxidayzz.uhc.managers;

import fr.oxidayzz.uhc.Main;
import fr.oxidayzz.uhc.listeners.OnJoinPlayer;
import fr.oxidayzz.uhc.listeners.OnServeurReload;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventManagers {

    public void registers() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new OnJoinPlayer(), Main.getInstance());
        pm.registerEvents(new OnServeurReload(), Main.getInstance());
    }
}
