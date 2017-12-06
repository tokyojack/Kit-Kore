package me.tokyojack.mcmarket.kitpvp.kits.events;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;
import me.tokyojack.mcmarket.kitpvp.utils.ItemCheck;

public class KitInventoryClick implements Listener {

	@EventHandler
	private void inventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getInventory().getName()).contains("Kits"))
			return;

		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack currentItem = event.getCurrentItem();

		Bukkit.broadcastMessage("asd");
		
		// Barier is "No permission" || Redstone is not enough money (purchasing
		// is handeled in other class
		if (!new ItemCheck(currentItem).isNotAir().hasItemMeta().hasDisplayName().isNotType(Material.BARRIER)
				.isNotType(Material.REDSTONE).check())
			return;
		Bukkit.broadcastMessage("aasd");

		Kits clickedKit = Arrays.asList(Kits.values()).stream().filter(kit -> kit.getMenuItem().getItemMeta()
				.getDisplayName().equals(currentItem.getItemMeta().getDisplayName())).findFirst().orElse(null);

		clickedKit.give(player);
		player.closeInventory();

		KitPVP.getPlugin().getProfileManager().getProfile(player).setCurrentKit(clickedKit);
	}

}
