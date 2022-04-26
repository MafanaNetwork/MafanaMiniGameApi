package me.TahaCheji.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardTeams {
    private final String name;
    private final String teamName;
    private final Integer score;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public ScoreboardTeams(String name, String teamName, Integer score, Scoreboard scoreboard, Objective objective) {
        this.name = name;
        this.teamName = teamName;
        this.score = score;
        this.scoreboard = scoreboard;
        this.objective = objective;
    }

    public void setTeam(ChatColor color1, ChatColor color2) {
        org.bukkit.scoreboard.Team gameInfo = scoreboard.registerNewTeam(teamName);
        gameInfo.addEntry(teamName);
        gameInfo.setPrefix(ChatColor.GRAY + ">> " + ChatColor.GOLD + name);
        objective.getScore(teamName).setScore(score);
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public String getTeamName() {
        return teamName;
    }
}
