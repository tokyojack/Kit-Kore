package me.tokyojack.mcmarket.kitpvp.game.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.utils.customEvents.minecraft.PlayerKilledPlayerEvent;

public class MoneyGainOnkill implements Listener {

	@EventHandler
	public void playerKill(PlayerKilledPlayerEvent event) {
		KitPVP kitpvp = KitPVP.getPlugin();
		int killStreak = kitpvp.getProfileManager().getProfile(event.getKiller()).getCurrentKillStreak();

		int amount = 50 + (killStreak * 5);

		kitpvp.getEconomy().depositPlayer(event.getKiller(), amount);

		event.getKiller()
				.sendMessage("You've gained $" + amount + ". (" + killStreak + " -> " + (killStreak + 1) + ")");
	}

}
