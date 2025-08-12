package com.monsterxsquad.widgets.Managers.Commands;

import com.monsterxsquad.widgets.Managers.Commands.SubCommands.SubCommands;

import java.util.ArrayList;

public interface CommandManagers {
    String name();

    ArrayList<SubCommands> getCommands();
}
