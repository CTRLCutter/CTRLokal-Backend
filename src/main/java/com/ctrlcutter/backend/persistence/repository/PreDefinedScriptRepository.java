package com.ctrlcutter.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctrlcutter.backend.persistence.model.PreDefinedScript;

public interface PreDefinedScriptRepository extends JpaRepository<PreDefinedScript, Long> {}
