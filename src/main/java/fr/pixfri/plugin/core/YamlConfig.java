package fr.pixfri.plugin.core;

import fr.pixfri.plugin.MaintenancePlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlConfig {

    private static final String AUTHORIZED_PLAYERS = "config.yml";
    private final File savedFile;
    private final YamlConfiguration configuration;

    public YamlConfig(MaintenancePlugin maintenancePlugin) {
        this.savedFile = new File(maintenancePlugin.getDataFolder(), AUTHORIZED_PLAYERS);
        this.configuration = YamlConfiguration.loadConfiguration(savedFile);
    }

    public void write() throws IOException {
        configuration.set("maintenance_enabled", MaintenancePlugin.MAINTENANCE_ENABLED);
        configuration.save(savedFile);
    }

    public void read() {
        MaintenancePlugin.MAINTENANCE_ENABLED = configuration.getBoolean("maintenance_enabled");
    }
}
