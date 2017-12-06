package me.tokyojack.mcmarket.kitpvp.world.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().isOp())
			return;
		event.setCancelled(true);
	}

}
