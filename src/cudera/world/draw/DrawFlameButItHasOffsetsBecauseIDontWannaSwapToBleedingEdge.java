package cudera.world.draw;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.world.draw.DrawFlame;

public class DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge extends DrawFlame {
    public float flameX = 0, flameY = 0;

    public DrawFlameButItHasOffsetsBecauseIDontWannaSwapToBleedingEdge(Color flameColor){
        this.flameColor = flameColor;
    }

    @Override
    public void draw(Building build){
        if(build.warmup() > 0f && flameColor.a > 0.001f){
            float g = 0.3f;
            float r = 0.06f;
            float cr = Mathf.random(0.1f);

            Draw.z(Layer.block + 0.01f);

            Draw.alpha(build.warmup());
            Draw.rect(top, build.x, build.y);

            Draw.alpha(((1f - g) + Mathf.absin(Time.time, 8f, g) + Mathf.random(r) - r) * build.warmup());

            Draw.tint(flameColor);
            Fill.circle(build.x + flameX, build.y + flameY, flameRadius + Mathf.absin(Time.time, flameRadiusScl, flameRadiusMag) + cr);
            Draw.color(1f, 1f, 1f, build.warmup());
            Fill.circle(build.x + flameX, build.y + flameY, flameRadiusIn + Mathf.absin(Time.time, flameRadiusScl, flameRadiusInMag) + cr);

            Draw.color();
        }
    }

    @Override
    public void drawLight(Building build){
        Drawf.light(build.x + flameX, build.y + flameY, (lightRadius + Mathf.absin(lightSinScl, lightSinMag)) * build.warmup() * build.block.size, flameColor, lightAlpha);
    }
}
