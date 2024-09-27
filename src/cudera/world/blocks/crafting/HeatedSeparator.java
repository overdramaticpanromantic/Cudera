package cudera.world.blocks.crafting;

import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.production.Separator;
import mindustry.world.meta.StatUnit;

public class HeatedSeparator extends Separator {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one.
    public float heatRadius = 20f;

    public HeatedSeparator(String name) {
        super(name);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (heated) {
            Drawf.dashCircle(x * Vars.tilesize + this.offset, y * Vars.tilesize + this.offset, heatRadius*8, Pal.placing);
        }
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(CuderaStats.heated, heated);
        if (heated) {
            stats.add(CuderaStats.heatRadius, heatRadius, StatUnit.blocks);
        }
    }

    public class HeatedSeparatorBuild extends SeparatorBuild implements HeatedBlock {
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
