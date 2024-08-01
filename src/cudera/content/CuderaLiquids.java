package cudera.content;

import arc.graphics.Color;
import cudera.graphics.CuderaPalette;
import mindustry.type.Liquid;

public class CuderaLiquids {
    public static Liquid
    // liquid
    solute,
    // gas
    dihydrate;

    public static void load(){
        // liquid
        solute = new Liquid("solute"){{
            color = CuderaPalette.quartzLight;
            gasColor = Color.valueOf("eec7e5");
            boilPoint = 0.6f;
            viscosity = 0.55f;
            heatCapacity = 0.35f;
            coolant = false;
        }};
        // gas
        dihydrate = new Liquid("dihydrate"){{
            gas = true;
            color = CuderaPalette.dihydrateLight;
        }};
    }
}
