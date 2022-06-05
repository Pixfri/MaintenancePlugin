package fr.pixfri.plugin.string;

// Class made by Frivec in FrivAPI https://gitlab.com/minecraftserver/frivapi
public class UUIDUtils {

    public static String addUUIDDashes(String firstUUID) {

        final StringBuffer stringBuffer = new StringBuffer(firstUUID);

        stringBuffer.insert(20, '-');
        stringBuffer.insert(16, '-');
        stringBuffer.insert(12, '-');
        stringBuffer.insert(8, '-');

        return stringBuffer.toString();
    }
}