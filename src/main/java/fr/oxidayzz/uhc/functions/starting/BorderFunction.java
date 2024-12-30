package fr.oxidayzz.uhc.functions.starting;

import fr.oxidayzz.uhc.managers.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;

public class BorderFunction {


    public static void setupBorder(int taille) {
        if(Bukkit.getWorld("Game") == null) {
            Bukkit.createWorld(new WorldCreator("Game"));
            setupBorder(taille);
        }else {
            World world = Bukkit.getWorld("Game");
            WorldBorder gameBorder = world.getWorldBorder();
            gameBorder.setCenter(0, 0);
            gameBorder.setSize(taille);
        }
    }
}

