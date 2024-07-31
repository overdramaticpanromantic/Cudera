package cudera.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class CuderaBlocks {
    public static Block
    // crafting
    leucoferriteKiln, siltStrainer;

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
        siltStrainer = new Separator("silt-strainer"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 15, CuderaItems.polysomate, 10));
            results = with(CuderaItems.anthracite, 3, CuderaItems.vitrinite, 2);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water, 1f), new DrawRegion("-spinner", 1, true), new DrawDefault());
            size = 2;
            health = 180;
            craftTime = 60f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;

            consumeItem(CuderaItems.silt, 1);
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(3f / 60f);
        }};
    }
}