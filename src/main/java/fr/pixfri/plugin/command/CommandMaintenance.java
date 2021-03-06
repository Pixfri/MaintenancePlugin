package fr.pixfri.plugin.command;

import fr.pixfri.plugin.MaintenancePlugin;
import fr.pixfri.plugin.constants.Messages;
import fr.pixfri.plugin.core.YamlAuthorized;
import fr.pixfri.plugin.core.YamlConfig;
import fr.pixfri.plugin.mojang.MojangRequest;
import fr.pixfri.plugin.util.ServerManagement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

public class CommandMaintenance implements CommandExecutor {

    private final MaintenancePlugin maintenancePlugin;

    public CommandMaintenance(MaintenancePlugin maintenancePlugin) {
        this.maintenancePlugin = maintenancePlugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(label.equalsIgnoreCase("maintenance")) {

            if(sender.hasPermission("maintenanceplugin.permissions.op")) {

                if(args.length == 1) {

                    // enable maintenance.
                    if(args[0].equalsIgnoreCase("on")) {
                        try {
                            MaintenancePlugin.MAINTENANCE_ENABLED = true;
                            ServerManagement.kickAllNotAllowed();
                            new YamlConfig(maintenancePlugin).write();



                            if(MaintenancePlugin.MAINTENANCE_DURATION > 0) {
                                final int duration = MaintenancePlugin.MAINTENANCE_DURATION;

                                Bukkit.getScheduler().scheduleSyncDelayedTask(maintenancePlugin, () ->
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "maintenance off"),
                                        duration * 20L);
                            }

                            sender.sendMessage(Component.text(Messages.MAINTENANCE_ENABLED.getMessage()));
                        } catch (IOException e) {
                            sender.sendMessage(Component.text(Messages.MAINTENANCE_STATUS_SAVE_FAIL.getMessage()));
                            e.printStackTrace();
                        }
                    }

                    // disable maintenance.
                    else if(args[0].equalsIgnoreCase("off")) {
                        try {
                            MaintenancePlugin.MAINTENANCE_ENABLED = false;
                            new YamlConfig(maintenancePlugin).write();
                            sender.sendMessage(Component.text(Messages.MAINTENANCE_DISABLED.getMessage()));
                        } catch (IOException e) {
                            sender.sendMessage(Component.text(Messages.MAINTENANCE_STATUS_SAVE_FAIL.getMessage()));
                            e.printStackTrace();
                        }
                    }

                    // list all the players in the maintenance allowed players list.
                    else if (args[0].equalsIgnoreCase("list")) {
                        //TODO: Make pages with org.bukkit.util.ChatPaginator
                        sender.sendMessage(Component.text(Messages.COMMAND_LIST_INTRODUCTION.getMessage()));
                        OfflinePlayer player;
                        for(UUID uuid : MaintenancePlugin.getAUTHORIZED()) {
                            player = Bukkit.getOfflinePlayer(uuid);

                            if(player != null) {
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + player.getName());
                            }
                        }
                    }

                    else {
                        sender.sendMessage(Component.text(Messages.COMMAND_INVALID_ARG.getMessage()));
                    }

                } else if (args.length == 2) {

                    // add a player to the maintenance allowed players list.
                    if (args[0].equalsIgnoreCase("add")) {
                        final String playername = args[1];
                        final OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
                        final UUID neverConnectedPlayerUUID = MojangRequest.getUUIDFromMojang(playername);

                        if(player != null) {
                            final UUID uuid = player.getUniqueId();

                            try {
                                MaintenancePlugin.getAUTHORIZED().add(uuid);
                                new YamlAuthorized(maintenancePlugin).writeAuthorizedPlayers();
                                sender.sendMessage(Component.text(Messages.PLAYER_MAINTENANCE_ADDED.getMessage()));
                            } catch (IOException e) {
                                sender.sendMessage(Component.text(Messages.PLAYER_SAVE_FAILED.getMessage()));
                                e.printStackTrace();
                            }

                        } else {

                            try {
                                MaintenancePlugin.getAUTHORIZED().add(neverConnectedPlayerUUID);
                                new YamlAuthorized(maintenancePlugin).writeAuthorizedPlayers();
                                sender.sendMessage(Component.text(Messages.PLAYER_MAINTENANCE_ADDED.getMessage()));
                            } catch (IOException e) {
                                sender.sendMessage(Component.text(Messages.PLAYER_SAVE_FAILED.getMessage()));
                                e.printStackTrace();
                            }
                        }
                    }

                    // remove a player to the maintenance allowed players list.
                    else if (args[0].equalsIgnoreCase("remove")) {
                        final String playername = args[1];
                        final OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
                        final UUID neverConnectedPlayerUUID = MojangRequest.getUUIDFromMojang(playername);

                        if(player != null) {
                            final UUID uuid = player.getUniqueId();

                            try {
                                MaintenancePlugin.getAUTHORIZED().remove(uuid);
                                new YamlAuthorized(maintenancePlugin).writeAuthorizedPlayers();
                                sender.sendMessage(Component.text(Messages.PLAYER_MAINTENANCE_REMOVED.getMessage()));
                            } catch (IOException e) {
                                sender.sendMessage(Component.text(Messages.PLAYER_SAVE_FAILED.getMessage()));
                                e.printStackTrace();
                            }

                        } else {

                            try {
                                MaintenancePlugin.getAUTHORIZED().remove(neverConnectedPlayerUUID);
                                new YamlAuthorized(maintenancePlugin).writeAuthorizedPlayers();
                                sender.sendMessage(Component.text(Messages.PLAYER_MAINTENANCE_REMOVED.getMessage()));
                            } catch (IOException e) {
                                sender.sendMessage(Component.text(Messages.PLAYER_SAVE_FAILED.getMessage()));
                                e.printStackTrace();
                            }
                        }
                    }

                    // Maintenance delay
                    else if(args[0].equalsIgnoreCase("delay")) {
                        final int delay = Integer.parseInt(args[1]); // Convert the arg (String) in seconds (int)

                        MaintenancePlugin.DELAY_BEFORE_MAINTENANCE = delay;

                        Bukkit.getScheduler().scheduleSyncDelayedTask(maintenancePlugin, () -> {

                            Bukkit.getServer().sendMessage(Component.text(ChatColor.LIGHT_PURPLE + "" + delay + " seconds before the maintenance."));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "maintenance on");
                            }, delay * 20L); // the last arg convert the number of seconds indicated in game in ticks
                    }

                    // Add a duration to the maintenance
                    else if(args[0].equalsIgnoreCase("duration")) {
                        final int duration = Integer.parseInt(args[1]); // Convert the arg (String) in seconds (int)

                        MaintenancePlugin.MAINTENANCE_DURATION = duration;

                        Bukkit.getServer().sendMessage(Component.text(ChatColor.LIGHT_PURPLE + "The maintenance will last " + duration + "seconds"));
                    }

                    else {
                        sender.sendMessage(Component.text(Messages.COMMAND_INVALID_ARG.getMessage()));
                    }
                }

                else {
                    sender.sendMessage(Component.text(Messages.COMMAND_INVALID_ARGS_LENGTH.getMessage()));
                }

            }

            else {
                sender.sendMessage(Component.text(Messages.PLAYER_NOT_OP.getMessage()));
            }
            return true;
        }
        return false;
    }
}
