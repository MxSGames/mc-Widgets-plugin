package com.monsterxsquad.widgets.Commands.WidgetsCommands;

import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;
import com.monsterxsquad.widgets.Managers.Widgets.PlayerWidgetData;
import com.monsterxsquad.widgets.Widgets;
import com.monsterxsquad.widgets.Utils.ColourUtils;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WidgetsShowCommand implements SubCommands {

    private final Widgets plugin;

    private final ColourUtils colourUtils = new ColourUtils();

    public WidgetsShowCommand(Widgets plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandAPICommand onCommand() {
        return new CommandAPICommand("show")
                .withPermission("widgets.commands.show")
                .withArguments(new PlayerArgument("playername"))
                .withArguments(new StringArgument("widget").replaceSuggestions(ArgumentSuggestions.stringCollection(info -> plugin.getConfigManager().getWidgets().keySet())))
                .executes((sender, args) -> {
                    Player playerTarget = (Player) args.get("playername");
                    if (playerTarget == null) {
                        sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.show.invalid-player")));
                        return;
                    }

                    String widgetName = (String) args.get("widget");
                    FileConfiguration widgetFile = plugin.getConfigManager().getWidgets().get(widgetName);
                    if (widgetFile == null) {
                        sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.show.invalid-widget")));
                        return;
                    }

                    if (!widgetFile.getBoolean("options.enabled")) {
                        sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("widgets.disabled")));
                        return;
                    }

                    PlayerWidgetData playerWidgetData = new PlayerWidgetData(widgetName);
                    plugin.getWidgetsManager().getWidgetsDataCache().put(playerTarget.getUniqueId(), playerWidgetData);

                    plugin.getWidgetsManager().displayWidget(playerTarget);
                    sender.sendMessage(colourUtils.placeHolderMiniFormat(playerTarget, plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.show.showing")));
                });
    }
}
