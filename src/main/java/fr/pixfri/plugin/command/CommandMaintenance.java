package fr.pixfri.plugin.command;

import fr.pixfri.plugin.MaintenancePlugin;
import fr.pixfri.plugin.constants.Messages;
import fr.pixfri.plugin.util.ServerManagement;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandMaintenance implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(label.equalsIgnoreCase("maintenance")) {
            if(sender.hasPermission("maintenanceplugin.permissions.op")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("on")) {
                        MaintenancePlugin.MAINTENANCE_ENABLED = true;
                        ServerManagement.kickAllNonOp();

                        sender.sendMessage(Messages.MAINTENANCE_ENABLED.getMessage());
                    } else if(args[0].equalsIgnoreCase("off")) {
                        MaintenancePlugin.MAINTENANCE_ENABLED = false;

                        sender.sendMessage(Messages.MAINTENANCE_DISABLED.getMessage());
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You aren't allowed to perform this command!");
            }

            return true;
        }
        return false;
    }
}
