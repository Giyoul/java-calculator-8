package calculator.service;

import calculator.constants.RegexPattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculatorService {

    public String calculate(String input) {
        // 1. Extract delimiter & validation check
        List<Character> delimiters = extractDelimiters(input);

        // 2. Remove custom delimiter from input
        String remaining = removeCustomDelimiter(input);

        // 3. Extract numbers by delimiters
        String[] numbers = splitByDelimiters(remaining, delimiters);

        // 4. Number validation check and calculate sum
        return calculateSum(numbers);
    }

    private List<Character> extractDelimiters(String input) {
        List<Character> delimiters = new ArrayList<>(List.of(',', ':'));
        Pattern customDelimiterPattern = RegexPattern.CUSTOM_DELIMITER.toPattern(); // 문자열의 시작만 확인
        Matcher customDelimiter = customDelimiterPattern.matcher(input);

        if(customDelimiter.find()){
            String delimiter = customDelimiter.group(1);
            if (delimiter.equals(",") || delimiter.equals(":")) {
                throw new IllegalArgumentException("Invalid custom delimiter: ',' and ':' are not allowed");
            }
            if (delimiter.length() > 1) {
                throw new IllegalArgumentException(
                        "Invalid custom delimiter: Delimiter length must be less than or equal to 1");
            }
            delimiters.add(delimiter.charAt(0));
        }

        return delimiters;
    }

    private String removeCustomDelimiter(String input) {
        Pattern customDelimiterPattern = RegexPattern.CUSTOM_DELIMITER.toPattern();
        Matcher customDelimiter = customDelimiterPattern.matcher(input);
        return customDelimiter.replaceFirst("");
    }

    private String[] splitByDelimiters(String remaining, List<Character> delimiters) {
        String delimiterPattern = delimiters.stream()
                .map(d -> "\\" + d)
                .collect(Collectors.joining("|"));

        delimiters.stream().forEach(d -> {
            String delim = String.valueOf(d);
            if (remaining.startsWith(delim)) {
                throw new IllegalArgumentException("Format error: Input cannot start with delimiter.");
            }
            if (remaining.endsWith(delim)) {
                throw new IllegalArgumentException("Format error: Input cannot end with delimiter.");
            }
        });

        String[] numbers = remaining.split(delimiterPattern);
        return Arrays.stream(numbers)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private String calculateSum(String[] numbers) {
        return "결과 : " + Arrays.stream(numbers)
                .mapToInt(s -> {
                    try {
                        int num = Integer.parseInt(s);
                        if (num < 0) {
                            throw new IllegalArgumentException("Invalid argument: Negative numbers are not allowed");
                        }
                        return num;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid argument: Include invalid character");
                    }
                })
                .sum();
    }
}
