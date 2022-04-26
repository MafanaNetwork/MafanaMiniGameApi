package me.TahaCheji.kit;

import java.util.HashMap;

import me.TahaCheji.Log.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Kit {
	
	private HashMap<ItemStack, Integer> Content = new HashMap<>();
	
	public Kit() {
		
		
	}
	
	public void giveKit(Player player) {

		for(ItemStack key : Content.keySet()) {
			if(Content.get(key) == 100) { player.getInventory().setBoots(key);}
			else if(Content.get(key) == 101) {player.getInventory().setLeggings(key);}
			else if(Content.get(key) == 102) {player.getInventory().setChestplate(key);}
			else if(Content.get(key) == 103) {player.getInventory().setHelmet(key);}
			
			else if(Content.get(key) <=35) { player.getInventory().setItem(Content.get(key), key); }
			
			else {
				Log.Error("Wrong Slot "  + Content.get(key) + " for item " + key);}
		}
		
	}
	
	public void addItem(int Slot, ItemStack item) {
		Content.put(item, Slot);
	}

	
	
	

}
