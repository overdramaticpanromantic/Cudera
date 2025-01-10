package cudera.content;

import arc.Core;
import arc.graphics.Color;
import arc.math.Interp;
import cudera.world.blocks.distribution.*;
import cudera.world.blocks.crafting.*;
import cudera.world.blocks.liquids.*;
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
import mindustry.world.meta.*;

public class CuderaBlocks {
    public static Block
    // environment - walls
    siltWall,
    // environment - floors
    siltFloor,
    // production
    algalPropagator,
    // distribution
    twinevoltDuct, twinevoltRouter, twinevoltBridge,
    // liquid
    electrocoilPipe, electrocoilPipeDirector, electrocoilPipeBridge,
    // power
    raycastPylon, capacitorCell, photovoltaicCollector, biosyntheticGenerator, chemolyticBoiler, chemolyticConverter, chemolyticEngine,
    // crafting
    biosiltStrainer, leucoferriteKiln, vitriniteCompactor, theoserineGalvanizer, dihydrateAcidifier, aragoniteDissolver,
    quartzRecrystallizer, lightcrudeProcessor, petroleumBoiler, polymerPress, martensiteHardener, plasteelFoundry,
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
            requirements(Category.production, ItemStack.with(CuderaItems.cyanomite, 50, CuderaItems.leucoferrite, 40));
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
                colorTo = col1.cpy().a(0f);
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
        // distribution
        twinevoltDuct = new HeatedBlendingDuct("twinevolt-duct"){{
            requirements(Category.distribution, ItemStack.with(CuderaItems.cyanomite, 2, CuderaItems.leucoferrite, 1));
            health = 180;
            speed = 6f;
            armored = true;
            enableDrawStatus = false;

            consumesPower = true;
            conductivePower = true;
            consumePower(0.016667f);

            heated = true;
            heatRadius = 0f;
        }};
        twinevoltRouter = new HeatedDuctRouter("twinevolt-router"){{
            requirements(Category.distribution, ItemStack.with(CuderaItems.cyanomite, 10, CuderaItems.leucoferrite, 20));
            health = 220;
            speed = 6f;
            enableDrawStatus = false;

            consumesPower = true;
            conductivePower = true;
            consumePower(0.16667f);

            heated = true;
            heatRadius = 0f;
        }};
        twinevoltBridge = new HeatedInvertDuctBridge("twinevolt-bridge"){{
            requirements(Category.distribution, ItemStack.with(CuderaItems.cyanomite, 20, CuderaItems.leucoferrite, 10));
            health = 200;
            speed = 6f;
            range = 2;
            itemCapacity = 3;
            enableDrawStatus = false;

            consumesPower = true;
            conductivePower = true;
            consumePower(0.083334f);

            heated = true;
            heatRadius = 0f;
        }};
        // liquid
        electrocoilPipe = new HeatedBlendingPipe("electrocoil-pipe"){{
            requirements(Category.liquid, ItemStack.with(CuderaItems.cyanomite, 1));
            health = 160;
            leaks = true;
            liquidCapacity = 20f;
            liquidPressure = 1.05f;
            botColor = Color.valueOf("405556");
            enableDrawStatus = false;

            consumesPower = true;
            conductivePower = true;
            consumePower(0.016667f);

            heated = true;
            heatRadius = 0f;
        }};
        electrocoilPipeDirector = new HeatedPipeRouter("electrocoil-pipe-director"){{
            requirements(Category.liquid, ItemStack.with(CuderaItems.cyanomite, 10));
            health = 180;
            liquidCapacity = 30f;
            liquidPadding = 0.75f;
            solid = false;
            enableDrawStatus = false;

            consumesPower = true;
            conductivePower = true;
            consumePower(0.016667f);

            heated = true;
            heatRadius = 0f;
        }};
        electrocoilPipeBridge = new HeatedInvertPipeBridge("electrocoil-pipe-bridge"){{
            requirements(Category.liquid, ItemStack.with(CuderaItems.cyanomite, 15));
            health = 200;
            liquidCapacity = 30f;
            range = 2;
            enableDrawStatus = false;

            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            consumePower(0.083334f);

            heated = true;
            heatRadius = 0f;

            ((HeatedBlendingPipe) electrocoilPipe).rotBridgeReplacement = this;
        }};
        // power
        raycastPylon = new RaycastPylon("raycast-pylon"){{
            requirements(Category.power, ItemStack.with(CuderaItems.cyanomite, 12));
            size = 1;
            range = 11;
            health = 120;
        }};
        capacitorCell = new Battery("capacitor-cell"){{
            requirements(Category.power, ItemStack.with(CuderaItems.cyanomite, 25));
            size = 2;
            health = 260;
            baseExplosiveness = 2f;

            consumePowerBuffered(6000f);
        }};
        photovoltaicCollector = new MergePanel("photovoltaic-collector"){{
            requirements(Category.power, ItemStack.with(CuderaItems.cyanomite, 20));
            size = 1;
            health = 140;
            powerProduction = 0.2f;
        }};
        biosyntheticGenerator = new HeatedConsumeGenerator("biosynthetic-generator"){{
            requirements(Category.power, ItemStack.with(CuderaItems.cyanomite, 35, CuderaItems.polysomate, 15));
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 1f),
                new DrawDefault()
            );
            generateEffect = new ParticleEffect(){{
                particles = 1;
                colorFrom = Color.valueOf("2d2820").a(0.8f);
                colorTo = Color.valueOf("45413b").a(0f);
                interp = Interp.fade;
                cone = 20f;
                length = 40f;
                lifetime = 300f;
                baseRotation = 65f;
                sizeFrom = 2f;
                sizeTo = 3f;
            }};
            generateEffectRange = 0f;
            effectChance = 0.4f;
            size = 2;
            health = 240;
            powerProduction = 5f;
            itemDuration = 120f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.05f;

            consumeLiquid(Liquids.water, 1f);
            consume(new ConsumeItemFlammable(0.5f));

            heated = true;
            heatRadius = 8f;
        }};
        chemolyticBoiler = new HeatedCrafter("chemolytic-boiler"){
            {
                requirements(Category.power, ItemStack.with(CuderaItems.anthracite, 80, CuderaItems.theoserine, 70, CuderaItems.quartz, 55));
                outputLiquid = new LiquidStack(CuderaFluids.naphtha, 50f);
                drawer = new DrawMulti(
                        new DrawRegion("-bottom"),
                        new DrawLiquidTile(CuderaFluids.petroleum, 0.5f),
                        new DrawBubbles() {{
                            color = CuderaFluids.petroleum.gasColor;
                            amount = 12;
                            spread = 8f;
                        }},
                        new DrawDefault()
                );
                size = 3;
                health = 385;
                liquidCapacity = 3000;
                hasItems = false;
                hasLiquids = true;
                hasPower = true;
                // it's the best i can do okay
                ambientSound = Sounds.spellLoop;
                ambientSoundVolume = 0.08f;

                rotate = false;
                rotateDraw = false;
                liquidOutputDirections = new int[1];

                consumeLiquid(CuderaFluids.petroleum, 0.8f);
                consumePower(2f);

                heated = true;
                heatRadius = 11f;
            }

            @Override
            public void loadIcon(){
                super.loadIcon();
                fullIcon = Core.atlas.find(name + "-full", fullIcon);
                uiIcon = Core.atlas.find(name + "-full", fullIcon);
            }
        };
        chemolyticConverter = new HeatedCrafter("chemolytic-converter"){
            {
                requirements(Category.power, ItemStack.with(CuderaItems.leucoferrite, 105, CuderaItems.aragonite, 90, CuderaItems.polymer, 75, CuderaItems.martensite, 65));
                outputLiquid = new LiquidStack(CuderaFluids.hydroflux, 50f);
                drawer = new DrawMulti(
                        new DrawRegion("-bottom"),
                        new DrawLiquidTile(CuderaFluids.naphtha, 0.5f),
                        new DrawDefault()
                );
                size = 3;
                health = 425;
                hasItems = false;
                hasLiquids = true;
                hasPower = true;
                liquidCapacity = 3000;
                ambientSound = Sounds.extractLoop;
                ambientSoundVolume = 0.07f;

                rotate = false;
                rotateDraw = false;
                liquidOutputDirections = new int[1];

                consumeLiquids(LiquidStack.with(CuderaFluids.naphtha, 50f, Liquids.water, 2f));
                consumePower(2f);

                heated = true;
                heatRadius = 11f;
            }

            @Override
            public void loadIcon(){
                super.loadIcon();
                fullIcon = Core.atlas.find(name + "-full", fullIcon);
                uiIcon = Core.atlas.find(name + "-full", fullIcon);
            }
        };
        chemolyticEngine = new HeatedConsumeGenerator("chemolytic-engine"){
            {
                requirements(Category.power, ItemStack.with(CuderaItems.cyanomite, 85, CuderaItems.polysomate, 70, CuderaItems.quartz, 60));
                drawer = new DrawMulti(
                        new DrawRegion("-bottom"),
                        new DrawLiquidTile(CuderaFluids.naphtha, 0.5f),
                        new DrawLiquidTile(CuderaFluids.hydroflux, 0.5f),
                        new DrawRegion("-turbine", -6f, true),
                        new DrawRegion("-turbine-diagonal", -6f, true),
                        new DrawDefault()
                );
                generateEffect = new ParticleEffect(){{
                    particles = 1;
                    colorFrom = Color.valueOf("ffffff").a(0.3f);
                    colorTo = Color.valueOf("ffffff").a(0f);
                    interp = Interp.fade;
                    cone = 20f;
                    length = 40f;
                    lifetime = 300f;
                    baseRotation = 65f;
                    sizeFrom = 2f;
                    sizeTo = 3f;
                }};
                generateEffectRange = 4f;
                effectChance = 0.5f;
                size = 3;
                health = 390;
                powerProduction = 4f;
                liquidCapacity = 3000;
                hasItems = true;
                hasLiquids = true;
                hasPower = true;
                ambientSound = Sounds.electricHum;
                ambientSoundVolume = 0.08f;

                warmupSpeed = 0.003334f;

                consume(new ConsumeLiquidFlammable(5f, 50f));

                heated = true;
                heatRadius = 11f;
            }

            @Override
            public void loadIcon(){
                super.loadIcon();
                fullIcon = Core.atlas.find(name + "-full", fullIcon);
                uiIcon = Core.atlas.find(name + "-full", fullIcon);
            }
            @Override
            public void setStats(){
                super.setStats();
                stats.remove(Stat.basePowerGeneration);
                stats.add(Stat.basePowerGeneration, powerProduction * 300f, StatUnit.powerSecond);
                stats.add(Stat.basePowerGeneration, powerProduction * 600f, StatUnit.powerSecond);
            }
        };
        // crafting
        biosiltStrainer = new HeatedSeparator("biosilt-strainer"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 35));
            results = ItemStack.with(CuderaItems.anthracite, 1, CuderaItems.vitrinite, 1);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 1f),
                new DrawRegion("-spinner", 1, true),
                new DrawDefault()
            );
            size = 2;
            health = 170;
            craftTime = 30f;
            hasItems = true;
            hasLiquids = true;
            hasPower = false;

            consumeItem(CuderaItems.biosilt, 1);
            consumeLiquid(Liquids.water, 0.5f);
        }};
        leucoferriteKiln = new HeatedCrafter("leucoferrite-kiln"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 45, CuderaItems.anthracite, 40));
            outputItem = new ItemStack(CuderaItems.leucoferrite, 8);
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

            consumeItem(CuderaItems.cyanomite, 8);
            consumePower(20f / 60f);

            heated = true;
            heatRadius = 8f;
        }};
        vitriniteCompactor = new ShakeCrafter("vitrinite-compactor"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 50, CuderaItems.leucoferrite, 35));
            outputItem = new ItemStack(CuderaItems.vitrinite, 4);
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

            consumeItems(ItemStack.with(CuderaItems.anthracite, 1, CuderaItems.algae, 2));
        }};
        theoserineGalvanizer = new HeatedCrafter("theoserine-galvanizer"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 65, CuderaItems.polysomate, 50));
            outputItem = new ItemStack(CuderaItems.theoserine, 3);
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
            craftTime = 90f;
            hasItems = true;
            hasLiquids = false;
            hasPower = true;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.05f;

            consumeItems(ItemStack.with(CuderaItems.leucoferrite, 1, CuderaItems.polysomate, 2));
            consumePower(36f / 60f);

            heated = true;
            heatRadius = 13f;
        }};
        dihydrateAcidifier = new HeatedCrafter("dihydrate-acidifier"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.polysomate, 40, CuderaItems.theoserine, 30));
            outputLiquid = new LiquidStack(CuderaFluids.dihydrate, 0.6f);
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

            consumeItem(CuderaItems.anthracite, 6);
            consumeLiquid(Liquids.water, 0.4f);
            consumePower(36f / 60f);
        }};
        aragoniteDissolver = new HeatedCrafter("aragonite-dissolver"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.anthracite, 70, CuderaItems.leucoferrite, 55, CuderaItems.theoserine, 45));
            outputLiquid = new LiquidStack(CuderaFluids.solute, 1f);
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

            consumeItem(CuderaItems.aragonite, 2);
            consumeLiquid(CuderaFluids.dihydrate, 0.4f);
            consumePower(1f);
        }};
        quartzRecrystallizer = new HeatedCrafter("quartz-recrystallizer"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 85, CuderaItems.polysomate, 75, CuderaItems.aragonite, 60));
            outputItem = new ItemStack(CuderaItems.quartz, 3);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.solute, 1f),
                new DrawDefault()
            );
            size = 3;
            health = 285;
            craftTime = 90f;
            hasItems = false;
            hasLiquids = true;
            hasPower = true;
            liquidCapacity = 24;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08f;

            consumeLiquid(CuderaFluids.solute, 0.5f);
            consumePower(1.25f);
        }};
        lightcrudeProcessor = new HeatedCrafter("lightcrude-processor"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.leucoferrite, 75, CuderaItems.theoserine, 70, CuderaItems.quartz, 60));
            outputLiquid = new LiquidStack(CuderaFluids.lightcrude, 1f);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.lightcrude, 1f),
                new DrawBubbles(){{
                    color = CuderaFluids.lightcrude.gasColor;
                    amount = 6;
                    spread = 5f;
                }},
                new DrawDefault()
            );
            size = 3;
            health = 310;
            craftTime = 40f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 12;
            liquidCapacity = 36;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08f;

            consumeItem(CuderaItems.vitrinite, 1);
            consumePower(1.5f);

            heated = true;
            heatRadius = 10f;
        }};
        petroleumBoiler = new HeatedCrafter("petroleum-boiler"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.anthracite, 90, CuderaItems.polysomate, 80, CuderaItems.aragonite, 65, CuderaItems.quartz, 55));
            outputLiquid = new LiquidStack(CuderaFluids.petroleum, 0.8f);
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

            consumeLiquids(LiquidStack.with(CuderaFluids.lightcrude, 0.8f));
            consumePower(1.5f);

            heated = true;
            heatRadius = 13f;
        }};
        polymerPress = new HeatedCrafter("polymer-press"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 55, CuderaItems.theoserine, 40, CuderaItems.quartz, 30));
            outputItem = new ItemStack(CuderaItems.polymer, 4);
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(CuderaFluids.petroleum, 1f),
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

            consumeLiquids(LiquidStack.with(CuderaFluids.petroleum, 0.5f, CuderaFluids.dihydrate, 1f / 3f));
            consumePower(1f);
        }};
        martensiteHardener = new HeatedCrafter("martensite-hardener"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.cyanomite, 115, CuderaItems.leucoferrite, 100, CuderaItems.quartz, 90, CuderaItems.polymer, 75));
            outputItem = new ItemStack(CuderaItems.martensite, 3);
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
            craftTime = 90f;
            hasItems = true;
            hasLiquids = true;
            hasPower = false;
            itemCapacity = 15;
            liquidCapacity = 180;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.08f;

            consumeItems(ItemStack.with(CuderaItems.leucoferrite, 2, CuderaItems.theoserine, 1));
            consumeLiquid(Liquids.water, 1.5f);
        }};
        plasteelFoundry = new HeatedCrafter("plasteel-foundry"){{
            requirements(Category.crafting, ItemStack.with(CuderaItems.anthracite, 140, CuderaItems.polysomate, 125, CuderaItems.aragonite, 115, CuderaItems.quartz, 100, CuderaItems.polymer, 90, CuderaItems.martensite, 75));
            outputItem = new ItemStack(CuderaItems.plasteel, 10);
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
            craftTime = 300f;
            hasItems = true;
            hasPower = true;
            itemCapacity = 24;
            ambientSound = CuderaSounds.plasteelHum;
            ambientSoundVolume = 0.06f;

            consumeItems(ItemStack.with(CuderaItems.polysomate, 6, CuderaItems.martensite, 4, CuderaItems.polymer, 4));
            consumePower(2f);

            heated = true;
            heatRadius = 15f;
        }};
        // storage
        coreTide = new HeatedCore("core-tide"){{
            requirements(Category.effect, ItemStack.with(CuderaItems.cyanomite, 650, CuderaItems.leucoferrite, 550));
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