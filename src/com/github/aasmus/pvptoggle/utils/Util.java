package com.github.aasmus.pvptoggle.utils;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.aasmus.pvptoggle.PvPToggle;

public class Util {
	
	private static float radius = 1f;
	
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
	
	public static void particleEffect(Player p) {
		new BukkitRunnable() {

			@Override
			public void run() {			
				if(!p.isOnline() || PvPToggle.players.get(p.getUniqueId()) != false) {
					this.cancel();
				} else if(!p.isDead()) {
					double angle = 0;
					Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
					Location location = p.getLocation();
					
					
					for(int i = 0; i < 25; i++) {
						double x = (radius * Math.sin(angle));
						double z = (radius * Math.cos(angle));
						angle += 0.251;
						location.getWorld().spawnParticle(Particle.REDSTONE, location.getX()+x, location.getY(), location.getZ()+z, 0, 0, 1, 0, dustOptions);	
					}
				}
				
			}
			
		}.runTaskTimer(PvPToggle.instance, 0L, 2L);
	}
	
}
