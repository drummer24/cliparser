package cliParser.parameter;

import cliParser.parameter.impl.ParameterStringImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterStringImplTest {
    private ParameterStringImpl parameter;

    @BeforeEach
    public void init() {
        parameter = new ParameterStringImpl.Builder()
                .minMax(1, 10)
                .build();
    }

    @Test
    public void shouldParseValidParameter() {
        parameter.parse("file");
        Assertions.assertEquals("file", parameter.getValue());
    }

    @Test
    public void shouldThrowExceptionForInValidParameter() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> parameter.parse("testString1"));
        Assertions.assertEquals(exception.getMessage(), "parameter must be minimal :1 characters long and maximal :10 characters long");
    }
}
