package me.tokyojack.mcmarket.kitpvp;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.tokyojack.mcmarket.kitpvp.Profile.ProfileManager;
import me.tokyojack.mcmarket.kitpvp.Profile.commands.Stats;
import me.tokyojack.mcmarket.kitpvp.Profile.events.ProfileCreateOnJoin;
import me.tokyojack.mcmarket.kitpvp.Profile.events.ProfileRemoveOnQuit;
import me.tokyojack.mcmarket.kitpvp.Profile.events.stats.PlayerKilledPlayer;
import me.tokyojack.mcmarket.kitpvp.Profile.events.stats.PlayerLoseKillstreak;
import me.tokyojack.mcmarket.kitpvp.bounty.BountyManager;
import me.tokyojack.mcmarket.kitpvp.bounty.commands.Bounty;
import me.tokyojack.mcmarket.kitpvp.bounty.commands.subCommands.Add;
import me.tokyojack.mcmarket.kitpvp.bounty.commands.subCommands.Top;
import me.tokyojack.mcmarket.kitpvp.game.events.MoneyGainOnkill;
import me.tokyojack.mcmarket.kitpvp.game.events.PlayerDeath;
import me.tokyojack.mcmarket.kitpvp.game.events.SoupDrink;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;
import me.tokyojack.mcmarket.kitpvp.kits.commands.KitsCommand;
import me.tokyojack.mcmarket.kitpvp.kits.events.KitInventoryClick;
import me.tokyojack.mcmarket.kitpvp.utils.Gependacy.Gependecy;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.Kommand.KommandManager;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.SubKommand.SubKommandManager;
import me.tokyojack.mcmarket.kitpvp.utils.Multipage.Multipage;
import me.tokyojack.mcmarket.kitpvp.utils.customEvents.minecraft.PlayerKilledPlayerEvent;
import me.tokyojack.mcmarket.kitpvp.utils.customEvents.minecraft.PlayerShootPlayerEvent;
import net.milkbowl.vault.economy.Economy;

@Getter
public class KitPVP extends JavaPlugin {

	@Getter
	private static KitPVP plugin;

	private BountyManager bountyManager;
	private ProfileManager profileManager;

	private Economy economy;

	public void onEnable() {
		plugin = this;
		this.bountyManager = new BountyManager();
		this.profileManager = new ProfileManager();

		new KommandManager().addCommand(new Stats()).addCommand(new KitsCommand()).build();
		new SubKommandManager(new Bounty(), false).addSubCommand(new Add()).addSubCommand(new Top()).build();

		Multipage.registerListener(this);
		new PlayerKilledPlayerEvent().registerListener(this);
		new PlayerShootPlayerEvent().registerListener(this);
		registerEvents();

		new Gependecy("Vault", true, this) {

			@Override
			public void ifFound() {
				setupEconomy();
			}

			@Override
			public void ifNotFound() {
				economy = null;
			}
		}.check();
	}

	private void registerEvents() {
		Kits.register();

		PluginManager pluginManager = getServer().getPluginManager();

		// /KitInventoryClick
		// Events
		pluginManager.registerEvents(new SoupDrink(), this);
		pluginManager.registerEvents(new MoneyGainOnkill(), this);

		pluginManager.registerEvents(new ProfileCreateOnJoin(), this);
		pluginManager.registerEvents(new ProfileRemoveOnQuit(), this);

		pluginManager.registerEvents(new PlayerKilledPlayer(), this);
		pluginManager.registerEvents(new PlayerLoseKillstreak(), this);

		pluginManager.registerEvents(new KitInventoryClick(), this);
		pluginManager.registerEvents(new PlayerDeath(), this);

	}

	// From Vault's WIKI if I'm correct
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			this.economy = economyProvider.getProvider();

		return (this.economy != null);
	}

}
