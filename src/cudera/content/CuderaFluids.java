package cudera.content;

import arc.graphics.Color;
import cudera.graphics.CuderaPalette;
import mindustry.type.Liquid;

public class CuderaFluids {
    public static Liquid
    // liquid
    solute, lightcrude,
    // gas
    dihydrate, dripgas;

    public static void load(){
        // liquid
        solute = new Liquid("solute"){{
            color = CuderaPalette.quartzLight;
            gasColor = Color.valueOf("eec7e5");
            coolant = false;
            boilPoint = 0.6f;
            viscosity = 0.55f;
            heatCapacity = 0.35f;
        }};
        lightcrude = new Liquid("lightcrude"){{
            color = CuderaPalette.lightcrudeMid;
            gasColor = Color.valueOf("ead9af");
            coolant = false;
            boilPoint = 0.8f;
            viscosity = 0.65f;
            flammability = 0.3f;
        }};

        // gas
        dihydrate = new Liquid("dihydrate"){{
            gas = true;
            color = CuderaPalette.dihydrateLight;
            coolant = false;
        }};
        dripgas = new Liquid("dripgas"){{
            gas = true;
            color = CuderaPalette.dripgasLight;
            coolant = false;
            flammability = 0.4f;
        }};
    }
}
