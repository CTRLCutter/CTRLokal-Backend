package com.ctrlcutter.backend.service.anonymization.parameter;

public class ScriptParameterAnonymizationServiceProvider {

    public static IScriptParameterAnonymizationService provideAnonymizationService(String command) {
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
