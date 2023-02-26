package com.github.aasmus.pvptoggle.utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.PvPToggle;

public class PersistentData {
	
	private File dir;
	
	public PersistentData(File file) {
		file.mkdir();
		this.dir = file;
	}
	
	public void addPlayer(Player p) {
		File file = new File(dir.getPath(), p.getUniqueId() + ".yml");
		
		if(!file.exists()) {
			try {
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
				playerData.createSection("PvPState");
				playerData.set("PvPState", PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF"));
				playerData.save(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void UpdatePlayerPvPState(Player p) {
		File file = new File(dir.getPath(), p.getUniqueId() + ".yml");
		try {
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
			playerData.set("PvPState", PvPToggle.instance.players.get(p.getUniqueId()));
			playerData.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean GetPlayerPvPState(Player p) {
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(new File(dir.getPath(), p.getUniqueId() + ".yml"));
		return playerData.getBoolean("PvPState");
	}

}
