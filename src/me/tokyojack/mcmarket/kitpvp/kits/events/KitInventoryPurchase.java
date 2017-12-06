package me.tokyojack.mcmarket.kitpvp.kits.events;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.kits.Kit;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;
import me.tokyojack.mcmarket.kitpvp.utils.ItemCheck;

public class KitInventoryPurchase implements Listener {

	@EventHandler(ignoreCancelled = true)
	private void inventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Kits"))
			return;

		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack currentItem = event.getCurrentItem();

		// Barier is "No permission"
		if (!new ItemCheck(currentItem).isNotAir().hasItemMeta().hasDisplayName().isNotType(Material.BARRIER)
				.isType(Material.REDSTONE).check())
			return;

		Kits clickedKit = Arrays.asList(Kits.values()).stream().filter(kit -> kit.getMenuItem().getItemMeta()
				.getDisplayName().equals(currentItem.getItemMeta().getDisplayName())).findFirst().orElse(null);

		Kit kit = clickedKit.getKit();

		player.sendMessage("-$" + kit.getCostToPurchase());
		KitPVP.getPlugin().getEconomy().withdrawPlayer(player, kit.getCostToPurchase());
		// TODO perm
	}

}
