package me.TahaCheji.lobbyData;

import me.TahaCheji.GameMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Lobby {

    private Location lobbyPoint = null;
    public Location getLobbyPoint() {
        if (lobbyPoint == null) {
            int x = 0;
            int y = 0;
            int z = 0;
            String world = "world";

            try {
                x = GameMain.getInstance().getConfig().getInt("lobby-point.x");
                y = GameMain.getInstance().getConfig().getInt("lobby-point.y");
                z = GameMain.getInstance().getConfig().getInt("lobby-point.z");
                world = GameMain.getInstance().getConfig().getString("lobby-point.world");
            } catch (Exception ex) {
                GameMain.getInstance().getLogger().severe("Lobby point failed with exception: " + ex);
                ex.printStackTrace();
            }

            lobbyPoint = new Location(Bukkit.getWorld(world), x, y, z);
        }

        return lobbyPoint;
    }
}
