package me.tokyojack.mcmarket.kitpvp.world.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tokyojack.mcmarket.kitpvp.utils.Chat;

public class PlayerJoinMessage implements Listener {
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(Chat.color("&7&oPlayer " + event.getPlayer().getName() + " has joined."));
	}
}
