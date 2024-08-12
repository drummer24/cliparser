package cliParser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Parser {

    private List<Option> options = new ArrayList<>();

    /**
     * add new option if not exsist adds option aliases to required field
     *
     * @param option
     */
    public void addOption(Option option) {
        if (!options.contains(option)) {
            options.add(option);
        }
    }

    /**
     * finds option by aliass
     *
     * @param alias long or short option
     * @return option
     * @throws IllegalArgumentException if option does not exist
     */
    public Option findOptionByAlias(String alias) {
        return options.stream()
                .filter(option -> option.getAliases().contains(alias)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("option: " + alias + "  does not exist"));
    }

    /**
     * gets required options from cli
     * and all required options
     *
     * @param args argumenents from cli
     * @throws IllegalArgumentException when required args are missing in cli
     */
    public void throwExeptionForMissingRequiredArguments(List<String> args) {
        List<String> requiredOptionsCli = getRequiredOptionsCli(args);
        List<Option> requiredOptions = getAllRequiredOptions();
        if (requiredOptionsCli.size() != requiredOptions.size()) {
            throw new IllegalArgumentException("required arguments not present:");
        }
    }

    /**
     * gets all required option
     * @return list of options
     */
    private List<Option> getAllRequiredOptions() {
        return options.stream().filter(Option::isRequired).collect(Collectors.toList());
    }

    /**
     * gets all  required argument present in args
     * @param args cli arguments
     * @return list of required options
     */
    private List<String> getRequiredOptionsCli(List<String> args) {
        return args.stream().filter(arg -> options.stream()
                .anyMatch(option -> option.getAliases().contains(arg) && option.isRequired())).collect(Collectors.toList());
    }

    /**
     * parses option and parameters from args
     *
     * @param args cli args
     */
    public void parse(String[] args) {
        throwExeptionForMissingRequiredArguments(List.of(args));
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-h") || args[i].equals("--help")) {
                printHelp();
            } else {
                Option option = findOptionByAlias(args[i]);
                if (option.isRequired()) {
                    i++;
                    option.getParameter().parse(args[i]);
                }
            }
            i++;
        }

    }

    /**
     * prints help for all options
     */
    public void printHelp() {
        options.forEach(option -> System.out.println(option.getHelp()));
    }
}
