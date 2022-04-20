package com.ctrlcutter.backend.persistence.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ctrlcutter.backend.dto.*;
import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.DefaultScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;
import com.ctrlcutter.backend.service.anonymization.ScriptAnonymizationService;

@Service
public class PersistenceSaveService {

    private final BasicHotstringScriptRepository basicHotstringScriptRepository;
    private final BasicScriptRepository basicScriptRepository;
    private final PreDefinedScriptRepository preDefinedScriptRepository;
    private final ScriptAnonymizationService scriptAnonymizationService;

    @Value("${web.api.url}")
    private String url;
    @Value("${web.api.username}")
    private String username;
    @Value("${web.api.password}")
    private String password;

    @Autowired
    public PersistenceSaveService(BasicHotstringScriptRepository basicHotstringScriptRepository, BasicScriptRepository basicScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository, ScriptAnonymizationService scriptAnonymizationService) {
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.basicScriptRepository = basicScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
        this.scriptAnonymizationService = scriptAnonymizationService;
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

    public List<AnonymizedScriptDTO> anonymizeScriptsForBackup() {
        List<PreDefinedScript> preDefinedScripts = this.preDefinedScriptRepository.findAll();
        List<BasicScript> basicScripts = this.basicScriptRepository.findBasicScriptsByCommand("RUN");

        List<AnonymizedScriptDTO> anonymizedScripts = new ArrayList<>();

        preDefinedScripts.stream().map(this.scriptAnonymizationService::anonymizePreDefinedScript).forEachOrdered(anonymizedScripts::add);
        basicScripts.stream().map(this.scriptAnonymizationService::anonymizeBasicScript).forEachOrdered(anonymizedScripts::add);

        return anonymizedScripts;
    }

    public ResponseEntity<String> saveScriptsToWeb(String sessionkey, List<AnonymizedScriptDTO> anonymizedScripts) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(this.username, this.password);
        headers.set("sessionkey", sessionkey);

        HttpEntity<List<AnonymizedScriptDTO>> httpEntity = new HttpEntity<>(anonymizedScripts, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(this.url, httpEntity, String.class);

        return response;
    }

}
