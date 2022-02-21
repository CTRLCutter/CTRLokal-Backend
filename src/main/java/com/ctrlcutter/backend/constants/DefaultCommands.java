package com.ctrlcutter.backend.constants;

public enum DefaultCommands {

    SEND("Send, "),
    RUN("Run, ");

    private String command;

    public String getCommand() {
        return this.command;
    }

    DefaultCommands(String command) {
        this.command = command;
    }
}
