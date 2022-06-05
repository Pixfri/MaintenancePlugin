package fr.pixfri.plugin.core;

import fr.pixfri.plugin.MaintenancePlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class YamlAuthorized {

    private static final String AUTHORIZED_PLAYERS = "authorized_players.yml";
    private static final String KEY = "authorized";
    private final File savedFile;
    private final YamlConfiguration configuration;

    public YamlAuthorized(MaintenancePlugin maintenancePlugin) {
        this.savedFile = new File(maintenancePlugin.getDataFolder(), AUTHORIZED_PLAYERS);
        this.configuration = YamlConfiguration.loadConfiguration(savedFile);
    }

    public void writeAuthorizedPlayers() throws IOException {
        final List<String> uuids = new ArrayList<>();

        for(UUID uuid : MaintenancePlugin.getAUTHORIZED()) {
            uuids.add(uuid.toString());
        }

        configuration.set(KEY, uuids);
        configuration.save(savedFile);
    }

    @SuppressWarnings("unchecked")
    public HashSet<UUID> readAuthorizedPlayers() {

        if(configuration != null) {
            final List<String> uuids = (List<String>) configuration.getList(KEY);

            if (uuids != null) {
                final HashSet<UUID> uuidsSet = new HashSet<>();

                for(String uuid : uuids) {
                    uuidsSet.add(UUID.fromString(uuid));
                }
                return uuidsSet;
            }
        }

        return new HashSet<>();
    }
}
