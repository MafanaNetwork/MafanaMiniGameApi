package me.TahaCheji.events;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockInteract implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(player.isOp()) {
            event.setCancelled(false);
            return;
        }

        handle(event, player);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.isOp()) {
            event.setCancelled(false);
            return;
        }

        handle(event, player);
    }

    private void handle(Cancellable event, Player player) {
        Game game = GameMain.getInstance().getGame(player);
        if (game != null) {
            if (game.isState(Game.GameState.LOBBY) || game.isState(Game.GameState.PREPARATION) || game.isState(Game.GameState.ENDING) || game.isState(Game.GameState.STARTING)) {
                event.setCancelled(true); // Cancel, game isn't active
                return;
            }
            GamePlayer gamePlayer = game.getGamePlayer(player);
            if (gamePlayer != null) {
                    if (gamePlayer.getPlayer() == player) {
                        if (!game.getPlayers().contains(gamePlayer)) {
                            event.setCancelled(true);
                        }
                    }
            }
        } else {
                event.setCancelled(true);
        }
    }

}