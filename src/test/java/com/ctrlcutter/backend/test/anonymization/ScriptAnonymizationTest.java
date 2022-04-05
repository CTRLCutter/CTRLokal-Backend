package com.ctrlcutter.backend.test.anonymization;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.service.anonymization.ScriptAnonymizationService;
import com.ctrlcutter.backend.service.anonymization.parameter.ScriptParameterAnonymizationServiceProvider;

@RunWith(SpringRunner.class)
public class ScriptAnonymizationTest {

    @TestConfiguration
    static class ScriptAnonymizationTestTestContextConfiguration {

        @Autowired
        private ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider;

        @Bean
        public ScriptParameterAnonymizationServiceProvider scriptParameterAnonymizationServiceProvider() {
            return new ScriptParameterAnonymizationServiceProvider();
        }

        @Bean
        public ScriptAnonymizationService scriptAnonymizationService() {
            return new ScriptAnonymizationService(this.scriptParameterAnonymizationServiceProvider);
        }
    }

    @Autowired
    private ScriptAnonymizationService scriptAnonymizationService;

    @Test
    public void testRunScriptAnonymization() {
        String runURL = "https://youtube.com";
        BasicScriptDTO exampleDTO = new BasicScriptDTO("win", "RUN", "c", new String[] {"CTRL"}, new String[] {runURL});
        AnonymizedScriptDTO anonymizedScriptDTO = this.scriptAnonymizationService.anonymizeScriptDTO(exampleDTO);

        assertFalse(runURL.equals(anonymizedScriptDTO.getParameters()[0]));
        assertEquals(anonymizedScriptDTO.getParameters()[0], "https://ctrlcutter.com");
        assertEquals(exampleDTO.getCommand(), anonymizedScriptDTO.getCommand());
        assertEquals(exampleDTO.getKey(), anonymizedScriptDTO.getKey());
        assertEquals(exampleDTO.getModifierKeys()[0], anonymizedScriptDTO.getModifierKeys()[0]);
        assertTrue(anonymizedScriptDTO.getModifierKeys().length == 1);
    }

    @Test
    public void testSendScriptAnonymization() {
        String textOutput = "Some Secret Text";
        BasicScriptDTO exampleDTO = new BasicScriptDTO("win", "SEND", "f", new String[] {"CTRL"}, new String[] {textOutput});
        AnonymizedScriptDTO anonymizedScriptDTO = this.scriptAnonymizationService.anonymizeScriptDTO(exampleDTO);

        assertFalse(textOutput.equals(anonymizedScriptDTO.getParameters()[0]));
        assertEquals(anonymizedScriptDTO.getParameters()[0], "Anonymous Sample Text");
        assertEquals(exampleDTO.getCommand(), anonymizedScriptDTO.getCommand());
        assertEquals(exampleDTO.getKey(), anonymizedScriptDTO.getKey());
        assertEquals(exampleDTO.getModifierKeys()[0], anonymizedScriptDTO.getModifierKeys()[0]);
        assertTrue(anonymizedScriptDTO.getModifierKeys().length == 1);
    }
}
