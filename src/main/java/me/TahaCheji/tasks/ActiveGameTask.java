package me.TahaCheji.tasks;

import me.TahaCheji.gameData.Game;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ActiveGameTask extends BukkitRunnable {


    private Game game;
    private int gameTimer;

    public ActiveGameTask(Game game, int gameTimer) {
        this.game = game;
        this.gameTimer = gameTimer;
    }
    @Override
    public void run() {
        if(this.gameTimer <= 0) {
            this.cancel();
            game.end();
            //gameScoreBoard.stopUpdating();
        } else {
            gameTimer --;
            game.setGameTime(gameTimer);
        }
    }

    public int getGameTimer() {
        return game.getGameTime();
    }

    public void setGameTimer(int gameTimer) {
        this.gameTimer = gameTimer;
    }

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }


}
