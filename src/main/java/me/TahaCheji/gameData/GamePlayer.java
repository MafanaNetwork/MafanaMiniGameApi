package me.TahaCheji.gameData;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayer {

    private final Player player;
    private Game game;
    private PlayerLocation playerLocation;
    private int kills;

    public GamePlayer(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public GamePlayer(Player player, PlayerLocation location) {
        this.player = player;
        this.playerLocation = location;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendMessage(String message) {
            player.sendMessage(message);
    }


    public Game getGame() {
        return game;
    }

    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }


    public void setPlayerLocation(PlayerLocation playerLocation) {
        this.playerLocation = playerLocation;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public void teleport(Location location) {
        if (location == null) {
            return;
        }
            getPlayer().teleport(location);
        }


    public String getName() {
            return player.getDisplayName();
    }
}
