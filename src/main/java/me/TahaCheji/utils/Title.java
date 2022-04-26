package me.TahaCheji.utils;
import org.bukkit.entity.Player;

public class Title {
	
	public static void newTitle(Player player, String Title, Integer In, Integer Stay, Integer Out) {
		player.sendTitle(Title, "", In, Stay, Out);
	}

	public static void newTitle(Player player, String Title, String subTitle, Integer In, Integer Stay, Integer Out) {
		player.sendTitle(Title, subTitle, In, Stay, Out);
		
	}
	
}
