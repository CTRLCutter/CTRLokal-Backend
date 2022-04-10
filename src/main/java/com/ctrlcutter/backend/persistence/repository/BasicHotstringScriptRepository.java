package com.ctrlcutter.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctrlcutter.backend.persistence.model.BasicHotstringScript;

@Repository
public interface BasicHotstringScriptRepository extends JpaRepository<BasicHotstringScript, Long> {}
