package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.aasmus.pvptoggle.PvPToggle;


public class PvP implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			if(event.getEntity() instanceof Player) {
				Player damager = (Player) event.getDamager();
				String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId().toString());
				Player attacked = (Player) event.getEntity();
				String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId().toString());
				if(!damagerStatus.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + "You have PvP disabled!");
					return;
				}
				if(!attackedStatus.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has PvP disabled!");
					return;
				}
			}
		}
	}
}
