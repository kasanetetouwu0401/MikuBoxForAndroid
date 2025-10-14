package io.nekohasekai.sagernet.widget;

public final class FabStyle {

    // Static instance (singleton)
    public static final FabStyle INSTANCE;

    // Constants
    public static final int End = 0;
    public static final int Center = 1;

    // Static initialization block
    static {
        FabStyle instance = new FabStyle();
        INSTANCE = instance;
    }

    // Private constructor
    private FabStyle() {
        // Prevent instantiation from outside
    }
}
