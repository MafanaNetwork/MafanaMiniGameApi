package me.TahaCheji.managers;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class DeathManager {

    public GamePlayer killer;
    public GamePlayer target;
    public Game game;

    public DeathManager(GamePlayer killer, GamePlayer target, Game game) {
        this.killer = killer;
        this.target = target;
        this.game = game;
    }

    public boolean ifLastPlayer() {
        return game.getPlayers().size() == 1;
    }

    public void handle() {
        if (!game.isState(Game.GameState.ACTIVE) && !game.isState(Game.GameState.DEATHMATCH)) {
            return;
        }
        GamePlayer gamePlayer = game.getGamePlayer(target.getPlayer());
        gamePlayer.getPlayer().playSound(target.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 10);
        game.getPlayers().remove(gamePlayer);
        gamePlayer.sendMessage(ChatColor.RED + "You have died");
        if (ifLastPlayer()) {
            GamePlayer winner = game.getPlayers().get(0);
            game.setWinner(winner);
            setSpectator();
        } else {
            GameMain.getInstance().getPlayer(killer.getPlayer()).setKills(gamePlayer.getKills() + 1);
            Bukkit.broadcastMessage(ChatColor.RED + GameMain.getInstance().getPlayer(killer.getPlayer()).getPlayer().getDisplayName() +
                    ChatColor.RED + " has killed " + target.getPlayer().getName() + ChatColor.RED + " due to blood loss.");
            setSpectator();
        }
    }

    public void setSpectator() {
        target.teleport(game.getLobbySpawn());
        game.activateSpectatorSettings(target.getPlayer());
    }


    public Game getGame() {
        return game;
    }

    public GamePlayer getKiller() {
        return killer;
    }

    public GamePlayer getTarget() {
        return target;
    }
}
