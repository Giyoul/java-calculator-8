package calculator.constants;

import java.util.regex.Pattern;

public enum RegexPattern {
    CUSTOM_DELIMITER("^//(.+)\\\\n");

    private final String pattern;

    RegexPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public Pattern toPattern() {
        return Pattern.compile(pattern);
    }
}
