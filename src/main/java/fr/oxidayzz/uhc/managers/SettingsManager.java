package fr.oxidayzz.uhc.managers;

import fr.oxidayzz.uhc.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    private final Main plugin;
    private final File file;
    private static FileConfiguration config;
    private static final Map<String, Object> settings = new HashMap<>();

    public SettingsManager(Main plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
        loadSettings();
    }

    /**
     * Charge tous les paramètres de la section "settings" dans la carte.
     */
    public static void loadSettings() {
        settings.clear();
        if (config.isConfigurationSection("settings")) {
            for (String key : config.getConfigurationSection("settings").getKeys(false)) {
                settings.put(key, config.get("settings." + key));
            }
        }
    }

    /**
     * Récupère un booléen par sa clé.
     *
     * @param key La clé du paramètre.
     * @return La valeur booléenne.
     */
    public static boolean getBoolean(String key) {
        Object value = settings.getOrDefault(key, false);
        return value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(value.toString());
    }

    /**
     * Récupère un entier par sa clé.
     *
     * @param key La clé du paramètre.
     * @return La valeur entière.
     */
    public static int getInt(String key) {
        Object value = settings.getOrDefault(key, 0);
        return value instanceof Integer ? (Integer) value : Integer.parseInt(value.toString());
    }

    /**
     * Met à jour ou ajoute un booléen dans le fichier et recharge.
     *
     * @param key   La clé du paramètre.
     * @param value La nouvelle valeur.
     */
    public void setBoolean(String key, boolean value) {
        config.set("settings." + key, value);
        save();
        loadSettings();
    }

    /**
     * Met à jour ou ajoute un entier dans le fichier et recharge.
     *
     * @param key   La clé du paramètre.
     * @param value La nouvelle valeur.
     */
    public void setInt(String key, int value) {
        config.set("settings." + key, value);
        save();
        loadSettings();
    }

    /**
     * Sauvegarde le fichier de configuration.
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Impossible de sauvegarder le fichier config.yml");
            e.printStackTrace();
        }
    }
}
