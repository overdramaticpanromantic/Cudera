package cudera.content;

import arc.graphics.Color;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class CuderaBlocks {
    public static Block
    // crafting
    leucoferriteKiln;
    public static void load(){
        // crafting
        leucoferriteKiln = new GenericCrafter("leucoferrite-kiln"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 20));
            outputItems = with(CuderaItems.leucoferrite, 3, CuderaItems.polysomate, 2);
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            size = 2;
            craftTime = 300f;
            hasItems = true;
            hasPower = true;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;

            consumeItem(CuderaItems.cyanomite, 5);
            consumePower(0.25f);
        }};
    }
}