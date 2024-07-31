package cudera.content;

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.Planets;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

// UNUSED

public class CuderaPlanets {
    public static Planet
    cudera;

    public static void load(){
        cudera = new Planet("cudera", Planets.sun, 1, 2){{
            // this is necessary to prevent crashes, even though it's unused
            generator = new AsteroidGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh()
            );
        }};
    }
}
