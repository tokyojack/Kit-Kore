package me.tokyojack.mcmarket.kitpvp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemCheck {

	private ItemStack item;

	private List<Boolean> resaults = new ArrayList<Boolean>();

	public ItemCheck(ItemStack item) {
		this.item = item;
	}

	public ItemCheck IsAir() {
		this.resaults.add(this.item == null);
		return this;
	}

	public ItemCheck isNotAir() {
		this.resaults.add(this.item != null);
		return this;
	}

	public ItemCheck hasItemMeta() {
		this.resaults.add(this.item.hasItemMeta());
		return this;
	}

	public ItemCheck doesNotHaveItemMeta() {
		this.resaults.add(!this.item.hasItemMeta());
		return this;
	}

	public ItemCheck hasDisplayName() {
		this.resaults.add(this.item.getItemMeta().hasDisplayName());
		return this;
	}

	public ItemCheck doesNotHaveDisplayName() {
		this.resaults.add(!this.item.getItemMeta().hasDisplayName());
		return this;
	}

	public ItemCheck sizeIsGreaterThan(int number) {
		this.resaults.add(this.item.getAmount() > number);
		return this;
	}

	public ItemCheck sizeIsGreaterThanOrEqualTo(int number) {
		this.resaults.add(this.item.getAmount() >= number);
		return this;
	}

	public ItemCheck sizeIsLessThan(int number) {
		this.resaults.add(this.item.getAmount() < number);
		return this;
	}

	public ItemCheck sizeIsLessThanOrEqualTo(int number) {
		this.resaults.add(this.item.getAmount() <= number);
		return this;
	}

	public ItemCheck sizeIsEqualTo(int number) {
		this.resaults.add(this.item.getAmount() == number);
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemCheck durabilityIsEqualTo(int number) {
		this.resaults.add(this.item.getData().getData() == (byte) number);
		return this;
	}

	public ItemCheck headNameEqualTo(String username) {
		if (!this.item.hasItemMeta()) {
			this.resaults.add(false);
			return this;
		}

		SkullMeta meta = (SkullMeta) this.item.getItemMeta();
		this.resaults.add(meta.getOwner().equalsIgnoreCase(username));
		return this;
	}

	public ItemCheck headNameDoesNotEqualTo(String username) {
		if (!this.item.hasItemMeta()) {
			this.resaults.add(false);
			return this;
		}

		SkullMeta meta = (SkullMeta) this.item.getItemMeta();
		this.resaults.add(!meta.getOwner().equalsIgnoreCase(username));
		return this;
	}

	public ItemCheck hasEnchantment(Enchantment enchantment) {
		this.resaults.add(this.item.containsEnchantment(enchantment));
		return this;
	}

	public ItemCheck doesNotHaveEnchantment(Enchantment enchantment) {
		this.resaults.add(!this.item.containsEnchantment(enchantment));
		return this;
	}

	public ItemCheck containsLoreLine(String line) {
		this.resaults.add(this.item.getItemMeta().getLore().contains(line));
		return this;
	}

	public ItemCheck containsLoreLine(String line, boolean clearColor) {
		this.resaults.add(this.item.getItemMeta().getLore().stream().map(loreLine -> ChatColor.stripColor(loreLine))
				.collect(Collectors.toList()).contains(ChatColor.stripColor(line)));
		return this;
	}

	public ItemCheck doesNotContainsLoreLine(String line) {
		this.resaults.add(!this.item.getItemMeta().getLore().contains(line));
		return this;
	}

	public ItemCheck doesNotContainsLoreLine(String line, boolean clearColor) {
		this.resaults.add(!this.item.getItemMeta().getLore().stream().map(loreLine -> ChatColor.stripColor(loreLine))
				.collect(Collectors.toList()).contains(ChatColor.stripColor(line)));
		return this;
	}

	public ItemCheck displayNameEquals(String text) {
		// Organize

		if (this.item.hasItemMeta()) {
			this.resaults.add(false);
			return this;
		}

		if (this.item.getItemMeta().hasDisplayName()) {
			this.resaults.add(false);
			return this;
		}

		this.resaults.add(this.item.getItemMeta().getDisplayName().equals(text));
		return this;
	}

	public ItemCheck displayNameEquals(String text, boolean clearColor) {

		if (this.item.hasItemMeta()) {
			this.resaults.add(false);
			return this;
		}

		if (this.item.getItemMeta().hasDisplayName()) {
			this.resaults.add(false);
			return this;
		}
		this.resaults.add(ChatColor.stripColor(this.item.getItemMeta().getDisplayName())
				.equalsIgnoreCase(ChatColor.stripColor(text)));
		return this;
	}

	public ItemCheck displayNameEqualsIgnorecase(String text) {
		this.resaults.add(this.item.getItemMeta().getDisplayName().equals(text));
		return this;
	}

	public ItemCheck displayNameEqualsIgnorecase(String text, boolean clearColor) {
		this.resaults.add(ChatColor.stripColor(this.item.getItemMeta().getDisplayName())
				.equalsIgnoreCase(ChatColor.stripColor(text)));
		return this;
	}

	public ItemCheck displayNameDoesNotEquals(String text) {
		this.resaults.add(!this.item.getItemMeta().getDisplayName().equals(text));
		return this;
	}

	public ItemCheck displayNameDoesNotEquals(String text, boolean clearColor) {
		this.resaults.add(!ChatColor.stripColor(this.item.getItemMeta().getDisplayName())
				.equalsIgnoreCase(ChatColor.stripColor(text)));
		return this;
	}

	public ItemCheck displayNameDoesNotEqualsIgnorecase(String text) {
		this.resaults.add(!this.item.getItemMeta().getDisplayName().equals(text));
		return this;
	}

	public ItemCheck displayNameDoesNotEqualsIgnorecase(String text, boolean clearColor) {
		this.resaults.add(!ChatColor.stripColor(this.item.getItemMeta().getDisplayName())
				.equalsIgnoreCase(ChatColor.stripColor(text)));
		return this;
	}

	public ItemCheck isType(Material material) {
		this.resaults.add(this.item.getType() == material);
		return this;
	}

	public ItemCheck isNotType(Material material) {
		this.resaults.add(this.item.getType() != material);
		return this;
	}

	public boolean check() {
		int size = this.resaults.stream().mapToInt(bool -> bool ? 1 : 0).sum();
		return size == this.resaults.size();
	}

}