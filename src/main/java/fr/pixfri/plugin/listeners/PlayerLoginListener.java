package fr.pixfri.plugin.listeners;

import fr.pixfri.plugin.MaintenancePlugin;
import fr.pixfri.plugin.constants.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if(MaintenancePlugin.MAINTENANCE_ENABLED) {
            if(!player.hasPermission("maintenanceplugin.permissions.op")) {
                player.kick(Component.text(Messages.PLAYER_MAINTENANCE_RECONNECT_KICK.getMessage()));
            }
        }
    }
}
