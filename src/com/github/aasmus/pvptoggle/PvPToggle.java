package com.github.aasmus.pvptoggle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.aasmus.pvptoggle.listeners.PlayerJoin;
import com.github.aasmus.pvptoggle.listeners.PlayerLeave;
import com.github.aasmus.pvptoggle.listeners.PvP;
import com.github.aasmus.pvptoggle.utils.PlaceholderAPIHook;

public class PvPToggle extends JavaPlugin implements Listener {
	
	public FileConfiguration config;
	public static List<String> blockedWorlds;
	public static PvPToggle instance;
	public HashMap<UUID,Boolean> players = new HashMap<>(); //False is pvp on True is pvp off
	public HashMap<UUID,Date> cooldowns = new HashMap<>();
	
	@Override
	public void onEnable() {
		instance = this;
		//save config
		if(config != null) {
			this.saveDefaultConfig();	
		}
		this.config = getConfig();
		
		//register events
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new PvP(), this);
		//register command
		this.getCommand("pvp").setExecutor(new PvPCommand());
		
		blockedWorlds = config.getStringList("SETTINGS.BLOCKED_WORLDS");
		
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new PlaceholderAPIHook(this).register();
		}		
	}

    @Override
    public void onDisable() {
    	
    }

}
