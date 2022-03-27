package uwu.narumi.argparser.rules;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Rules {

    private final Map<String, Rule> rules;

    private Rules(Builder builder) {
        rules = builder.rules;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Rule> getRules() {
        return rules;
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

    public boolean containsKey(String ruleName) {
        return rules.containsKey(ruleName.toLowerCase(Locale.ROOT));
    }

    public Rule get(String key) {
        return rules.get(key.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return "Rules=" + rules.values();
    }

    public static class Builder {
        private final Map<String, Rule> rules = new HashMap<>();

        private Builder() {
        }

        public Builder add(String name, boolean optional, boolean isArgument) {
            rules.put(name, new Rule(name.toLowerCase(Locale.ROOT), optional, isArgument));
            return this;
        }

        public Rules build() {
            return new Rules(this);
        }
    }

    public static class Rule {
        private final String name;
        private final boolean optional;
        private final boolean argument;

        public Rule(String name, boolean optional, boolean argument) {
            this.name = name;
            this.optional = optional;
            this.argument = argument;
        }

        public String getName() {
            return name;
        }

        public boolean isOptional() {
            return optional;
        }

        public boolean isArgument() {
            return argument;
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "name='" + name + '\'' +
                    ", optional=" + optional +
                    ", argument=" + argument +
                    '}';
        }
    }
}
