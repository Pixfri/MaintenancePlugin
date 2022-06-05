package fr.pixfri.plugin.mojang;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import fr.pixfri.plugin.string.UUIDUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

// Class made by Frivec in FrivAPI https://gitlab.com/minecraftserver/frivapi
public class MojangRequest {

    public static UUID getUUIDFromMojang(String name) {

        final String stringURL = "https://api.mojang.com/users/profiles/minecraft/" + name;

        try {

            @SuppressWarnings("deprecation")
            final String UUIDJson = IOUtils.toString(new URL(stringURL));

            if(UUIDJson.isEmpty())

                return null;

            final JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

            return UUID.fromString(UUIDUtils.addUUIDDashes(UUIDObject.get("id").toString()));

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }

        return null;

    }

    public static String getName(String uuid) {

        final String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";

        try {

            @SuppressWarnings("deprecation")

            final String nameJson = IOUtils.toString(new URL(url));
            final JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);

            final String playerSlot = nameValue.get(nameValue.size() - 1).toString();
            final JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);

            return nameObject.get("name").toString();

        } catch (IOException | ParseException e) {
            return "error";
        }

    }

}
