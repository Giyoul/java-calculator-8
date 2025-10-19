package calculator.view;

/**
 * View class that handles output operations.
 */
public class OutputView {

    /**
     * Prints the start message to the console.
     */
    public void printStartMessage() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    /**
     * Prints the calculation result to the console.
     *
     * @param result the result value to print
     */
    public void printResultMessage(String result) {
        System.out.println(result);
    }
}
