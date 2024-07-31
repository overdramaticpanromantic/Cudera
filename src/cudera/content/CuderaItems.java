package cudera.content;

import arc.struct.Seq;
import cudera.graphics.CuderaPalette;
import mindustry.type.Item;

public class CuderaItems {
    public static Item cyanomite, leucoferrite, polysomate, silt, anthracite, aragonite, quartz, algae, vitrinite;
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
            lowPriority = true;
        }};

        anthracite = new Item("anthracite"){{
            color = CuderaPalette.anthraciteLight;
            cost = 0.8f;
            flammability = 0.75f;
        }};

        aragonite = new Item("aragonite"){{
            color = CuderaPalette.aragoniteLight;
            hardness = 2;
        }};

        quartz = new Item("quartz"){{
            color = CuderaPalette.quartzLight;
            cost = 0.9f;
        }};

        algae = new Item("algae"){{
            color = CuderaPalette.algaeLight;
            flammability = 0.5f;
        }};

        vitrinite = new Item("vitrinite"){{
            color = CuderaPalette.vitriniteLight;
            hardness = 2;
            flammability = 1f;
        }};

        cuderaItems.addAll(
            cyanomite, leucoferrite, polysomate, silt, anthracite, aragonite, quartz, algae, vitrinite
        );
    }
}
