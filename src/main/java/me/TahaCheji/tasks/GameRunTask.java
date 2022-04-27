package me.TahaCheji.tasks;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.gameData.PlayerLocation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;


public class GameRunTask extends BukkitRunnable {

    private Game game;
    private int startIn = 10;
    ActiveGameTask gameTask;

    public GameRunTask(Game game) {
        this.game = game;
        this.game.setState(Game.GameState.PREPARATION);
        for (GamePlayer gamePlayer : game.getPlayers()) {
            gamePlayer.setPlayerLocation(PlayerLocation.GAME);
            gamePlayer.getPlayer().playSound(gamePlayer.getPlayer().getLocation(), Sound.BLOCK_LEVER_CLICK, 2, 1);
        }
        this.game.assignSpawnPositions();
        this.game.sendMessage(ChatColor.GOLD + "[Game Manager] "  + "You've been teleported.");
        this.game.sendMessage(ChatColor.GOLD + "[Game Manager] " + "The game will begin in " + this.startIn + " seconds...");
        this.game.setMovementFrozen(true);
    }

    @Override
    public void run() {
        if (startIn <= 1) {
            this.cancel();
            game.start();
            gameTask = new ActiveGameTask(game, game.getGameTime());
            gameTask.runTaskTimer(GameMain.getInstance(), 0, 20);
        } else {
            startIn -= 1;
            this.game.sendMessage(ChatColor.GOLD + "[Game Manager] " + "The game will begin in " + startIn + " second" + (startIn == 1 ? "" : "s"));
        }
    }

    public ActiveGameTask getGameTask() {
        return gameTask;
    }
}