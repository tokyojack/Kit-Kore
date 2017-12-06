package me.tokyojack.mcmarket.kitpvp.bounty.commands.subCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.kitpvp.KitPVP;
import me.tokyojack.mcmarket.kitpvp.bounty.BountyManager;
import me.tokyojack.mcmarket.kitpvp.utils.Chat;
import me.tokyojack.mcmarket.kitpvp.utils.Kommand.SubKommand.SubKommand;
import net.milkbowl.vault.economy.Economy;

public class Add extends SubKommand {

	public Add() {
		super("Adds a bounty to a player", new ArrayList<String>(Arrays.asList("set")));
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender commandSender, String[] args) {
		if (args.length == 0) {
			commandSender.sendMessage("/bounty add <amount> <player>");
			return false;
		}

		if (!isNumber(args[0])) {
			commandSender.sendMessage("You must put a number for the amount.");
			return false;
		}

		int amount = Integer.parseInt(args[0]);

		if (amount <= 0) {
			commandSender.sendMessage(Chat.color("The number must be over 0"));
			return false;
		}

		UUID targetedPlayerUUID = getUUIDFromPlayername(args[1]);

		BountyManager bountyManager = KitPVP.getPlugin().getBountyManager();

		if ((!bountyManager.hasBounty(targetedPlayerUUID)) && Bukkit.getPlayer(args[1]) == null) {
			commandSender.sendMessage("That player must be online to add a bounty!");
			return false;
		}

		Economy economy = KitPVP.getPlugin().getEconomy();

		if (economy.getBalance(args[1]) < amount) {
			commandSender.sendMessage("You don't have enough money!");
			return false;
		}

		economy.withdrawPlayer(args[1], amount);
		commandSender.sendMessage("You've added " + amount + " to " + args[1]);
		bountyManager.addBounty(targetedPlayerUUID, amount);

		return true;
	}

	@SuppressWarnings("deprecation")
	private UUID getUUIDFromPlayername(String playerName) {
		Player player = Bukkit.getPlayer(playerName);
		return player != null ? player.getUniqueId() : Bukkit.getOfflinePlayer(playerName).getUniqueId();
	}

	private boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException exception) {
			return false;
		}
	}
}
