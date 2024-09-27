package cudera.world.blocks.storage;

import arc.Core;
import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.StatUnit;

// Literally just CoreBlock but it doesn't take blizzard damage.
// Also, semi-fixes icons to work with linear filtering.
// Anuke, please disable linear filtering by default, I beg of you.

public class HeatedCore extends CoreBlock {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one.
    public float heatRadius = 20f;

    public HeatedCore(String name) {
        super(name);
    }

    @Override
    public void loadIcon(){
        super.loadIcon();
        fullIcon = Core.atlas.find(name + "-full", fullIcon);
        uiIcon = Core.atlas.find(name + "-ui", fullIcon);
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
            stats.add(CuderaStats.heatRadius, heatRadius / 8f, StatUnit.blocks);
        }
    }

    public class HeatedCoreBuild extends CoreBuild implements HeatedBlock {

        @Override
        public boolean isHeating(float x, float y) {
            return Mathf.dst(this.x/8, this.y/8, x, y) <= heatRadius;
        }

        @Override
        public float getHeatRadius() {
            return heatRadius;
        }
    }
}
