package cudera.world.meta;

import mindustry.world.meta.*;

public class CuderaStats {
    public static final StatCat heat = new StatCat("cudera-heat");

    public static final Stat
    heated = new Stat("cudera-heated", heat),
    heatRadius = new Stat("cudera-heatRadius", heat);
}
