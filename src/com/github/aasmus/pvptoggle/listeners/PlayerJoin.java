package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.aasmus.pvptoggle.PvPToggle;

public class PlayerJoin implements Listener {
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Player p = event.getPlayer();
    	PvPToggle.instance.players.put(p.getUniqueId().toString(), "off");
	}

}
