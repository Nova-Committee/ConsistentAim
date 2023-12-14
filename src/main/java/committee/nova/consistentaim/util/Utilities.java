package committee.nova.consistentaim.util;

import committee.nova.consistentaim.api.IOptions;
import committee.nova.consistentaim.config.ClientConfig;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class Utilities {
    private static final Map<CameraType, Component> langCache = new HashMap<>();

    public static boolean is1stPerson() {
        return Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }

    public static void onStartAiming() {
        final Options options = Minecraft.getInstance().options;
        ((IOptions) options).consistentaim$setCameraTypeZoomed(options.getCameraType());
        options.setCameraType(CameraType.FIRST_PERSON);
    }

    public static void onStopAiming() {
        if (ClientConfig.alwaysReturnTo1stPerson.get()) return;
        final Options options = Minecraft.getInstance().options;
        options.setCameraType(((IOptions) options).consistentaim$getCameraTypeZoomed());
    }

    public static Component getCameraTypeName(CameraType cameraType) {
        if (langCache.containsKey(cameraType)) return langCache.get(cameraType);
        final String translatedKey = "cameratype.consistentaim." + cameraType.name().toLowerCase();
        if (Language.getInstance().has(translatedKey)) {
            final Component translated = Component.translatable(translatedKey);
            langCache.put(cameraType, translated);
            return translated;
        }
        final Component literal = Component.literal(toCamelCase(cameraType.name()));
        langCache.put(cameraType, literal);
        return literal;
    }

    private static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) return input;
        final StringBuilder builder = new StringBuilder();
        boolean toUpperCase = false;
        boolean first = true;
        for (char ch : input.toCharArray()) {
            if (ch == '_' || ch == '-') {
                toUpperCase = true;
            } else {
                if (first) {
                    builder.append(Character.toUpperCase(ch));
                    first = false;
                    toUpperCase = false;
                } else if (toUpperCase) {
                    builder.append(" ");
                    builder.append(Character.toUpperCase(ch));
                    toUpperCase = false;
                } else {
                    builder.append(Character.toLowerCase(ch));
                }
            }
        }
        return builder.toString();
    }
}
