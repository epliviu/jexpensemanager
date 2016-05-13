/**
 * 
 */
package expensesmanager.gui;
import expensesmanager.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author nicolicioiul
 *
 */
@SuppressWarnings("serial")
public class ManagerFrame extends JFrame{
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	
	public  ManagerFrame(final Collector collector){
		super();
		setTitle("Expenses Manager");
		setPreferredSize(new Dimension(1000, 600));
	    setMinimumSize(new Dimension(1000, 600));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// listeners that saves catalog on exit
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				LOGGER.fine("saving expenses " + collector);
				save(collector);
				LOGGER.info("window closed successfully and expenses saved");
			}

		});
		getContentPane().setLayout(new GridBagLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel addExpenses = new AddExpensePanel(collector);
		JPanel budgetLimit = new BudgetLimitPanel(collector);
		JPanel statistics = new StatisticsPanel(collector);
		JPanel forecast = new ForecastPanel(collector);
		JPanel expensesFilter = new ExpensesFilterPanel(collector);		
		//Here goes the interesting code
        getContentPane().add(addExpenses,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        getContentPane().add(statistics,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        getContentPane().add(budgetLimit,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        getContentPane().add(forecast,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        getContentPane().add(expensesFilter,  new GridBagConstraints(2, 0, 1, 2, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        
		pack();
	}
	/**
	 * Save the expenses collector
	 * @param collector
	 */
	private void save(Collector collector){
		LOGGER.info("Start save expenses");
		Persistence storage = new Persistence();
		storage.saveCollector(collector);
		LOGGER.info("End save expenses");
	}
}
