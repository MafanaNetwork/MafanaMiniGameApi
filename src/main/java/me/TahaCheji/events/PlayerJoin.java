package me.TahaCheji.events;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.gameData.PlayerLocation;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {


    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        GamePlayer gamePlayer = new GamePlayer(e.getPlayer(), PlayerLocation.LOBBY);
        if(!GameMain.getInstance().getPlayers().contains(gamePlayer)) {
            GameMain.getInstance().addGamePlayer(gamePlayer);
        }
        gamePlayer.getPlayer().setHealth(20);
        gamePlayer.getPlayer().setFoodLevel(20);
        gamePlayer.getPlayer().getInventory().clear();
        gamePlayer.getPlayer().getInventory().setArmorContents(null);
        e.getPlayer().teleport(GameMain.getInstance().getLobbyPoint());
        e.setJoinMessage(null);
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        //LobbyScoreBoard lobbyScoreBoard = new LobbyScoreBoard();
        //lobbyScoreBoard.setLobbyScoreBoard(gamePlayer);
        //lobbyScoreBoard.updateScoreBoard(gamePlayer);
    }
}
