package fr.pixfri.plugin;

import fr.pixfri.plugin.command.CommandMaintenance;
import fr.pixfri.plugin.core.YamlAuthorized;
import fr.pixfri.plugin.listeners.PlayerLoginListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MaintenancePlugin extends JavaPlugin {

    private static Set<UUID> AUTHORIZED = new HashSet<>();

    public static boolean MAINTENANCE_ENABLED = false;

    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        readAuthorizedPlayersList();

        pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerLoginListener(), this);

        getCommand("maintenance").setExecutor(new CommandMaintenance(this));
    }

    @Override
    public void onDisable() {

    }

    public static Set<UUID> getAUTHORIZED() {
        return AUTHORIZED;
    }

    private void readAuthorizedPlayersList() {
        MaintenancePlugin.AUTHORIZED = new YamlAuthorized(this).readAuthorizedPlayers();
    }
}
