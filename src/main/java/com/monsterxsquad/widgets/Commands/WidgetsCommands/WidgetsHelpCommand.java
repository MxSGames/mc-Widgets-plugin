package com.monsterxsquad.widgets.Commands.WidgetsCommands;

import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;
import com.monsterxsquad.widgets.Widgets;
import com.monsterxsquad.widgets.Utils.ColourUtils;
import dev.jorel.commandapi.CommandAPICommand;

public class WidgetsHelpCommand implements SubCommands {

    private final Widgets plugin;

    private final ColourUtils colourUtils = new ColourUtils();

    public WidgetsHelpCommand(Widgets plugin) {
        this.plugin = plugin;
    }

    public CommandAPICommand onCommand() {
        return new CommandAPICommand("help")
                .withPermission("widgets.commands.help")
                .executes((sender, args) -> {
                    plugin.getConfigManager().getLang().getStringList("commands.help.usage").forEach(string -> {
                        sender.sendMessage(colourUtils.miniFormat(string));
                    });
                });
    }
}
