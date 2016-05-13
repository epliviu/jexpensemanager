/**
 * 
 */
package expensesmanager;

/**
 * @author nicolicioiul
 *
 */
public enum ExpenseType {
	MONTHLY("Monthly"), WEEKLY("Weekly"), DAILY("Daily"), ONEOFF("OneOff");
	/**
	 * Play it with days
	 */
	private final String type;

	/**
	 * Constructor
	 * 
	 * @param string
	 *            type
	 */
	ExpenseType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	/**
	 * Get Enumtype from a string
	 * @param text
	 * @return
	 */
	public static ExpenseType fromString(String text) {
		if (text != null) {
			for (ExpenseType e : ExpenseType.values()) {
				if (text.equalsIgnoreCase(e.type)) {
					return e;
				}
			}
		}
		return null;
	}
}
