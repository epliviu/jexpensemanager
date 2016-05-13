/**
 * 
 */
package expensesmanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import expensesmanager.Expense;
import expensesmanager.ExpenseType;
import expensesmanager.ManagerInterface;

/**
 * @author nicolicioiul
 *
 */
@SuppressWarnings("serial")
public class ExpensesFilterPanel extends JPanel{
	/***
	 * UI Helper
	 */
	public static JHelper JHELPER = null;
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	/**
	 * Constructor
	 * @param managerInterface
	 */
	public ExpensesFilterPanel(ManagerInterface managerInterface) {
		super();
		JHELPER = JHelper.getInstance();
		createFilterExpensesPanel(managerInterface);
	}
	/**
	 * create jpanel and add it
	 */
	private void createFilterExpensesPanel(ManagerInterface managerInterface) {
		JPanel jPanel = new JPanel();
		//Add label:  budget and limit
		jPanel.setLayout(new GridBagLayout());
		// Init 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Add labels and inputs
		JHELPER.addPair(jPanel, gbc, "Expenses", "(filter expenses)");
		JComboBox<Object> JComboboxType = new JComboBox<Object>();
		JComboboxType.addItem("All");
		for(ExpenseType type : ExpenseType.values()){
			JComboboxType.addItem(type);
		}
		JHELPER.addPair(jPanel, gbc, "Type", JComboboxType);
		JTextField inputDate = JHELPER.addPair(jPanel, gbc, "Date", 10);
		JButton viewButton = new JButton("View");
		JHELPER.addPair(jPanel, gbc, viewButton);
		
		setLayout(new GridLayout(2, 1));
		
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(jPanel);
		// Draw expenses filter
		JPanel jTablePanel = new JPanel();
		JTable jtable = new JTable();
		try {
			displayExpenses(managerInterface, jtable, inputDate.getText(), JComboboxType.getSelectedItem().toString());
			jtable.setPreferredScrollableViewportSize(jtable.getPreferredSize());
			jtable.setFillsViewportHeight(true);
		} catch (IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(jPanel, e1.getMessage());
		}
		// Add view button filter
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					displayExpenses(managerInterface, jtable, inputDate.getText(), JComboboxType.getSelectedItem().toString());
				} catch (IllegalArgumentException e1) {
					LOGGER.warning("Error on display expsense filter table: "+e1.getMessage());
					JOptionPane.showMessageDialog(jPanel, e1.getMessage());
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane( jtable );
		jTablePanel.add( scrollPane, BorderLayout.CENTER );
		jTablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(jTablePanel);
	}
	/**
	 *  Display expenses
	 * @param managerFrame
	 * @param grades
	 */
	public void displayExpenses(ManagerInterface managerInterface, JTable jtable, String filterDate, String type) throws IllegalArgumentException{
		LOGGER.info("Start display expenses table on filter panel.");
		jtable.setPreferredScrollableViewportSize(jtable.getPreferredSize());
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		model.addColumn("Name");
		model.addColumn("Value");
		model.addColumn("Type");
		model.addColumn("Date");
		List<Expense> expenses = new ArrayList<Expense>();
		ExpenseType selectedType = ExpenseType.fromString(type);
		if(selectedType != null){
			expenses = managerInterface.filterExpensesByDateAndType(filterDate, selectedType);
		}else{
			expenses = managerInterface.filterExpensesByDate(filterDate);
		}
		for (Expense expense : expenses) {
			Object[] rowData = new Object[] { expense.getName(), expense.getValue(), expense.getType(), expense.getDateFormated() };
			model.addRow(rowData);
		}
		
		model.fireTableStructureChanged();
		LOGGER.info("End display expenses table on filter panel.");
	}
	
}
