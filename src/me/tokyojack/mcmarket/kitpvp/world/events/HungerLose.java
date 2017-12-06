package me.tokyojack.mcmarket.kitpvp.world.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerLose implements Listener {

	@EventHandler
	public void onHungerLose(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

}
