package com.github.aasmus.pvptoggle.listeners;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerFishEvent;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Chat;


public class PvP implements Listener {

	@EventHandler
	//fired when an entity is hit
	public void onHit(EntityDamageByEntityEvent event) {
		//check if attack was a player
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player && event.getEntity() != null) {
			Player damager = (Player) event.getDamager(); //player who hit
			Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
			Player attacked = (Player) event.getEntity(); //player who was hit
			Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
			if (damagerState == true) { 
				event.setCancelled(true);
				Chat.send(damager, "PVP_DISABLED");
			} else if (attackedState == true) {
				event.setCancelled(true);
				Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
			}
		//checks if damage was done by a projectile
		} else if (event.getDamager() instanceof Projectile) {
			Projectile arrow = (Projectile) event.getDamager();
			if(arrow.getShooter() instanceof Player) {
				if(event.getEntity() instanceof Player && event.getEntity() != null) {
					Player damager = (Player) arrow.getShooter();
					Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
					Player attacked = (Player) event.getEntity();
					Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
					if(damagerState == true) {
						event.setCancelled(true);
						Chat.send(damager, "PVP_DISABLED");
					}else if(attackedState == true) {
						event.setCancelled(true);
						Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
					}
				}
			}
		//checks if damage was done by a potion
		} else if(event.getDamager() instanceof ThrownPotion) {
			ThrownPotion potion = (ThrownPotion) event.getDamager();
			if (potion.getShooter() instanceof Player && event.getEntity() instanceof Player && event.getEntity() != null) {
				Player damager = (Player) potion.getShooter();
				Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
				Player attacked = (Player) event.getEntity();
				Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
				if (damagerState == true) {
					event.setCancelled(true);
					Chat.send(damager, "PVP_DISABLED");
				}else if (attackedState == true) {
					event.setCancelled(true);
					Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
				}
			}
		}
	}
	
	@EventHandler
	//fired when a player is shot with a flaming arrow
	public void onFlameArrow(EntityCombustByEntityEvent event) {
		if(event.getCombuster() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getCombuster();
			if(arrow.getShooter() instanceof Player && event.getEntity() != null) {
				Player damager = (Player) arrow.getShooter();
				Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
				Player attacked = (Player) event.getEntity();
				Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
				if (damagerState == true) {
					event.setCancelled(true);
				}else if (attackedState == true) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	//fired when a splash potion is thrown
	public void onPotionSplash(PotionSplashEvent event) {
		if(event.getPotion().getShooter() instanceof Player) {
			   for(LivingEntity entity : event.getAffectedEntities()) {
			        if(entity instanceof Player && event != null) {
			    		Player damager = (Player) event.getPotion().getShooter();
			    		Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
			        	Player attacked = (Player) entity;
			    		Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
			    		if(damagerState == true) {
			    			event.setCancelled(true);
			    			Chat.send(damager, "PVP_DISABLED");
			    		}else if(attackedState == true) {
			    			event.setCancelled(true);
			    			Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
			    		}
			        }
			   }
		}
	}
	
	@EventHandler
	//fired when a lingering potion is thrown
	public void onLingeringPotionSplash(LingeringPotionSplashEvent event) {
		if(event.getEntity().getShooter() instanceof Player) {
			if(event.getHitEntity() instanceof Player && event.getHitEntity() != null) {
	    		Player damager = (Player) event.getEntity().getShooter();
	    		Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
	        	Player attacked = (Player) event.getHitEntity();
	    		Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
	    		if(damagerState == true) {
	    			event.setCancelled(true);
	    			Chat.send(damager, "PVP_DISABLED");
	    		} else if(attackedState == true) {
	    			event.setCancelled(true);
	    			Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
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
        		if(entity instanceof Player && entity != null) {
    	    		Player damager = (Player) event.getEntity().getSource();
    	    		Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
    	        	Player attacked = (Player) entity;
    	    		Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
    	    		if(attackedState == true)
    	    			it.remove();
    	    		else if(damagerState == true)
    	    			it.remove();
        		}
        	}
    	}
    }
    
    @EventHandler
    //fired when a player uses a fishing rod
    public void onPlayerFishing (final PlayerFishEvent event) {
        if (event.getCaught() instanceof Player && event.getCaught() != null) {
            final Player damager = event.getPlayer();
            Boolean damagerState = PvPToggle.players.get(damager.getUniqueId());
            final Player attacked = (Player) event.getCaught();
            Boolean attackedState = PvPToggle.players.get(attacked.getUniqueId());
            if (damager.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD || damager.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
    			if (damagerState == true) {
    				event.setCancelled(true);
    				Chat.send(damager, "PVP_DISABLED");
    			} else if (attackedState == true) {
    				event.setCancelled(true);
    				Chat.send(damager, "PVP_DISABLED_OTHERS", attacked.getDisplayName());
    			}
            }
        }
    }
    
}
