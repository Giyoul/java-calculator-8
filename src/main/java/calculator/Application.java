package calculator;

import calculator.controller.CalculatorController;
import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

/**
 * Main class of the calculator application.
 * Gets user input, performs calculations, and outputs results.
 */
public class Application {

    /**
     * Entry point of the application.
     *
     * @param args command line
     */
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CalculatorService calculatorService = new CalculatorService();
        CalculatorController calculatorController = new CalculatorController(inputView, outputView, calculatorService);

        calculatorController.run();
    }
}
