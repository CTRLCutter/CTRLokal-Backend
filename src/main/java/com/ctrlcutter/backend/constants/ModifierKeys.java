package com.ctrlcutter.backend.constants;

import java.util.Arrays;
import java.util.List;

public enum ModifierKeys {

    CTRL("^"),
    SHIFT("+"),
    ALT("!"),
    WINDOWS("#");

    private String symbol;

    public String getSymbol() {
        return this.symbol;
    }

    private ModifierKeys(String symbol) {
        this.symbol = symbol;
    }

    public static ModifierKeys getModifierKeyFromString(String modifierKey) {
        
        List<ModifierKeys> keys = Arrays.asList(values());
        
        for (ModifierKeys key : keys) {
            if (key.name().equals(modifierKey)) {
                return key;
            }
        }
        
        return null;
    }
}
