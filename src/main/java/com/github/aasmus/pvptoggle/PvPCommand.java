package com.github.aasmus.pvptoggle;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.utils.Chat;
import com.github.aasmus.pvptoggle.utils.Util;

public class PvPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) { //check if command sender is console
			ConsoleCommandSender console = (ConsoleCommandSender) sender;
			if(args.length == 0) {
				Chat.send(console, "HELP_HEADER");
				Chat.send(console, "HELP_SET_OTHERS");
			} else {
				try {
					Player other = Bukkit.getPlayerExact(args[1]);
					if(other == null) { //make sure the player is online
					    Chat.send(console, "NO_PLAYER", args[1]);
					} else { //set pvp state
						Boolean current = PvPToggle.players.get(other.getUniqueId());
						if(args[0].equals("toggle")) {
							if(current) {
								Util.setPlayerState(other.getUniqueId(), false);
								Chat.send(other, "PVP_STATE_ENABLED");
							} else {
								Util.setPlayerState(other.getUniqueId(), true);
								Chat.send(other, "PVP_STATE_DISABLED");
							}
						} else if(args[0].equalsIgnoreCase("on")) {
							Util.setPlayerState(other.getUniqueId(), false);
							Chat.send(other, "PVP_STATE_ENABLED");
						} else if(args[0].equalsIgnoreCase("off")) {
							Util.setPlayerState(other.getUniqueId(), true);
							Chat.send(other, "PVP_STATE_DISABLED");
						}
						current = PvPToggle.players.get(other.getUniqueId());
						Chat.send(console, "PVP_STATE_CHANGED_OTHERS", other.getDisplayName(), current);
					}		
				} catch (Exception e) {
					//nothing needs to be done
				}
			}
		} else if(sender instanceof Player) { //check if command sender is player
			if(cmd.getName().equalsIgnoreCase("pvp")) {
				Player p = (Player) sender;
				if(args.length == 0) {
					Chat.send(p, "HELP_HEADER");
					Chat.send(p, "HELP_GENERAL_USEAGE");
					if(p.hasPermission("pvptoggle.others"))
						Chat.send(p, "HELP_VIEW_OTHERS");
					if(p.hasPermission("pvptoggle.others.set"))
						Chat.send(p, "HELP_SET_OTHERS");
				} else if(args.length == 1) {
					if(p.hasPermission("pvptoggle.allow")) {
						
						if(!Util.getCooldown(p) || p.hasPermission("pvptoggle.bypass")) {
							Boolean current = PvPToggle.players.get(p.getUniqueId());
							if(args[0].equals("toggle")) {
								Util.setCooldownTime(p);
								if(current) {
									Util.setPlayerState(p.getUniqueId(), false);
									Chat.send(p, "PVP_STATE_ENABLED");
								} else {
									Util.setPlayerState(p.getUniqueId(), true);
									Chat.send(p, "PVP_STATE_DISABLED");
								}
							} else if(args[0].equalsIgnoreCase("on")) {
								Util.setCooldownTime(p);
								Util.setPlayerState(p.getUniqueId(), false);
								Chat.send(p, "PVP_STATE_ENABLED");
							} else if(args[0].equalsIgnoreCase("off")) {
								Util.setCooldownTime(p);
								Util.setPlayerState(p.getUniqueId(), true);
								Chat.send(p, "PVP_STATE_DISABLED");
							} else if(args[0].equalsIgnoreCase("status")) {
								Chat.send(p, "PVP_STATUS", null, current);
							} else {
								if(sender.hasPermission("pvptoggle.others")) {
									Player other = Bukkit.getPlayerExact(args[0]);
									if(other == null) {
										Chat.send(p, "NO_PLAYER", args[0]);
									} else {
										current = PvPToggle.players.get(other.getUniqueId());
										Chat.send(p, "PVP_STATUS_OTHERS", other.getDisplayName(), current);
									}
								} else {
									Chat.send(p, "COMMAND_INVALID_PARAMETER", args[0]);
								}
							}
						}
							
					}
				} else if(args.length == 2) {
					if(sender.hasPermission("pvptoggle.others.set")) {
						Player other = Bukkit.getPlayerExact(args[1]);
						if(other == null) {
							Chat.send(p, "NO_PLAYER", args[1]);
						} else {
							Boolean current = PvPToggle.players.get(other.getUniqueId());
							if(args[0].equals("toggle")) {
								if(current) {
									Util.setPlayerState(other.getUniqueId(), false);
									Chat.send(other, "PVP_STATE_ENABLED");
								} else {
									Util.setPlayerState(other.getUniqueId(), true);
									Chat.send(other, "PVP_STATE_DISABLED");
								}
							} else if(args[0].equalsIgnoreCase("on")) {
								Util.setPlayerState(other.getUniqueId(), false);
								Chat.send(other, "PVP_STATE_ENABLED");
							} else if(args[0].equalsIgnoreCase("off")) {
								Util.setPlayerState(other.getUniqueId(), true);
								Chat.send(other, "PVP_STATE_DISABLED");
							}
							current = PvPToggle.players.get(other.getUniqueId());
							Chat.send(p, "PVP_STATE_CHANGED_OTHERS", other.getDisplayName(), current);
						}
					} else {
						Chat.send(p, "COMMAND_NO_PERMISSION");
					}
				}
			}
			return true;
		}
		return false;
	}
	
}
