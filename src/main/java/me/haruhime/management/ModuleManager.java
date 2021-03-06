package me.haruhime.management;

import me.haruhime.events.Event;
import me.haruhime.modules.Module;
import me.haruhime.modules.other.SelfDestruct;
import me.haruhime.modules.render.Fullbright;
import me.haruhime.modules.render.HUD;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {

    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

    public static CopyOnWriteArrayList<Module> getModules() {
        return ModuleManager.modules;
    }

    public static void initModules() {
        modules.add(new HUD());
        modules.add(new Fullbright());
        modules.add(new SelfDestruct());
    }

    public static void keyPress(int key) {
        for (Module m : modules) {
            if (m.getKey() == key) {
                m.toggle();
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void onEvent(Event e) {
        for (Module m : modules) {
            if (!m.toggled)
                continue;

            m.onEvent(e);
        }
    }

}
