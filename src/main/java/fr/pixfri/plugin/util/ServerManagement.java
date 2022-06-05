package fr.pixfri.plugin.util;

import fr.pixfri.plugin.MaintenancePlugin;
import fr.pixfri.plugin.constants.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerManagement {
    public static void kickAllNotAllowed() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission("maintenanceplugin.permissions.op") || MaintenancePlugin.getAUTHORIZED().contains(player.getUniqueId())) {
                continue;
            } else {
                player.kick(Component.text(Messages.PLAYER_MAINTENANCE_KICK.getMessage()));
            }
        }
    }
}
