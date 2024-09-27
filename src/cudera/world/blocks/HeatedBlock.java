package cudera.world.blocks;

public interface HeatedBlock {
    boolean isHeating(float x, float y);

    float getHeatRadius();
}
