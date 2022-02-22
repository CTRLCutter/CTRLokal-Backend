package com.ctrlcutter.backend.util;

import java.util.List;

import com.ctrlcutter.backend.constants.HotstringOptions;

public class HotstringOptionBuilder {

    private List<HotstringOptions> options;

    public HotstringOptionBuilder(List<HotstringOptions> options) {
        this.options = options;
    }

    public String getHotstringOptions() {
        StringBuilder optionsString = new StringBuilder();
        options.forEach(option -> optionsString.append(option.getOption()));

        return optionsString.toString();
    }
}
