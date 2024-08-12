package cliParser;

import cliParser.parameter.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private List<String> aliases;
    private boolean required;
    private String help;
    private Parameter parameter;

    private Option(Builder builder) {
        this.aliases = builder.aliases;
        this.required = builder.required;
        this.help = builder.help;
        this.parameter = builder.parameter;
    }

    public static class Builder {

        private List<String> aliases;
        private boolean required;
        private String help;
        private Parameter parameter;

        public Builder aliases(List<String> aliases) {
            if (aliases.contains("-h") || aliases.contains("--help")) {
                throw new IllegalArgumentException("-h and --help reserved command for help not allowed");
            }
            aliases.stream()
                    .filter(alias -> alias.startsWith("-") && alias.length() == 2)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("long option or shor must be set"));
            this.aliases = aliases;
            return this;
        }

        public Builder required() {
            this.required = true;
            return this;
        }

        public Builder help(String help) {
            this.help = help;
            return this;
        }


        public Builder parameter(Parameter parameter) {
            this.parameter = parameter;
            return this;
        }

        public Option build() {
            return new Option(this);
        }
    }
}
