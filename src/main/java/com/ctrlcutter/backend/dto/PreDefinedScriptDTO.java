package com.ctrlcutter.backend.dto;

public class PreDefinedScriptDTO {

    private String os;
    private String scriptType;
    private DefaultDTO[] shortcuts;

    public PreDefinedScriptDTO(String os, String scriptType, DefaultDTO[] shortcuts) {
        this.os = os;
        this.scriptType = scriptType;
        this.shortcuts = shortcuts;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public DefaultDTO[] getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(DefaultDTO[] shortcuts) {
        this.shortcuts = shortcuts;
    }
}
