package me.tokyojack.mcmarket.kitpvp.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.kits.kits.Choose;
import me.tokyojack.mcmarket.kitpvp.kits.kits.None;
import me.tokyojack.mcmarket.kitpvp.kits.kits.Warrior;
import me.tokyojack.mcmarket.kitpvp.utils.ItemBuilder;

@Getter
public enum Kits {
	WARRIOR(new Warrior(), new ItemBuilder(Material.IRON_SWORD).setName("Warrior").toItemStack()),
	CHOOSE(new Choose(), new ItemBuilder(Material.CHEST).setName("Choose").toItemStack()),
	NONE(new None(), new ItemBuilder(Material.GLASS_BOTTLE).setName("None").toItemStack(), KitAttribute.HIDDEN);

	private Kit kit;
	private ItemStack menuItem;
	private List<KitAttribute> kitAtributes;

	private Kits(Kit kit, ItemStack menuItem, KitAttribute... kitAtribute) {
		this.kit = kit;
		this.menuItem = menuItem;
		this.kitAtributes = Arrays.asList(kitAtribute);
	}

	private Kits(Kit kit, ItemStack menuItem) {
		this(kit, menuItem, new KitAttribute[] {});
	}

	public String getKitName() {
		return this.kit.getClass().getSimpleName();
	}

	public ItemStack getMenuItem(Player player) {
		if (!player.hasPermission(this.kit.getKitPermission() == null ? "" : this.kit.getKitPermission()))
			return new ItemBuilder(Material.BARRIER)
					.setName("You don't have the permission for this kit: " + this.kit.getKitName()).toItemStack();

		if (!hasEnoughMoney(player))
			return new ItemBuilder(Material.REDSTONE)
					.setName("You need: " + this.kit.getCostToPurchase() + " to buy this kit!").toItemStack();

		return this.menuItem;
	}

	public void give(Player player) {
		if (this.kitAtributes.contains(KitAttribute.OP_ONLY)) {
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED + "You must be op to use this command.");
				return;
			}
		}
		this.kit.give(player);
	}

	// Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

	public static void register() {
		Arrays.asList(Kits.values()).stream().map(kit -> kit).filter(kit -> kit.getKit() instanceof Listener)
				.forEach(kit -> Bukkit.getServer().getPluginManager().registerEvents((Listener) kit.getKit(),
						KitPVP.getPlugin()));
	}

	private boolean hasEnoughMoney(Player player) {
		int cost = this.kit.getCostToPurchase() < 0 ? 0 : this.kit.getCostToPurchase();
		return cost == 0 ? true : KitPVP.getPlugin().getEconomy().getBalance(player) >= cost;
	}
}
