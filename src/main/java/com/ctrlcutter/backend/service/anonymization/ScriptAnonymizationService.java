package com.ctrlcutter.backend.service.anonymization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.service.anonymization.parameter.IScriptParameterAnonymizationService;
import com.ctrlcutter.backend.service.anonymization.parameter.ScriptParameterAnonymizationServiceProvider;

@Service
public class ScriptAnonymizationService {

    private ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider;

    @Autowired
    public ScriptAnonymizationService(ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider) {
        this.scriptParameterAnonymizationServiceProvider = scriptParameterAnonymizationServiceProvider;
    }

    public AnonymizedScriptDTO anonymizeBasicScript(BasicScript basicScript) {
        AnonymizedScriptDTO anonymizedScriptDTO = new AnonymizedScriptDTO();
        anonymizedScriptDTO.setKey(basicScript.getKey());
        anonymizedScriptDTO.setModifierKeys(basicScript.getModifierKeys().toArray(new String[0]));
        anonymizedScriptDTO.setCommand(basicScript.getCommand());

        String[] anonymizedParameters = this.anonymizeScriptParameters(basicScript.getCommand());
        anonymizedScriptDTO.setParameters(anonymizedParameters);

        return anonymizedScriptDTO;
    }

    //command == scriptType of anonymized preDefinedScript
    public AnonymizedScriptDTO anonymizePreDefinedScript(PreDefinedScript preDefinedScript) {
        AnonymizedScriptDTO anonymizedScriptDTO = new AnonymizedScriptDTO();
        anonymizedScriptDTO.setCommand(preDefinedScript.getScriptType());
        anonymizedScriptDTO.setKey(preDefinedScript.getShortcuts().get(0).getKey());
        anonymizedScriptDTO.setModifierKeys(preDefinedScript.getShortcuts().get(0).getModifierKeys().toArray(new String[0]));
        anonymizedScriptDTO.setParameters(null);

        return anonymizedScriptDTO;
    }

    private String[] anonymizeScriptParameters(String command) {
        IScriptParameterAnonymizationService service = this.scriptParameterAnonymizationServiceProvider.provideAnonymizationService(command);
        return service.anonymizeScriptParameter();
    }
}
