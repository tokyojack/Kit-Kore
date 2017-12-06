package me.tokyojack.mcmarket.kitpvp.world.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class StopItemLoseDuability implements Listener {

	@EventHandler
	public void itemLoseDurability(PlayerItemDamageEvent event) {
		event.setCancelled(true);
	}

}
