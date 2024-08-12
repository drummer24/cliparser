package cliParser.parameter;

import cliParser.parameter.impl.ParameterEnumImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterEnumImplTest {
    private ParameterEnumImpl parameter;

    @BeforeEach
    public void init() {
        parameter = new ParameterEnumImpl.Builder()
                .enumType(ExampleEnum.class).build();
    }

    @Test
    public void shouldParseValidParameter() {
        parameter.parse("EXAMPLE");
        Assertions.assertEquals(parameter.getValue(), ExampleEnum.EXAMPLE);
    }

}
