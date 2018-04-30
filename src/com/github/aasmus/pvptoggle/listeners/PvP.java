package com.github.aasmus.pvptoggle.listeners;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerFishEvent;

import com.github.aasmus.pvptoggle.PvPToggle;


public class PvP implements Listener {

	@EventHandler
	//fired when an entity is hit
	public void onHit(EntityDamageByEntityEvent event) {
		//check if attack was a player
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			Player damager = (Player) event.getDamager(); //player who hit
			String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
			Player attacked = (Player) event.getEntity(); //player who was hit
			String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
			if (!damagerState.equalsIgnoreCase("on")) { 
				event.setCancelled(true);
				damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
			}else if (!attackedState.equalsIgnoreCase("on")) {
				event.setCancelled(true);
				damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
			}
		//checks if damage was done by a projectile
		} else if (event.getDamager() instanceof Projectile) {
			Projectile arrow = (Projectile) event.getDamager();
			if(arrow.getShooter() instanceof Player) {
				if(event.getEntity() instanceof Player) {
					Player damager = (Player) arrow.getShooter();
					String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
					Player attacked = (Player) event.getEntity();
					String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
					if(!damagerState.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
					}else if(!attackedState.equalsIgnoreCase("on")) {
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
					}
				}
			}
		//checks if damage was done by a potion
		} else if(event.getDamager() instanceof ThrownPotion) {
			ThrownPotion potion = (ThrownPotion) event.getDamager();
			if (potion.getShooter() instanceof Player && event.getEntity() instanceof Player) {
				Player damager = (Player) potion.getShooter();
				String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
				Player attacked = (Player) event.getEntity();
				String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
				if (!damagerState.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
				}else if (!attackedState.equalsIgnoreCase("on")) {
					event.setCancelled(true);
					damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
				}
			}
		}
	}
	
	@EventHandler
	//fired when a splash potion is thrown
	public void onPotionSplash(PotionSplashEvent event) {
		if(event.getPotion().getShooter() instanceof Player) {
			   for(LivingEntity entity : event.getAffectedEntities()) {
			        if(entity instanceof Player) {
			    		Player damager = (Player) event.getPotion().getShooter();
			    		String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
			        	Player attacked = (Player) entity;
			    		String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
			    		if(!damagerState.equalsIgnoreCase("on")) {
			    			event.setCancelled(true);
			    			damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
			    		}else if(!attackedState.equalsIgnoreCase("on")) {
			    			event.setCancelled(true);
			    			damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
			    		}
			        }
			   }
		}
	}
	
	@EventHandler
	//fired when a lingering potion is thrown
	public void onLingeringPotionSplash(LingeringPotionSplashEvent event) {
		if(event.getEntity().getShooter() instanceof Player) {
			if(event.getHitEntity() instanceof Player) {
	    		Player damager = (Player) event.getEntity().getShooter();
	    		String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
	        	Player attacked = (Player) event.getHitEntity();
	    		String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
	    		if(!damagerState.equalsIgnoreCase("on")) {
	    			event.setCancelled(true);
	    			damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
	    		} else if(!attackedState.equalsIgnoreCase("on")) {
	    			event.setCancelled(true);
	    			damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
	    		}
			}
		}
	}
	
    @EventHandler
    //fired when lingering potion cloud is active
    public void onCloudEffects(AreaEffectCloudApplyEvent event) {
    	if(event.getEntity().getSource() instanceof Player) {
    		Iterator<LivingEntity> it = event.getAffectedEntities().iterator();
        	while(it.hasNext()) {
        		LivingEntity entity = it.next();
        		if(entity instanceof Player) {
    	    		Player damager = (Player) event.getEntity().getSource();
    	    		String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
    	        	Player attacked = (Player) entity;
    	    		String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
    	    		if(!attackedState.equalsIgnoreCase("on"))
    	    			it.remove();
    	    		else if(!damagerState.equalsIgnoreCase("on"))
    	    			it.remove();
        		}
        	}
    	}
    }
    
    @EventHandler
    //fired when a player uses a fishing rod
    public void onPlayerFishing (final PlayerFishEvent event) {
        final Player damager = event.getPlayer();
        String damagerState = PvPToggle.instance.players.get(damager.getUniqueId());
        if (event.getCaught() instanceof Player) {
            final Player attacked = (Player) event.getCaught();
            String attackedState = PvPToggle.instance.players.get(attacked.getUniqueId());
            if (damager.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD || damager.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
    			if (!damagerState.equalsIgnoreCase("on")) {
    				event.setCancelled(true);
    				damager.sendMessage(ChatColor.RED + "You have pvp disabled!");
    			} else if (!attackedState.equalsIgnoreCase("on")) {
    				event.setCancelled(true);
    				damager.sendMessage(ChatColor.RED + attacked.getDisplayName() + " has pvp disabled!");
    			}
            }
        }
    }
    
}
