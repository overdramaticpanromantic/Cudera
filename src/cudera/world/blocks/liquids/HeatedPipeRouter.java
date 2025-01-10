package cudera.world.blocks.liquids;

import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import cudera.world.meta.CuderaStats;
import mindustry.gen.Building;
import mindustry.type.Liquid;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.meta.StatUnit;

public class HeatedPipeRouter extends LiquidRouter {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one. Only heats itself and not other blocks by default.
    public float heatRadius = 0f;

    public HeatedPipeRouter(String name) {
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(CuderaStats.heated, heated);
        if (heated && heatRadius > 0f) {
            stats.add(CuderaStats.heatRadius, heatRadius, StatUnit.blocks);
        }
    }

    public class HeatedPipeRouterBuild extends LiquidRouterBuild implements HeatedBlock {
        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return super.acceptLiquid(source, liquid) && this.block.hasPower && consPower.efficiency(this) > 0f;
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
