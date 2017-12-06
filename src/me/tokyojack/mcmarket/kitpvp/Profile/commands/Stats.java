package me.tokyojack.mcmarket.kitpvp.Profile.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.Kommand.Kommand;

public class Stats extends Kommand {

	public Stats() {
		super("Stats command", new ArrayList<String>(Arrays.asList("stat")));
	}

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) {
		commandSender
				.sendMessage("Kill Streak: "
						+ KitPVP.getPlugin().getProfileManager().getProfile(((Player) commandSender))
								.getCurrentKillStreak()
						+ " | Kills"
						+ KitPVP.getPlugin().getProfileManager().getProfile(((Player) commandSender)).getTotalKills());

		return false;
	}

}
