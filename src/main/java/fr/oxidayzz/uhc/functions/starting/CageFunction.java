package fr.oxidayzz.uhc.functions.starting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CageFunction {

    public static void createCage(Location location, World world, Player player) {
        int startX = location.getBlockX() - 2;
        int startZ = location.getBlockZ() - 2;
        int startY = location.getBlockY() - 1;

        for (int x = startX; x < startX + 5; x++) {
            for (int z = startZ; z < startZ + 5; z++) {
                Location blockLocation = new Location(world, x, startY, z);
                Block block = blockLocation.getBlock();
                block.setType(Material.GLASS);
            }
        }

        player.setFallDistance(0);
    }

    public static void destroyCage(Location location, World world) {
        int startX = location.getBlockX() - 2;
        int startZ = location.getBlockZ() - 2;
        int startY = location.getBlockY() - 1;

        for (int x = startX; x < startX + 5; x++) {
            for (int z = startZ; z < startZ + 5; z++) {
                Location blockLocation = new Location(world, x, startY, z);
                Block block = blockLocation.getBlock();
                block.setType(Material.AIR);
            }
        }
    }

}
