package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Util;

public class PlayerJoin implements Listener {
	
	public PlayerJoin() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!PvPToggle.instance.getConfig().getBoolean("SETTINGS.PERSISTENT_PVP_STATE")) {
				PvPToggle.instance.players.put(p.getUniqueId(), PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF")); //add player to players hash map and set their pvp state
				if(PvPToggle.instance.players.get(p.getUniqueId()) == false) {
					if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
						Util.particleEffect(p.getPlayer());	
					}
					if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
	    				Util.ChangeNametag(p.getPlayer(), "&c");
					}
				}	
			} else {
				PvPToggle.instance.dataUtils.addPlayer(p.getPlayer());
				PvPToggle.instance.players.put(p.getUniqueId(), PvPToggle.instance.dataUtils.GetPlayerPvPState(p.getPlayer())); //add player to players hash map and set their pvp state
				if(PvPToggle.instance.players.get(p.getUniqueId()) == false) {
					if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
						Util.particleEffect(p.getPlayer());	
					}
					if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
	    				Util.ChangeNametag(p.getPlayer(), "&c");
					}
				}
			}
		}
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Player p = event.getPlayer();
    	if(!PvPToggle.instance.getConfig().getBoolean("SETTINGS.PERSISTENT_PVP_STATE")) {
        	PvPToggle.instance.players.put(p.getUniqueId(), PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF")); //add player to players hash map and set their pvp state
    		if(PvPToggle.instance.players.get(p.getUniqueId()) == false) {
    			if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
    				Util.particleEffect(p.getPlayer());	
    			}
    			if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
    				Util.ChangeNametag(p.getPlayer(), "&c");
    			}
    		}	
    	} else {
			PvPToggle.instance.dataUtils.addPlayer(p.getPlayer());
			PvPToggle.instance.players.put(p.getUniqueId(), PvPToggle.instance.dataUtils.GetPlayerPvPState(p.getPlayer())); //add player to players hash map and set their pvp state
			if(PvPToggle.instance.players.get(p.getUniqueId()) == false) {
				if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
					Util.particleEffect(p.getPlayer());	
				}
				if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
    				Util.ChangeNametag(p.getPlayer(), "&c");
				}
			}
		}
	}

}
