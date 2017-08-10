package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.aasmus.pvptoggle.PvPToggle;

public class PlayerLeave implements Listener {
	
	@EventHandler
    public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		PvPToggle.instance.players.remove(p.getUniqueId().toString());
	}

}
