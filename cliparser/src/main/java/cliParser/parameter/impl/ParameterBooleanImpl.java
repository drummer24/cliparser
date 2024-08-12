package cliParser.parameter.impl;

import cliParser.parameter.Parameter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */
@NoArgsConstructor
public class ParameterBooleanImpl implements Parameter<Boolean> {
    Boolean value;
    List<String> valuesTrue;
    List<String> valuesFalse;

    public ParameterBooleanImpl(Builder builder){
        this.valuesTrue=builder.valuesTrue;
        this.valuesFalse=builder.valuesFalse;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void parse(String parameter) {
        if (valuesTrue.contains(parameter)) {
            value = true;
        } else if (valuesFalse.contains(parameter)) {
            value = false;
        } else {
            throw new IllegalArgumentException("parameter must be value from :" + valuesFalse.toString() + valuesTrue.toString());
        }
    }

    public static class Builder {
        List<String> valuesTrue;
        List<String> valuesFalse;

        public Builder setTrueValues(List<String> valuesTrue){
            this.valuesTrue=valuesTrue;
            return this;
        }

        public Builder setFalseValues(List<String> valuesFalse){
            this.valuesFalse=valuesFalse;
            return this;
        }

        public ParameterBooleanImpl build(){
            return new ParameterBooleanImpl(this);
        }
    }
}
