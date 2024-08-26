package cudera.world.blocks.storage;

import arc.Core;
import cudera.world.blocks.HeatedBlock;
import mindustry.world.blocks.storage.CoreBlock;

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

    public class HeatedCoreBuild extends CoreBuild implements HeatedBlock {
        @Override
        public boolean isHeated() {
            return heated;
        }
    }
}
