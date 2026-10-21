package dev.nova.module;

import java.util.List;

public final class ModuleManager {
    private static final List<Module> MODULES = List.of(
            new AutoKiller(),
            new FastPlace(),
            new Nametags(),
            new Sprint(),
            new Fullbright()
    );

    private ModuleManager() {}

    public static List<Module> all() { return MODULES; }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T get(Class<T> type) {
        for (Module m : MODULES) if (type.isInstance(m)) return (T) m;
        throw new IllegalStateException("Brak modulu " + type);
    }
}
