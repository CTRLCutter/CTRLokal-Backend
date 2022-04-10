package com.ctrlcutter.backend.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;

@Service
public class PersistenceReceiveService {

    private BasicHotstringScriptRepository basicHotstringScriptRepository;
    private BasicScriptRepository basicScriptRepository;
    private PreDefinedScriptRepository preDefinedScriptRepository;

    @Autowired
    public PersistenceReceiveService(BasicHotstringScriptRepository basicHotstringScriptRepository, BasicScriptRepository basicScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository) {
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.basicScriptRepository = basicScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
    }

    public List<BasicScript> getAllBasicScripts() {
        return this.basicScriptRepository.findAll();
    }

    public List<BasicHotstringScript> getAllHotstringScripts() {
        return this.basicHotstringScriptRepository.findAll();
    }

    public List<PreDefinedScript> getAllPreDefinedScripts() {
        return this.preDefinedScriptRepository.findAll();
    }
}
