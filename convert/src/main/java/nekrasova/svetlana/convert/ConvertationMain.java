package nekrasova.svetlana.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConvertationMain {

	public static final ArrayList<ConvertRules> rules = new ArrayList<ConvertRules>();
	public static final ArrayList<InputValues> results = new ArrayList<InputValues>();
	public static final ArrayList<String> inputErrors = new ArrayList<String>();

	public static void main(String[] args) {
		System.out.println("Enter input data");
		String currentText;

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (!(currentText = input.readLine()).isEmpty()) {
				parceInputDataString(currentText);
			}

			while (!(currentText = input.readLine()).isEmpty()) {
				parceInputQuestionString(currentText);
			}

			input.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (inputErrors.size() > 0) {
			System.out.println("input errors: ");
			for (String s : inputErrors) {
				System.out.println(s);
			}
		}

		System.out.println("results: ");
		for (InputValues v : results) {
			if (v.getDimensionLeft().equals(v.getDimensionRight())) {
				v.setMultiplierRight(v.getMultiplierLeft());
				System.out.println(v.toString());
			} else {
				double mult = ConvertRules.searchMultiplier(rules, v.getDimensionLeft(), v.getDimensionRight());
				if (Double.compare(mult, -1) == 0) {
					System.out.println(ConvertAppConstants.CONVERTATION_NOT_POSSIBLE);
				} else {
					v.setMultiplierRight(v.getMultiplierLeft() * mult);
					System.out.println(v.toString());
				}
			}
		}
	}

	private static void parceInputDataString(String currentText) {

		InputDataParser parse = InputDataParser.parseInputString(currentText, false);

		if (parse.isCorrect()) {
			// straight
			insertInitRules(currentText, parse.getInputValues().getDimensionLeft(),
					parse.getInputValues().getDimensionRight(),
					parse.getInputValues().getMultiplierRight() / parse.getInputValues().getMultiplierLeft());
			// reverse
			insertInitRules(currentText, parse.getInputValues().getDimensionRight(),
					parse.getInputValues().getDimensionLeft(),
					parse.getInputValues().getMultiplierLeft() / parse.getInputValues().getMultiplierRight());

		} else {
			inputErrors.add(parse.getMessage());
		}
	}

	private static void insertInitRules(String input, String keyLeft, String keyRight, double mult) {
		InputDataCheck dataCheck = ConvertRules.checkKey(keyLeft, keyRight, mult);

		if (!dataCheck.isKeyLeftExists()) {
			ConvertRules.insertRule(1, keyLeft, keyRight, mult);
		} else if (!dataCheck.isKeyRightExists()) {
			ConvertRules.insertRule(2, keyLeft, keyRight, mult);
		} else if (!dataCheck.isMultipliersEquals()) {
			inputErrors.add(input + dataCheck.getMessage());
		}
	}

	private static void parceInputQuestionString(String currentText) {

		InputDataParser parse = InputDataParser.parseInputString(currentText, true);

		if (parse.isCorrect() && parse.isQuestion()) {
			results.add(parse.getInputValues());

		} else {
			inputErrors.add(parse.getMessage());
		}
	}

}
