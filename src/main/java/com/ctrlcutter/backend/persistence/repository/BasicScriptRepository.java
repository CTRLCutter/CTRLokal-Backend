package com.ctrlcutter.backend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctrlcutter.backend.persistence.model.BasicScript;

@Repository
public interface BasicScriptRepository extends JpaRepository<BasicScript, Long> {

    List<BasicScript> findBasicScriptsByCommand(String command);
}
