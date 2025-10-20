package com.monsterxsquad.widgets;

import com.monsterxsquad.widgets.Listeners.Widgets.EntityDamageListener;
import com.monsterxsquad.widgets.Listeners.Widgets.PlayerItemListener;
import com.monsterxsquad.widgets.Listeners.Widgets.PlayerListener;
import com.monsterxsquad.widgets.Listeners.Widgets.ResourcePackListener;
import com.monsterxsquad.widgets.Managers.Commands.CommandManager;
import com.monsterxsquad.widgets.Managers.GUI.GUIManager;
import com.monsterxsquad.widgets.Listeners.GUI.GUIClickListener;
import com.monsterxsquad.widgets.Managers.ConfigManager;
import com.monsterxsquad.widgets.Managers.ResourcePack.ResourcePackManager;
import com.monsterxsquad.widgets.Managers.Widgets.WidgetsManager;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPIPaperConfig;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Widgets extends JavaPlugin {

    //TODO add Dialog support

    private ConfigManager configManager;

    private WidgetsManager widgetsManager;
    private GUIManager GUIManager;

    private ResourcePackManager resourcePackManager;

    private CommandManager commandManager;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIPaperConfig(this));
    }

    @Override
    public void onEnable() {
        loadHooks();
        loadManagers();
        loadListeners();

        loadCommands();

        loadBStats();
    }

    @Override
    public void onDisable() {
        widgetsManager.giveAllItemsBack();
        CommandAPI.onDisable();
    }

    private void loadHooks() {
        CommandAPI.onEnable();
    }

    private void loadManagers() {
        this.configManager = new ConfigManager(this);

        this.widgetsManager = new WidgetsManager(this);
        this.GUIManager = new GUIManager();

        this.resourcePackManager = new ResourcePackManager(this);
    }

    private void loadCommands() {
        this.commandManager = new CommandManager(this);
    }

    private void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ResourcePackListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemListener(this), this);

        Bukkit.getPluginManager().registerEvents(new GUIClickListener(this), this);
    }

    private void loadBStats() {
        new Metrics(this, 26894);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public WidgetsManager getWidgetsManager() {
        return widgetsManager;
    }

    public GUIManager getGUIManager() {
        return GUIManager;
    }

    public ResourcePackManager getResourcePackManager() {
        return resourcePackManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
