package cudera;

import arc.util.Log;
import cudera.content.*;
import mindustry.mod.*;
import cudera.gen.*;

public class CuderaMod extends Mod{
    @Override
    public void loadContent()
    {
        Log.info("Loaded Cudera content.");
        EntityRegistry.register();
        CuderaItems.load();
        CuderaLiquids.load();
        CuderaBlocks.load();
    }
}
