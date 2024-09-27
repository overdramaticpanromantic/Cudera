package cudera.content;

import arc.graphics.Color;
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
    }),
    polymerSquish = new Effect(30, e -> {
        randLenVectors(e.id, 6, 3f + e.fin() * 8f, (x, y) -> {
            color(CuderaPalette.polymerLight);
            Fill.square(e.x + x, e.y + y, e.fout() * 2f + 0.5f, 0);
        });
    }),
    thermoplastCraft = new Effect(24, e -> {
        randLenVectors(e.id, 8, 4f + e.fin() * 8f, (x, y) -> {
            color(CuderaPalette.thermoplastLight);
            Fill.square(e.x + x, e.y + y, e.fout() * 2f + 0.5f, 45);
        });
    }),
    smeltSmokeHuge = new Effect(20, e -> {
        randLenVectors(e.id, 12, 6f + e.fin() * 5f, (x, y) -> {
            color(Color.white, e.color, e.fin());
            Fill.square(e.x + x, e.y + y, 1f + e.fout() * 2f, 45);
        });
    });
}