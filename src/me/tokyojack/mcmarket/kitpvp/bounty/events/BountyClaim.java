package me.tokyojack.mcmarket.kitpvp.bounty.events;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.bounty.BountyManager;
import me.tokyojack.mcmarket.kitpvp.utils.customEvents.minecraft.PlayerKilledPlayerEvent;

public class BountyClaim implements Listener {

	@EventHandler
	public void playerKill(PlayerKilledPlayerEvent event) {
		BountyManager bountyManager = KitPVP.getPlugin().getBountyManager();

		UUID victimUUID = event.getVictim().getUniqueId();

		if (!bountyManager.getBountyPlayers().containsKey(victimUUID))
			return;

		KitPVP.getPlugin().getEconomy().depositPlayer(event.getKiller(), bountyManager.getBounty(victimUUID));
		bountyManager.getBountyPlayers().remove(victimUUID);
	}

}
