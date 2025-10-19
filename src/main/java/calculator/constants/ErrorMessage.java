package calculator.constants;

public enum ErrorMessage {
    INVALID_DELIMITER("Invalid custom delimiter: ',' and ':' are not allowed"),
    DELIMITER_LENGTH("Invalid custom delimiter: Delimiter length must be less than or equal to 1"),
    START_DELIMITER("Format error: Input cannot start with delimiter."),
    END_DELIMITER("Format error: Input cannot end with delimiter."),
    NEGATIVE_NUMBER("Invalid argument: Negative numbers are not allowed"),
    INVALID_CHARACTER("Invalid argument: Include invalid character");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
