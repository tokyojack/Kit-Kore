package me.tokyojack.mcmarket.kitpvp.kits.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.tokyojack.mcmarket.kitpvp.kits.Kit;
import me.tokyojack.mcmarket.kitpvp.utils.ItemBuilder;

public class Warrior extends Kit {

	public Warrior() {
		super(0, null);
	}

	@Override
	public void armour(PlayerInventory inv) {
		inv.setHelmet(new ItemStack(Material.IRON_HELMET));
		inv.setHelmet(new ItemStack(Material.IRON_CHESTPLATE));
		inv.setHelmet(new ItemStack(Material.IRON_LEGGINGS));
		inv.setHelmet(new ItemStack(Material.IRON_BOOTS));
	}

	@Override
	public void items(PlayerInventory inv) {
		inv.addItem(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack());
	}

	@Override
	public void extras(Player player) {

	}
}
