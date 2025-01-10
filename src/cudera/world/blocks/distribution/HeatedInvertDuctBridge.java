package cudera.world.blocks.distribution;

import java.util.ArrayList;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Nullable;
import cudera.world.blocks.HeatedBlock;
import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.blocks.distribution.DuctBridge;

public class HeatedInvertDuctBridge extends DuctBridge {
    // Custom code by Photon_Gravity

    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one. Only heats itself and not other blocks by default.
    public float heatRadius = 0f;

    public TextureRegion[] bridgeSprites = new TextureRegion[2];

    public HeatedInvertDuctBridge(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        bridgeSprites[0] = Core.atlas.find(name + "-bridge-0");
        bridgeSprites[1] = Core.atlas.find(name + "-bridge-1");
    }

    public class HeatedInvertDuctBridgeBuild extends DuctBridge.DuctBridgeBuild implements HeatedBlock {
        ArrayList<Item> storedItems = new ArrayList<>();
        ArrayList<Float> itemOffsets = new ArrayList<>();

        public void drawBridgeBuild(int rotation, float x1, float y1, float x2, float y2, @Nullable Color liquidColor) {
            Draw.alpha(Renderer.bridgeOpacity);
            float
                angle = Angles.angle(x1, y1, x2, y2),
                cx = (x1 + x2)/2f,
                cy = (y1 + y2)/2f,
                len = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - size * Vars.tilesize;

            if (bridgeBotRegion.found()){
                Draw.color(1f, 1f, 1f, Renderer.bridgeOpacity);
                Draw.rect(bridgeBotRegion, cx, cy, len, bridgeBotRegion.height * bridgeBotRegion.scl(), angle);
                Draw.reset();
            }
            for (int i = 0; i < storedItems.size(); i++) {
                Item item = storedItems.get(i);
                float offset = itemOffsets.get(i);
                TextureRegion itemRegion = item.fullIcon;

                Draw.alpha(Renderer.bridgeOpacity);
                Draw.rect(itemRegion, x1 + (x2 - x1 - Geometry.d4x[rotation] * 8) * offset / speed + Geometry.d4x[rotation] * 4, y1 + (y2 - y1 - Geometry.d4y[rotation] * 8) * offset / speed + Geometry.d4y[rotation] * 4, Vars.itemSize * 0.4f, Vars.itemSize * 0.4f);
            }
            if (liquidColor != null){
                Draw.color(liquidColor, liquidColor.a * Renderer.bridgeOpacity);
                Draw.rect(bridgeLiquidRegion, cx, cy, len, bridgeLiquidRegion.height * bridgeLiquidRegion.scl(), angle);
                Draw.color();
                Draw.alpha(Renderer.bridgeOpacity);
            }
            if (rotation == 0 || rotation == 3) {
                Draw.alpha(Renderer.bridgeOpacity);
                Draw.rect(bridgeSprites[0], cx, cy, len, bridgeSprites[0].height * bridgeSprites[0].scl(), angle);
            } else {
                Draw.alpha(Renderer.bridgeOpacity);
                Draw.rect(bridgeSprites[1], cx, cy, len, bridgeSprites[1].height * bridgeSprites[1].scl(), angle);
            }
            Draw.alpha(Renderer.bridgeOpacity);

            for(float i = 6f; i <= len + size * Vars.tilesize - 5f; i += 5f){
                Draw.rect(arrowRegion, x1 + Geometry.d4x(rotation) * i, y1 + Geometry.d4y(rotation) * i, angle);
            }

            Draw.reset();
        }

        @Override
        public void draw() {
            float z = Draw.z();

            Draw.z(Layer.block + 4f);
            Draw.rect(block.region, x, y);
            Draw.rect(dirRegion, x, y, rotdeg());
            var link = findLink();
            if(link != null){
                Draw.z(Layer.block + 2f);
                drawBridgeBuild(rotation, x, y, link.x, link.y, null);
            }
            Draw.z(z);
        }

        @Override
        public void updateTile() {
            var link = lastLink = findLink();
            if(link != null){
                link.occupied[rotation % 4] = this;
                if(items.any() && link.items.total() < link.block.itemCapacity){
                    progress += edelta();
                    //noinspection Java8ListReplaceAll
                    for (int i = 0; i < itemOffsets.size(); i++) {
                        itemOffsets.set(i, itemOffsets.get(i) + edelta());
                    }
                    while(progress > speed){
                        Item next = items.take();
                        if(next != null && link.items.total() < link.block.itemCapacity) {
                            link.handleItem(this, next);
                        }
                        progress -= speed;
                    }
                }
            }

            if(link == null && items.any()) {
                Item next = items.first();
                if(moveForward(next)) {
                    items.remove(next, 1);
                }
            }

            for(int i = 0; i < 4; i++){
                if(occupied[i] == null || occupied[i].rotation != i || !occupied[i].isValid() || occupied[i].lastLink != this) {
                    occupied[i] = null;
                }
            }

            int iter = 0;
            while(iter < itemOffsets.size()){
                if (itemOffsets.get(iter) > speed) {
                    itemOffsets.remove(iter);
                    storedItems.remove(iter);
                } else {
                    iter++;
                }
            }
        }

        @Override
        public void handleItem(Building source, Item item) {
            super.handleItem(source, item);

            storedItems.add(item);
            itemOffsets.add(0f);
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
