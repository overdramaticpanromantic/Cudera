package cudera.content;

import cudera.graphics.CuderaPalette;
import mindustry.type.Liquid;

public class CuderaLiquids {
    public static Liquid
    // gas
    dihydrate;

    public static void load(){
        dihydrate = new Liquid("dihydrate"){{
            gas = true;
            color = CuderaPalette.dihydrateLight;
        }};
    }
}
