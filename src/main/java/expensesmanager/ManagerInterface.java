package expensesmanager;

import java.util.List;

public interface ManagerInterface {
	/**
	 * add expense
	 * 
	 * @param expense
	 *            the expense
	 */
	void addExpense(Expense expense);
	List<Expense> getExpenses();
	/**
	 * Filter expensens by date 
	 * @param dateInput dd-mm-yyyy
	 * @return
	 */
	List<Expense> filterExpensesByDate(String dateInput);
	/**
	 * Filter expensens by date and type
	 * @param dateInput dd-mm-yyyy
	 * @return
	 */
	List<Expense> filterExpensesByDateAndType(String dateInput, ExpenseType type);
	/**
	 * Order expensens and filter by date and type
	 * @param dateInput dd-mm-yyyy
	 * @return
	 */
	List<Expense> OrderExpensesByValue(String dateInput, ExpenseType type);
	/**
	 * Set expenses limits
	 * @param double limit
	 * @param String type
	 * @return
	 */
	void setExpensesLimit(double value, String type);
	/**
	 * Get expenses limits
	 * @param type
	 * @return double
	 */
	double getExpensesLimit(String type);
	
	/**
	 * Check if an expense is over limit into a month
	 * @param expense
	 * @return boolean
	 */
	boolean isExpenseOverLimit(Expense expense, String LimitKey);
	/**
	 * Get expense forecast for one year
	 * @param year
	 * @param addPercent
	 * @return double
	 */
	double getForecastExpenses(String year, int addPercent);
	/**
	 * Get expenses values
	 * @param dateInput dd-mm-yyyy
	 * @return double
	 */
	double getExpensesTotalValue(String dateInput);
}
