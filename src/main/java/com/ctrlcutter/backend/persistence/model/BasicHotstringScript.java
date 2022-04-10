package com.ctrlcutter.backend.persistence.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class BasicHotstringScript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String os;

    @ElementCollection
    private List<String> options;

    private String command;

    private String parameter;

    public BasicHotstringScript() {

    }

    public BasicHotstringScript(String os, List<String> options, String command, String parameter) {
        this.os = os;
        this.options = options;
        this.command = command;
        this.parameter = parameter;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
