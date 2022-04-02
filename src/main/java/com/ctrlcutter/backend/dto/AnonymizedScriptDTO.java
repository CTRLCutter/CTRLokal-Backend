package com.ctrlcutter.backend.dto;

public class AnonymizedScriptDTO {

    private String command;
    private String key;
    private String[] modifierKeys;
    private String[] parameters;

    public AnonymizedScriptDTO() {}

    public AnonymizedScriptDTO(String command, String key, String[] modifierKeys, String[] parameters) {
        this.command = command;
        this.key = key;
        this.modifierKeys = modifierKeys;
        this.parameters = parameters;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getModifierKeys() {
        return modifierKeys;
    }

    public void setModifierKeys(String[] modifierKeys) {
        this.modifierKeys = modifierKeys;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }
}
