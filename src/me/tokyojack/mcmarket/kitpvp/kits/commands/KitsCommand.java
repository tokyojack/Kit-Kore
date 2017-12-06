package me.tokyojack.mcmarket.kitpvp.kits.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.kitpvp.kits.KitAttribute;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.Kommand.Kommand;
import me.tokyojack.mcmarket.kitpvp.utils.Multipage.Multipage;

public class KitsCommand extends Kommand {

	public KitsCommand() {
		super("kit", "Kits command", new ArrayList<String>(Arrays.asList("kits", "kittest")));
	}

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage("Only players can do this command!");
			return false;
		}

		Player player = (Player) commandSender;

		new Multipage("Kits %page%",
				Arrays.asList(Kits.values()).stream()
						.filter(kit -> !kit.getKitAtributes().contains(KitAttribute.HIDDEN))
						.map(kit -> kit.getMenuItem(player)).collect(Collectors.toList())).openInventory(player);

		return true;
	}
}
