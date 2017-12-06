package me.tokyojack.mcmarket.kitpvp.Profile.events.stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.Profile.ProfileManager;
import me.tokyojack.mcmarket.kitpvp.utils.customEvents.minecraft.PlayerKilledPlayerEvent;

public class PlayerKilledPlayer implements Listener {

	@EventHandler
	public void playerKill(PlayerKilledPlayerEvent event) {
		ProfileManager profileManager = KitPVP.getPlugin().getProfileManager();

		// May want to split these up
		profileManager.getProfile(event.getKiller()).addKill();
		event.getKiller().setLevel(profileManager.getProfile(event.getKiller()).getCurrentKillStreak());

		profileManager.getProfile(event.getVictim()).setCurrentKillStreak(0);
	}

}
