package cudera.world.blocks;

public interface HeatedBlock {
    boolean isHeated();
    default boolean isHeating(float x, float y) {
        return false;
    }
}
