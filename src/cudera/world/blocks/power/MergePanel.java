package cudera.world.blocks.power;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import mindustry.Vars;
import mindustry.world.Block;
import mindustry.world.blocks.power.SolarGenerator;

// Solar panels, but they tile. Requires 47 sprites, generate them via GlennFolker/tile-gen.
// The code here is courtesy of Photon_Gravity and GlennFolker.

public class MergePanel extends SolarGenerator {

    public TextureRegion[] regions = new TextureRegion[47];
    public static final int[] tiles = new int[]{
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3,  4,  3,  4, 15, 40, 15, 20,  3,  4,  3,  4, 15, 40, 15, 20,
            5, 28,  5, 28, 29, 10, 29, 23,  5, 28,  5, 28, 31, 11, 31, 32,
            3,  4,  3,  4, 15, 40, 15, 20,  3,  4,  3,  4, 15, 40, 15, 20,
            2, 30,  2, 30,  9, 46,  9, 22,  2, 30,  2, 30, 14, 44, 14,  6,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3,  0,  3,  0, 15, 42, 15, 12,  3,  0,  3,  0, 15, 42, 15, 12,
            5,  8,  5,  8, 29, 35, 29, 33,  5,  8,  5,  8, 31, 34, 31,  7,
            3,  0,  3,  0, 15, 42, 15, 12,  3,  0,  3,  0, 15, 42, 15, 12,
            2,  1,  2,  1,  9, 45,  9, 19,  2,  1,  2,  1, 14, 18, 14, 13
    };

    public MergePanel(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        for (int i = 0; i < 47; i++) {
            regions[i] = Core.atlas.find(name + "-" + i);
        }
    }

    public class MergePanelBuild extends SolarGeneratorBuild {
        @Override
        public void draw() {
            super.draw();
            int mask = 0;
            for (int i = 0; i < 8; i++) {
                Block checkedBlock =  Vars.world.tile((int) (x / 8 + Geometry.d8[i].x), (int) (y / 8 + Geometry.d8[i].y)).block();
                boolean isConnected = checkedBlock != null && checkedBlock == this.block;
                mask |= (isConnected ? 1 : 0) << i;
            }
            Draw.rect(regions[tiles[mask]], x, y);
        }
    }
}
