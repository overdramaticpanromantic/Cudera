package cudera.world.blocks;

import mindustry.entities.Effect;
import mindustry.world.blocks.production.GenericCrafter;

public class ShakeCrafter extends GenericCrafter {
    public float shakeMag = 1f;

    public ShakeCrafter(String name) {
        super(name);
    }

    public class ShakeCrafterBuild extends GenericCrafterBuild {
        @Override
        public void craft(){
            consume();

            if(outputItems != null){
                for(var output : outputItems){
                    for(int i = 0; i < output.amount; i++){
                        offload(output.item);
                    }
                }
            }

            if(wasVisible){
                craftEffect.at(x, y);
            }
            progress %= 1f;
            Effect.shake(shakeMag, shakeMag, this);
        }
    }
}
