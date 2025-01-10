package cudera.world.blocks.liquids;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import cudera.world.blocks.HeatedBlock;
import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.type.Liquid;
import mindustry.world.blocks.distribution.DirectionLiquidBridge;
import mindustry.world.blocks.liquid.LiquidBlock;

public class HeatedInvertPipeBridge extends DirectionLiquidBridge {
    // Custom code by Photon_Gravity

    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one. Only heats itself and not other blocks by default.
    public float heatRadius = 0f;

    public TextureRegion[] bridgeSprites = new TextureRegion[2];

    public HeatedInvertPipeBridge(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        bridgeSprites[0] = Core.atlas.find(name + "-bridge-0");
        bridgeSprites[1] = Core.atlas.find(name + "-bridge-1");
    }

    // why is it called this
    public class HeatedInvertPipeBridgeBuild extends DirectionLiquidBridge.DuctBridgeBuild implements HeatedBlock {
        public void drawBridgeBuild(int rotation, float x1, float y1, float x2, float y2) {
            Draw.alpha(Renderer.bridgeOpacity);
            float
                    angle = Angles.angle(x1, y1, x2, y2),
                    cx = (x1 + x2)/2f,
                    cy = (y1 + y2)/2f,
                    len = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - size * Vars.tilesize;

            if (rotation == 0 || rotation == 3) {
                Draw.alpha(Renderer.bridgeOpacity);
                Draw.rect(bridgeSprites[0], cx, cy, len, bridgeSprites[0].height * bridgeSprites[0].scl(), angle);
            } else {
                Draw.alpha(Renderer.bridgeOpacity);
                Draw.rect(bridgeSprites[1], cx, cy, len, bridgeSprites[1].height * bridgeSprites[1].scl(), angle);
            }
            Draw.alpha(Renderer.bridgeOpacity);

            for(float i = 6f; i <= len + size * Vars.tilesize - 5f; i += 5f) {
                Draw.rect(arrowRegion, x1 + Geometry.d4x(rotation) * i, y1 + Geometry.d4y(rotation) * i, angle);
            }

            Draw.reset();
        }

        public void drawBridgeBottomRegionBecauseFuckYeah(float x1, float y1, float x2, float y2) {
            Draw.alpha(Renderer.bridgeOpacity);
            float
                    angle = Angles.angle(x1, y1, x2, y2),
                    cx = (x1 + x2)/2f,
                    cy = (y1 + y2)/2f,
                    len = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - size * Vars.tilesize;

            if (bridgeBotRegion.found()) {
                Draw.color(1f, 1f, 1f, Renderer.bridgeOpacity);
                Draw.rect(bridgeBotRegion, cx, cy, len, bridgeBotRegion.height * bridgeBotRegion.scl(), angle);
                Draw.reset();
            }
        }

        @Override
        public void draw() {
            float z = Draw.z();

            Draw.z(Layer.block + 4f);
            Draw.rect(bottomRegion, x, y);
            if(liquids.currentAmount() > 0.001f){
                LiquidBlock.drawTiledFrames(size, x, y, liquidPadding, liquids.current(), liquids.currentAmount() / liquidCapacity);
            }
            Draw.rect(block.region, x, y);
            Draw.rect(dirRegion, x, y, rotdeg());
            var link = findLink();
            if(link != null){
                Draw.z(Layer.block + 2f);
                drawBridgeBottomRegionBecauseFuckYeah(x, y, link.x, link.y);
                if(liquids.currentAmount() > 0.001f) {
                    Draw.color(liquids.current().color, Renderer.bridgeOpacity);
                    Draw.rect(bridgeLiquidRegion, (x + link.x) / 2f, (y + link.y) / 2f , Math.max(Math.abs(x - link.x), Math.abs(y - link.y) - size * Vars.tilesize), bridgeLiquidRegion.height * bridgeLiquidRegion.scl(), Angles.angle(x, y, link.x, link.y));
                    Draw.color();
                    Draw.alpha(Renderer.bridgeOpacity);
                }
                drawBridgeBuild(rotation, x, y, link.x, link.y);
            }
            Draw.z(z);
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            var link = findLink();
            //only accept if there's an output point, or it comes from a link
            if (link == null && !(source instanceof DirectionBridgeBuild b && b.findLink() == this)) return false;
            if (this.block.hasPower && !(consPower.efficiency(this) > 0f)) return false;

            int rel = this.relativeToEdge(source.tile);

            return
                hasLiquids && team == source.team &&
                    (liquids.current() == liquid || liquids.get(liquids.current()) < 0.2f) && rel != rotation &&
                    (occupied[(rel + 2) % 4] == null || occupied[(rel + 2) % 4] == source);
        }

        @Override
        public boolean isHeating(float x, float y) {
            return Mathf.dst(this.x / 8, this.y / 8, x, y) <= heatRadius;
        }

        @Override
        public float getHeatRadius() {
            return heatRadius;
        }
    }
}
