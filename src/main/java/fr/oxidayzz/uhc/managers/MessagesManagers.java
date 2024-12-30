package fr.oxidayzz.uhc.managers;

import fr.oxidayzz.uhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MessagesManagers {
    private final Main plugin;
    private final File file;
    private static FileConfiguration config;
    private static final Map<String, String> messages = new HashMap<>();

    public MessagesManagers(Main plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "messages.yml");

        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
        loadMessages();
    }
    public static void loadMessages() {
        messages.clear();
        if (config.isConfigurationSection("messages")) {
            for (String key : config.getConfigurationSection("messages").getKeys(false)) {
                messages.put(key, config.getString("messages." + key, ""));
            }
        }
    }

    /**
     * Récupère un message par sa clé et le formate.
     *
     * @param key  La clé du message.
     * @param player Le joueur (optionnel, peut être null).
     * @return Le message formaté.
     */
    public static String getMessage(String key, Player player) {
        String message = messages.getOrDefault(key, "Message non trouvé: " + key);
        if (player != null) {
            message = message.replace("{PLAYER}", player.getName());
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Impossible de sauvegarder le fichier messages.yml");
            e.printStackTrace();
        }
    }

    /**
     * Met à jour ou ajoute un message dans le fichier et recharge.
     *
     * @param key   La clé du message.
     * @param value Le nouveau contenu.
     */
    public void setMessage(String key, String value) {
        config.set("messages." + key, value);
        save();
        loadMessages();
    }
}
