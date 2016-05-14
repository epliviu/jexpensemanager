package expensesmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
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
	/**
	 * Get recurrence years
	 * @param recurrenceDate end
	 * @param Date end
	 * @return ListDate
	 */
	public List<Date> reccurenceYearsDate(Date recurrenceDate, Date end) {
		List<Date> dates = new ArrayList<Date>();
		LocalDate localRecurrenceDate = convertUtilDateToLocalDate(recurrenceDate);
		LocalDate localEnd = convertUtilDateToLocalDate(end);
		for (LocalDate localDate = localRecurrenceDate; localRecurrenceDate.isBefore(localEnd); localDate = localDate.plusYears(1))		{
			dates.add(localDate.toDate());
		}
		return dates;
    }
	/**
	 * Get recurrence months
	 * @param recurrenceDate end
	 * @param Date end
	 * @return ListDate
	 */
	public List<Date> reccurenceMonths(Date recurrenceDate, Date end) {
		List<Date> dates = new ArrayList<Date>();
		LocalDate localRecurrenceDate = convertUtilDateToLocalDate(recurrenceDate);
		LocalDate localEnd = convertUtilDateToLocalDate(end);
		for (LocalDate localDate = localRecurrenceDate; localRecurrenceDate.isBefore(localEnd); localDate = localDate.plusMonths(1))		{
			dates.add(localDate.toDate());
		}
		return dates;
    }
	/**
	 * Get recurrence weeks
	 * @param recurrenceDate end
	 * @param Date end
	 * @return ListDate
	 */
	public List<Date> reccurenceWeeks(Date recurrenceDate, Date end) {
		List<Date> dates = new ArrayList<Date>();
		LocalDate localRecurrenceDate = convertUtilDateToLocalDate(recurrenceDate);
		LocalDate localEnd = convertUtilDateToLocalDate(end);
		for (LocalDate localDate = localRecurrenceDate; localRecurrenceDate.isBefore(localEnd); localDate = localDate.plusWeeks(1))		{
			dates.add(localDate.toDate());
		}
		return dates;
    }

	/**
	 * Get recurrence Days
	 * @param recurrenceDate end
	 * @param Date end
	 * @return ListDate
	 */
	public List<Date> reccurenceDays(Date recurrenceDate, Date end) {
		List<Date> dates = new ArrayList<Date>();
		LocalDate localRecurrenceDate = convertUtilDateToLocalDate(recurrenceDate);
		LocalDate localEnd = convertUtilDateToLocalDate(end);
		for (LocalDate localDate = localRecurrenceDate; localRecurrenceDate.isBefore(localEnd); localDate = localDate.plusDays(1))		{
			dates.add(localDate.toDate());
		}
		return dates;
    }
}
