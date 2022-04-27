package me.TahaCheji.command;

import me.TahaCheji.Main;
import me.TahaCheji.gameData.Game;
import me.TahaCheji.gameData.GameGui;
import me.TahaCheji.gameData.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(label.equalsIgnoreCase("game")) {
            if (args[0].equalsIgnoreCase("join")) {
                if (args.length == 1) {
                    new GameGui().getGameGui().open(player);
                    return true;
                }
                Game game = Main.getInstance().getGame(args[1]);
                if (game == null) {
                    player.sendMessage(ChatColor.RED + "[Game Manager] " + "That game does not exist");
                    return true;
                }
                GamePlayer gamePlayer = Main.getInstance().getPlayer(player);
                if (Main.getInstance().isInGame(player)) {
                    player.sendMessage(ChatColor.GREEN + "[Game Manager] " + "You are already in a game");
                    return true;
                }
                gamePlayer.setGame(game);
                game.playerJoin(gamePlayer);
            }
        }
        return false;
    }
}
