package cudera.content;

import arc.graphics.g2d.*;
import cudera.graphics.CuderaPalette;
import mindustry.entities.Effect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class CuderaEffects {
    public static final Effect

    anthraciteCrush = new Effect(24, e -> {
        randLenVectors(e.id, 8, 4f + e.fin() * 8f, (x, y) -> {
            color(CuderaPalette.anthraciteLight);
            Fill.square(e.x + x, e.y + y, e.fout() * 2f + 0.5f, 45);
        });
    });
}
