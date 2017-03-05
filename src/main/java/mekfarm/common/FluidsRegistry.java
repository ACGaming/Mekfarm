package mekfarm.common;

import mekfarm.fluids.SewageFluid;

/**
 * Created by CF on 2017-02-25.
 */
public final class FluidsRegistry {
    public static SewageFluid sewage;

    public static void createFluids() {
        // FluidRegistry.enableUniversalBucket();
        FluidsRegistry.sewage = new SewageFluid();
        FluidsRegistry.sewage.register();
    }
}
