package cudera.content;

import arc.Core;
import arc.graphics.Color;
import arc.math.Interp;
import cudera.world.blocks.crafting.*;
import cudera.world.blocks.power.*;
import cudera.world.blocks.storage.*;
import cudera.world.draw.*;
import mindustry.content.Liquids;
import mindustry.entities.effect.*;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class CuderaBlocks {
    public static Block
    // environment - walls
    siltWall,
    // environment - floors
    siltFloor,
    // production
    algalPropagator,
    // power
    raycastPylon, capacitorCell, photovoltaicCollector,
    // crafting
    leucoferriteKiln, siltStrainer, vitriniteCompactor, dihydrateAcidifier, aragoniteDissolver, quartzRecrystallizer, lightcrudeProcessor, naphthaDistiller, polymerPress,
    martensiteHardener, theoserineGalvanizer, petroleumBoiler, thermoplastCondenser, plasteelFoundry,
    // storage
    coreTide;

    public static void load(){
        // environment - walls
        siltWall = new StaticWall("silt-wall"){{
            variants = 3;
        }};
        // environment - floors
        siltFloor = new Floor("silt-floor"){{
            variants = 11;
        }};
        // production
        algalPropagator = new HeatedCrafter("algal-propagator"){{
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
        // power
        raycastPylon = new RaycastPylon("raycast-pylon"){{
            requirements(Category.power, with(CuderaItems.cyanomite, 12));
            size = 1;
            range = 11;
            health = 120;
        }};
        capacitorCell = new Battery("capacitor-cell"){{
            requirements(Category.power, with(CuderaItems.cyanomite, 25));
            size = 2;
            health = 260;
            baseExplosiveness = 2f;

            consumePowerBuffered(6000f);
        }};
        photovoltaicCollector = new MergePanel("photovoltaic-collector"){{
            requirements(Category.power, with(CuderaItems.cyanomite, 20));
            size = 1;
            health = 140;
            powerProduction = 0.2f;
        }};
        // crafting
        leucoferriteKiln = new HeatedCrafter("leucoferrite-kiln"){{
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

            heated = true;
            heatRadius = 8f;
        }};
        siltStrainer = new HeatedSeparator("silt-strainer"){{
            requirements(Category.crafting, with(CuderaItems.polysomate, 15, CuderaItems.cyanomite, 10));
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
            hasPower = false;

            consumeItem(CuderaItems.silt, 1);
            consumeLiquid(Liquids.water, 0.1f);
        }};
        vitriniteCompactor = new ShakeCrafter("vitrinite-compactor"){{
            requirements(Category.crafting, with(CuderaItems.leucoferrite, 20, CuderaItems.cyanomite, 15));
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
            hasPower = false;
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.05f;

            consumeItems(with(CuderaItems.anthracite, 1, CuderaItems.algae, 1));
        }};
        dihydrateAcidifier = new HeatedCrafter("dihydrate-acidifier"){{
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
        aragoniteDissolver = new HeatedCrafter("aragonite-dissolver"){{
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
        quartzRecrystallizer = new HeatedCrafter("quartz-recrystallizer"){{
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
        lightcrudeProcessor = new HeatedCrafter("lightcrude-processor"){
            {
                requirements(Category.crafting, with(CuderaItems.quartz, 30, CuderaItems.polysomate, 25, CuderaItems.cyanomite, 15));
                outputLiquids = LiquidStack.with(CuderaFluids.lightcrude, 0.2f, CuderaFluids.dripgas, 0.1f);
                drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(CuderaFluids.lightcrude, 1f),
                    new DrawBubbles(){{
                        color = CuderaFluids.lightcrude.gasColor;
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

                heated = true;
                heatRadius = 10f;
            }

            @Override
            public void loadIcon(){
                super.loadIcon();
                fullIcon = Core.atlas.find(name + "-full", fullIcon);
                uiIcon = Core.atlas.find(name + "-ui", fullIcon);
            }
        };
        naphthaDistiller = new HeatedCrafter("naphtha-distiller"){{
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

            heated = true;
            heatRadius = 11f;
        }};
        polymerPress = new HeatedCrafter("polymer-press"){{
            requirements(Category.crafting, with(CuderaItems.quartz, 30, CuderaItems.cyanomite, 25));
            outputItem = new ItemStack(CuderaItems.polymer, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.naphtha, 1f),
                new DrawDefault(),
                new DrawPress(){{
                    progress = a -> Interp.sine.apply(Interp.slope.apply(a));
                }}
            );
            craftEffect = CuderaEffects.polymerSquish;
            size = 2;
            health = 260;
            craftTime = 120f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 8;
            liquidCapacity = 12;
            ambientSound = Sounds.bioLoop;
            ambientSoundVolume = 0.06f;

            consumeLiquids(LiquidStack.with(CuderaFluids.naphtha, 0.1f, CuderaFluids.dihydrate, 0.05f));
            consumePower(40f / 60f);
        }};
        martensiteHardener = new HeatedCrafter("martensite-hardener"){{
            requirements(Category.crafting, with(CuderaItems.leucoferrite, 55, CuderaItems.quartz, 40, CuderaItems.polymer, 30));
            outputItem = new ItemStack(CuderaItems.martensite, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawCells(){{
                    range = 12f;
                    particles = 20;
                    color = Color.valueOf("4655aa");
                    particleColorFrom = Color.valueOf("5981d7");
                    particleColorTo = Color.valueOf("5981d7");
                }},
                new DrawDefault()
            );
            size = 3;
            health = 360;
            craftTime = 24f;
            hasItems = true;
            hasLiquids = true;
            hasPower = false;
            itemCapacity = 15;
            liquidCapacity = 12;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.08f;

            consumeItems(ItemStack.with(CuderaItems.leucoferrite, 2, CuderaItems.anthracite, 1));
            consumeLiquid(Liquids.water, 0.2f);
        }};
        theoserineGalvanizer = new HeatedCrafter("theoserine-galvanizer"){{
            requirements(Category.crafting, with(CuderaItems.quartz, 50, CuderaItems.polymer, 45, CuderaItems.martensite, 30));
            outputItem = new ItemStack(CuderaItems.theoserine, 2);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color.valueOf("ffef99")){{
                    flameRadius = 3f;
                    flameRadiusIn = 2f;
                }},
                new DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color.valueOf("ffef99")){{
                    flameRadius = 2f;
                    flameRadiusIn = 1.3f;
                    flameX = 6f;
                    flameY = 6f;
                }},
                new DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color.valueOf("ffef99")){{
                    flameRadius = 2f;
                    flameRadiusIn = 1.3f;
                    flameX = 6f;
                    flameY = -6f;
                }},
                new DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color.valueOf("ffef99")){{
                    flameRadius = 2f;
                    flameRadiusIn = 1.3f;
                    flameX = -6f;
                    flameY = -6f;
                }},
                new DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color.valueOf("ffef99")){{
                    flameRadius = 2f;
                    flameRadiusIn = 1.3f;
                    flameX = -6f;
                    flameY = 6f;
                }}
            );
            craftEffect = CuderaEffects.smeltSmokeHuge;
            size = 3;
            health = 410;
            craftTime = 120f;
            hasItems = true;
            hasLiquids = false;
            hasPower = true;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.05f;

            consumeItems(ItemStack.with(CuderaItems.martensite, 1, CuderaItems.polysomate, 2));
            consumePower(36f / 60f);

            heated = true;
            heatRadius = 13f;
        }};
        petroleumBoiler = new HeatedCrafter("petroleum-boiler"){{
            requirements(Category.crafting, with(CuderaItems.polymer, 40, CuderaItems.martensite, 35));
            outputLiquid = new LiquidStack(CuderaFluids.petroleum, 0.1f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.petroleum, 1f),
                new DrawBubbles(){{
                    color = CuderaFluids.petroleum.gasColor;
                    amount = 6;
                    spread = 5f;
                }},
                new DrawDefault()
            );
            size = 3;
            health = 380;
            craftTime = 60f;
            hasItems = false;
            hasLiquids = true;
            hasPower = true;
            liquidCapacity = 12;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.06f;

            consumeLiquids(LiquidStack.with(CuderaFluids.naphtha, 0.1f, CuderaFluids.dripgas, 0.05f));
            consumePower(1f);

            heated = true;
            heatRadius = 13f;
        }};
        thermoplastCondenser = new HeatedCrafter("thermoplast-condenser"){{
            requirements(Category.crafting, with(CuderaItems.polysomate, 35, CuderaItems.polymer, 25, CuderaItems.theoserine, 20));
            outputItem = new ItemStack(CuderaItems.thermoplast, 1);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(CuderaFluids.petroleum, 1f),
                    new DrawDefault()
            );
            craftEffect = CuderaEffects.thermoplastCraft;
            size = 2;
            health = 320;
            craftTime = 60f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 6;
            liquidCapacity = 12;
            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.06f;

            consumeItem(CuderaItems.quartz, 1);
            consumeLiquid(CuderaFluids.petroleum, 0.1f);
            consumePower(1f);
        }};
        plasteelFoundry = new HeatedCrafter("plasteel-foundry"){{
            requirements(Category.crafting, with(CuderaItems.quartz, 65, CuderaItems.martensite, 50, CuderaItems.thermoplast, 45));
            outputItem = new ItemStack(CuderaItems.plasteel, 2);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawCrucibleFlame(),
                    new DrawDefault()
            );
            craftEffect = new ExplosionEffect(){{
                layer = 30.002f;
                lifetime = 60f;
                waveLife = 8f;
                waveStroke = 2f;
                waveRad = 8f;
                waveRadBase = 1f;
                sparkStroke = 1f;
                sparkRad = 12f;
                sparkLen = 3f;
                smokeSize = 3f;
                smokes = 6;
                sparks = 8;
            }};
            size = 3;
            health = 420;
            craftTime = 90f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 12;
            liquidCapacity = 12;
            ambientSound = CuderaSounds.plasteelHum;
            ambientSoundVolume = 0.06f;

            consumeItems(ItemStack.with(CuderaItems.martensite, 2, CuderaItems.thermoplast, 2));
            consumeLiquid(CuderaFluids.dihydrate, 0.1f);
            consumePower(90f / 60f);

            heated = true;
            heatRadius = 15f;
        }};
        // storage
        coreTide = new HeatedCore("core-tide"){{
            requirements(Category.effect, with(CuderaItems.cyanomite, 650, CuderaItems.leucoferrite, 550, CuderaItems.polysomate, 450));
            size = 2;
            alwaysUnlocked = true;
            // unitType = CuderaUnits.gale
            unitCapModifier = 6;
            isFirstTier = true;
            health = 850;
            itemCapacity = 2400;

            heated = true;
            heatRadius = 30f;
        }};
    }
}