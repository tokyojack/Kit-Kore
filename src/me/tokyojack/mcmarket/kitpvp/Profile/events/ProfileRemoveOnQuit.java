package me.tokyojack.mcmarket.kitpvp.Profile.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.tokyojack.mcmarket.kitpvp.KitPVP;

public class ProfileRemoveOnQuit implements Listener {

	@EventHandler
	public void playerJoin(PlayerQuitEvent event) {
		KitPVP.getPlugin().getProfileManager().removeProfile(event.getPlayer());
	}

}
