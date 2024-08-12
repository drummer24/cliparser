package cliParser.parameter;

import cliParser.parameter.impl.ParameterIntegerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterIntegerImplTest {
    private ParameterIntegerImpl parameter;

    @BeforeEach
    public void init() {
        parameter = new ParameterIntegerImpl.Builder().minMax(0, 10).build();
    }

    @Test
    public void shouldParseValidParameter() {
        parameter.parse("5");
        Assertions.assertEquals(5, (int) parameter.getValue());
    }

    @Test
    public void shouldThrowExceptionForInValidParameter() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> parameter.parse("11"));
        Assertions.assertEquals(exception.getMessage(), "value :11 must be >= than: 0 and <= than: 10");
    }
}
