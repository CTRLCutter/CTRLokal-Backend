package com.ctrlcutter.backend.dto;

public class BasicHotstringDTO {

    private String os;
    private String[] options;
    private String command;
    private String parameter;

    public BasicHotstringDTO(String os, String[] options, String command, String parameter) {
        this.os = os;
        this.options = options;
        this.command = command;
        this.parameter = parameter;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
