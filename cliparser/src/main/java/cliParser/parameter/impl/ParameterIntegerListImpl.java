package cliParser.parameter.impl;

import cliParser.parameter.Parameter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ParameterIntegerListImpl implements Parameter<List<Integer>> {
    List<Integer> value;
    String separator = ",";

    public ParameterIntegerListImpl(Builder builder) {
        this.separator = builder.separator;
    }

    @Override
    public List<Integer> getValue() {
        return value;
    }

    @Override
    public void parse(String parameter) {
        if (!parameter.contains(separator)) {
            throw new IllegalArgumentException("wrong separator valid Separator: " + separator);
        }

        Pattern pattern = Pattern.compile(separator);
        value = pattern.splitAsStream(parameter)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public static class Builder {
        String separator;

        public Builder setSeparator(String separator) {
            this.separator = separator;
            return this;
        }

        public ParameterIntegerListImpl build() {
            return new ParameterIntegerListImpl(this);
        }
    }
}
