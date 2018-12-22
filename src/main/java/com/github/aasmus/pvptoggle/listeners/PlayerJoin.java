package com.github.aasmus.pvptoggle.listeners;

import com.github.aasmus.pvptoggle.PlayerConfiguration;
import com.github.aasmus.pvptoggle.PvPToggle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		PlayerConfiguration config = new PlayerConfiguration(p.getUniqueId());
		if (config.exists()) {
			PvPToggle.players.put(p.getUniqueId(), config.getStatus());
		} else {
			config.setStatus(true);
			PvPToggle.players.put(p.getUniqueId(), PvPToggle.instance.getConfig().getBoolean("SETTINGS.PVP_DEFAULT_STATE")); //add player to players hash map and set their pvp state to off
		}
	}
}
