package me.tokyojack.mcmarket.kitpvp.bounty;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Getter;

@Getter
public class BountyManager {

	private LinkedHashMap<UUID, Integer> bountyPlayers;

	public BountyManager() {
		this.bountyPlayers = new LinkedHashMap<UUID, Integer>();
	}

	public void addBounty(UUID playerUUID, int amount) {
		if (bountyPlayers.containsKey(playerUUID)) {
			int pastAmount = this.bountyPlayers.get(playerUUID);
			this.bountyPlayers.put(playerUUID, pastAmount + amount);
		} else
			this.bountyPlayers.put(playerUUID, amount);
	}

	public boolean hasBounty(Player player) {
		return this.bountyPlayers.containsKey(player.getUniqueId());
	}

	public boolean hasBounty(UUID playerUUID) {
		return this.bountyPlayers.containsKey(playerUUID);
	}

	public int getBounty(UUID playerUUID) {
		return this.bountyPlayers.get(playerUUID);
	}

	public void removeBounty(Player player) {
		this.bountyPlayers.remove(player.getUniqueId());
	}

	public void test() {
		for (int i = 0; i < 20; i++) {
			this.bountyPlayers.put(UUID.randomUUID(), RandomNumber(1000));
		}
	}

	private int RandomNumber(int s) {
		Random random = new Random();
		return random.nextInt(s) + 1;
	}
}
