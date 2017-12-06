package me.tokyojack.mcmarket.kitpvp.bounty.commands.subCommands;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.utils.ItemBuilder;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.SubKommand.SubKommand;

public class Top extends SubKommand {

	public Top() {
		super("Get top bounties");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean execute(CommandSender commandSender, String[] args) {

		LinkedHashMap<UUID, Integer> bountyPlayers = KitPVP.getPlugin().getBountyManager().getBountyPlayers();

		Object[] a = bountyPlayers.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<UUID, Integer>) o2).getValue().compareTo(((Map.Entry<UUID, Integer>) o1).getValue());
			}
		});

		if (commandSender instanceof Player) {
			Player player = (Player) commandSender;

			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Top 9");

			for (int i = 0; i < a.length; i++) {
				String name = Bukkit.getOfflinePlayer(((Map.Entry<UUID, Integer>) a[i]).getKey()).getName();
				int amount = ((Map.Entry<UUID, Integer>) a[i]).getValue();

				inv.addItem(new ItemBuilder(Material.SKULL_ITEM).setHead(name).setName(name + " | " + amount)
						.toItemStack());
			}

			player.openInventory(inv);
		} else {
			// Yes its the same for loop, but it'll look much nicer like this
			// than cramming it into one
			for (int i = 0; i < a.length; i++) {
				String name = Bukkit.getOfflinePlayer(((Map.Entry<UUID, Integer>) a[i]).getKey()).getName();
				int amount = ((Map.Entry<UUID, Integer>) a[i]).getValue();

				commandSender.sendMessage(name + " | " + amount);
			}
		}

		return true;
	}
}
