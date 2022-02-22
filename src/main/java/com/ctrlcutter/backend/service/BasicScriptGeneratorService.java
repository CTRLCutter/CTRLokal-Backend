package com.ctrlcutter.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.constants.DefaultCommands;
import com.ctrlcutter.backend.constants.DefaultKeywords;
import com.ctrlcutter.backend.constants.ModifierKeys;
import com.ctrlcutter.backend.dto.BasicScriptDTO;

@Service
public class BasicScriptGeneratorService {

    public String generateBasicScript(BasicScriptDTO basicScriptDTO) {

        List<String> translatedModifierKeys = translateModifierKeys(basicScriptDTO.getModifierKeys());

        DefaultCommands command = DefaultCommands.getDefaultCommandFromString(basicScriptDTO.getCommand());
        String translatedCommand = command.getCommand();

        if (translatedModifierKeys.isEmpty() || translatedCommand == null) {
            return null;
        }

        String hotkeyDefinition = generateHotkeyDefinition(translatedModifierKeys, basicScriptDTO.getKey());

        String hotkeyCommand = generateHotkeyCommand(translatedCommand, basicScriptDTO.getParameters());

        return hotkeyDefinition + hotkeyCommand + DefaultKeywords.RETURN.getKeyword();
    }

    private List<String> translateModifierKeys(String[] modifierKeys) {

        List<String> translatedModifierKeys = new ArrayList<>(modifierKeys.length);
        List<String> mKeys = Arrays.asList(modifierKeys);
        List<ModifierKeys> keys = mKeys.stream().map(ModifierKeys::getModifierKeyFromString).collect(Collectors.toList());
        translatedModifierKeys = keys.stream().map(ModifierKeys::getSymbol).collect(Collectors.toList());

        return translatedModifierKeys;
    }

    private String generateHotkeyDefinition(List<String> translatedModifierKeys, String key) {

        StringBuilder hotkeyDefinitonBuilder = new StringBuilder();

        translatedModifierKeys.forEach(hotkeyDefinitonBuilder::append);
        hotkeyDefinitonBuilder.append(key);
        hotkeyDefinitonBuilder.append(DefaultKeywords.START.getKeyword());
        hotkeyDefinitonBuilder.append(System.lineSeparator());

        return hotkeyDefinitonBuilder.toString();
    }

    private String generateHotkeyCommand(String command, String[] parameters) {

        StringBuilder hotkeyCommandBuilder = new StringBuilder(command);

        for (String parameter : parameters) {
            hotkeyCommandBuilder.append(parameter);
            hotkeyCommandBuilder.append(" ");
        }

        hotkeyCommandBuilder.append(System.lineSeparator());

        return hotkeyCommandBuilder.toString();
    }
}
