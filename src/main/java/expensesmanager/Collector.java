package expensesmanager;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Collector implements ManagerInterface, Serializable {
	/**
	 * Expenses Limit
	 */
	private Map<String, Double> expensesLimits = new HashMap<String, Double>();
	/**
	 * Serial Version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Expenses List
	 */
	List<Expense> expenses = new ArrayList<Expense>();
	public static final String MONTH_LIMIT = "month";
	public static final String YEAR_LIMIT = "year";
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	@Override
	public void addExpense(Expense expense) {
		// TODO Auto-generated method stub
		expenses.add(expense);
		//LOGGER.info("Add new expense.");
	}

	@Override
	public List<Expense> getExpenses() {
		return expenses;
	}

	@Override
	public List<Expense> filterExpensesByDate(String dateInput) throws IllegalArgumentException {
		// No filter arguments
		if (dateInput.length() == 0) {
			return getExpenses();
		}
		// Filter by date
		Date date = null;
		DateFormat df = null;
		List<Expense> filteredExpenses = new ArrayList<Expense>();
		try {
			String format = "dd-mm-yyyy";
			if (dateInput.length() == 7) {
				format = "mm-yyyy";
			} else if (dateInput.length() == 4) {
				format = "yyyy";
			}
			df = new SimpleDateFormat(format);
			date = df.parse(dateInput);
		} catch (ParseException e1) {
			throw new IllegalArgumentException("Invalid date, expected format: yyyy or mm-yyyy or dd-mm-yyyy.");
		}
		for (Expense expense : expenses) {
			if (df.format(expense.getDate()).equals(df.format(date))) {
				filteredExpenses.add(expense);
			}
		}
		return filteredExpenses;
	}

	/**
	 * Order desc the expenses by value
	 * 
	 * @param String
	 * @return List<Expense>
	 */
	public List<Expense> OrderExpensesByValue(String dateInput, ExpenseType type) throws IllegalArgumentException {
		List<Expense> orderedExpensesByValue = null;
		if (type == null) {
			orderedExpensesByValue = filterExpensesByDate(dateInput);
		} else {
			orderedExpensesByValue = filterExpensesByDateAndType(dateInput, type);
		}
		Collections.sort(orderedExpensesByValue, new Comparator<Expense>() {
			@Override
			public int compare(Expense o1, Expense o2) {
				if (o1.getValue() >= o2.getValue()) {
					return -1;
				}
				return 1;
			}
		});
		return orderedExpensesByValue;
	}

	@Override
	public List<Expense> filterExpensesByDateAndType(String dateInput, ExpenseType type) {
		// Filter by date
		Date date = null;
		DateFormat df = null;
		List<Expense> filteredExpenses = new ArrayList<Expense>();
		boolean filterByDate = false;
		if (dateInput.length() > 0) {
			filterByDate = true;
			try {
				String format = "dd-mm-yyyy";
				if (dateInput.length() == 7) {
					format = "mm-yyyy";
				} else if (dateInput.length() == 4) {
					format = "yyyy";
				}
				df = new SimpleDateFormat(format);
				date = df.parse(dateInput);
			} catch (ParseException e1) {
				throw new IllegalArgumentException("Invalid date, expected format: yyyy or mm-yyyy or dd-mm-yyyy.");
			}
		}
		for (Expense expense : expenses) {
			if (expense.getType() == type && (!filterByDate || df.format(expense.getDate()).equals(df.format(date)))) {
				filteredExpenses.add(expense);
			}
		}
		return filteredExpenses;
	}

	/**
	 * Set expenses limit
	 * 
	 * @param double
	 *            value
	 * @param ExpenseType
	 *            type
	 */
	public void setExpensesLimit(double value, String type) {
		this.expensesLimits.put(type, value);
	}

	/**
	 * Get expenses limit
	 * 
	 * @param double
	 *            value
	 * @param ExpenseType
	 *            type
	 */
	public double getExpensesLimit(String type) {
		double limit = 0;
		if (expensesLimits.size() > 0 && expensesLimits.containsKey(type)) {
			limit = expensesLimits.get(type);
		}
		return limit;
	}

	/**
	 * Check if the budget overrun 
	 * @param Expense expense
	 * @param String limitKey
	 * @return boolean
	 */
	public boolean isExpenseOverLimit(Expense expense, String limitKey) throws IllegalArgumentException{
		Date date = expense.getDate();
		double totalValue = 0;
		Format formatter = null;
		Double limitValue = 0d;
		if(limitKey == MONTH_LIMIT){
			limitValue = expensesLimits.get(MONTH_LIMIT);
			if(limitValue != null && limitValue > 0){
				formatter = new SimpleDateFormat("mm-yyyy");
				totalValue = getExpensesTotalValue(formatter.format(date));
				if(totalValue > 0 && totalValue > limitValue){
					return true;
				}
			}else if(limitKey == YEAR_LIMIT){
				expensesLimits.get(MONTH_LIMIT);
				if(limitValue != null && limitValue > 0){
					formatter = new SimpleDateFormat("yyyy");
					totalValue = getExpensesTotalValue(formatter.format(date));
					if(totalValue > 0 && totalValue > limitValue){
						return true;
					}
				}
			}else{
				throw new IllegalArgumentException("Invalid date, expected format: yyyy or mm-yyyy or dd-mm-yyyy.");
			}
		}
		return false;
	}

	/**
	 * Get expenses total value for a date input, accepted formats: yyyy and
	 * mm-yyyy
	 * 
	 * @param dateInput
	 * @return double
	 * @throws IllegalArgumentException
	 */
	public double getExpensesTotalValue(String dateInput) throws IllegalArgumentException {
		double totalValue = 0;
		Date fromDate = null;
		DateFormat df = null;
		try {
			String format = "yyyy";
			if (dateInput.length() == 7) {
				format = "mm-yyyy";
			}
			df = new SimpleDateFormat(format);
			fromDate = df.parse(dateInput);
		} catch (ParseException e1) {
			throw new IllegalArgumentException("Invalid date, expected format: yyyy or mm-yyyy.");
		}
		List<Expense> filteredExpenses = filterExpensesByDate(dateInput);
		for (Expense expense : filteredExpenses) {
			totalValue = totalValue + expense.getValue(fromDate);
		}
		return totalValue;
	}

	@Override
	public double getForecastExpenses(String year, int addPercent) throws IllegalArgumentException {
		double totalValue = 0;
		Date dateBegin = null;
		Date dateEnd = null;
		DateFormat df = null;
		String oneYearAgo = null;
		try {
			oneYearAgo = Integer.toString((Integer.parseInt(year) - 1));
			String format = "yyyy";
			df = new SimpleDateFormat(format);
			dateBegin = df.parse(oneYearAgo);
			dateEnd = df.parse(year);
		} catch (ParseException e1) {
			throw new IllegalArgumentException("Invalid date, expected format: yyyy");
		}
		System.out.println("Expenses Filter " + oneYearAgo);
		List<Expense> filteredExpenses = filterExpensesByDate(oneYearAgo);
		for (Expense expense : filteredExpenses) {
			System.out.println("Expenses:" + expenses + " - " + expense.getValue(dateBegin, dateEnd));
			totalValue = totalValue + expense.getValue(dateBegin, dateEnd);
		}
		if (totalValue > 0 && addPercent > 0) {
			totalValue = totalValue * (1 + addPercent / 100);
		}
		return totalValue;
	}
}
