package me.tokyojack.mcmarket.kitpvp.Profile;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.kitpvp.Profile.Objects.Profile;

public class ProfileManager {

	private Map<String, Profile> profiles;

	public ProfileManager() {
		this.profiles = new HashMap<String, Profile>();
	}

	public Profile getProfile(Player player) {
		return this.profiles.get(player.getName());
	}

	public void addProfile(Player player) {
		if (this.profiles.containsKey(player.getName()))
			return;

		this.profiles.put(player.getName(), new Profile());
	}

	public void removeProfile(Player player) {
		if (!this.profiles.containsKey(player.getName()))
			return;

		this.profiles.remove(player.getName());
	}

}
