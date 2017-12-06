package me.tokyojack.mcmarket.kitpvp.Profile.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tokyojack.mcmarket.kitpvp.KitPVP;

public class ProfileCreateOnJoin implements Listener {

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		KitPVP.getPlugin().getProfileManager().addProfile(event.getPlayer());
	}

}
