package com.ctrlcutter.backend.constants;

public enum HotstringOptions {

    NO_TRIGGER("*"),
    IS_WORD_IN_MIDDLE("?"),
    NO_AUTOBACKSPACE("B0"),
    CASE_SENSITIVE("C"),
    OMIT_LAST_CHARACTER("O"),
    RAW_TEXT("R");

    private String option;

    private HotstringOptions(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public static HotstringOptions getHotstringOptionFromString(String hotstringOption) {

        for (HotstringOptions option : values()) {
            if (hotstringOption.equals(option.name())) {
                return option;
            }
        }

        return null;
    }

}
