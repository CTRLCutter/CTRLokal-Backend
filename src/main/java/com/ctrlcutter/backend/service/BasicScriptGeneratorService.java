package com.ctrlcutter.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.constants.DefaultCommands;
import com.ctrlcutter.backend.constants.DefaultKeywords;
import com.ctrlcutter.backend.constants.ModifierKeys;
import com.ctrlcutter.backend.dto.BasicScriptDTO;

@Service
public class BasicScriptGeneratorService {

    public String generateBasicScript(BasicScriptDTO basicScriptDTO) {

        List<String> translatedModifierKeys = translateModifierKeys(basicScriptDTO.getModifierKeys());
        String command = translateCommand(basicScriptDTO.getCommand());

        if (translatedModifierKeys.isEmpty() || command == null) {
            return null;
        }

        String hotkeyDefinition = generateHotkeyDefinition(translatedModifierKeys, basicScriptDTO.getKey());

        String hotkeyCommand = generateHotkeyCommand(command, basicScriptDTO.getParameters());

        return hotkeyDefinition + hotkeyCommand + DefaultKeywords.RETURN.getKeyword();
    }

    private List<String> translateModifierKeys(String[] modifierKeys) {
        List<String> translatedModifierKeys = new ArrayList<>(modifierKeys.length);

        for (String modifierKey : modifierKeys) {
            for (ModifierKeys keyConstants : ModifierKeys.values()) {
                if (modifierKey.equals(keyConstants.name())) {
                    translatedModifierKeys.add(keyConstants.getSymbol());
                }
            }
        }

        return translatedModifierKeys;
    }

    private String translateCommand(String command) {

        for (DefaultCommands commandConstant : DefaultCommands.values()) {
            if (command.equals(commandConstant.name())) {
                return commandConstant.getCommand();
            }
        }
        return null;
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
