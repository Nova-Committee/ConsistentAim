package committee.nova.consistentaim.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec CFG;
    public static final ForgeConfigSpec.BooleanValue vanillaBow;
    public static final ForgeConfigSpec.BooleanValue cgm;
    public static final ForgeConfigSpec.BooleanValue tac;
    public static final ForgeConfigSpec.BooleanValue alwaysReturnTo1stPerson;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Consistent Aim: Config").push("support");
        builder.push("vanilla");
        vanillaBow = builder
                .comment("Enable Consistent Aim for vanilla bow")
                .define("vanillaBow", false);
        builder.pop();
        builder.push("modded");
        cgm = builder
                .comment("Enable Consistent Aim for MrCrayfish's Gun Mod")
                .define("cgm", true);
        tac = builder
                .comment("Enable Consistent Aim for Timeless & Classics")
                .define("tac", true);
        builder.pop();
        builder.pop();
        builder.push("mechanism");
        alwaysReturnTo1stPerson = builder.define("alwaysReturnTo1stPerson", true);
        builder.pop();
        CFG = builder.build();
    }
}
