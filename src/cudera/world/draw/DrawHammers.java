package cudera.world.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Interp;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;

// Draws hammers that smash together, crafting every time they do.
// Draw code made by uujuju, or Liz. The rest is made by me, and thus, is scuffed as all hell.

public class DrawHammers extends DrawBlock {
    public TextureRegion hammerRegion, iconRegion;
    public int hammers = 2;
    public boolean shake = true;
    public float shakeMag = 1f;
    public float offsetX = 0f, offsetY = 0f;
    public float moveX = 0f, moveY = 0f;
    public String suffix = "-hammer";
    public Interp moveProgress = Interp.slope;

    // DO NOT TOUCH
    public boolean shouldShake = false;

    @Override
    public void draw(Building build){
        float progress = moveProgress.apply(build.progress());
        float mx = moveX * progress, my = moveY * progress;
        if (!shouldShake && progress > 0.01f){
            shouldShake = true;
        }
        if (shake && shouldShake && progress <= 0.01f){
            Effect.shake(shakeMag, shakeMag, build);
            shouldShake = false;
        }
        for (int i = 0; i < hammers; i++){
            Draw.rect(
                    hammerRegion,
                    build.x + Angles.trnsx(360f / hammers * i, offsetX + mx, offsetY + my),
                    build.y + Angles.trnsy(360f / hammers * i, offsetX + mx, offsetY + my)
                    // 360f / hammers * i
            );
        }
    }

    @Override
    public void load(Block block){
        hammerRegion = Core.atlas.find(block.name + suffix);
        iconRegion = Core.atlas.find(block.name + suffix + "-icon");
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{iconRegion};
    }
}