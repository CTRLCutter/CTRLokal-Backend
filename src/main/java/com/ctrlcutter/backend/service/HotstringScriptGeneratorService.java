package com.ctrlcutter.backend.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.constants.DefaultKeywords;
import com.ctrlcutter.backend.constants.HotstringOptions;
import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.util.HotstringOptionBuilder;

@Service
public class HotstringScriptGeneratorService {

    public String generateHotstringScript(BasicHotstringDTO hotstringDTO) {

        List<String> options = Arrays.asList(hotstringDTO.getOptions());
        List<HotstringOptions> hotstringOptions = options.stream().map(HotstringOptions::getHotstringOptionFromString).collect(Collectors.toList());

        HotstringOptionBuilder optionBuilder = new HotstringOptionBuilder(hotstringOptions);

        String inputCommand = hotstringDTO.getCommand();
        String outputValue = hotstringDTO.getParameter();

        StringBuilder hotstringScriptBuilder = new StringBuilder();

        hotstringScriptBuilder.append(DefaultKeywords.HOTSTRING_START.getKeyword());
        hotstringScriptBuilder.append(optionBuilder.getHotstringOptions());
        hotstringScriptBuilder.append(DefaultKeywords.HOTSTRING_START.getKeyword());
        hotstringScriptBuilder.append(inputCommand);
        hotstringScriptBuilder.append(DefaultKeywords.HOTKEY_START.getKeyword());
        hotstringScriptBuilder.append(outputValue);

        return hotstringScriptBuilder.toString();
    }

}
