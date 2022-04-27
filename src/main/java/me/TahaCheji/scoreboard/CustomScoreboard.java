package me.TahaCheji.scoreboard;

import me.TahaCheji.GameMain;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class CustomScoreboard {

	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard scoreboard = manager.getNewScoreboard();
	Objective objective = null;
	List<ScoreboardTeams> teams = new ArrayList<>();
	public int TaskId;
	
	
	public CustomScoreboard(String Name, DisplaySlot displaySlot) {
		objective = scoreboard.registerNewObjective(Name, "dummy");
		objective.setDisplayName(Name);
		objective.setDisplaySlot(displaySlot);
	}
	
	public void setScore(String name, Integer Score) {
		Score score = objective.getScore(name);
		score.setScore(Score);
	}

	public void setTeam(String name, String teamName,ChatColor color1, ChatColor color2, Integer score) {
		ScoreboardTeams team = new ScoreboardTeams(name, teamName, score, scoreboard, objective);
		team.setTeam(color1, color2);
		teams.add(team);
	}

	public void updateScoreBoard(GamePlayer player) {
		TaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(GameMain.getInstance(), new Runnable() {
			@Override
			public void run() {
					if(!player.getPlayer().isOnline()) {
						stopUpdating();
						return;
					}
					Scoreboard board = player.getPlayer().getScoreboard();
					for(ScoreboardTeams teams : teams) {
						if(board.getTeam(teams.getTeamName()) != null) {
							board.getTeam(teams.getTeamName()).setPrefix(ChatColor.GRAY + ">> " + ChatColor.GOLD + teams.getName());
						}
				}
			}
		}, 0, 5);
	}

	public void stopUpdating() {
		Bukkit.getScheduler().cancelTask(TaskId);
	}
	
	public int getScore(String name) {
		Score score = objective.getScore(name);
		
		return score.getScore();
	}
	
	public void setScoreboard(GamePlayer player) {
		player.getPlayer().setScoreboard(scoreboard);
		updateScoreBoard(player);
	}
	
	public void setScoreboard(Team team) {
		for (Player player : team.getPlayers()) {
			player.setScoreboard(scoreboard);
		}
	}
} 
