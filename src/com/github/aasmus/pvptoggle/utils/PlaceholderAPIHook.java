package com.github.aasmus.pvptoggle.utils;

import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.PvPToggle;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;


public class PlaceholderAPIHook extends PlaceholderExpansion {

	private PvPToggle plugin;
	
	public PlaceholderAPIHook(PvPToggle plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public String onPlaceholderRequest(Player player, String identifier) {
		if(player == null) { return ""; }
		
		//Placeholder: %pvptoggle_positive_rep%
		if(identifier.equals("pvp_state")) {
			return PvPToggle.instance.players.get(player.getUniqueId()) ? PvPToggle.instance.getConfig().getString("MESSAGES.PLACEHOLDER_OFF") : PvPToggle.instance.getConfig().getString("MESSAGES.PLACEHOLDER_ON");
		}
		
		return null;
	}
	
	@Override
	public boolean persist() {
		return true;
	}
	
	@Override
	public boolean canRegister() {
		return true;
	}
	
	@Override
	public String getIdentifier() {
		return "PvPToggle";
	}

	@Override
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}


	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

}
