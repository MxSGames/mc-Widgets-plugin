package com.monsterxsquad.widgets.Managers.Commands;

import com.monsterxsquad.widgets.Widgets;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandManager {

    private final Widgets plugin;

    private final HashMap<String, CommandManagers> commandManagersCache = new HashMap<>();

    public CommandManager(Widgets plugin) {
        this.plugin = plugin;

        load("com.monsterxsquad.widgets.Managers.Commands.Managers");
    }

    private void load(String path) {
        for (Class<?> clazz : new Reflections(path).getSubTypesOf(CommandManagers.class)) {
            try {
                CommandManagers commandManager = (CommandManagers) clazz.getDeclaredConstructor(Widgets.class).newInstance(plugin);
                plugin.getLogger().warning("Loading Command Manager - " + commandManager.name());
                commandManagersCache.put(commandManager.name(), commandManager);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException err) {
                plugin.getLogger().severe("Failed to load Command Manager " + clazz.getName());
                throw new RuntimeException(err);
            }
        }
    }

    public HashMap<String, CommandManagers> getCommandManagersCache() {
        return commandManagersCache;
    }
}
