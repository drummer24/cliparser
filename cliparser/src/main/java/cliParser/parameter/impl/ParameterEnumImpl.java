package cliParser.parameter.impl;

import cliParser.parameter.Parameter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParameterEnumImpl implements Parameter<Enum> {
    Enum value;
    Class enumType;

    private ParameterEnumImpl(Builder builder){
        this.enumType= builder.enumType;
    }

    public static class Builder {
        Class enumType;

        public Builder enumType(Class enumType){
            if (enumType.isEnum()) {
                this.enumType = enumType;
            } else {
                throw new IllegalArgumentException("wrong class type is not enum: " + enumType);
            }
            return this;
        }
        public ParameterEnumImpl build(){
            return new ParameterEnumImpl(this);
        }
    }

    @Override
    public Enum getValue() {
        return value;
    }

    @Override
    public void parse(String parameter) {
        value = value.valueOf(enumType, parameter);
    }
}
