package com.monsterxsquad.widgets.Commands.WidgetsCommands;

import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;
import com.monsterxsquad.widgets.Widgets;
import com.monsterxsquad.widgets.Utils.ColourUtils;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;

public class WidgetsReloadCommand implements SubCommands {

    private final Widgets plugin;

    private final ColourUtils colourUtils = new ColourUtils();

    public WidgetsReloadCommand(Widgets plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandAPICommand onCommand() {
        return new CommandAPICommand("reload")
                .withPermission("widgets.commands.reload")
                .executes((sender, args) -> {
                    Bukkit.getAsyncScheduler().runNow(plugin, task -> plugin.getConfigManager().load());

                    sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.reload.config-reloaded")));
                });
    }
}
