package calculator.service;

import calculator.constants.ErrorMessage;
import calculator.constants.RegexPattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service class that handles calculator business logic.
 * parsing input, dealing delimiters, and validate & calculate numbers.
 */
public class CalculatorService {

    /**
     * Gets user input and calculate
     *
     * @param input the string to calculate (e.g. "1:2,3", "//;\n1;2;3")
     * @return calculation result string (e.g. "결과 : 6")
     * @throws IllegalArgumentException include negative number case or wrong delimiter
     */
    public String calculate(String input) {
        // 1. Extract delimiter & validation check
        List<String> delimiters = extractDelimiters(input);

        // 2. Remove custom delimiter from input
        String remaining = removeCustomDelimiter(input);

        // 3. Extract numbers by delimiters
        String[] numbers = splitByDelimiters(remaining, delimiters);

        // 4. Number validation check and calculate sum
        return calculateSum(numbers);
    }

    /**
     * Extracts delimiters from the input string.
     *
     * @param input target string to extract delimiters
     * @return list of extracted delimiters
     */
    private List<String> extractDelimiters(String input) {
        List<String> delimiters = new ArrayList<>(List.of(",", ":"));
        Pattern customDelimiterPattern = RegexPattern.CUSTOM_DELIMITER.toPattern(); // 문자열의 시작만 확인
        Matcher customDelimiter = customDelimiterPattern.matcher(input);

        if(customDelimiter.find()){
            String delimiter = customDelimiter.group(1);
            if (delimiter.equals(",") || delimiter.equals(":")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_DELIMITER.getMessage());
            }
            if (delimiter.length() > 1) {
                throw new IllegalArgumentException(ErrorMessage.DELIMITER_LENGTH.getMessage());
            }
            delimiters.add(delimiter);
        }

        return delimiters;
    }

    /**
     * Remove custom delimiter from the front of input string.
     *
     * @param input original string
     * @return string, which custom delimiter removed from original string
     */
    private String removeCustomDelimiter(String input) {
        Pattern customDelimiterPattern = RegexPattern.CUSTOM_DELIMITER.toPattern();
        Matcher customDelimiter = customDelimiterPattern.matcher(input);
        return customDelimiter.replaceFirst("");
    }

    /**
     * Split the string using the provided delimiters.
     *
     * @param remaining string to split
     * @param delimiters list of delimiters to use.
     * @return array of split number strings.
     * @throws IllegalArgumentException if delimiter position is invalid
     */
    private String[] splitByDelimiters(String remaining, List<String> delimiters) {
        String delimiterPattern = delimiters.stream()
                .map(d -> "\\" + d)
                .collect(Collectors.joining("|"));

        delimiters.stream().forEach(d -> {
            String delim = String.valueOf(d);
            if (remaining.startsWith(delim)) {
                throw new IllegalArgumentException(ErrorMessage.START_DELIMITER.getMessage());
            }
            if (remaining.endsWith(delim)) {
                throw new IllegalArgumentException(ErrorMessage.END_DELIMITER.getMessage());
            }
        });

        String[] numbers = remaining.split(delimiterPattern);
        return Arrays.stream(numbers)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * Calculates the sum of the number array.
     *
     * @param numbers array of number strings to calculate
     * @return calculation result string (e.g. "결과 : 6")
     * @throws IllegalArgumentException if array contains negative numbers or invalid characters
     */
    private String calculateSum(String[] numbers) {
        return "결과 : " + Arrays.stream(numbers)
                .mapToInt(s -> {
                    try {
                        int num = Integer.parseInt(s);
                        if (num < 0) {
                            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER.getMessage());
                        }
                        return num;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(ErrorMessage.INVALID_CHARACTER.getMessage());
                    }
                })
                .sum();
    }
}
