package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * View class that handles user input operations.
 */
public class InputView {

    /**
     * Reads input from user.
     *
     * @return string that entered by the user
     */
    public String readInput() {
        try {
            String input = Console.readLine();
            return input != null ? input : "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Closes the console input stream.
     */
    public void closeConsole() {
        Console.close();
    }
}
