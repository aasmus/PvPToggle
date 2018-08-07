package com.github.aasmus.pvptoggle.utils;

import java.util.Date;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.PvPToggle;

public class Util {
	
	public static boolean getPlayerState(UUID uuid){
		Boolean result = PvPToggle.players.get(uuid);
		if(result==null) return false;
		else return result;
	}
	
	public static void setPlayerState(UUID uuid,boolean state){
		PvPToggle.players.put(uuid,state);
	}
	
	public static void setCooldownTime(Player p) {
		PvPToggle.cooldowns.put(p.getUniqueId(), new Date());
	}
	
	public static void removeCooldownTime(Player p) {
		PvPToggle.cooldowns.remove(p.getUniqueId());
	}
	
	public static boolean getCooldown(Player p) {
		if(PvPToggle.cooldowns.containsKey(p.getUniqueId())) {
			Date lastChange = PvPToggle.cooldowns.get(p.getUniqueId());
			Date currentTime = new Date();
			int seconds = (int) (currentTime.getTime() - lastChange.getTime())/1000;
			if(seconds > PvPToggle.instance.getConfig().getInt("SETTINGS.COOLDOWN") || p.hasPermission("pvptoggle.bypass")) {
				Util.removeCooldownTime(p);
				return false;
			} else {
				Chat.send(p, "PVP_COOLDOWN", String.valueOf(PvPToggle.instance.getConfig().getInt("SETTINGS.COOLDOWN") - seconds));
				return true;
			}
		} else {
			return false;
		}
	}
}
