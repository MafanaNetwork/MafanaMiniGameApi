package me.TahaCheji.team;

import me.TahaCheji.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeamListener implements Listener{

	@EventHandler
	private void friendlyFire(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player geter = (Player) e.getEntity();
			Player seter = (Player) e.getDamager();
			for (Team team : Main.getInstance().getTeams()) {
				if(team.containsPlayer(geter) && team.containsPlayer(seter)) {
					if(!team.getFriendlyFire()) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	
	@EventHandler
	private void allowInteractBlock(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		for (Team team : Main.getInstance().getTeams()) {
			if(team.containsPlayer(player)) {
				if(!team.getCanInteract()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	private void allowInteractEntity(PlayerInteractAtEntityEvent e) {
		Player player = e.getPlayer();
		for (Team team : Main.getInstance().getTeams()) {
			if(team.containsPlayer(player)) {
				if(!team.getCanInteract()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	private void allowDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();
			for (Team team : Main.getInstance().getTeams()) {
				if(team.containsPlayer(player)) {
					if(!team.getCanInteract()) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void canBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		for (Team team : Main.getInstance().getTeams()) {
			if(team.containsPlayer(player)) {
				if(!team.getCanBreak()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	private void canPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		for (Team team : Main.getInstance().getTeams()) {
			if(team.containsPlayer(player)) {
				if(!team.getCanPlace()) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	
}
