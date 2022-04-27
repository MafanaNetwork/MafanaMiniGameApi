package me.TahaCheji.game;

import fr.mrmicky.fastboard.FastBoard;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.scoreboard.GameScoreboard;
import org.bukkit.ChatColor;

public class TestScoreboard2 extends GameScoreboard {
    Game game;
    public TestScoreboard2(Game game) {
        super("2");
        this.game = game;
    }

    @Override
    public void updateBoard(FastBoard board, GamePlayer gamePlayer) {
        board.updateLines(
                ChatColor.GOLD + "=-=-=-=-=-=-=-=-=-=-=-=-",
                "",
                ChatColor.GRAY + ">> " + ChatColor.GOLD + "Time Left: " + game.getGameTime(),
                "",
                ChatColor.GRAY + ">> " + ChatColor.GOLD + "Players: " + game.getPlayers().size(),
                "",
                ChatColor.GRAY + ">> " + ChatColor.GOLD + "Kills: " + gamePlayer.getKills(),
                "",
                ChatColor.GOLD + "-=-=-=-Mafana.us.to-=-=-=-"
        );
    }
}
