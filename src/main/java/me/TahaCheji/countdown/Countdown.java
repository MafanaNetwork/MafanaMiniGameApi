package me.TahaCheji.countdown;

import me.TahaCheji.Main;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.scoreboard.ScoreboardTeams;
import me.TahaCheji.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.scoreboard.Scoreboard;

import java.lang.reflect.Method;

public class Countdown {

	private int[] Countdowntimes = null;
	private int Startvalue = 0;
	private Boolean useXp = false;
	private Boolean useTitle = false;
	private int Scheduler;
	private ChatColor titleColor = ChatColor.WHITE;

	public Countdown(int[] Countdowntimes, int Startvalue) {
		this.Countdowntimes = Countdowntimes;
		this.Startvalue = Startvalue;
	}
	public void start(final String message, Player player, GameState gameState, Game game) {

			Scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			int curentCount = Startvalue;
			@Override
			public void run() {
				String finalmessage = message.replaceAll("%time%", String.valueOf(curentCount));
				for (int countTime : Countdowntimes) {
					if (countTime == curentCount) {

						player.sendMessage(finalmessage);
					}
				}
				if (useXp) {
					player.setLevel(curentCount);
				}

				if (useTitle && curentCount <= 5) {
					Title.newTitle(player, titleColor + String.valueOf(curentCount), 5, 10, 1);
				}

				if (curentCount == 0) {
					Bukkit.getScheduler().cancelTask(Scheduler);
					if(gameState == GameState.Active) {
						game.end();
					}
					if(gameState == GameState.Lobby) {
						game.start();
					}
					if(gameState == GameState.SpawnPoints) {
						game.assignSpawnPositions();
					}
				}
				curentCount--;
			}
		}, 0L, 20L);
	}

	public void Stop() {
		Bukkit.getScheduler().cancelTask(Scheduler);
	}

	public int[] getCountdowntimes() {
		return Countdowntimes;
	}

	public void setCountdowntimes(int[] countdowntimes) {
		Countdowntimes = countdowntimes;
	}

	public int getStartValue() {
		return Startvalue;
	}

	public void setStartValue(int Startvalue) {
		this.Startvalue = Startvalue;
	}

	public Boolean getUseXp() {
		return useXp;
	}

	public void setUseXp(Boolean useXp) {
		this.useXp = useXp;
	}

	public Boolean getUseTitle() {
		return useTitle;
	}

	public void setUseTitle(Boolean useTitle) {
		this.useTitle = useTitle;
	}

	public ChatColor getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(ChatColor titleColor) {
		this.titleColor = titleColor;
	}

}
