/**
 * 
 */
package expensesmanager;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author nicolicioiul
 *
 */
public class Expense  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ExpenseType type;
	private double value;
	private Date date;
	/**
	 *  Date helper
	 */
	private DateHelper dateHelper;
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Return formated date
	 * @return
	 */
	public String getDateFormated() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("FF-mm-yyyy");
		return formatter.format(date);
	}
	/**
	 * Constructor for the object
	 * @param amount
	 * @param date
	 * @param type
	 */
	public Expense(String name, double value, String Inputdate, ExpenseType type) throws IllegalArgumentException{
		super();
		if(value <= 0){
			throw new IllegalArgumentException("Invalid value.");
		}
		try {
			DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
			date = df.parse(Inputdate);
		} catch (ParseException e) {
			LOGGER.info("Fail add new expense, invalid date format.");
			throw new IllegalArgumentException("Invalid date, expected format: dd-mm-yyyy.");
		}
		this.dateHelper = DateHelper.getInstance();
		this.value = value;
		this.name = name;
		this.type = type;
	}
	/**
	 * Get expense value from date
	 * @param fromDate
	 */
	public double getValue(Date beginDate){
		Date endDate = new Date();
		return getValue(beginDate, endDate);
	}
	/**
	 * Get expense value from date
	 * @param fromDate
	 */
	public double getValue(Date beginDate, Date endDate){
		if(type.equals(ExpenseType.DAILY)){
			// Days between intervals
			int days = dateHelper.getDaysInterval(beginDate, endDate);
			LOGGER.info("Days between interval:"+beginDate+" and "+endDate +": " + days);
			if(days > 0){
				return days * getValue();
			}
		}else if(type.equals(ExpenseType.MONTHLY)){	
			int months = dateHelper.getMonthsInterval(beginDate, endDate);
			LOGGER.info("Months between interval:"+beginDate+" and "+endDate +": " + months);
	        if(months > 0){
	        	return months * getValue(); 
	        }
		}else if(type.equals(ExpenseType.WEEKLY)){
			int weeks = dateHelper.getWeeksInterval(beginDate, endDate);
			LOGGER.info("Weeks between interval:"+beginDate+" and "+endDate +": " + weeks);
	        if(weeks > 0){
	        	return weeks * value; 
	        }
		}else if(type.equals(ExpenseType.ONEOFF)){
			// Is between dates
			if(beginDate.compareTo(getDate()) * getDate().compareTo(endDate) > 0){
				LOGGER.info("Expense is between interval:"+beginDate+" and "+endDate+ ":" + getDate());
				return getValue();
			}else{
				LOGGER.info("Expense is NOT between interval:"+beginDate+" and "+endDate+ ":" + getDate());
			}
		}
		return 0f;
	}
}
