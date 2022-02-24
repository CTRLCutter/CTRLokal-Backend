package com.ctrlcutter.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ctrlcutter.backend.constants.ModifierKeys;
import com.ctrlcutter.backend.dto.DefaultDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.util.FileReader;

public class PreDefinedScriptGeneratorService {

    private static final String SCRIPT_BASE_PATH = "predefinedScripts/";

    public String generatePreDefinedScript(PreDefinedScriptDTO scriptDTO) {

        String os = scriptDTO.getOs();
        String scriptType = scriptDTO.getScriptType();
        List<DefaultDTO> shortcutList = Arrays.asList(scriptDTO.getShortcuts());
        List<String> translatedShortcutList = translateShortcutList(shortcutList);

        String scriptBaseContent = FileReader.readCompleteFile("./src/main/resources/" + SCRIPT_BASE_PATH + os + "/" + scriptType + ".txt");

        Object[] shortcutFormattingList = translatedShortcutList.stream().toArray();

        return String.format(scriptBaseContent, shortcutFormattingList);
    }

    private List<String> translateShortcutList(List<DefaultDTO> shortcutList) {

        List<String> translatedShortcutList = new ArrayList<>();

        for (DefaultDTO shortcut : shortcutList) {
            StringBuilder translatedShortcutBuilder = new StringBuilder();
            translatedShortcutBuilder.append(translateModifierKeys(shortcut.getModifierKeys()));
            translatedShortcutBuilder.append(shortcut.getKey());

            translatedShortcutList.add(translatedShortcutBuilder.toString());
        }

        return translatedShortcutList;
    }

    private String translateModifierKeys(String[] modifierKeys) {

        StringBuilder modifierKeysBuilder = new StringBuilder();

        List<String> mKeys = Arrays.asList(modifierKeys);
        List<ModifierKeys> keys = mKeys.stream().map(ModifierKeys::getModifierKeyFromString).collect(Collectors.toList());

        for (ModifierKeys key : keys) {
            modifierKeysBuilder.append(key.getSymbol());
        }

        return modifierKeysBuilder.toString();
    }
}
