package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        String output = "결과 : ";

        List<Character> delimiters = new ArrayList<>(List.of(',', ':'));
        Pattern customDelimiterPattern = Pattern.compile("^//(.+)\\\\n"); // 문자열의 시작만 확인
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

        String remaining = customDelimiter.replaceFirst("");
        String numSeparateDelimiterPattern = delimiters.stream()
                .map(d -> "\\" + d)
                .collect(Collectors.joining("|"));
        String[] nums = remaining.split(numSeparateDelimiterPattern);

        int ans = Arrays.stream(nums)
                .mapToInt(Integer::parseInt)
                .sum();
        output += String.valueOf(ans);

        System.out.println(output);
        Console.close();
    }
}
