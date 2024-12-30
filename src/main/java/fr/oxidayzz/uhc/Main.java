package fr.oxidayzz.uhc;

import fr.oxidayzz.uhc.commands.bypass;
import fr.oxidayzz.uhc.commands.uhc;
import fr.oxidayzz.uhc.managers.EventManagers;
import fr.oxidayzz.uhc.managers.MessagesManagers;
import fr.oxidayzz.uhc.managers.SettingsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;
    private MessagesManagers messagesManagers;

    public static String prefix = MessagesManagers.getMessage("prefix", null);

    @Override
    public void onEnable() {

        instance = this;
        new EventManagers().registers();
        getCommand("bypass").setExecutor(new bypass());
        getCommand("uhc").setExecutor(new uhc());

        saveDefaultConfig();
        messagesManagers = new MessagesManagers(this);
        SettingsManager settingsManager = new SettingsManager(this);

    }

    public static Main getInstance() {
        return instance;
    }

}