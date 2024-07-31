package cudera.content;

import arc.struct.Seq;
import cudera.graphics.CuderaPalette;
import mindustry.type.Item;

public class CuderaItems {
    public static Item cyanomite, leucoferrite, polysomate;
    public static final Seq<Item> cuderaItems = new Seq<>();
    public static void load(){
        cyanomite = new Item("cyanomite"){{
            color = CuderaPalette.baseLight;
            hardness = 1;
        }};

        leucoferrite = new Item("leucoferrite"){{
            color = CuderaPalette.leucoferriteLight;
            cost = 0.6f;
        }};

        polysomate = new Item("polysomate"){{
            color = CuderaPalette.polysomateLight;
            cost = 0.7f;
        }};

        cuderaItems.addAll(
            cyanomite, leucoferrite, polysomate
        );
    }
}
