package com.github.aasmus.pvptoggle.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.github.aasmus.pvptoggle.PvPToggle;

import me.clip.placeholderapi.external.EZPlaceholderHook;

public class PlaceholderAPIHook extends EZPlaceholderHook {

	public PlaceholderAPIHook(Plugin plugin) {
		super(plugin, "pvptoggle");
	}
	
	public String onPlaceholderRequest(Player player, String identifier) {
		if(player == null) { return ""; }
		
		//Placeholder: %pvptoggle_positive_rep%
		if(identifier.equals("pvp_state")) {
			return PvPToggle.players.get(player.getUniqueId()) ? "&aOff" : "&cOn";
		}
		
		return null;
	}

}
