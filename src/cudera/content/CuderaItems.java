package cudera.content;

import arc.struct.Seq;
import cudera.graphics.CuderaPalette;
import mindustry.type.Item;

public class CuderaItems {
    public static Item cyanomite, leucoferrite, polysomate, silt, anthracite, vitrinite;
    public static final Seq<Item> cuderaItems = new Seq<>();
    public static void load(){
        cyanomite = new Item("cyanomite"){{
            color = CuderaPalette.baseLight;
            hardness = 1;
            cost = 0.5f;
        }};

        leucoferrite = new Item("leucoferrite"){{
            color = CuderaPalette.leucoferriteLight;
            cost = 0.6f;
        }};

        polysomate = new Item("polysomate"){{
            color = CuderaPalette.polysomateLight;
            cost = 0.7f;
        }};

        silt = new Item("silt"){{
            color = CuderaPalette.siltLight;
            hardness = 1;
        }};

        anthracite = new Item("anthracite"){{
            color = CuderaPalette.anthraciteLight;
        }};

        vitrinite = new Item("vitrinite"){{
            color = CuderaPalette.vitriniteLight;
            hardness = 2;
        }};

        cuderaItems.addAll(
            cyanomite, leucoferrite, polysomate, silt, anthracite, vitrinite
        );
    }
}
