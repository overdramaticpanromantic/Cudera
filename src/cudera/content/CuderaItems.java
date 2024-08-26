package cudera.content;

import arc.struct.Seq;
import cudera.graphics.CuderaPalette;
import mindustry.type.Item;

public class CuderaItems {
    public static Item cyanomite, leucoferrite, polysomate, silt, anthracite, algae, vitrinite, aragonite, quartz, polymer, martensite, theoserine, thermoplast, plasteel;
    public static final Seq<Item> cuderaItems = new Seq<>();
    public static void load(){
        cyanomite = new Item("cyanomite"){{
            color = CuderaPalette.baseLight;
            hardness = 1;
            cost = 1f;
        }};
        leucoferrite = new Item("leucoferrite"){{
            color = CuderaPalette.leucoferriteLight;
            cost = 1.2f;
        }};
        polysomate = new Item("polysomate"){{
            color = CuderaPalette.polysomateLight;
            cost = 1.3f;
        }};
        silt = new Item("silt"){{
            color = CuderaPalette.siltLight;
            hardness = 1;
            lowPriority = true;
        }};
        algae = new Item("algae"){{
            color = CuderaPalette.algaeLight;
            flammability = 0.5f;
        }};
        vitrinite = new Item("vitrinite"){{
            color = CuderaPalette.vitriniteLight;
            hardness = 2;
            flammability = 0.75f;
        }};
        anthracite = new Item("anthracite"){{
            color = CuderaPalette.anthraciteLight;
            cost = 1.45f;
            flammability = 1f;
        }};
        aragonite = new Item("aragonite"){{
            color = CuderaPalette.aragoniteLight;
            hardness = 2;
        }};
        quartz = new Item("quartz"){{
            color = CuderaPalette.quartzLight;
            cost = 1.6f;
        }};
        polymer = new Item("polymer"){{
            color = CuderaPalette.polymerLight;
            cost = 1.7f;
        }};
        martensite = new Item("martensite"){{
            color = CuderaPalette.martensiteLight;
            cost = 2f;
        }};
        theoserine = new Item("theoserine"){{
            color = CuderaPalette.theoserineLight;
            cost = 2.5f;
        }};
        thermoplast = new Item("thermoplast"){{
            color = CuderaPalette.thermoplastLight;
            cost = 2.6f;
        }};
        plasteel = new Item("plasteel"){{
            color = CuderaPalette.plasteelLight;
            cost = 3f;
        }};

        cuderaItems.addAll(
            cyanomite, leucoferrite, polysomate, silt, anthracite, aragonite, quartz, algae, vitrinite, polymer, martensite, theoserine, thermoplast, plasteel
        );
    }
}
