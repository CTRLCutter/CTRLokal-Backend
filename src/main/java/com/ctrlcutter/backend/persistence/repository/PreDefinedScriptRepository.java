package com.ctrlcutter.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctrlcutter.backend.persistence.model.PreDefinedScript;

@Repository
public interface PreDefinedScriptRepository extends JpaRepository<PreDefinedScript, Long> {}
