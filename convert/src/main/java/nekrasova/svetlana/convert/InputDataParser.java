package nekrasova.svetlana.convert;

public class InputDataParser {

	private boolean isCorrect;
	private String message;
	private boolean isQuestion;
	private InputValues inputValues;

	public InputDataParser(boolean isCorrect, String message, boolean isQuestion, InputValues inputValues) {
		super();
		this.isCorrect = isCorrect;
		this.message = message;
		this.isQuestion = isQuestion;
		this.inputValues = inputValues;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public String getMessage() {
		return message;
	}

	public boolean isQuestion() {
		return isQuestion;
	}

	public InputValues getInputValues() {
		return inputValues;
	}

	public static InputDataParser parseInputString(String input, boolean parseQuestion) {

		String[] inputParts = input.split(" +");

		if (inputParts.length != 5 || !inputParts[2].equals("=")) {
			return new InputDataParser(false, input + ConvertAppConstants.INPUT_NOT_CORRECT_FORMAT, false, null);
		} else {
			double mult1;
			double mult2;
			try {
				mult1 = Double.parseDouble(inputParts[0]);
			} catch (NumberFormatException e) {
				return new InputDataParser(false, input + ConvertAppConstants.INPUT_NOT_CORRECT_FORMAT, false, null);
			}

			if (parseQuestion) {
				if (inputParts[3].equals("?")) {
					return new InputDataParser(true, "", true, new InputValues(mult1, inputParts[1], inputParts[4]));
				} else {
					return new InputDataParser(false, input + ConvertAppConstants.INPUT_NOT_CORRECT_QUESTION, false, null);
				}
			}

			try {
				mult2 = Double.parseDouble(inputParts[3]);
			} catch (NumberFormatException e) {
				return new InputDataParser(false, input + ConvertAppConstants.INPUT_NOT_CORRECT_FORMAT, false, null);
			}

			if (inputParts[1].equals(inputParts[4]) && Double.compare(mult1, mult2) != 0) {
				return new InputDataParser(false, input + ConvertAppConstants.INPUT_NOT_CORRECT_DATA, false, null);
			}

			return new InputDataParser(true, "", false, new InputValues(mult1, inputParts[1], mult2, inputParts[4]));

		}

	}

}
