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
			Player other = Bukkit.getPlayerExact(args[1]);
			if(other == null) { //make sure the player is online
			    Chat.send(console, "NO_PLAYER", args[1]);
			} else { //set pvp state
				String current = PvPToggle.instance.players.get(other.getUniqueId());
				if(args[0].equals("toggle")) {
					if(current.equalsIgnoreCase("off"))
						Util.setPvPOn(other);
					else if(current.equalsIgnoreCase("off"))
						Util.setPvPOff(other);
				} else if(args[0].equalsIgnoreCase("on")) {
					if(!current.equalsIgnoreCase("on"))
						Util.setPvPOn(other);
					else
						Chat.send(console, "PVP_ALREADY_ON_OTHERS", args[0]);
				} else if(args[0].equalsIgnoreCase("off")) {
					if(!current.equalsIgnoreCase("off"))
						Util.setPvPOff(other);
					else
						Chat.send(console, "PVP_ALREADY_OFF_OTHERS", args[0]);
				}
				current = PvPToggle.instance.players.get(other.getUniqueId());
				Chat.send(console, "PVP_STATE_CHANGED_OTHERS", other.getDisplayName(), current);
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
						String current = PvPToggle.instance.players.get(p.getUniqueId());
						if(args[0].equals("toggle")) {
							if(current.equalsIgnoreCase("off"))
								Util.setPvPOn(p);
							else if(current.equalsIgnoreCase("on"))
								Util.setPvPOff(p);
						} else if(args[0].equalsIgnoreCase("on")) {
							if(!current.equalsIgnoreCase("on"))
								Util.setPvPOn(p);
							else
								Chat.send(p, "PVP_ALREADY_ON");
						} else if(args[0].equalsIgnoreCase("off")) {
							if(!current.equalsIgnoreCase("off"))
								Util.setPvPOff(p);
							else
								Chat.send(p, "PVP_ALREADY_OFF");
						} else if(args[0].equalsIgnoreCase("status")) {
							Chat.send(p, "PVP_STATUS", null, current);
						} else {
							if(sender.hasPermission("pvptoggle.others")) {
								Player other = Bukkit.getPlayerExact(args[0]);
								if(other == null) {
									Chat.send(p, "NO_PLAYER", args[0]);
								} else {
									current = PvPToggle.instance.players.get(other.getUniqueId());
									Chat.send(p, "PVP_STATUS_OTHERS", other.getDisplayName(), current);
								}
							} else {
								Chat.send(p, "COMMAND_INVALID_PARAMETER", args[0]);
							}
						}	
					}
				} else if(args.length == 2) {
					if(sender.hasPermission("pvptoggle.others.set")) {
						Player other = Bukkit.getPlayerExact(args[1]);
						if(other == null) {
							Chat.send(p, "NO_PLAYER", args[1]);
						} else {
							String current = PvPToggle.instance.players.get(other.getUniqueId());
							if(args[0].equals("toggle")) {
								if(current.equalsIgnoreCase("off"))
									Util.setPvPOn(other);
								else if(current.equalsIgnoreCase("on"))
									Util.setPvPOff(other);
							} else if(args[0].equalsIgnoreCase("on")) {
								if(!current.equalsIgnoreCase("on"))
									Util.setPvPOn(other);
								else
									Chat.send(p, "PVP_ALREADY_ON_OTHERS", args[0]);
							} else if(args[0].equalsIgnoreCase("off")) {
								if(!current.equalsIgnoreCase("off"))
									Util.setPvPOff(other);
								else
									Chat.send(p, "PVP_ALREADY_OFF_OTHERS", args[0]);
							}
							current = PvPToggle.instance.players.get(other.getUniqueId());
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
