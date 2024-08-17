package cudera.content;

import arc.graphics.Color;
import cudera.graphics.CuderaPalette;
import mindustry.type.Liquid;

public class CuderaFluids {
    public static Liquid
    // liquid
    solute, lightcrude, naphtha, petroleum,
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
        }};
        lightcrude = new Liquid("lightcrude"){{
            color = CuderaPalette.lightcrudeLight;
            gasColor = Color.valueOf("c3c0b1");
            coolant = false;
            boilPoint = 0.8f;
            viscosity = 0.6f;
            flammability = 0.3f;
        }};
        naphtha = new Liquid("naphtha"){{
            color = CuderaPalette.naphthaLight;
            gasColor = Color.valueOf("cfb4e1");
            coolant = false;
            boilPoint = 0.85f;
            viscosity = 0.65f;
        }};
        petroleum = new Liquid("petroleum"){{
            color = CuderaPalette.petroleumMid;
            gasColor = Color.valueOf("ead9af");
            coolant = false;
            boilPoint = 0.9f;
            viscosity = 0.7f;
            flammability = 0.45f;
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
