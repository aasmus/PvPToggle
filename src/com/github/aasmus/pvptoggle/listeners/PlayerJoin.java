package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Util;
import org.bukkit.Bukkit;

public class PlayerJoin implements Listener {
	
	public PlayerJoin() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			PvPToggle.players.put(p.getUniqueId(), PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF")); //add player to players hash map and set their pvp state to off
			if(PvPToggle.players.get(p.getUniqueId()) == false && PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
				Util.particleEffect(p.getPlayer());
			}
		}
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Player p = event.getPlayer();
    	PvPToggle.players.put(p.getUniqueId(), PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF")); //add player to players hash map and set their pvp state to off
		if(PvPToggle.players.get(p.getUniqueId()) == false && PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
			Util.particleEffect(p.getPlayer());
		}
	}

}
