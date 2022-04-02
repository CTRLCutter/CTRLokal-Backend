package com.ctrlcutter.backend.service.anonymization.parameter;

public class TextScriptParameterAnonymizationService implements IScriptParameterAnonymizationService {

    @Override
    public String[] anonymizeScriptParameter() {
        return new String[] {"Anonymous Sample Text"};
    }
}
