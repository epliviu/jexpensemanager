package expensesmanager;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;

public class DateHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DateHelper instance;
	protected DateHelper() {
	}
	/**
	 * Singleton instance
	 * @return
	 */
	public static DateHelper getInstance() {
		if (instance == null) {
			instance = new DateHelper();
		}
		return instance;
	}
	/**
	 * Get month occurences between interval
	 * @param begin
	 * @param end
	 * @return int months
	 */
	public int getMonthsInterval(Date begin, Date end){
		org.joda.time.LocalDate dateBegin = convertUtilDateToLocalDate(begin);
		org.joda.time.LocalDate dateEnd = convertUtilDateToLocalDate(end);
        Months months = Months.monthsBetween(dateBegin, dateEnd);
        return months.getMonths();
	}
	/**
	 * Get days between interval
	 * @param begin
	 * @param end
	 * @return int days
	 */
	public int getDaysInterval(Date begin, Date end){
		return Days.daysBetween(convertUtilDateToLocalDate(begin), convertUtilDateToLocalDate(end)).getDays();
	}
	/**
	 * Get weeks between interval
	 * @param begin
	 * @param end
	 * @return int weeks
	 */
	public int getWeeksInterval(Date begin, Date end){
		org.joda.time.LocalDate date1Weeks = convertUtilDateToLocalDate(begin);
		org.joda.time.LocalDate date2Weeks = convertUtilDateToLocalDate(end);
		Weeks weeks = Weeks.weeksBetween(date1Weeks, date2Weeks);
        return weeks.getWeeks();
	}
	/**
	 * Convert date to LocalDate
	 * @param date
	 * @return org.joda.time.LocalDate LocalDate
	 */
	public static org.joda.time.LocalDate convertUtilDateToLocalDate(Date date) {
        if(date==null) return null;
        DateTime dt = new DateTime(date);
        return dt.toLocalDate();
    }
}
