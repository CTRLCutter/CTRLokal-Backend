package com.ctrlcutter.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ctrlcutter.backend.dto.BasicScriptDTO;
import com.ctrlcutter.backend.service.BasicScriptGeneratorService;

public class BasicScriptGeneratorTest {

    @Test
    public void sendScriptGenerationTest() {

        BasicScriptGeneratorService service = new BasicScriptGeneratorService();

        BasicScriptDTO scriptDTO1 = new BasicScriptDTO("win", "SEND", "f", new String[] {"CTRL", "ALT"}, new String[] {"Hello Test."});
        BasicScriptDTO scriptDTO2 = new BasicScriptDTO("win", "SEND", "g", new String[] {"CTRL"}, new String[] {"Hey."});
        BasicScriptDTO scriptDTO3 = new BasicScriptDTO("win", "SEND", "q", new String[] {"CTRL", "ALT", "SHIFT", "WINDOWS"}, new String[] {"How are you?"});
        
        assertEquals("^!f::\r\nSend, Hello Test.\r\nreturn", service.generateBasicScript(scriptDTO1));
        assertEquals("^g::\r\nSend, Hey.\r\nreturn", service.generateBasicScript(scriptDTO2));
        assertEquals("^!+#q::\r\nSend, How are you?\r\nreturn", service.generateBasicScript(scriptDTO3));
    }

}
