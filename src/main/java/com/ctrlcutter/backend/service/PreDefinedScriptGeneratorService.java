package com.ctrlcutter.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.constants.ModifierKeys;
import com.ctrlcutter.backend.dto.DefaultDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.util.io.FileReader;

@Service
public class PreDefinedScriptGeneratorService {

    private static final String SCRIPT_BASE_PATH = "predefinedScripts/";

    public String generatePreDefinedScript(PreDefinedScriptDTO scriptDTO) {

        String os = scriptDTO.getOs();
        String scriptType = scriptDTO.getScriptType();
        List<DefaultDTO> shortcutList = Arrays.asList(scriptDTO.getShortcuts());
        List<String> translatedShortcutList = this.translateShortcutList(os, shortcutList);

        if (translatedShortcutList == null) {
            return null;
        }

        // Or rather put that in a constants class???
        FileReader fileReader = new FileReader();
        String scriptBaseContent = fileReader.readFile(SCRIPT_BASE_PATH + os + "/" + scriptType + ".txt");

        Object[] shortcutFormattingList = translatedShortcutList.toArray();

        return String.format(scriptBaseContent, shortcutFormattingList);
    }

    private List<String> translateShortcutList(String os, List<DefaultDTO> shortcutList) {
        switch (os) {
            case "win":
                return this.translateShortcutListWindows(shortcutList);
            case "mac":
                return this.translateShortcutListMac(shortcutList);
            default:
                return null;
        }
    }

    private List<String> translateShortcutListMac(List<DefaultDTO> shortcutList) {

        List<String> translatedShortcutList = new ArrayList<>();

        for (DefaultDTO shortcut : shortcutList) {
            translatedShortcutList.add("'" + shortcut.getKey() + "'");

            translatedShortcutList.add(this.transformModifierKeysMac(shortcut.getModifierKeys()));
        }
        return translatedShortcutList;
    }

    private String transformModifierKeysMac(String[] modifierKeys) {
        StringBuilder modifierKeyString = new StringBuilder();

        for (int i = 0; i < modifierKeys.length; i++) {
            if (i == 0) {
                modifierKeyString.append("'").append(modifierKeys[i]).append("'");
            } else {
                modifierKeyString.append(", ");
                modifierKeyString.append("'").append(modifierKeys[i]).append("'");
            }
        }

        return modifierKeyString.toString();
    }

    private List<String> translateShortcutListWindows(List<DefaultDTO> shortcutList) {

        List<String> translatedShortcutList = new ArrayList<>();

        for (DefaultDTO shortcut : shortcutList) {
            StringBuilder translatedShortcutBuilder = new StringBuilder();
            translatedShortcutBuilder.append(this.translateModifierKeysWindows(shortcut.getModifierKeys()));
            translatedShortcutBuilder.append(shortcut.getKey());

            translatedShortcutList.add(translatedShortcutBuilder.toString());
        }

        return translatedShortcutList;
    }

    private String translateModifierKeysWindows(String[] modifierKeys) {

        StringBuilder modifierKeysBuilder = new StringBuilder();

        List<String> mKeys = Arrays.asList(modifierKeys);
        List<ModifierKeys> keys = mKeys.stream().map(ModifierKeys::getModifierKeyFromString).collect(Collectors.toList());

        for (ModifierKeys key : keys) {
            modifierKeysBuilder.append(key.getSymbol());
        }

        return modifierKeysBuilder.toString();
    }
}
