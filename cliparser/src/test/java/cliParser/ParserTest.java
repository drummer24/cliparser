package cliParser;

import cliParser.parameter.impl.ParameterIntegerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.*;

public class ParserTest {
    @Spy
    Parser sut = new Parser();

    @BeforeEach
    public void init() {
        sut = spy(Parser.class);
        ParameterIntegerImpl parameter = new ParameterIntegerImpl.Builder().build();
        Option option = new Option.Builder().aliases(List.of("-l", "--left"))
                .required()
                .help("help")
                .parameter(spy(parameter))
                .build();
        sut.addOption(spy(option));
        option = new Option.Builder().aliases(List.of("-r", "--right"))
                .required()
                .help("help1")
                .parameter(spy(parameter))
                .build();
        sut.addOption(spy(option));
    }

    @Test
    public void shouldParseValidArgs() {
        sut.parse(new String[]{"-l", "5", "--r", "6"});
        verify(sut, times(2)).findOptionByAlias(anyString());
        verify(sut.getOptions().get(0).getParameter(), times(1)).parse(anyString());
        verify(sut.getOptions().get(1).getParameter(), times(1)).parse(anyString());
    }

    @Test
    public void shouldPrintHelp() {
        sut.parse(new String[]{"-h", "--help", "-l", "5", "-r", "6"});
        verify(sut, times(2)).printHelp();
    }

    @Test
    public void shouldThrowExceptionOnMissingRequired() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.parse(new String[]{"-l", "5"}));
        Assertions.assertEquals(exception.getMessage(), "required arguments not present:");
    }

    @Test
    public void shouldThrowExceptionInvalidOption() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.parse(new String[]{"-l", "5", "-r", "5", "-n"}));
        Assertions.assertEquals(exception.getMessage(), "option: -n  does not exist");
    }

}
