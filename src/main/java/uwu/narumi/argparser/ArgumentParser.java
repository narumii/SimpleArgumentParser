package uwu.narumi.argparser;

import uwu.narumi.argparser.option.Options;
import uwu.narumi.argparser.rules.Rules;

import java.util.*;

public class ArgumentParser {

    private final String optionStart;
    private final String argumentStart;

    private ArgumentParser(Builder builder) {
        optionStart = builder.optionStart;
        argumentStart = builder.argumentStart;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Options parse(Rules rules, String[] arguments) {
        return parse(rules, String.join(" ", arguments));
    }

    public Options parse(Rules rules, String joined) {
        Map<String, Options.Option> options = new HashMap<>();
        List<Integer> indexes = new ArrayList<>();

        String lowerCaseJoined = joined.toLowerCase(Locale.ROOT);
        rules.getRules().forEach((name, rule) -> {
            String prefix = (rule.isArgument() ? argumentStart : optionStart) + name;
            int index = lowerCaseJoined.indexOf(prefix);
            if (index == -1) {
                if (!rule.isOptional())
                    throw new IllegalArgumentException(String.format("%s \"%s\" missing", rule.isArgument() ? "Argument" : "Option", name));

                return;
            }

            indexes.add(index);
        });

        indexes.sort(Comparator.comparingInt(Integer::intValue));
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            int nextIndex = i + 1 < indexes.size() ? indexes.get(i + 1) - 1 : joined.length();

            String string = joined.substring(index, nextIndex);
            String lowerCaseString = string.toLowerCase(Locale.ROOT);
            if (string.startsWith(optionStart)) {
                options.put(string.substring(1), new Options.Option("true"));
            } else if (string.startsWith(argumentStart)) {
                rules.getRules().values().stream()
                        .filter(Rules.Rule::isArgument)
                        .filter(name -> lowerCaseString.startsWith(argumentStart + name.getName()))
                        .findFirst()
                        .ifPresent(rule ->
                                options.put(rule.getName(), new Options.Option(string.substring((argumentStart + rule.getName()).length() + 1))));
            }
        }

        return new Options(options);
    }

    @Override
    public String toString() {
        return "ArgumentParser{" +
                "optionStart='" + optionStart + '\'' +
                ", argumentStart='" + argumentStart + '\'' +
                '}';
    }

    public static class Builder {
        private String optionStart = "+";
        private String argumentStart = "-";

        private Builder() {
        }

        public Builder optionStart(String optionStart) {
            this.optionStart = optionStart;
            return this;
        }

        public Builder argumentStart(String argumentStart) {
            this.argumentStart = argumentStart;
            return this;
        }

        public ArgumentParser build() {
            return new ArgumentParser(this);
        }
    }
}
