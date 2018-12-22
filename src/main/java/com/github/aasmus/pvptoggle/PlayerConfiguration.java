package com.github.aasmus.pvptoggle;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfiguration {

    private YamlConfiguration playerConfig;
    private File playerFile;

    public PlayerConfiguration(UUID playerID){
        this.playerConfig = YamlConfiguration.loadConfiguration(new File(PvPToggle.instance.getDataFolder() + "/data/" + playerID + ".yml"));
        this.playerFile = new File(PvPToggle.instance.getDataFolder() + "/data/" + playerID + ".yml");
    }

    public boolean exists(){
        return playerFile.exists();
    }

    public void setStatus(boolean status){
        //noinspection ResultOfMethodCallIgnored
        new File(PvPToggle.instance.getDataFolder() + "/data/").mkdirs();
        playerConfig.set("status", status);
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getStatus(){
        return playerConfig.getBoolean("status");
    }
}
