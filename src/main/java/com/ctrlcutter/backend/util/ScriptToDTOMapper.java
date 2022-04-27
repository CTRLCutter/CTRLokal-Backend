package com.ctrlcutter.backend.util;

import org.springframework.stereotype.Component;

import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.dto.ShortcutDTO;

@Component
public class ScriptToDTOMapper {

    public BasicScriptDTO mapBasicScriptDTO(ShortcutDTO shortcut) {
        BasicScriptDTO basicScriptDTO = new BasicScriptDTO();

        //TODO IS THIS CORRECT???
        basicScriptDTO.setOs(System.getProperty("os.name").toLowerCase());
        basicScriptDTO.setKey(shortcut.getKeyboardKey());
        basicScriptDTO.setCommand(shortcut.getCommand());

        String[] modifierKeys = shortcut.getModifierKeys().toArray(new String[] {});
        basicScriptDTO.setModifierKeys(modifierKeys);

        String[] parameters = shortcut.getParameters().toArray(new String[] {});
        basicScriptDTO.setParameters(parameters);

        return basicScriptDTO;
    }
}
