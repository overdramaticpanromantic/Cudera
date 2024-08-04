package cudera.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class CuderaBlocks {
    public static Block
    // crafting
    leucoferriteKiln, siltStrainer, dihydrateAcidifier, aragoniteDissolver, quartzRecrystallizer;

    public static void load(){
        // crafting
        leucoferriteKiln = new GenericCrafter("leucoferrite-kiln"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 20));
            outputItems = with(CuderaItems.leucoferrite, 3, CuderaItems.polysomate, 2);
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            size = 2;
            craftTime = 300f;
            hasItems = true;
            hasLiquids = false;
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
            consumePower(0.05f);
        }};
        dihydrateAcidifier = new GenericCrafter("dihydrate-acidifier"){{
            requirements(Category.crafting, with(CuderaItems.leucoferrite, 20, CuderaItems.polysomate, 15));
            outputLiquid = new LiquidStack(CuderaFluids.dihydrate, 0.1f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(CuderaFluids.dihydrate, 1f), new DrawDefault());
            size = 2;
            health = 175;
            craftTime = 240f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.07f;

            consumeItem(CuderaItems.anthracite, 5);
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(0.3f);
        }};
        aragoniteDissolver = new GenericCrafter("aragonite-dissolver"){{
            requirements(Category.crafting, with(CuderaItems.anthracite, 25, CuderaItems.leucoferrite, 15));
            outputLiquid = new LiquidStack(CuderaFluids.solute, 0.1f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(CuderaFluids.solute, 1f), new DrawDefault());
            size = 3;
            health = 260;
            craftTime = 60f / (10f / 6f);
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.05f;

            consumeItem(CuderaItems.aragonite, 1);
            consumeLiquid(CuderaFluids.dihydrate, 0.4f / 3f);
            consumePower(1f / 3f);
        }};
        quartzRecrystallizer = new GenericCrafter("quartz-recrystallizer"){{
            requirements(Category.crafting, with(CuderaItems.anthracite, 25, CuderaItems.polysomate, 20));
            outputItem = new ItemStack(CuderaItems.quartz, 1);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(CuderaFluids.solute, 1f), new DrawDefault());
            size = 3;
            health = 240;
            craftTime = 60f;
            hasItems = false;
            hasLiquids = true;
            hasPower = true;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08f;

            consumeLiquid(CuderaFluids.solute, 0.06f);
            consumePower(1f / 2f);
        }};
    }
}