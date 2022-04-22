package com.ctrlcutter.backend.persistence.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;
import com.ctrlcutter.backend.persistence.model.BasicScript;
import com.ctrlcutter.backend.persistence.model.PreDefinedScript;
import com.ctrlcutter.backend.persistence.repository.BasicHotstringScriptRepository;
import com.ctrlcutter.backend.persistence.repository.BasicScriptRepository;
import com.ctrlcutter.backend.persistence.repository.PreDefinedScriptRepository;
import com.ctrlcutter.backend.util.DTOToScriptMapper;

@Service
public class ScriptEditService {

    private final BasicScriptRepository basicScriptRepository;
    private final BasicHotstringScriptRepository basicHotstringScriptRepository;
    private final PreDefinedScriptRepository preDefinedScriptRepository;
    private final DTOToScriptMapper dtoToScriptMapper;

    @Autowired
    public ScriptEditService(BasicScriptRepository basicScriptRepository, BasicHotstringScriptRepository basicHotstringScriptRepository,
            PreDefinedScriptRepository preDefinedScriptRepository, DTOToScriptMapper dtoToScriptMapper) {
        this.basicScriptRepository = basicScriptRepository;
        this.basicHotstringScriptRepository = basicHotstringScriptRepository;
        this.preDefinedScriptRepository = preDefinedScriptRepository;
        this.dtoToScriptMapper = dtoToScriptMapper;
    }

    @Transactional
    public boolean editBasicScript(int id, BasicScriptDTO basicScriptDTO) {
        Optional<BasicScript> basicScriptOptional = this.basicScriptRepository.findById((long) id);

        if (basicScriptOptional.isPresent()) {
            BasicScript basicScript = basicScriptOptional.get();

            this.dtoToScriptMapper.mapBasicScript(basicScript, basicScriptDTO);

            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean editHotstringScript(int id, BasicHotstringDTO basicHotstringDTO) {
        Optional<BasicHotstringScript> basicHotstringScriptOptional = this.basicHotstringScriptRepository.findById((long) id);

        if (basicHotstringScriptOptional.isPresent()) {
            BasicHotstringScript basicHotstringScript = basicHotstringScriptOptional.get();

            this.dtoToScriptMapper.mapHotstringScript(basicHotstringScript, basicHotstringDTO);

            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean editPreDefinedScript(int id, PreDefinedScriptDTO preDefinedScriptDTO) {
        Optional<PreDefinedScript> preDefinedScriptOptional = this.preDefinedScriptRepository.findById((long) id);

        if (preDefinedScriptOptional.isPresent()) {
            PreDefinedScript preDefinedScript = preDefinedScriptOptional.get();

            this.dtoToScriptMapper.mapPreDefinedScript(preDefinedScript, preDefinedScriptDTO);

            return true;
        } else {
            return false;
        }
    }
}
