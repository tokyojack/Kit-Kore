package me.tokyojack.mcmarket.kitpvp.game.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;
import me.tokyojack.mcmarket.kitpvp.utils.KountdownPlayer;

public class PlayerDeath implements Listener {

	KountdownPlayer specatorKountdown = new KountdownPlayer(KitPVP.getPlugin()) {

		@Override
		public void start(Player player) {
			KitPVP.getPlugin().getProfileManager().getProfile(player).setCurrentKit(Kits.NONE);
			player.sendMessage("You've died!");
			player.setGameMode(GameMode.SPECTATOR);
		}

		@Override
		public void tick(Player player, int countdown) {
			player.sendMessage("Respawning in " + countdown);
		}

		@Override
		public void stop(Player player) {
			// Should never be stopped
		}

		@Override
		public void finish(Player player) {
			player.sendMessage("You've respanwed!");

			player.setGameMode(GameMode.SURVIVAL);
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 0));
			player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());

			Kits.CHOOSE.give(player);
		}

	};

	@EventHandler
	public void playerDeath(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;

		Player player = (Player) event.getEntity();

		if (!(event.getDamage() >= player.getHealth()))
			return;

		event.setCancelled(true);
		specatorKountdown.startPlayer(player, 5);

	}

}
