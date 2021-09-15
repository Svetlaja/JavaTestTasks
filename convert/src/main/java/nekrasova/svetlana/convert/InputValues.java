package nekrasova.svetlana.convert;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class InputValues {
	private double multiplierLeft;
	private String dimensionLeft;
	private double multiplierRight;
	private String dimensionRight;

	public InputValues(double multiplierLeft, String dimensionLeft, double multiplierRight, String dimensionRight) {
		super();
		this.multiplierLeft = multiplierLeft;
		this.dimensionLeft = dimensionLeft;
		this.multiplierRight = multiplierRight;
		this.dimensionRight = dimensionRight;
	}

	public InputValues(double multiplierLeft, String dimensionLeft, String dimensionRight) {
		super();
		this.multiplierLeft = multiplierLeft;
		this.dimensionLeft = dimensionLeft;
		this.dimensionRight = dimensionRight;
	}
	
	

	public double getMultiplierLeft() {
		return multiplierLeft;
	}

	public String getDimensionLeft() {
		return dimensionLeft;
	}

	public double getMultiplierRight() {
		return multiplierRight;
	}
	
	

	public void setMultiplierRight(double multiplierRight) {
		this.multiplierRight = multiplierRight;
	}

	public String getDimensionRight() {
		return dimensionRight;
	}

	@Override
	public String toString() {
		
		NumberFormat formatter = new DecimalFormat();
		formatter.setGroupingUsed(false);
		
		return formatter.format(multiplierLeft) + " " + dimensionLeft + " = " + formatter.format(multiplierRight) + " " + dimensionRight;
	}

}
