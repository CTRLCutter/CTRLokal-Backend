package com.ctrlcutter.backend.service.anonymization;

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.service.anonymization.parameter.IScriptParameterAnonymizationService;
import com.ctrlcutter.backend.service.anonymization.parameter.ScriptParameterAnonymizationServiceProvider;

public class ScriptAnonymizationService {

    public static AnonymizedScriptDTO anonymizeScriptDTO(BasicScriptDTO scriptDTO) {
        AnonymizedScriptDTO anonymizedScriptDTO = new AnonymizedScriptDTO();
        anonymizedScriptDTO.setKey(scriptDTO.getKey());
        anonymizedScriptDTO.setModifierKeys(scriptDTO.getModifierKeys());
        anonymizedScriptDTO.setCommand(scriptDTO.getCommand());

        String[] anonymizedParameters = anonymizeScriptParameters(scriptDTO.getCommand());
        anonymizedScriptDTO.setParameters(anonymizedParameters);

        return anonymizedScriptDTO;
    }

    private static String[] anonymizeScriptParameters(String command) {
        IScriptParameterAnonymizationService service = ScriptParameterAnonymizationServiceProvider.provideAnonymizationService(command);
        return service.anonymizeScriptParameter();
    }
}
