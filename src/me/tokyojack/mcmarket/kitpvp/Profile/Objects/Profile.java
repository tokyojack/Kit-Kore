package me.tokyojack.mcmarket.kitpvp.Profile.Objects;

import lombok.Getter;
import lombok.Setter;
import me.tokyojack.mcmarket.kitpvp.kits.Kits;

@Getter
@Setter
public class Profile {

	private int totalKills;
	private int currentKillStreak;
	private Kits currentKit;

	public Profile() {
		this.totalKills = 0;
		this.currentKillStreak = 0;
		this.currentKit = Kits.NONE;
	}

	public Profile(int totalKills, int currentKillStreak) {
		this.totalKills = totalKills;
		this.currentKillStreak = currentKillStreak;
	}

	public void addKill() {
		this.totalKills++;
		this.currentKillStreak++;
	}

	public void resetKillStreak() {
		this.currentKillStreak = 0;
	}

}
