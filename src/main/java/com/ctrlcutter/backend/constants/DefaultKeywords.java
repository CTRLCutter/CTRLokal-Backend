package com.ctrlcutter.backend.constants;

public enum DefaultKeywords {

    HOTKEY_START("::"),
    HOTSTRING_START(":"),
    RETURN("return");

    private String keyword;

    public String getKeyword() {
        return this.keyword;
    }

    private DefaultKeywords(String keyword) {
        this.keyword = keyword;
    }
}
