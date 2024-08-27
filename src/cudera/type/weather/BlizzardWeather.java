package cudera.type.weather;

import arc.Core;
import arc.math.*;
import arc.graphics.g2d.TextureRegion;
import arc.util.Time;
import cudera.world.blocks.HeatedBlock;
import mindustry.Vars;
import mindustry.type.weather.ParticleWeather;
import mindustry.gen.*;
import mindustry.world.Tile;

// Damages blocks every damageTime frames.
// The majority of this code is by sh1penfire and uujuju/Liz.

public class BlizzardWeather extends ParticleWeather {
    public TextureRegion snowRegion;

    public static float time;
    public static float damageTime = 60f;

    public BlizzardWeather(String name) {
        super(name);
    }

    @Override
    public void update(WeatherState state) {
        super.update(state);
        time += Time.delta;
        if (time < damageTime) return;
        time %= damageTime;
        Vars.world.tiles.each((x, y) -> {
            Building b1 = Vars.world.tile(x, y).build;
            if (b1 == null) return;
            if (!(b1 instanceof HeatedBlock b2 && b2.isHeated())) {
                boolean cold = true;
                for (Tile tile : Vars.world.tiles) {
                    if (tile.build instanceof HeatedBlock b3 && b3.isHeating(b1.x, b1.y)) cold = false;
                }
                if (cold) {
                    b1.damage(b1.maxHealth / (6 * Mathf.pow(b1.maxHealth, 0.333f) * Mathf.pow(b1.block.size, 2f)));
                }
            }
        });
    }

    @Override
    public void load() {
        super.load();
        snowRegion = Core.atlas.find("cudera-snow-buildup");
    }
}
