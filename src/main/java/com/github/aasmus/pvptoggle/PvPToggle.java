package com.github.aasmus.pvptoggle;

import com.github.aasmus.pvptoggle.listeners.PlayerJoin;
import com.github.aasmus.pvptoggle.listeners.PlayerLeave;
import com.github.aasmus.pvptoggle.listeners.PvP;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PvPToggle extends JavaPlugin implements Listener {
	
	private FileConfiguration config = getConfig();
	public static PvPToggle instance;
	public static HashMap<UUID,Boolean> players = new HashMap<>();
	public static HashMap<UUID,Date> cooldowns = new HashMap<>();
	
	@Override
	public void onEnable() {
		instance = this;
		//save config
		if(config != null) {
			this.saveDefaultConfig();	
		}
		//register events
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new PvP(), this);
		//register command
		this.getCommand("pvp").setExecutor(new PvPCommand());
	}

    @Override
    public void onDisable() {
    	
    }

}
