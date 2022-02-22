package com.ctrlcutter.backend.constants;

import java.util.Arrays;
import java.util.List;

public enum DefaultCommands {

    SEND("Send, "),
    RUN("Run, ");

    private String command;

    public String getCommand() {
        return this.command;
    }

    private DefaultCommands(String command) {
        this.command = command;
    }

    public static DefaultCommands getDefaultCommandFromString(String defaultCommand) {

        List<DefaultCommands> commands = Arrays.asList(values());

        for (DefaultCommands command : commands) {
            if (command.name().equals(defaultCommand)) {
                return command;
            }
        }

        return null;
    }
}
