package me.tokyojack.mcmarket.kitpvp.utils.Kommand.Kommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.defaults.BukkitCommand;

public abstract class Kommand extends BukkitCommand {

	public Kommand() {
		super(null, "No description", null, new ArrayList<String>());
	}

	public Kommand(String description) {
		super(null, description, null, new ArrayList<String>());
	}

	public Kommand(List<String> alias) {
		super(null, "No description", null, alias);
	}

	public Kommand(String description, List<String> alias) {
		super(null, description, null, alias);
	}

	public Kommand(String commandName, String description) {
		super(commandName, description, null, new ArrayList<String>());
	}

	public Kommand(String commandName, String description, List<String> alias) {
		super(commandName, description, null, alias);
	}
}
