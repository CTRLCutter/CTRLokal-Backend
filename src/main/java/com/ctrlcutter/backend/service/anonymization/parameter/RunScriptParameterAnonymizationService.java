package com.ctrlcutter.backend.service.anonymization.parameter;

public class RunScriptParameterAnonymizationService implements IScriptParameterAnonymizationService {

    public RunScriptParameterAnonymizationService() {}

    @Override
    public String[] anonymizeScriptParameter() {
        String anonymizedScriptParameterValue = "https://ctrlcutter.com";
        String[] scriptParameters = new String[] {anonymizedScriptParameterValue};

        return scriptParameters;
    }
}
