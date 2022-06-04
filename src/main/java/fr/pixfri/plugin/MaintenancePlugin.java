package fr.pixfri.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MaintenancePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Ok");
    }

    @Override
    public void onDisable() {
        System.out.println("Nope");
    }
}
