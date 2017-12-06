package me.tokyojack.mcmarket.kitpvp.utils.Gependacy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Gependecy {

	private String dependecyPluginName;
	private JavaPlugin javaPlugin;
	private boolean isRequired;

	private boolean isFound;

	public Gependecy(String dependecyPluginName, boolean isRequired, JavaPlugin javaPlugin) {
		this.dependecyPluginName = dependecyPluginName;
		this.javaPlugin = javaPlugin;
		this.isRequired = isRequired;

		this.isFound = false;
	}

	public abstract void ifFound();

	public abstract void ifNotFound();

	public void check() {
		if (Bukkit.getPluginManager().getPlugin(this.dependecyPluginName) == null
				|| !Bukkit.getPluginManager().getPlugin(this.dependecyPluginName).isEnabled()) {

			if (isRequired)
				Bukkit.getPluginManager().disablePlugin(this.javaPlugin);

			String finalText = isRequired ? "Plugin is disabeling" : "Luckily, that plugin is optional";
			logToConsole(
					ChatColor.RED + "The plugin '" + this.dependecyPluginName + "' is not found. " + finalText + ".");

			ifNotFound();
		} else {
			logToConsole(ChatColor.GREEN + "The plugin '" + this.dependecyPluginName + "' has been found.");
			ifFound();
		}
	}

	public boolean isFound() {
		return this.isFound;
	}

	private void logToConsole(String message) {
		final String PREFIX = ChatColor.AQUA + "[Gependecy] [" + this.javaPlugin.getDescription().getName() + "]";
		Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + " " + message);
	}
}
