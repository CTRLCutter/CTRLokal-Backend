package com.ctrlcutter.backend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.dto.DefaultDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.DefaultScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;

@Component
public class DTOToScriptMapper {

    public BasicScript mapBasicScript(BasicScript basicScript, BasicScriptDTO basicScriptDTO) {
        basicScript.setOs(basicScriptDTO.getOs());
        basicScript.setCommand(basicScriptDTO.getCommand());
        basicScript.setKey(basicScriptDTO.getKey());
        basicScript.setModifierKeys(Arrays.asList(basicScriptDTO.getModifierKeys()));
        basicScript.setParameters(Arrays.asList(basicScriptDTO.getParameters()));

        return basicScript;
    }

    public BasicHotstringScript mapHotstringScript(BasicHotstringScript basicHotstringScript, BasicHotstringDTO basicHotstringDTO) {
        basicHotstringScript.setOs(basicHotstringDTO.getOs());
        basicHotstringScript.setOptions(Arrays.asList(basicHotstringDTO.getOptions()));
        basicHotstringScript.setCommand(basicHotstringDTO.getCommand());
        basicHotstringScript.setParameter(basicHotstringDTO.getParameter());

        return basicHotstringScript;
    }

    public PreDefinedScript mapPreDefinedScript(PreDefinedScript preDefinedScript, PreDefinedScriptDTO preDefinedScriptDTO) {
        preDefinedScript.setOs(preDefinedScriptDTO.getOs());
        preDefinedScript.setScriptType(preDefinedScriptDTO.getScriptType());
        preDefinedScript.setShortcuts(this.convertDefaultDTOtoScript(preDefinedScriptDTO.getShortcuts(), preDefinedScript));

        return preDefinedScript;
    }

    private List<DefaultScript> convertDefaultDTOtoScript(DefaultDTO[] defaultDTOs, PreDefinedScript preDefinedScript) {
        List<DefaultScript> defaultScriptList = new ArrayList<>();

        for (DefaultDTO defaultDTO : defaultDTOs) {
            DefaultScript defaultScript = new DefaultScript();

            defaultScript.setKey(defaultDTO.getKey());
            defaultScript.setModifierKeys(Arrays.asList(defaultDTO.getModifierKeys()));
            defaultScript.setPreDefinedScript(preDefinedScript);

            defaultScriptList.add(defaultScript);
        }

        return defaultScriptList;
    }
}
