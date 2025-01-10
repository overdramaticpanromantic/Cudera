package cudera.world.blocks.distribution;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.*;
import mindustry.world.meta.StatUnit;

public class HeatedBlendingDuct extends Duct {
    // Code by Photon_Gravity

    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one. Only heats itself and not other blocks by default.
    public float heatRadius = 0f;

    public TextureRegion[][] overlaySprites;

    public HeatedBlendingDuct(String name) {
        super(name);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (heated && heatRadius > 0f) {
            Drawf.dashCircle(x * Vars.tilesize + this.offset, y * Vars.tilesize + this.offset, heatRadius*8, Pal.placing);
        }
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(CuderaStats.heated, heated);
        if (heated && heatRadius > 0f) {
            stats.add(CuderaStats.heatRadius, heatRadius, StatUnit.blocks);
        }
    }

    @Override
    public void load() {
        super.load();
        overlaySprites = new TextureRegion[5][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                overlaySprites[i][j] = Core.atlas.find(name + "-overlay-" + i + "-" + j);
            }
        }
    }

    @Override
    public boolean blends(Tile tile, int rotation, int direction) {
        Building other = tile.nearbyBuild(Mathf.mod(rotation - direction, 4));
        return super.blends(tile, rotation, direction) && !(other instanceof DirectionBridge.DirectionBridgeBuild bridge && bridge.findLink() != null);
    }

    @Override
    public void loadIcon(){
        super.loadIcon();
        fullIcon = Core.atlas.find(name + "-full", fullIcon);
        uiIcon = Core.atlas.find(name + "-ui", uiIcon);
    }

    public class HeatedBlendingDuctBuild extends DuctBuild implements HeatedBlock {
        @Override
        public void draw() {
            super.draw();
            boolean rotateCorner = blendbits == 1 && (buildBlending(tile, rotation, null, true)[1] * buildBlending(tile, rotation, null, true)[2] == -1);
            boolean rotateYMerge = blendbits == 2 && (buildBlending(tile, rotation, null, true)[1] * buildBlending(tile, rotation, null, true)[2] == -1);
            boolean rotateTMerge = blendbits == 4;

            Draw.rect(overlaySprites[blendbits == 4 ? 2 : blendbits][(rotation
                - (rotateCorner ? 1 : 0)
                - (rotateYMerge ? 2 : 0)
                - (blendbits == 2 || blendbits == 4 ? 1 : 0)
                + (rotateTMerge ? 1 : 0) + 256) % 4], x, y
            );
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
