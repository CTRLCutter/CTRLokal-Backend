package com.ctrlcutter.backend.persistence.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
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

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.dto.ShortcutDTO;
import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;
import com.ctrlcutter.backend.service.anonymization.ScriptAnonymizationService;
import com.ctrlcutter.backend.util.DTOToScriptMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Service
public class PersistenceSaveService {

    private final BasicHotstringScriptRepository basicHotstringScriptRepository;
    private final BasicScriptRepository basicScriptRepository;
    private final PreDefinedScriptRepository preDefinedScriptRepository;
    private final ScriptAnonymizationService scriptAnonymizationService;
    private final DTOToScriptMapper dtoToScriptMapper;

    @Value("${web.api.url}")
    private String url;
    @Value("${web.api.username}")
    private String username;
    @Value("${web.api.password}")
    private String password;

    @Autowired
    public PersistenceSaveService(BasicHotstringScriptRepository basicHotstringScriptRepository, BasicScriptRepository basicScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository, ScriptAnonymizationService scriptAnonymizationService, DTOToScriptMapper dtoToScriptMapper) {
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.basicScriptRepository = basicScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
        this.scriptAnonymizationService = scriptAnonymizationService;
        this.dtoToScriptMapper = dtoToScriptMapper;
    }

    public void saveBasicScript(BasicScriptDTO basicScriptDTO) {
        BasicScript basicScript = new BasicScript();

        basicScript = this.dtoToScriptMapper.mapBasicScript(basicScript, basicScriptDTO);

        this.basicScriptRepository.saveAndFlush(basicScript);
    }

    public void saveBasicHotstringScript(BasicHotstringDTO basicHotstringDTO) {
        BasicHotstringScript basicHotstringScript = new BasicHotstringScript();

        basicHotstringScript = this.dtoToScriptMapper.mapHotstringScript(basicHotstringScript, basicHotstringDTO);

        this.basicHotstringScriptRepository.saveAndFlush(basicHotstringScript);
    }

    public void savePreDefinedScript(PreDefinedScriptDTO preDefinedScriptDTO) {
        PreDefinedScript preDefinedScript = new PreDefinedScript();

        preDefinedScript = this.dtoToScriptMapper.mapPreDefinedScript(preDefinedScript, preDefinedScriptDTO);

        this.preDefinedScriptRepository.saveAndFlush(preDefinedScript);
    }

    public List<AnonymizedScriptDTO> anonymizeScriptsForBackup(boolean saveAll) {
        List<PreDefinedScript> preDefinedScripts = this.preDefinedScriptRepository.findAll();
        List<BasicScript> basicScripts;
        if (saveAll) {
            basicScripts = this.basicScriptRepository.findAll();
        } else {
            basicScripts = this.basicScriptRepository.findBasicScriptsByCommand("RUN");
        }

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

        ResponseEntity<String> response = restTemplate.postForEntity(this.url + "/scripts/saveAll", httpEntity, String.class);

        return response;
    }

    public List<ShortcutDTO> retrieveScriptsFromWeb(String sessionkey) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(this.url + "/scripts/getAll")).header("content-type", "application/json")
                .header("sessionkey", sessionkey).header("Authorization", generateBasicAuthHeaderValue()).GET().build();

        String responseJson = sendRequest(req);

        ObjectMapper mapper = new ObjectMapper();
        List<ShortcutDTO> list;
        try {
            list = mapper.readValue(responseJson, TypeFactory.defaultInstance().constructCollectionType(List.class, ShortcutDTO.class));
            return list;
        } catch (JsonProcessingException e) {
            throw new APIRequestException("Error during JSON-Mapping of ShortcutDTO during API-Request", e);
        }
    }

    public void deleteBasicScript(Long id) {
        this.basicScriptRepository.deleteById(id);
    }

    public void deleteHotstring(Long id) {
        this.basicHotstringScriptRepository.deleteById(id);
    }

    public void deletePredefinedScript(Long id) {
        this.preDefinedScriptRepository.deleteById(id);
    }

    public void deleteAll() {
        this.basicScriptRepository.deleteAll();
        this.basicHotstringScriptRepository.deleteAll();
        this.preDefinedScriptRepository.deleteAll();
    }

    private String sendRequest(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new APIRequestException("Error during API-Request", e);
        }
    }

    private String generateBasicAuthHeaderValue() {
        Encoder base64Encoder = Base64.getEncoder();
        String unencodedAuthentication = this.username + ":" + this.password;
        byte[] unencodedAuthenticationBytes = unencodedAuthentication.getBytes();
        byte[] encodedAuthenticationBytes = base64Encoder.encode(unencodedAuthenticationBytes);

        String headerBase = "Basic ";
        String headerValue = new String(encodedAuthenticationBytes);

        return headerBase + headerValue;
    }
}
