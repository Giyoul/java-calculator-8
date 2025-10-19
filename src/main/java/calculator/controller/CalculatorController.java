package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

/**
 * Controller class that manages the flow of the calculator application.
 */
public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    /**
     * Constructor of CalculatorController
     *
     * @param inputView input handling view
     * @param outputView output handling view
     * @param calculatorService calculation service
     */
    public CalculatorController(InputView inputView, OutputView outputView, CalculatorService calculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculatorService = calculatorService;
    }

    /**
     * Runs the calculator application
     */
    public void run() {
        // 1. Display start massage
        outputView.printStartMessage();

        // 2. Get users input
        String input = inputView.readInput();

        // 3. Calculate result
        String result = calculatorService.calculate(input);

        // 4. Display result
        outputView.printResultMessage(result);

        // 5. Close input console
        inputView.closeConsole();
    }
}
