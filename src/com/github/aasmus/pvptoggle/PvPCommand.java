package com.github.aasmus.pvptoggle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.Util;

public class PvPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			Player other = Bukkit.getPlayerExact(args[1]);
			if(other == null) {
			    sender.sendMessage(ChatColor.RED + "Could not find a player by the name " + args[1]);
			} else {
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
						sender.sendMessage(ChatColor.GREEN + args[0] + " already has pvp on!");
				} else if(args[0].equalsIgnoreCase("off")) {
					if(!current.equalsIgnoreCase("off"))
						Util.setPvPOff(other);
					else
						sender.sendMessage(ChatColor.GREEN + args[0] + " already has pvp off!");
				}
				current = PvPToggle.instance.players.get(other.getUniqueId());
				sender.sendMessage(ChatColor.GREEN + other.getDisplayName() + "'s pvp state has been changed to " + current + ".");
			}
		} else if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("pvp")) {
				Player p = (Player) sender;
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Commands:");
					sender.sendMessage(ChatColor.GREEN + "Use /pvp {toggle|on|off|status} to change/view pvp state.");
					if(p.hasPermission("pvptoggle.others"))
						sender.sendMessage(ChatColor.GREEN + "Use /pvp <player> to see another player's pvp state.");
					if(p.hasPermission("pvptoggle.others.set"))
						sender.sendMessage(ChatColor.GREEN + "Use /pvp {toggle|on|off} <player> to set another player's pvp state.");
				} else if(args.length == 1) {
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
							sender.sendMessage(ChatColor.GREEN + "You already have pvp on!");
					} else if(args[0].equalsIgnoreCase("off")) {
						if(!current.equalsIgnoreCase("off"))
							Util.setPvPOff(p);
						else
							sender.sendMessage(ChatColor.GREEN + "You already have pvp off!");
					} else if(args[0].equalsIgnoreCase("status")) {
						sender.sendMessage(ChatColor.GREEN + "Your pvp set to " + current + ".");
					} else {
						if(sender.hasPermission("pvptoggle.others")) {
							Player other = Bukkit.getPlayerExact(args[0]);
							if(other == null) {
							    sender.sendMessage(ChatColor.RED + "Could not find a player by the name " + args[0] + ".");
							} else {
								current = PvPToggle.instance.players.get(other.getUniqueId());
								sender.sendMessage(ChatColor.GREEN + other.getDisplayName() + ChatColor.GREEN + "'s pvp is " + current + ".");
							}
						} else {
							sender.sendMessage(ChatColor.RED + args[0] +" is an invalid parameter. {toggle|on|off|status}");
						}
					}
				} else if(args.length == 2) {
					if(sender.hasPermission("pvptoggle.others.set")) {
						Player other = Bukkit.getPlayerExact(args[1]);
						if(other == null) {
						    sender.sendMessage(ChatColor.RED + "Could not find a player by the name " + args[1]);
						} else {
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
									sender.sendMessage(ChatColor.GREEN + args[0] + " already has pvp on!");
							} else if(args[0].equalsIgnoreCase("off")) {
								if(!current.equalsIgnoreCase("off"))
									Util.setPvPOff(other);
								else
									sender.sendMessage(ChatColor.GREEN + args[0] + " already has pvp off!");
							}
							current = PvPToggle.instance.players.get(other.getUniqueId());
							sender.sendMessage(ChatColor.GREEN + other.getDisplayName() + "'s pvp state has been changed to " + current + ".");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to set others pvp  state.");
					}
				}
			}
			return true;
		}
		return false;
	}
}
