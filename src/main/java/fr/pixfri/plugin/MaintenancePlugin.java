package fr.pixfri.plugin;

import fr.pixfri.plugin.command.CommandMaintenance;
import fr.pixfri.plugin.listeners.PlayerLoginListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MaintenancePlugin extends JavaPlugin {

    public static boolean MAINTENANCE_ENABLED = false;
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerLoginListener(), this);

        getCommand("maintenance").setExecutor(new CommandMaintenance());
    }

    @Override
    public void onDisable() {

    }
}
