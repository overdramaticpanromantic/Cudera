package cudera.world.blocks.crafting;

import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.StatUnit;

public class HeatedCrafter extends GenericCrafter {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one.
    public float heatRadius = 20f;

    public HeatedCrafter(String name) {
        super(name);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (heated) {
            Drawf.dashCircle(x * Vars.tilesize + this.offset, y * Vars.tilesize + this.offset, heatRadius, Pal.placing);
        }
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(CuderaStats.heated, heated);
        if (heated) {
            stats.add(CuderaStats.heatRadius, heatRadius / 8f, StatUnit.blocks);
        }
    }

    public class HeatedCrafterBuild extends GenericCrafterBuild implements HeatedBlock {
        @Override
        public boolean isHeated() {
            return heated;
        }

        @Override
        public boolean isHeating(float x, float y) {
            return Mathf.dst(this.x, this.y, x, y) <= heatRadius;
        }
    }
}
