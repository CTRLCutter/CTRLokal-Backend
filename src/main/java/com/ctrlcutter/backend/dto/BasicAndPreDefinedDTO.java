package com.ctrlcutter.backend.dto;

import java.util.List;

import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;

public class BasicAndPreDefinedDTO {

    private List<BasicScript> basicScripts;

    private List<PreDefinedScript> preDefinedScripts;

    public BasicAndPreDefinedDTO() {}

    public BasicAndPreDefinedDTO(List<BasicScript> basicScripts, List<PreDefinedScript> preDefinedScripts) {
        this.basicScripts = basicScripts;
        this.preDefinedScripts = preDefinedScripts;
    }

    public List<BasicScript> getBasicScripts() {
        return this.basicScripts;
    }

    public void setBasicScripts(List<BasicScript> basicScripts) {
        this.basicScripts = basicScripts;
    }

    public List<PreDefinedScript> getPreDefinedScripts() {
        return this.preDefinedScripts;
    }

    public void setPreDefinedScripts(List<PreDefinedScript> preDefinedScripts) {
        this.preDefinedScripts = preDefinedScripts;
    }
}
