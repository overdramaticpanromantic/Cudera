package cudera.world.blocks.distribution;

import arc.math.Mathf;
import cudera.world.blocks.HeatedBlock;
import mindustry.world.blocks.distribution.DuctRouter;

public class HeatedDuctRouter extends DuctRouter {
    // Whether this building emits heat or not.
    public boolean heated = false;
    // The radius in which blocks are considered heated alongside this one. Only heats itself and not other blocks by default.
    public float heatRadius = 0f;

    public HeatedDuctRouter(String name) {
        super(name);
    }

    public class HeatedDuctRouterBuild extends DuctRouterBuild implements HeatedBlock {
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
