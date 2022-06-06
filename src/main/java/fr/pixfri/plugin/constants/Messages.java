package fr.pixfri.plugin.constants;

import org.bukkit.ChatColor;

public enum Messages {

    COMMAND_INVALID_ARGS_LENGTH(ChatColor.RED + "Invalid arguments number."),
    COMMAND_INVALID_ARG(ChatColor.RED + "Invalid arguments."),
    COMMAND_LIST_INTRODUCTION(ChatColor.LIGHT_PURPLE + " === List of authorized maintenance players ==="),

    MAINTENANCE_ENABLED(ChatColor.GOLD + "Maintenance is now enabled."),
    MAINTENANCE_DISABLED(ChatColor.GOLD + "Maintenance is now disabled."),

    PLAYER_SAVE_FAILED(ChatColor.RED + "Fail during the save of authorized players. Please read the logs."),
    MAINTENANCE_STATUS_SAVE_FAIL(ChatColor.RED + "Cannot save the state of the maintenance. Please read the logs."),
    MAINTENANCE_DELAY_SAVE_FAIL(ChatColor.RED + "Cannot save the maintenance delay. Please read the logs."),
    MAINTENANCE_DURATION_SAVE_FAIL(ChatColor.RED + "Cannot save the maintenance duration. Please read the logs."),

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
