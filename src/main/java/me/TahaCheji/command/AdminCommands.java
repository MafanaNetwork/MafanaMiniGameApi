package me.TahaCheji.command;

import me.TahaCheji.Main;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GameMode;
import me.TahaCheji.gameData.GamePlayer;
import me.TahaCheji.gameData.PlayerLocation;
import me.TahaCheji.mapUtil.GameMap;
import me.TahaCheji.mapUtil.LocalGameMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class AdminCommands implements CommandExecutor {
    String gameName = null;
    ItemStack gameIcon = null;
    GameMode gameMode = null;
    GameMap gameMap;
    int maxPlayers;

    Game game;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("gameAdmin")) {
            Player player = (Player) sender;
            if(!player.isOp()) {
                player.sendMessage(ChatColor.RED + "You Do Not Have The Permission To Do This Command");
            }
            if (args[0].equalsIgnoreCase("edit")) {
                File gameMapsFolder = new File("plugins/SkyWiz/", "maps");
                GameMap gameMap = new LocalGameMap(gameMapsFolder, args[1], false);
                if(args.length == 3 && args[2].equalsIgnoreCase("save")) {
                    GameMap newGameMap = Main.getInstance().getPlayerGameHashMap().get(player);
                    player.teleport(Main.getInstance().getLobbyPoint());
                    newGameMap.saveMap();
                    Main.getInstance().removeMap(player, newGameMap);
                    return true;
                }
                gameMap.load();
                player.teleport(gameMap.getWorld().getSpawnLocation());
                Main.getInstance().getPlayer(player).setPlayerLocation(PlayerLocation.GAME);
                player.setGameMode(org.bukkit.GameMode.CREATIVE);
                Main.getInstance().addMap(player, gameMap);
            }
            if (args[0].equalsIgnoreCase("create")) {
                if (args[1].equalsIgnoreCase("game")) {
                    if (args[2].equalsIgnoreCase("save")) {
                        game = new Game(gameName, gameIcon, gameMode, gameMap, maxPlayers);
                        try {
                            game.save();
                            player.sendMessage(ChatColor.GOLD + "[GameBuilder]: " + "Great now edit the spawn in the yml!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                    if (!(args.length >= 6)) {
                        return true;
                    }
                    gameName = args[2];
                    if (args[3].equalsIgnoreCase("gameIcon")) {
                        gameIcon = player.getItemInHand();
                    }
                    gameMode = GameMode.NORMAL;
                    File gameMapsFolder = new File(Main.getInstance().getDataFolder(), "maps");
                    gameMap = new LocalGameMap(gameMapsFolder, args[4], false);
                    player.sendMessage(ChatColor.GOLD + "[GameBuilder]: " + "Great now all you need to do is save your game then add the spawn points! [You can do that in the yml game file]");
                    maxPlayers = Integer.parseInt(args[5]);
                }
            }
        }
        return false;
    }
}
