package fr.pixfri.plugin;

import fr.pixfri.plugin.command.CommandMaintenance;
import org.bukkit.plugin.java.JavaPlugin;

public class MaintenancePlugin extends JavaPlugin {

    public static boolean MAINTENANCE_ENABLED = false;
    @Override
    public void onEnable() {
        getCommand("maintenance").setExecutor(new CommandMaintenance());
    }

    @Override
    public void onDisable() {

    }
}
