package committee.nova.consistentaim.util;

import committee.nova.consistentaim.api.IOptions;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.HashMap;
import java.util.Map;

public class Utilities {
    private static final Map<PointOfView, ITextComponent> langCache = new HashMap<>();

    public static boolean is1stPerson() {
        return Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }

    public static void onStartAiming() {
        final GameSettings options = Minecraft.getInstance().options;
        ((IOptions) options).consistentaim$setCameraTypeZoomed(options.getCameraType());
        options.setCameraType(PointOfView.FIRST_PERSON);
    }

    public static void onStopAiming() {
        final GameSettings options = Minecraft.getInstance().options;
        options.setCameraType(((IOptions) options).consistentaim$getCameraTypeZoomed());
    }

    public static ITextComponent getCameraTypeName(PointOfView cameraType) {
        if (langCache.containsKey(cameraType)) return langCache.get(cameraType);
        final String translatedKey = "cameratype.consistentaim." + cameraType.name().toLowerCase();
        if (LanguageMap.getInstance().has(translatedKey)) {
            final ITextComponent translated = new TranslationTextComponent(translatedKey);
            langCache.put(cameraType, translated);
            return translated;
        }
        final ITextComponent literal = new StringTextComponent(toCamelCase(cameraType.name()));
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
