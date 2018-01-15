package me.tokyojack.mcmarket.kitpvp.utils.Multipage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/*
 * Created by tokyojack
 * 
 * DON't REMOVE
 * 
 * GITHUB: https://github.com/tokyojack
 * DISCORD: tokyojack#7353
 * 
 */

public class MutltipageClick implements Listener {

	@EventHandler
	private void inventoryClick(InventoryClickEvent event) {
		String fixedInventoryName = clearNumbers(event.getInventory().getName());
		MultipageManager multipageManager = MultipageManager.getInstance();

		if (!multipageManager.getMultipages().containsKey(fixedInventoryName))
			return;

		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		ItemStack currentItem = event.getCurrentItem();

		if (currentItem == null || currentItem.getType() == Material.AIR)
			return;

		Multipage multipage = multipageManager.getMultipages().get(fixedInventoryName);

		String currentItemName = currentItem.getItemMeta().getDisplayName();

		String nextButtonName = multipage.getNextButtonItem().getItemMeta().getDisplayName();
		String backButtonName = multipage.getBackButtonItem().getItemMeta().getDisplayName();

		Map<String, Integer> playerPages = multipageManager.getPlayerPages();

		if (currentItemName.equals(nextButtonName)) {
			playerPages.put(player.getName(),
					(playerPages.containsKey(player.getName()) ? (playerPages.get(player.getName()) + 1) : 2));

			event.setCancelled(true);
		}

		if (currentItemName.equals(backButtonName)) {
			int playerPage = playerPages.get(player.getName());

			if (playerPage == 1) {
				playerPages.remove(player.getName());
			} else {
				playerPages.put(player.getName(), (playerPage - 1));
			}

			event.setCancelled(true);
		}

		multipage.openInventory(player,
				playerPages.containsKey(player.getName()) ? playerPages.get(player.getName()) : 1, true);
	}

	private String clearNumbers(String string) {
		String fixed = string.replaceAll("\\d", "%page%");
		Pattern p = Pattern.compile("%page%");
		Matcher m = p.matcher(fixed);

		StringBuilder builder = new StringBuilder();

		while (m.find())
			builder.append("%page%");

		return fixed.replace(builder.toString(), "%page%");
	}

}
