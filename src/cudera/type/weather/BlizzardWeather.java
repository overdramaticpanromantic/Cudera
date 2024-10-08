package cudera.type.weather;
import arc.math.Mathf;
import arc.util.Time;
import cudera.world.blocks.HeatedBlock;
import cudera.world.util.BoolMask;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.WeatherState;
import mindustry.type.weather.ParticleWeather;

// Damages blocks every damageTime frames.
// The majority of this code is by Photon_Gravity.

public class BlizzardWeather extends ParticleWeather {
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
        BoolMask isHeated = new BoolMask(Vars.world.width(), Vars.world.height());
        Vars.world.tiles.each((x, y) -> {
            Building b1 = Vars.world.tile(x, y).build;
            if (b1 == null) return;
            if (b1 instanceof HeatedBlock) {
                isHeated.addHeatOfBlock(b1);
            }
        });

        Vars.world.tiles.each((x, y) ->{
            Building b3 = Vars.world.tile(x, y).build;
            if (b3 == null) return;
            if (!isHeated.get(x, y)){
                b3.damage(b3.maxHealth/ (6 * Mathf.pow(b3.maxHealth, 0.333f) * Mathf.pow(b3.block.size, 2f)));
            }
        });
    }
}
