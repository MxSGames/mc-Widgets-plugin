package com.monsterxsquad.widgets.Managers.Commands.Managers.Widgets;

import com.monsterxsquad.widgets.Commands.WidgetsCommands.WidgetsFadeCommand;
import com.monsterxsquad.widgets.Commands.WidgetsCommands.WidgetsHelpCommand;
import com.monsterxsquad.widgets.Commands.WidgetsCommands.WidgetsReloadCommand;
import com.monsterxsquad.widgets.Commands.WidgetsCommands.WidgetsShowCommand;
import com.monsterxsquad.widgets.Managers.Commands.CommandManagers;
import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;
import com.monsterxsquad.widgets.Widgets;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class WidgetsCommandManager implements CommandManagers {

    private final Widgets plugin;

    private final ArrayList<SubCommands> commands = new ArrayList<>();

    public WidgetsCommandManager(Widgets plugin) {
        this.plugin = plugin;

        commands.add(new WidgetsHelpCommand(plugin));
        commands.add(new WidgetsFadeCommand(plugin));
        commands.add(new WidgetsShowCommand(plugin));
        commands.add(new WidgetsReloadCommand(plugin));

        registerCommand();
    }

    private void registerCommand() {
        CommandAPICommand mainCommandManager = new CommandAPICommand(name())
                .withAliases("widget")
                .executes((sender, args) -> {
                    Bukkit.dispatchCommand(sender, "widgets help");
                });

        commands.forEach(subCommands -> mainCommandManager.withSubcommand(subCommands.onCommand()));

        mainCommandManager.register();
    }

    @Override
    public String name() {
        return "widgets";
    }

    @Override
    public ArrayList<SubCommands> getCommands() {
        return commands;
    }
}
