package com.ctrlcutter.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ctrlcutter.backend.dto.DefaultDTO;
import com.ctrlcutter.backend.dto.PreDefinedScriptDTO;
import com.ctrlcutter.backend.service.PreDefinedScriptGeneratorService;

public class PreDefinedScriptGenerationTest {

    @Test
    public void testPreDefinedScriptGeneration() {

        DefaultDTO testDTO1 = new DefaultDTO("s", "CTRL", "SHIFT");
        DefaultDTO testDTO2 = new DefaultDTO("Left", "CTRL");

        PreDefinedScriptDTO scriptDTO = new PreDefinedScriptDTO("win", "soundControl", new DefaultDTO[] {testDTO1, testDTO2});
        PreDefinedScriptGeneratorService service = new PreDefinedScriptGeneratorService();

        assertEquals("^+s::SoundSet,-5\n^Left::SoundSet,+5", service.generatePreDefinedScript(scriptDTO));
    }
}
