package nekrasova.svetlana.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConvertRules {

	private final String left;
	private final Map<String, Double> rightSides;

	public ConvertRules(String left) {
		this.left = left;
		this.rightSides = new HashMap<String, Double>();
	}

	public String getLeft() {
		return left;
	}

	public Map<String, Double> getRightSides() {
		return rightSides;
	}

	public static InputDataCheck checkKey(String keyLeft, String keyRight, double mult) {
		for (ConvertRules con : ConvertationMain.rules) {
			if (con.getLeft().equals(keyLeft)) {
				if (con.getRightSides().containsKey(keyRight)) {
					if (Double.compare(con.getRightSides().get(keyRight), mult) == 0) {
						return new InputDataCheck(true, true, true, "");
					} else {
						return new InputDataCheck(true, true, false, ConvertAppConstants.INPUT_IS_CONFLICT);
					}
				}
				return new InputDataCheck(true, false, false, "");

			}
		}
		return new InputDataCheck(false, false, false, "");
	}

	public static void insertRule(int insertType, String keyLeft, String keyRight, double mult) {
		// 1 - новый объект
		// 2 - новая связка к существующему
		switch (insertType) {
		case 1:
			ConvertRules newRule = new ConvertRules(keyLeft);
			newRule.getRightSides().put(keyRight, mult);
			ConvertationMain.rules.add(newRule);
			break;
		case 2:
			for (ConvertRules con : ConvertationMain.rules) {
				if (con.getLeft().equals(keyLeft)) {
					con.getRightSides().put(keyRight, mult);
					break;
				}
			}
			break;

		default:
			break;
		}
		insertDependencies(keyLeft, keyRight, mult);
	}

	public static void insertDependencies(String keyLeft, String keyRight, double mult) {

		for (ConvertRules con : ConvertationMain.rules) {
			if (!con.getLeft().equals(keyRight)) {
				if (con.getRightSides().containsKey(keyLeft) && !con.getRightSides().containsKey(keyRight)) {
					double newMult = con.getRightSides().get(keyLeft) * mult;
					con.getRightSides().put(keyRight, newMult);
					insertDependencies(con.getLeft(), keyRight, newMult);
//					insertDependencies(keyRight, con.getLeft(), 1 / newMult);
				}
			} else {
				for (ConvertRules ruleLeft : ConvertationMain.rules) {
					if (ruleLeft.getLeft().equals(keyLeft)) {
						for (Entry<String, Double> entry : con.getRightSides().entrySet()) {
							if (!ruleLeft.getRightSides().containsKey(entry.getKey())
									&& !ruleLeft.getLeft().equals(entry.getKey())) {
								ruleLeft.getRightSides().put(entry.getKey(), mult * entry.getValue());
							}
						}
					}
				}
			}
		}

	}

	public static double searchMultiplier(List<ConvertRules> data, String keyLeft, String keyRight) {
		for (ConvertRules con : data) {
			if (con.getLeft().equals(keyLeft) && con.getRightSides().containsKey(keyRight)) {
				return con.getRightSides().get(keyRight);
			}
		}
		return -1;
	}

}
