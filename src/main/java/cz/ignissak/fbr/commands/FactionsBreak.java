package cz.ignissak.fbr.commands;

import cz.ignissak.fbr.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FactionsBreak implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("factionsbreak.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("messages.no-perm")));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("§c/factionsbreak [reload]");
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Core.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("messages.reloaded")));
                return true;
            } else {
                sender.sendMessage("§c/factionsbreak [reload]");
                return true;
            }
        }
        return true;
    }
}
