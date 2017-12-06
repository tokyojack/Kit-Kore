package me.tokyojack.mcmarket.kitpvp.world.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.tokyojack.mcmarket.kitpvp.utils.Chat;

public class CustomWhitelistMessage implements Listener {

	@EventHandler
	public void handleKicks(PlayerLoginEvent event) {
		if (event.getResult() != Result.KICK_WHITELIST)
			return;

		event.setKickMessage(Chat.color("Server is whitelisted :((((((((((((("));
	}
}
