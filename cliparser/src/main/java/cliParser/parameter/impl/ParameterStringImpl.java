package cliParser.parameter.impl;

import cliParser.parameter.Parameter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParameterStringImpl implements Parameter<String> {

    String value;
    int min;
    int max;

    private ParameterStringImpl(Builder builder) {
        this.min = builder.min;
        this.max = builder.max;
    }

    public static class Builder {
        private int min;
        private int max = Integer.MAX_VALUE;

        public Builder minMax(int min, int max) {
            if (min > max) {
                throw new IllegalArgumentException("min: " + min + " is greater than max: " + max);
            }
            this.min = min;
            this.max = max;
            return this;
        }

        public ParameterStringImpl build() {
            return new ParameterStringImpl(this);
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void parse(String parameter) {
        value = parameter;
        if (value.length() < min || value.length() > max) {
            throw new IllegalArgumentException("parameter must be minimal :" + min + " characters long and maximal :"+ max + " characters long");
        }
    }
}
