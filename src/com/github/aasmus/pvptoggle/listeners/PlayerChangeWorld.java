package com.github.aasmus.pvptoggle.listeners;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Chat;
import com.github.aasmus.pvptoggle.utils.Util;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        boolean playerPvpEnabled = !Util.getPlayerState(player.getUniqueId());

        // If PVP isn't enabled in the world but the player has it enabled, disable it.
        if (!world.getPVP() && playerPvpEnabled) {
            Util.setPlayerState(player.getUniqueId(), true);
            Chat.send(player, "PVP_WORLD_CHANGE_DISABLED");
            /*if (PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
                Util.particleEffect(player);
            }
			if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
				Util.ChangeNametag(player, "&c");
			}*/
            return;
        }

        // If PVP is required (i.e. the world has PVP enabled and it is in the blocked worlds) and the player has it disabled, enable it.
        if (player.getWorld().getPVP() && PvPToggle.blockedWorlds.contains(world.getName()) && !playerPvpEnabled) {
            Util.setPlayerState(player.getUniqueId(), false);
            Chat.send(player, "PVP_WORLD_CHANGE_REQUIRED");
            if (PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
                Util.particleEffect(player);
            }
			if(PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
				Util.ChangeNametag(player, "&c");
			}
            return;
        }
    }
}
