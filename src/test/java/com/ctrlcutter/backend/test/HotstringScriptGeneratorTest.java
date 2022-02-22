package com.ctrlcutter.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ctrlcutter.backend.dto.BasicHotstringDTO;
import com.ctrlcutter.backend.service.HotstringScriptGeneratorService;

public class HotstringScriptGeneratorTest {

    @Test
    public void testHotstringGeneration() {

        HotstringScriptGeneratorService service = new HotstringScriptGeneratorService();

        BasicHotstringDTO hotstringDTO1 = new BasicHotstringDTO("win", new String[] {}, "hey", "Hello World");
        BasicHotstringDTO hotstringDTO2 = new BasicHotstringDTO("win", new String[] {"NO_TRIGGER"}, "c@", "mail@ctrlcutter.com");
        BasicHotstringDTO hotstringDTO3 =
                new BasicHotstringDTO("win", new String[] {"NO_TRIGGER", "NO_AUTOBACKSPACE", "CASE_SENSITIVE"}, "c@", "mail@ctrlcutter.com");

        System.out.println(service.generateHotstringScript(hotstringDTO1));

        assertEquals("::hey::Hello World", service.generateHotstringScript(hotstringDTO1));
        assertEquals(":*:c@::mail@ctrlcutter.com", service.generateHotstringScript(hotstringDTO2));
        assertEquals(":*B0C:c@::mail@ctrlcutter.com", service.generateHotstringScript(hotstringDTO3));
    }
}
