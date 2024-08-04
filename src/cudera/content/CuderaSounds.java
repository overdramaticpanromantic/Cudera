package cudera.content;

import arc.audio.Sound;
import mindustry.Vars;

public class CuderaSounds {
    public static Sound bubbling;
    public static void load(){
        bubbling = Vars.tree.loadSound("bubbling");
    }
}
