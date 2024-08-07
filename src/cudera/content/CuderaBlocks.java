package cudera.content;

import arc.Core;
import arc.graphics.Color;
import arc.math.Interp;
import cudera.world.blocks.*;
import cudera.world.draw.*;
import mindustry.content.Liquids;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class CuderaBlocks {
    public static Block
    // production
    algalPropagator,
    // crafting
    leucoferriteKiln, siltStrainer, vitriniteCompactor, dihydrateAcidifier, aragoniteDissolver, quartzRecrystallizer, lightcrudeProcessor, naphthaDistiller;

    public static void load(){
        // production
        algalPropagator = new GenericCrafter("algal-propagator"){{
            Color col1 = Color.valueOf("0f481c");
            Color col2 = Color.valueOf("3ba350");
            requirements(Category.production, with(CuderaItems.leucoferrite, 20, CuderaItems.polysomate, 10));
            outputItem = new ItemStack(CuderaItems.algae, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 1f),
                new DrawDefault()
            );
            craftEffect = new ParticleEffect(){{
                particles = 5;
                lifetime = 60f;
                colorFrom = col2;
                colorTo = col1.a(0f);
                sizeFrom = 0f;
                sizeTo = 2f;
                length = 16f;
                interp = Interp.pow3In;
                sizeInterp = Interp.pow3Out;
                lightColor = Color.white.cpy().a(0f);
            }};
            size = 2;
            craftTime = 120f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.06f;

            consumeLiquid(Liquids.water, 0.1f);
            consumePower(24f / 60f);
        }};
        // crafting
        leucoferriteKiln = new GenericCrafter("leucoferrite-kiln"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 20));
            outputItems = with(CuderaItems.leucoferrite, 3, CuderaItems.polysomate, 2);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("ffc099"))
            );
            size = 2;
            craftTime = 300f;
            hasItems = true;
            hasLiquids = false;
            hasPower = true;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.04f;

            consumeItem(CuderaItems.cyanomite, 5);
            consumePower(20f / 60f);
        }};
        siltStrainer = new Separator("silt-strainer"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 15, CuderaItems.polysomate, 10));
            results = with(CuderaItems.anthracite, 3, CuderaItems.vitrinite, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 1f),
                new DrawRegion("-spinner", 1, true),
                new DrawDefault()
            );
            size = 2;
            health = 170;
            craftTime = 60f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;

            consumeItem(CuderaItems.silt, 1);
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(24f / 60f);
        }};
        vitriniteCompactor = new ShakeCrafter("vitrinite-compactor"){{
            requirements(Category.crafting, with(CuderaItems.cyanomite, 20, CuderaItems.leucoferrite, 15));
            outputItem = new ItemStack(CuderaItems.vitrinite, 2);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawHammers(){{
                    // slight overlap between pistons - this is to make linear filtering less ass
                    offsetX = 1.75f;
                    moveX = 4f;
                    moveProgress = a -> Interp.pow2Out.apply(Interp.slope.apply(a));
                }},
                new DrawDefault()
            );
            craftEffect = CuderaEffects.anthraciteCrush;
            size = 2;
            health = 180;
            craftTime = 120f;
            hasItems = true;
            hasLiquids = false;
            hasPower = true;
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.05f;

            consumeItems(with(CuderaItems.anthracite, 1, CuderaItems.algae, 1));
            consumePower(30f / 60f);
        }};
        dihydrateAcidifier = new GenericCrafter("dihydrate-acidifier"){{
            requirements(Category.crafting, with(CuderaItems.leucoferrite, 20, CuderaItems.polysomate, 15));
            outputLiquid = new LiquidStack(CuderaFluids.dihydrate, 0.1f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.dihydrate, 1f),
                new DrawBubbles(){{
                    color = Color.valueOf("c3bec2");
                    amount = 8;
                }},
                new DrawDefault()
            );
            size = 2;
            health = 215;
            craftTime = 240f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            ambientSound = CuderaSounds.bubbling;
            ambientSoundVolume = 0.025f;

            consumeItem(CuderaItems.anthracite, 5);
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(36f / 60f);
        }};
        aragoniteDissolver = new GenericCrafter("aragonite-dissolver"){{
            requirements(Category.crafting, with(CuderaItems.anthracite, 25, CuderaItems.leucoferrite, 15));
            outputLiquid = new LiquidStack(CuderaFluids.solute, 0.1f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.solute, 1f),
                new DrawDefault()
            );
            size = 3;
            health = 260;
            craftTime = 60f / (10f / 6f);
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            liquidCapacity = 18;
            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.05f;

            consumeItem(CuderaItems.aragonite, 1);
            consumeLiquid(CuderaFluids.dihydrate, 0.4f / 3f);
            consumePower(1f);
        }};
        quartzRecrystallizer = new GenericCrafter("quartz-recrystallizer"){{
            requirements(Category.crafting, with(CuderaItems.anthracite, 25, CuderaItems.polysomate, 20));
            outputItem = new ItemStack(CuderaItems.quartz, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.solute, 1f),
                new DrawDefault()
            );
            size = 3;
            health = 285;
            craftTime = 60f;
            hasItems = false;
            hasLiquids = true;
            hasPower = true;
            liquidCapacity = 24;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08f;

            consumeLiquid(CuderaFluids.solute, 0.06f);
            consumePower(1f);
        }};
        lightcrudeProcessor = new GenericCrafter("lightcrude-processor"){
            {
                requirements(Category.crafting, with(CuderaItems.quartz, 30, CuderaItems.polysomate, 25, CuderaItems.cyanomite, 15));
                outputLiquids = LiquidStack.with(CuderaFluids.lightcrude, 0.2f, CuderaFluids.dripgas, 0.1f);
                drawer = new DrawMulti(
                        new DrawRegion("-bottom"),
                        new DrawLiquidTile(CuderaFluids.lightcrude, 1f),
                        new DrawBubbles(){{
                            color = Color.valueOf("f3d283");
                            amount = 6;
                            spread = 5f;
                        }},
                        new DrawDefault(),
                        new DrawLiquidOutputs()
                );
                size = 3;
                health = 310;
                craftTime = 36f;
                rotate = true;
                rotateDraw = false;
                invertFlip = true;
                regionRotated1 = 2;
                liquidOutputDirections = new int[]{1, 3};
                hasItems = true;
                hasLiquids = true;
                hasPower = true;
                itemCapacity = 12;
                liquidCapacity = 36;
                dumpExtraLiquid = false;
                ambientSound = Sounds.extractLoop;
                ambientSoundVolume = 0.08f;

                consumeItem(CuderaItems.vitrinite, 1);
                consumePower(1.25f);
            }

            @Override
            public void loadIcon(){
                super.loadIcon();
                fullIcon = Core.atlas.find(name + "-full", fullIcon);
                uiIcon = Core.atlas.find(name + "-ui", fullIcon);
            }
        };
        naphthaDistiller = new GenericCrafter("naphtha-distiller"){{
            requirements(Category.crafting, with(CuderaItems.quartz, 45, CuderaItems.leucoferrite, 40));
            outputLiquid = new LiquidStack(CuderaFluids.naphtha, 0.1f);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(CuderaFluids.naphtha, 1f),
                    new DrawDefault(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("9269ad");
                    }}
            );
            size = 3;
            health = 340;
            craftTime = 120f;
            hasItems = true;
            hasLiquids = true;
            hasPower = false;
            itemCapacity = 6;
            liquidCapacity = 24;
            ambientSound = Sounds.glow;
            ambientSoundVolume = 0.07f;

            consume(new ConsumeItemFlammable(0.5f));
            consumeLiquid(CuderaFluids.lightcrude, 0.1f);
        }};
    }
}