package com.ctrlcutter.backend.service.anonymization.parameter;

import org.springframework.stereotype.Service;

@Service
public class ScriptParameterAnonymizationServiceProvider {

    public IScriptParameterAnonymizationService provideAnonymizationService(String command) {
        switch (command) {
            case "RUN":
                return new RunScriptParameterAnonymizationService();
            case "SEND":
                return new TextScriptParameterAnonymizationService();
            default:
                return new DefaultScriptParameterAnonymizationService();
        }
    }
}
