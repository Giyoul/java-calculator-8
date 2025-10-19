package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readInput() {
        try {
            String input = Console.readLine();
            return input != null ? input : "";
        } catch (Exception e) {
            return "";
        }
    }

    public void closeConsole() {
        Console.close();
    }
}
