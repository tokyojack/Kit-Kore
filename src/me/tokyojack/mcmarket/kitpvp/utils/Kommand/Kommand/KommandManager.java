package me.tokyojack.mcmarket.kitpvp.utils.Kommand.Kommand;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

public class KommandManager {

	private Map<String, Kommand> commands = new HashMap<String, Kommand>();

	public KommandManager() {

	}

	public KommandManager addCommand(Kommand kommand) {
		String name = kommand.getName() == null ? kommand.getClass().getSimpleName().toLowerCase() : kommand.getName();

		kommand.setName(name);
		kommand.setUsage("/" + name);

		this.commands.put(name, kommand);
		return this;
	}

	public void build() {
		this.commands.values().forEach(command -> {
			try {
				Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

				bukkitCommandMap.setAccessible(true);
				CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

				commandMap.register(command.getName(), command);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

}
