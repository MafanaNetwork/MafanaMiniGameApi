package me.TahaCheji.events;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.gameData.PlayerLocation;
import me.TahaCheji.managers.DeathManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.IOException;

public class PlayerDeath implements Listener {


    @EventHandler
    public void onDeath (PlayerDeathEvent e) {
        Player player = e.getEntity();
        Game game = GameMain.getInstance().getGame(player);
        if (game != null && game.getGamePlayer(player) != null) {
            GamePlayer gamePlayer = game.getGamePlayer(player);
            if (gamePlayer.getPlayer().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                new DeathManager(GameMain.getInstance().getPlayer(e.getPlayer().getKiller()), gamePlayer, game).handle();
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMoveDeathInGame(PlayerMoveEvent event) throws IOException {
        Player player = event.getPlayer();
        Game game = GameMain.getInstance().getGame(player);
        if (game != null && game.getGamePlayer(player) != null) {
            GamePlayer gamePlayer = game.getGamePlayer(player);
            if (!(gamePlayer.getPlayerLocation() == PlayerLocation.GAME)) {
                return;
            }
            if (!(event.getTo().getY() <= 0)) {
                return;
            }
            if (gamePlayer.getPlayer().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                new DeathManager(gamePlayer, gamePlayer, game).handle();
            }
        }
    }
}
