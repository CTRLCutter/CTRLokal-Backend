package com.ctrlcutter.backend.test.anonymization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ctrlcutter.backend.dto.AnonymizedScriptDTO;
import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.service.anonymization.ScriptAnonymizationService;

public class ScriptAnonymizationTest {

    @Test
    public void testScriptAnonymization() {
        String runURL = "https://youtube.com";
        BasicScriptDTO exampleDTO = new BasicScriptDTO("win", "RUN", "c", new String[] {"CTRL"}, new String[] {runURL});
        AnonymizedScriptDTO anonymizedScriptDTO = ScriptAnonymizationService.anonymizeScriptDTO(exampleDTO);

        assertFalse(runURL.equals(anonymizedScriptDTO.getParameters()[0]));
        assertEquals(exampleDTO.getCommand(), anonymizedScriptDTO.getCommand());
        assertEquals(exampleDTO.getKey(), anonymizedScriptDTO.getKey());
        assertEquals(exampleDTO.getModifierKeys()[0], anonymizedScriptDTO.getModifierKeys()[0]);
        assertTrue(anonymizedScriptDTO.getModifierKeys().length == 1);
    }
}
