package com.ctrlcutter.backend.service.anonymization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.service.anonymization.parameter.IScriptParameterAnonymizationService;
import com.ctrlcutter.backend.service.anonymization.parameter.ScriptParameterAnonymizationServiceProvider;

@Service
public class ScriptAnonymizationService {

    private ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider;

    @Autowired
    public ScriptAnonymizationService(ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider) {
        this.scriptParameterAnonymizationServiceProvider = scriptParameterAnonymizationServiceProvider;
    }

    public AnonymizedScriptDTO anonymizeScriptDTO(BasicScriptDTO scriptDTO) {
        AnonymizedScriptDTO anonymizedScriptDTO = new AnonymizedScriptDTO();
        anonymizedScriptDTO.setKey(scriptDTO.getKey());
        anonymizedScriptDTO.setModifierKeys(scriptDTO.getModifierKeys());
        anonymizedScriptDTO.setCommand(scriptDTO.getCommand());

        String[] anonymizedParameters = this.anonymizeScriptParameters(scriptDTO.getCommand());
        anonymizedScriptDTO.setParameters(anonymizedParameters);

        return anonymizedScriptDTO;
    }

    private String[] anonymizeScriptParameters(String command) {
        IScriptParameterAnonymizationService service = this.scriptParameterAnonymizationServiceProvider.provideAnonymizationService(command);
        return service.anonymizeScriptParameter();
    }
}
