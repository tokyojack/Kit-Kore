package me.tokyojack.mcmarket.kitpvp.utils.Multipage;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*
 * Created by tokyojack
 * 
 * DON't REMOVE
 * 
 * GITHUB: https://github.com/tokyojack
 * DISCORD: tokyojack#7353
 * 
 */

public class MultipageManager {

	public final static ItemStack DEFAULT_NEXT_BUTTON = createNamedItem(Material.STAINED_GLASS_PANE, 13, "&aNext page");

	public final static ItemStack DEFAULT_BACK_BUTTON = createNamedItem(Material.STAINED_GLASS_PANE, 14, "&cBack page");

	public final static ItemStack DEFAULT_NO_ITEMS = createNamedItem(Material.GLASS_BOTTLE, "&fNo items are here :(");

	private static MultipageManager instance;

	private Map<String, Multipage> multipages;
	private Map<String, Integer> playerPages;

	public MultipageManager() {
		instance = this;
		this.multipages = new HashMap<String, Multipage>();
		this.playerPages = new HashMap<String, Integer>();
	}

	public Map<String, Multipage> getMultipages() {
		return this.multipages;
	}

	public Map<String, Integer> getPlayerPages() {
		return this.playerPages;
	}

	// Not sure if this is the best way todo this. (I'm wanting to keep the same
	// values, without creating a new object each time with new MultipageMain();
	public static MultipageManager getInstance() {
		return instance;
	}

	private static ItemStack createNamedItem(Material mat, String name) {
		ItemStack item = new ItemStack(mat);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(itemMeta);
		return item;
	}

	private static ItemStack createNamedItem(Material mat, int durability, String name) {
		ItemStack item = new ItemStack(mat, 1, (short) durability);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(itemMeta);
		return item;
	}

}
