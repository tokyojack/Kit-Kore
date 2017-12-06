package me.tokyojack.mcmarket.kitpvp.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public enum Console {
	ERROR(ChatColor.RED + "[ERROR]"),
	SUCCESS(ChatColor.GREEN + "[SUCCESS]"),
	INFO(ChatColor.WHITE + "[INFO]");

	// Could get the JavaPlugin instance and then get the plugin name off the
	// config you want.
	private final String PLUGIN_NAME = ChatColor.GOLD + "[PLUGIN NAME]";
	private String prefix;

	private Console(String prefix) {
		this.prefix = prefix;
	}

	public void log(Object... values) {
		Bukkit.getServer().getConsoleSender()
				.sendMessage(PLUGIN_NAME + " " + this.prefix + ": " + mergeAllValuesIntoString(values));
	}

	private String mergeAllValuesIntoString(Object[] objects) {
		StringBuilder stringBuilder = new StringBuilder();
		Arrays.asList(objects).stream().forEach(value -> stringBuilder.append(value.toString() + " "));
		return stringBuilder.toString();
	}
}