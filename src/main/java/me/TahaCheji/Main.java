package me.TahaCheji;

import me.TahaCheji.command.AdminCommands;
import me.TahaCheji.countdown.CountDownEvent;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GameData;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.lobbyData.Lobby;
import me.TahaCheji.mapUtil.GameMap;
import me.TahaCheji.team.Team;
import me.TahaCheji.util.FileUtil;
import me.TahaCheji.utils.Files;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class Main extends JavaPlugin {

    public static Main instance;
    public List<GameMap> activeMaps = new ArrayList<>();
    private Set<Game> games = new HashSet<>();
    private Set<Game> activeGames = new HashSet<>();
    public Map<Player, Game> playerGameMap = new HashMap<>();
    public Set<GamePlayer> players = new HashSet<>();
    private HashMap<Player, Game> playerCreateGameHashMap = new HashMap<>();
    public ArrayList<Team> Teams = new ArrayList<Team>();
    private static HashMap<Player, GameMap> playerGameHashMap = new HashMap<>();
    public CountDownEvent countDownEvent;

    @Override
    public void onEnable() {
        instance = this;
        countDownEvent = new CountDownEvent();
        File dataFolder = getServer().getWorldContainer();
        File[] files = dataFolder.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().contains("active")) {
                FileUtil.delete(file);
            }
        }
        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName, ".events").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.initFiles();
        } catch (IOException | InvalidConfigurationException e2) {
            e2.printStackTrace();
        }
        getCommand("game").setExecutor(new AdminCommands());
        getCommand("gameAdmin").setExecutor(new AdminCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isInGame(Player player) {
        return this.playerGameMap.containsKey(player);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void addActiveGame(Game game) {
        activeGames.add(game);
    }

    public Location getLobbyPoint() {
       return new Lobby().getLobbyPoint();
    }

    public void removeActiveGame(Game game) {
        activeGames.remove(game);
    }

    public void addMap (Player player, GameMap gameMap) {
        playerGameHashMap.put(player, gameMap);
    }

    public void removeMap (Player player, GameMap gameMap) {
        playerGameHashMap.remove(player, gameMap);
    }

    public Game getActiveGame(String name) {
        Game game = GameData.getGame(name);
        if(activeGames.contains(game)) {
            return game;
        } else {
            return null;
        }
    }

    public Game getGame(String gameName) {
        for (Game game : games) {
            if (game.getName().equalsIgnoreCase(gameName)) {
                return game;
            }
        }

        return null;
    }

    public HashMap<Player, GameMap> getPlayerGameHashMap() {
        return playerGameHashMap;
    }

    public Game getGame(Game game) {
        return game;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        players.add(gamePlayer);
    }

    public Game getGame(Player player) {
        return this.playerGameMap.get(player);
    }

    public void setGame(Player player, Game game) {
        if (game == null) {
            this.playerGameMap.remove(player);
        } else {
            this.playerGameMap.put(player, game);
        }
    }

    public GamePlayer getPlayer(Player player) {
        GamePlayer gPlayer = null;
        for(GamePlayer gamePlayer : players) {
            if(gamePlayer.getPlayer().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                gPlayer = gamePlayer;
            }
        }
        return gPlayer;
    }

    public static Main getInstance() {
        return instance;
    }

    public ArrayList<Team> getTeams() {
        return Teams;
    }

    public CountDownEvent getCountDownEvent() {
        return countDownEvent;
    }

    public HashMap<Player, Game> getPlayerCreateGameHashMap() {
        return playerCreateGameHashMap;
    }

    public Map<Player, Game> getPlayerGameMap() {
        return playerGameMap;
    }

    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public Set<Game> getActiveGames() {
        return activeGames;
    }

    public Set<Game> getGames() {
        return games;
    }

    public List<GameMap> getActiveMaps() {
        return activeMaps;
    }
}
