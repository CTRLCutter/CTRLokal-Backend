package com.ctrlcutter.backend.persistence.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class PreDefinedScript {

    @Id
    private Long id;

    private String os;

    private String scriptType;

    @OneToMany(mappedBy = "preDefinedScript", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DefaultScript> shortcuts;

    public PreDefinedScript() {}

    public PreDefinedScript(String os, String scriptType, List<DefaultScript> shortcuts) {
        this.os = os;
        this.scriptType = scriptType;
        this.shortcuts = shortcuts;
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

    public String getScriptType() {
        return this.scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public List<DefaultScript> getShortcuts() {
        return this.shortcuts;
    }

    public void setShortcuts(List<DefaultScript> shortcuts) {
        this.shortcuts = shortcuts;
    }
}
