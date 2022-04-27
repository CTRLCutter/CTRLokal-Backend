package com.ctrlcutter.backend.dto;

import java.util.List;

public class ShortcutDTO {

    private Long id;
    private String command;
    private String keyboardKey;
    private List<String> modifierKeys;
    private List<String> parameters;
    private CustomerDTO customer;

    public ShortcutDTO() {}

    public ShortcutDTO(String command, String keyboardKey, List<String> modifierKeys, List<String> parameters, CustomerDTO customer) {
        this.command = command;
        this.keyboardKey = keyboardKey;
        this.modifierKeys = modifierKeys;
        this.parameters = parameters;
        this.customer = customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKeyboardKey() {
        return this.keyboardKey;
    }

    public void setKeyboardKey(String key) {
        this.keyboardKey = key;
    }

    public List<String> getModifierKeys() {
        return this.modifierKeys;
    }

    public void setModifierKeys(List<String> modifierKeys) {
        this.modifierKeys = modifierKeys;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
