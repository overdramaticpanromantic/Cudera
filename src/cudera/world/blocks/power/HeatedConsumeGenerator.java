package cudera.world.blocks.power;

import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.meta.StatUnit;

public class HeatedConsumeGenerator extends ConsumeGenerator {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one.
    public float heatRadius = 20f;

    public HeatedConsumeGenerator(String name) {
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

    // waxed lightly weathered cut copper stairs
    public class HeatedConsumeGeneratorBuild extends ConsumeGeneratorBuild implements HeatedBlock {
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
