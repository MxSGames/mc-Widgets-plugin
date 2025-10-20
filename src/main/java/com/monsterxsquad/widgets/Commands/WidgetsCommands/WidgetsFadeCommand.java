package com.monsterxsquad.widgets.Commands.WidgetsCommands;

import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;
import com.monsterxsquad.widgets.Widgets;
import com.monsterxsquad.widgets.Utils.ColourUtils;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerProfileArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.time.Duration;

public class WidgetsFadeCommand implements SubCommands {

    private final Widgets plugin;

    private final ColourUtils colourUtils = new ColourUtils();

    public WidgetsFadeCommand(Widgets plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandAPICommand onCommand() {
        return new CommandAPICommand("fade")
                .withPermission("widgets.commands.fade")
                .withArguments(new PlayerProfileArgument("playername"))
                .withArguments(new StringArgument("widget").replaceSuggestions(ArgumentSuggestions.stringCollection(info -> plugin.getConfigManager().getWidgets().keySet())))
                .withArguments(new IntegerArgument("fadein"))
                .withArguments(new IntegerArgument("stay"))
                .withArguments(new IntegerArgument("fadeout"))
                .executes((sender, args) -> {
                    Player playerTarget = (Player) args.get("playername");
                    if (playerTarget == null) {
                        sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.fade.invalid-player")));
                        return;
                    }

                    String widgetName = (String) args.get("widget");
                    FileConfiguration widgetFile = plugin.getConfigManager().getWidgets().get(widgetName);
                    if (widgetFile == null) {
                        sender.sendMessage(colourUtils.miniFormat(plugin.getConfigManager().getLang().getString("prefix") + plugin.getConfigManager().getLang().getString("commands.fade.invalid-widget")));
                        return;
                    }

                    int fadeIn = (int) args.get("fadein");
                    int stay = (int) args.get("stay");
                    int fadeOut = (int) args.get("fadeout");


                    playerTarget.showTitle(Title.title(colourUtils.miniFormat(widgetFile.getString("unicodes.background")), colourUtils.miniFormat(""), Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut))));
                });
    }
}
