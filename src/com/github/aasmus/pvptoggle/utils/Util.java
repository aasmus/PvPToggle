package com.github.aasmus.pvptoggle.utils;

import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.PvPToggle;

public class Util {
	
	public static void setPvPOn(Player p) {
		PvPToggle.instance.players.replace(p.getUniqueId(), "on");
		Chat.send(p, "PVP_STATE_ENABLED");
	}
	
	public static void setPvPOff(Player p) {
		PvPToggle.instance.players.replace(p.getUniqueId(), "off");
		Chat.send(p, "PVP_STATE_DISABLED");
	}
}
