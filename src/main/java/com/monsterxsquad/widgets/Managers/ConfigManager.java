package com.monsterxsquad.widgets.Managers;

import com.monsterxsquad.widgets.Widgets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private final Widgets plugin;

    private FileConfiguration config, lang;

    private final HashMap<String, FileConfiguration> widgets = new HashMap<>();

    public ConfigManager(Widgets plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            plugin.saveResource("config.yml", false);
            plugin.saveResource("Widgets/welcome.yml", false);
            plugin.saveResource("Lang/messages.yml", false);
        }

        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
        this.lang = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "Lang/messages.yml"));

        loadWidgets();
    }

    private void loadWidgets() {
        for (File file : getAllFiles(new File(plugin.getDataFolder(), "Widgets"), ".yml")) {
            widgets.put(file.getName().replace(".yml", ""), YamlConfiguration.loadConfiguration(file));
        }
    }

    public List<File> getAllFiles(File folder, String fileType) {
        List<File> files = new ArrayList<>();
        if (folder == null || !folder.exists()) return files;

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getAllFiles(file, fileType));
            } else if (file.getName().endsWith(fileType)) {
                files.add(file);
            }
        }

        return files;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public HashMap<String, FileConfiguration> getWidgets() {
        return widgets;
    }

    public FileConfiguration getLang() {
        return lang;
    }
}
