package fr.pixfri.plugin.util;

import fr.pixfri.plugin.constants.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerManagement {

    public static void kickAllNonOp() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.hasPermission("maintenanceplugin.permissions.op")) {
                player.kick(Component.text(Messages.PLAYER_MAINTENANCE_KICK.getMessage()));
            }
        }
    }
}
