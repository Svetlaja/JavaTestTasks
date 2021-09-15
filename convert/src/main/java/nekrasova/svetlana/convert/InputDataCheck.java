package nekrasova.svetlana.convert;

public class InputDataCheck {
	
	private boolean keyLeftExists;
	private boolean keyRightExists;
	private boolean multipliersEquals;
	private String message;
	
	public InputDataCheck(boolean keyLeftExists, boolean keyRightExists, boolean multipliersEquals, String message) {
		this.keyLeftExists = keyLeftExists;
		this.keyRightExists = keyRightExists;
		this.multipliersEquals = multipliersEquals;
		this.message = message;
	}
	public boolean isKeyLeftExists() {
		return keyLeftExists;
	}
	public boolean isKeyRightExists() {
		return keyRightExists;
	}
	public boolean isMultipliersEquals() {
		return multipliersEquals;
	}
	public String getMessage() {
		return message;
	}
	
	

}
