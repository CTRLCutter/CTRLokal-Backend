package com.ctrlcutter.backend.persistence.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class DefaultScript {

    @Id
    private Long id;

    private String key;

    @ElementCollection
    private List<String> modifierKeys;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "preDefinedScript_id", nullable = false)
    private PreDefinedScript preDefinedScript;

    public DefaultScript() {}

    public DefaultScript(String key, List<String> modifierKeys, PreDefinedScript preDefinedScript) {
        this.key = key;
        this.modifierKeys = modifierKeys;
        this.preDefinedScript = preDefinedScript;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getModifierKeys() {
        return this.modifierKeys;
    }

    public void setModifierKeys(List<String> modifierKeys) {
        this.modifierKeys = modifierKeys;
    }

    public PreDefinedScript getPreDefinedScript() {
        return this.preDefinedScript;
    }

    public void setPreDefinedScript(PreDefinedScript preDefinedScript) {
        this.preDefinedScript = preDefinedScript;
    }
}
