package com.github.aasmus.pvptoggle.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;

public class PVPToggleEvent extends Event {

    private final Player player;
    private final boolean pvp;

    public PVPToggleEvent(Player player, boolean pvp) {
        this.player = player;
        this.pvp = pvp;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getPVP() {
        return this.pvp;
    }

}