package me.tokyojack.mcmarket.kitpvp.kits.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import me.tokyojack.mcmarket.kitpvp.kits.Kit;
import me.tokyojack.mcmarket.kitpvp.utils.ItemBuilder;
import me.tokyojack.mcmarket.kitpvp.utils.ItemCheck;

public class Choose extends Kit implements Listener {

	public Choose() {
		super(0, null);
	}

	@Override
	public void armour(PlayerInventory inv) {

	}

	@Override
	public void items(PlayerInventory inv) {
		inv.addItem(new ItemBuilder(Material.CHEST).setName("Pick Kit").toItemStack());
	}

	@Override
	public void extras(Player player) {

	}

	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {
		if (!new ItemCheck(event.getItem()).isNotAir().isType(Material.CHEST).hasItemMeta().hasDisplayName()
				.displayNameEquals("Pick Kit", true).check())
			return;

		event.getPlayer().performCommand("kittest");
	}

}
