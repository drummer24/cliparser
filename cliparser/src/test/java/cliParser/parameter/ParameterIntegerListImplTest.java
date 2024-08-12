package cliParser.parameter;

import cliParser.parameter.impl.ParameterIntegerListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParameterIntegerListImplTest {
    private ParameterIntegerListImpl parameter;

    @BeforeEach
    public void init(){
        parameter = new ParameterIntegerListImpl
                .Builder()
                .setSeparator(",")
                .build();
    }

    @Test
    public void shouldParseValidParameter() {
        parameter.parse("1,2,4,5");
        Assertions.assertEquals(List.of(1,2,4,5), parameter.getValue());
    }

    @Test
    public void shouldThrowExceptionForInValidParameter() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> parameter.parse("1;2;3;4;"));
        Assertions.assertEquals(exception.getMessage(), "wrong separator valid Separator: ,");
    }
}
