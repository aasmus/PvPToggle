package com.github.aasmus.pvptoggle.listeners;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;

import com.github.aasmus.pvptoggle.PvPToggle;


public class PvP implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			if(event.getEntity() instanceof Player) {
				Player damager = (Player) event.getDamager();
				String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
				Player attacked = (Player) event.getEntity();
				String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
				if(!damagerStatus.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
					return;
				}
				if(!attackedStatus.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
					return;
				}
			}
		} else if(event.getDamager() instanceof Projectile) {
			Projectile arrow = (Projectile) event.getDamager();
			if(arrow.getShooter() instanceof Player) {
				if(event.getEntity() instanceof Player) {
					Player damager = (Player) arrow.getShooter();
					String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
					Player attacked = (Player) event.getEntity();
					String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
					if(!damagerStatus.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
						return;
					}
					if(!attackedStatus.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
						return;
					}
				}
			}
		} else if(event.getDamager() instanceof ThrownPotion) {
			ThrownPotion potion = (ThrownPotion) event.getDamager();
			if(potion.getShooter() instanceof Player) {
				if(event.getEntity() instanceof Player) {
					Player damager = (Player) potion.getShooter();
					String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
					Player attacked = (Player) event.getEntity();
					String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
					if(!damagerStatus.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
						return;
					}
					if(!attackedStatus.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPotionSplash(PotionSplashEvent event) {
		if(event.getPotion().getShooter() instanceof Player) {
			   for(LivingEntity entity : event.getAffectedEntities()) {
			        if(entity instanceof Player) {
			    		Player damager = (Player) event.getPotion().getShooter();
			    		String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
			        	Player attacked = (Player) entity;
			    		String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
			    		if(!damagerStatus.equalsIgnoreCase("on")) {
			    			event.setCancelled(true);
			    			damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
			    			return;
			    		}
			    		if(!attackedStatus.equalsIgnoreCase("on")) {
			    			event.setCancelled(true);
			    			damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
			    			return;
			    		}
			        }
			   }
		}
	}
	
	@EventHandler
	public void onLingeringPotionSplash(LingeringPotionSplashEvent event) {
		if(event.getEntity().getShooter() instanceof Player) {
			if(event.getHitEntity() instanceof Player) {
	    		Player damager = (Player) event.getEntity().getShooter();
	    		String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
	        	Player attacked = (Player) event.getHitEntity();
	    		String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
	    		if(!damagerStatus.equalsIgnoreCase("on")) {
	    			event.setCancelled(true);
	    			damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
	    			return;
	    		}
	    		if(!attackedStatus.equalsIgnoreCase("on")) {
	    			event.setCancelled(true);
	    			damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
	    			return;
	    		}
			}
		}
	}
	
    @EventHandler
    public void onCloudEffects(AreaEffectCloudApplyEvent event) {
    	if(event.getEntity().getSource() instanceof Player) {
    		Iterator<LivingEntity> it = event.getAffectedEntities().iterator();
        	while(it.hasNext()) {
        		LivingEntity entity = it.next();
        		if(entity instanceof Player) {
    	    		Player damager = (Player) event.getEntity().getSource();
    	    		String damagerStatus = PvPToggle.instance.players.get(damager.getUniqueId());
    	        	Player attacked = (Player) entity;
    	    		String attackedStatus = PvPToggle.instance.players.get(attacked.getUniqueId());
    	    		if(!damagerStatus.equalsIgnoreCase("on")) {
    	    			it.remove();
    	    		}
    	    		if(!attackedStatus.equalsIgnoreCase("on")) {
    	    			it.remove();
    	    		}
        		}
        	}
    	}
    }
    
}
