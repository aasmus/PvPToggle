package com.github.aasmus.pvptoggle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	public static void setPvPOn(Player p) {
		PvPToggle.instance.players.replace(p.getUniqueId(), "on");
		p.sendMessage(ChatColor.GREEN + "Your pvp has been enabled!");
	}
	
	public static void setPvPOff(Player p) {
		PvPToggle.instance.players.replace(p.getUniqueId(), "off");
		p.sendMessage(ChatColor.GREEN + "Your pvp has been disabled!");
	}
}
