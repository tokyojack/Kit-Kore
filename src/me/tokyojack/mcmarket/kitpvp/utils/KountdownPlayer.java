package me.tokyojack.mcmarket.kitpvp.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Created by tokyojack
 * 
 * DON't REMOVE
 * 
 * GITHUB: https://github.com/tokyojack
 * DISCORD: tokyojack#7353
 * 
 */

public abstract class KountdownPlayer {

	private Map<String, Integer> players;
	private int tickDelay; // May want to change this name

	private int runnableID;
	private JavaPlugin plugin;

	public KountdownPlayer(JavaPlugin plugin) {
		this.players = new HashMap<String, Integer>();
		this.tickDelay = 20;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	public KountdownPlayer(int tickDelay, JavaPlugin plugin) {
		this.players = new HashMap<String, Integer>();
		this.tickDelay = tickDelay;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	public KountdownPlayer(JavaPlugin plugin, int tickDelay) {
		this.players = new HashMap<String, Integer>();
		this.tickDelay = tickDelay;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	protected abstract void start(Player player);

	protected abstract void tick(Player player, int seconds);

	protected abstract void stop(Player player);

	protected abstract void finish(Player player);

	public void startPlayer(Player player, int time) {
		this.players.put(player.getName(), time);
		start(player);

		if (this.runnableID == -1)
			this.startPlayerRunnable();
	}

	public void stopPlayer(Player player) {
		this.players.remove(player.getName());
		stop(player);

		if (this.isEmpty())
			this.stopPlayerRunnable();
	}

	public void finishPlayer(Player player) {
		this.players.remove(player.getName());
		finish(player);

		if (this.isEmpty())
			this.stopPlayerRunnable();
	}

	public void addTimeToPlayer(Player player, int newAmount) {
		int pastAmount = this.players.get(player.getName());
		this.players.put(player.getName(), pastAmount + newAmount);
	}

	public void subtractTimeFromPlayer(Player player, int newAmount) {
		int pastAmount = this.players.get(player.getName());
		this.players.put(player.getName(), pastAmount - newAmount);
	}

	public void setTickDelay(int ticks) {
		this.tickDelay = ticks;
	}

	public int getTickDelay() {
		return this.tickDelay;
	}

	public int getPlayerTime(Player player) {
		return this.players.get(player.getName());
	}

	public Map<String, Integer> getPlayers() {
		return this.players;
	}

	public boolean containsPlayer(Player player) {
		return this.players.containsKey(player.getName());
	}

	public Boolean isPlayerStarted(Player player) {
		return this.players.containsKey(player.getName());
	}

	public Boolean isRunnableStarted() {
		return this.runnableID != -1;
	}

	public Boolean isEmpty() {
		return this.players.isEmpty();
	}

	private void startPlayerRunnable() {
		this.runnableID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() {
				Iterator<Map.Entry<String, Integer>> it = players.entrySet().iterator();
				while (it.hasNext()) { // Gotta do a ireator as I'm removing
										// while looping
					Map.Entry<String, Integer> pair = it.next();

					String playerName = pair.getKey();
					int time = pair.getValue();
					Player player = Bukkit.getPlayer(playerName);

					if (player != null) {
						if (time <= 0) {
							finishPlayer(player);
							continue;
						}

						tick(player, time);
						players.put(playerName, time - 1);
					}

				}
			}
		}, 0, this.tickDelay);
	}

	private void stopPlayerRunnable() {
		Bukkit.getScheduler().cancelTask(this.runnableID);
		this.runnableID = -1;
	}

}