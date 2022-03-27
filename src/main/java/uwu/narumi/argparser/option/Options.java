package uwu.narumi.argparser.option;

import java.math.BigInteger;
import java.util.*;

public class Options {

    private final Map<String, Option> options;

    public Options(Map<String, Option> options) {
        this.options = options;
    }

    public Optional<Option> getOptional(String optionName) {
        return Optional.ofNullable(get(optionName));
    }

    public Optional<Option> getOrDefaultOptional(String optionName, Option defaultValue) {
        return Optional.ofNullable(getOrDefault(optionName, defaultValue));
    }

    public Optional<String> getOrDefaultOptional(String optionName, String defaultValue) {
        return Optional.ofNullable(getOrDefault(optionName, defaultValue));
    }

    public Option get(String optionName) {
        return options.get(optionName.toLowerCase(Locale.ROOT));
    }

    public Option getOrDefault(String optionName, Option defaultValue) {
        Option option = options.get(optionName.toLowerCase(Locale.ROOT));
        return option == null || option.isEmpty() ? defaultValue : option;
    }

    public String getOrDefault(String optionName, String defaultValue) {
        Option option = options.get(optionName.toLowerCase(Locale.ROOT));
        return option == null || option.isEmpty() ? defaultValue : option.toString();
    }

    public Optional<String> asString(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asString();
    }

    public Optional<Boolean> asBoolean(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asBoolean();
    }

    public Optional<Byte> asByte(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asByte();
    }

    public Optional<Short> asShort(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asShort();
    }

    public Optional<Integer> asInt(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asInt();
    }

    public Optional<Long> asLong(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asLong();
    }

    public Optional<Float> asFloat(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asFloat();
    }

    public Optional<Double> asDouble(String optionName) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asDouble();
    }

    public Optional<BigInteger> asBigInteger(String optionName) {
        return asBigInteger(optionName, 10);
    }

    public Optional<BigInteger> asBigInteger(String optionName, int radix) {
        if (!options.containsKey(optionName))
            return Optional.empty();

        return get(optionName).asBigInteger(radix);
    }

    public int size() {
        return options.size();
    }

    public boolean isEmpty() {
        return options.isEmpty();
    }

    public Set<String> keySet() {
        return options.keySet();
    }

    public Collection<Option> values() {
        return options.values();
    }

    public Set<Map.Entry<String, Option>> entrySet() {
        return options.entrySet();
    }

    @Override
    public String toString() {
        return "Options=" + options;
    }

    public static class Option {
        private final String value;

        public Option(String value) {
            this.value = value;
        }

        public Optional<String> asString() {
            return Optional.ofNullable(value);
        }

        public Optional<Boolean> asBoolean() {
            try {
                return Optional.of(Boolean.parseBoolean(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Byte> asByte() {
            try {
                return Optional.of(Byte.parseByte(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Short> asShort() {
            try {
                return Optional.of(Short.parseShort(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Integer> asInt() {
            try {
                return Optional.of(Integer.parseInt(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Long> asLong() {
            try {
                return Optional.of(Long.parseLong(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Float> asFloat() {
            try {
                return Optional.of(Float.parseFloat(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<Double> asDouble() {
            try {
                return Optional.of(Double.parseDouble(value));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<BigInteger> asBigInteger() {
            try {
                return asBigInteger(10);
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public Optional<BigInteger> asBigInteger(int radix) {
            try {
                return Optional.of(new BigInteger(value, radix));
            } catch (Exception e) {
                return Optional.empty();
            }
        }

        public boolean isEmpty() {
            return value == null;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
