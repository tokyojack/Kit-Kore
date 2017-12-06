package me.tokyojack.mcmarket.kitpvp.Profile.events.stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.tokyojack.mcmarket.kitpvp.KitPVP;

public class PlayerLoseKillstreak implements Listener {

	@EventHandler
	public void playerDeath(PlayerDeathEvent event) {
		KitPVP.getPlugin().getProfileManager().getProfile(event.getEntity()).setCurrentKillStreak(0);
	}

}
