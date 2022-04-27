package me.TahaCheji.game;

import fr.mrmicky.fastboard.FastBoard;
import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GameMode;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.kit.Kit;
import me.TahaCheji.mapUtil.LocalGameMap;
import me.TahaCheji.scoreboard.GameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class Duels extends Game {

    GameScoreboard activeGameBord = new TestScoreboard(this);
    GameScoreboard test = new TestScoreboard2(this);

    public Duels() {
        super("Test", new ItemStack(Material.STONE_SWORD), GameMode.NORMAL,
                new LocalGameMap(new File("plugins/MafanaGameAPI/", "maps"), "Arena", false), 2);
        setMaxGameTime(230);
        setGameTime(getMaxGameTime());
        List<Location> spawnPoints = new ArrayList<>();
        spawnPoints.add(new Location(Bukkit.getWorld("world"),
                6, 136, 12));
        spawnPoints.add(new Location(Bukkit.getWorld("world"),
                4, 136, -14));
        Location lobbySpawn = new Location(Bukkit.getWorld("world"),
                3, 137, -7);
        setLobbySpawn(lobbySpawn);
        setPlayerSpawnLocations(spawnPoints);
    }

    @Override
    public void playerJoin(GamePlayer gamePlayer) {
        super.playerJoin(gamePlayer);
        activeGameBord.onJoin(gamePlayer);
        activeGameBord.updateScoreboard();
        /*
        CustomScoreboard onJoinSideBord = new CustomScoreboard(ChatColor.GOLD + "Mafana Duels", DisplaySlot.SIDEBAR);
        gamePlayer.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        onJoinSideBord.setScore(ChatColor.GOLD + "=-=-=-=-=-=-=-=-=-=-=-=-", 16);
        onJoinSideBord.setScore(" ", 15);

        onJoinSideBord.setTeam(ChatColor.GRAY + ">> " + ChatColor.GOLD + "Name: " + this.getName() + " | Mode: " +
                        this.getGameMode().toString() + " | Map: " + this.getMap().getName(),
                "GameInfo", ChatColor.GOLD, ChatColor.GOLD, 14);
        onJoinSideBord.setScore("  ", 13);
        onJoinSideBord.setScore(ChatColor.GRAY + ">> " + ChatColor.GOLD + "Time: " + this.getGameTime(), 12);
        onJoinSideBord.setScore("   ", 11);
        onJoinSideBord.setTeam(ChatColor.GRAY + ">> " + ChatColor.GOLD + "Players: " + this.getPlayers().size(),
                "Players", ChatColor.GOLD, ChatColor.BLACK, 10);
        onJoinSideBord.setScore("    ", 9);
        onJoinSideBord.setScore(ChatColor.GOLD + "-=-=-=-Mafana.us.to-=-=-=-", 8);
       onJoinSideBord.setScoreboard(gamePlayer);
         */
    }

    @Override
    public void assignSpawnPositions() {
        super.assignSpawnPositions();
        for (GamePlayer gamePlayer : getPlayers()) {
            Kit kit = new Kit();
            kit.addItem(0, new ItemStack(Material.STONE_SWORD));
            kit.addItem(102, new ItemStack(Material.DIAMOND_CHESTPLATE));
            kit.giveKit(gamePlayer.getPlayer());
            test.onJoin(gamePlayer);
            test.updateScoreboard();
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void end() {
        super.end();
        for(GamePlayer gamePlayer : getPlayers()) {
            activeGameBord.onLeave(gamePlayer);
        }
    }
}
