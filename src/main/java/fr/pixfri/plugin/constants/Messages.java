package fr.pixfri.plugin.constants;

import org.bukkit.ChatColor;

public enum Messages {

    COMMAND_INVALID_ARGS_LENGTH(ChatColor.RED + "Invalid arguments number."),
    COMMAND_INVALID_ARG(ChatColor.RED + "Invalid arguments."),
    COMMAND_LIST_INTRODUCTION(ChatColor.LIGHT_PURPLE + " === List of authorized maintenance players ==="),

    MAINTENANCE_ENABLED(ChatColor.GOLD + "Maintenance is now enabled."),
    MAINTENANCE_DISABLED(ChatColor.GOLD + "Maintenance is now disabled."),

    PLAYER_NOT_OP(ChatColor.RED + "You aren't allowed to perform this command."),
    PLAYER_MAINTENANCE_ADDED(ChatColor.LIGHT_PURPLE + "Player added to the authorized players list."),
    PLAYER_MAINTENANCE_REMOVED(ChatColor.LIGHT_PURPLE + "Player removed from the authorized players list."),
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
