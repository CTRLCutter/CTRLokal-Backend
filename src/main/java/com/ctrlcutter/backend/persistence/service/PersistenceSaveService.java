package com.ctrlcutter.backend.persistence.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.dto.DefaultDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.DefaultScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;

@Service
public class PersistenceSaveService {

    private BasicHotstringScriptRepository basicHotstringScriptRepository;
    private BasicScriptRepository basicScriptRepository;
    private PreDefinedScriptRepository preDefinedScriptRepository;

    @Autowired
    public PersistenceSaveService(BasicHotstringScriptRepository basicHotstringScriptRepository, BasicScriptRepository basicScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository) {
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.basicScriptRepository = basicScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
    }

    public void saveBasicScript(BasicScriptDTO basicScriptDTO) {
        BasicScript basicScript = new BasicScript();
        basicScript.setOs(basicScriptDTO.getOs());
        basicScript.setCommand(basicScriptDTO.getCommand());
        basicScript.setKey(basicScriptDTO.getKey());
        basicScript.setModifierKeys(Arrays.asList(basicScriptDTO.getModifierKeys()));
        basicScript.setParameters(Arrays.asList(basicScriptDTO.getParameters()));

        this.basicScriptRepository.saveAndFlush(basicScript);
    }

    public void saveBasicHotstringScript(BasicHotstringDTO basicHotstringDTO) {
        BasicHotstringScript basicHotstringScript = new BasicHotstringScript();
        basicHotstringScript.setOs(basicHotstringDTO.getOs());
        basicHotstringScript.setOptions(Arrays.asList(basicHotstringDTO.getOptions()));
        basicHotstringScript.setCommand(basicHotstringDTO.getCommand());
        basicHotstringScript.setParameter(basicHotstringDTO.getParameter());

        this.basicHotstringScriptRepository.saveAndFlush(basicHotstringScript);
    }

    public void savePreDefinedScript(PreDefinedScriptDTO preDefinedScriptDTO) {
        PreDefinedScript preDefinedScript = new PreDefinedScript();
        preDefinedScript.setOs(preDefinedScriptDTO.getOs());
        preDefinedScript.setScriptType(preDefinedScriptDTO.getScriptType());
        preDefinedScript.setShortcuts(this.convertDefaultDTOtoScript(preDefinedScriptDTO.getShortcuts(), preDefinedScript));

        this.preDefinedScriptRepository.saveAndFlush(preDefinedScript);
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
