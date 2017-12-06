package me.tokyojack.mcmarket.kitpvp.kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import lombok.Getter;

@Getter
public abstract class Kit {

	private String kitName;
	private int costToPurchase;
	private String kitPermission;
	private List<KitAttribute> kitAttributes;

	//May not need some of these constructors
	
	public Kit(String kitName, int costToPurchase, String kitPermission, List<KitAttribute> kitAttributes) {
		this.kitName = kitName;
		this.costToPurchase = costToPurchase;
		this.kitPermission = kitPermission;
		this.kitAttributes = kitAttributes;
	}

	public Kit(String kitName, int costToPurchase, String kitPermission, KitAttribute... kitAttributes) {
		this(kitName, costToPurchase, kitPermission, Arrays.asList(kitAttributes));
	}

	public Kit(int costToPurchase, String kitPermission, KitAttribute... kitAttributes) {
		this("", costToPurchase, kitPermission, Arrays.asList(kitAttributes));
		this.kitName = this.getClass().getSimpleName();
	}

	public Kit(int costToPurchase, String kitPermission, List<KitAttribute> kitAttributes) {
		this("", costToPurchase, kitPermission, new ArrayList<>(kitAttributes));
		this.kitName = this.getClass().getSimpleName();
	}

	public Kit(String kitName, int costToPurchase, String kitPermission) {
		this(kitName, costToPurchase, kitPermission, new ArrayList<KitAttribute>());
	}

	public Kit(int costToPurchase, String kitPermission) {
		this("", costToPurchase, kitPermission);
		this.kitName = this.getClass().getSimpleName();
	}

	public abstract void armour(PlayerInventory inv);

	public abstract void items(PlayerInventory inv);

	public abstract void extras(Player player);

	public void give(Player player) {
		resetPlayer(player);

		PlayerInventory inv = player.getInventory();

		armour(inv);
		items(inv);
		player.updateInventory();

		extras(player);

		player.sendMessage("You've gained kit: " + this.kitName);
	}

	private void resetPlayer(Player player) {
		player.setMaxHealth(20);
		player.setHealth(20);
		player.setExhaustion(20);
		player.setFireTicks(0);
		player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));

		PlayerInventory playerInventory = player.getInventory();

		playerInventory.setHelmet(new ItemStack(Material.AIR));
		playerInventory.setHelmet(new ItemStack(Material.AIR));
		playerInventory.setHelmet(new ItemStack(Material.AIR));
		playerInventory.setHelmet(new ItemStack(Material.AIR));
		playerInventory.clear();

		player.updateInventory();
	}

}
