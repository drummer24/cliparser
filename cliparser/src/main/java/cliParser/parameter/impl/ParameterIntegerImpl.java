package cliParser.parameter.impl;

import cliParser.parameter.Parameter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParameterIntegerImpl implements Parameter<Integer> {
    private Integer value;

    private int min;
    private int max;

    private ParameterIntegerImpl(Builder builder) {
        this.min = builder.min;
        this.max = builder.max;
    }

    public static class Builder {
        private int min=Integer.MIN_VALUE;
        private int max= Integer.MAX_VALUE;

        public Builder minMax(int min, int max) {
            if (min > max) {
                throw new IllegalArgumentException("min: " + min + " is greater than max: " + max);
            }
            this.min = min;
            this.max = max;
            return this;
        }

        public ParameterIntegerImpl build() {
            return new ParameterIntegerImpl(this);
        }
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void parse(String parameter) {
        value = Integer.parseInt(parameter);
        if ((value <= min || value >= max)) {
            throw new IllegalArgumentException("value :" + parameter + " must be >= than: " + min + " and <= than: " + max);
        }
    }

}
