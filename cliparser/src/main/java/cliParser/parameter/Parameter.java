package cliParser.parameter;

/**
 * basic interFace for parsing option parameter
 * @param <T> type of Param
 */
public interface Parameter<T> {
    T getValue();

    /**
     * parses parameter to value
     * @param parameter to be parsed
     */
    void parse(String parameter);

}
