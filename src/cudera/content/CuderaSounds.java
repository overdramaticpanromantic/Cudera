package cudera.content;

import arc.audio.Sound;
import mindustry.Vars;

public class CuderaSounds {
    public static Sound bubbling, plasteelHum;
    public static void load(){
        bubbling = Vars.tree.loadSound("bubbling");
        plasteelHum = Vars.tree.loadSound("plasteelHum");
    }
}
