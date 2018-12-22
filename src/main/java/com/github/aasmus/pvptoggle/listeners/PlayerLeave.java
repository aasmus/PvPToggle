package com.github.aasmus.pvptoggle.listeners;

import com.github.aasmus.pvptoggle.PlayerConfiguration;
import com.github.aasmus.pvptoggle.PvPToggle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
	
	@EventHandler
    public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		PlayerConfiguration config = new PlayerConfiguration(p.getUniqueId());
		config.setStatus(PvPToggle.players.get(p.getUniqueId()));
		PvPToggle.players.remove(p.getUniqueId()); //remove player from players hash map
	}

}
