package cudera.world.blocks.crafting;

import cudera.world.blocks.HeatedBlock;
import mindustry.entities.Effect;

public class ShakeCrafter extends HeatedCrafter {
    public float shakeMag = 1f;

    public ShakeCrafter(String name) {
        super(name);
    }

    public class ShakeCrafterBuild extends HeatedCrafterBuild implements HeatedBlock {
        @Override
        public void craft(){
            super.craft();
            Effect.shake(shakeMag, shakeMag, this);
        }

        @Override
        public boolean isHeated() {
            return heated;
        }
    }
}
