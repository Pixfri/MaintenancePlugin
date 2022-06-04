package fr.pixfri.plugin.constants;

import org.bukkit.ChatColor;

public enum Messages {

    MAINTENANCE_ENABLED(ChatColor.GOLD + "Maintenance is now enabled."),
    MAINTENANCE_DISABLED(ChatColor.GOLD + "Maintenance is now disabled."),

    PLAYER_MAINTENANCE_KICK(ChatColor.AQUA + "Server is now in maintenance."),
    PLAYER_MAINTENANCE_RECONNECT_KICK(ChatColor.AQUA + "Server is in maintenance, cannot be reconnected.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
