package cliParser.parameter;

import cliParser.parameter.impl.ParameterBooleanImpl;
import cliParser.parameter.impl.ParameterIntegerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParameterBooleanImplTest {
    private ParameterBooleanImpl parameter;

    @BeforeEach
    public void init(){
        parameter = new ParameterBooleanImpl.Builder()
                .setTrueValues(List.of("yes", "true"))
                .setFalseValues(List.of("no", "false")).build();
    }

    @Test
    public void shouldParseValidParameter(){
        parameter.parse("yes");
        Assertions.assertTrue(parameter.getValue().equals(true));
    }

    @Test
    public void shouldThrowExceptionForInValidParameter(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> parameter.parse("arg"));
        Assertions.assertEquals(exception.getMessage(), "parameter must be value from :[no, false][yes, true]");
    }
}
