package cudera.world.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Interp;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;

// This code is not mine. It's made by uujuju, or Liz.

public class DrawHammers extends DrawBlock {
    public TextureRegion hammerRegion, iconRegion;
    public int hammers = 2;
    public float offsetX = 0f, offsetY = 0f;
    public float moveX = 0f, moveY = 0f;
    public String suffix = "-hammer";
    public Interp moveProgress = Interp.slope;

    @Override
    public void draw(Building build){
        float progress = moveProgress.apply(build.progress());
        float mx = moveX * progress, my = moveY * progress;
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