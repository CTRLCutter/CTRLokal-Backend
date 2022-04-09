package com.ctrlcutter.backend.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;

@Service
public class PersistenceService {

    private BasicHotstringScriptRepository basicHotstringScriptRepository;
    private BasicScriptRepository basicScriptRepository;
    private PreDefinedScriptRepository preDefinedScriptRepository;

    @Autowired
    public PersistenceService(BasicHotstringScriptRepository basicHotstringScriptRepository, BasicScriptRepository basicScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository) {
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.basicScriptRepository = basicScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
    }

}
