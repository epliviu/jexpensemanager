/**
 * 
 */
package expensesmanager.gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import expensesmanager.*;
/**
 * @author nicolicioiul
 *
 */
@SuppressWarnings("serial")
public class AddExpensePanel extends JPanel{
	/***
	 * UI Helper
	 */
	public static JHelper JHELPER = null;
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	public AddExpensePanel(ManagerInterface managerInterface) {
		super();
		JHELPER = JHelper.getInstance();
		createAddExpencePanel(managerInterface);
	}
	/**
	 * create jpanel and add it
	 */
	private void createAddExpencePanel(ManagerInterface managerInterface) {
		LOGGER.info("Start create add expenses panel");
		JPanel jPanel = new JPanel();
		//Add label:  expense and  new expense
		jPanel.setLayout(new GridBagLayout());
		// Init bag
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Add labels and inputs
		JHELPER.addPair(jPanel, gbc, "Expense", "(new expence)");
		JTextField jtfName = JHELPER.addPair(jPanel, gbc, "Name", 10);
		JTextField jtfValue =JHELPER.addPair(jPanel, gbc, "Value", 10);
		JComboBox<Object> jComboboxType = new JComboBox<Object>(ExpenseType.values());
		JHELPER.addPair(jPanel, gbc, "Type", jComboboxType);
		JTextField jtfDate = JHELPER.addPair(jPanel, gbc, "Date", 10);
			
		//Add button
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridy ++;
		
		JButton addButton = new JButton("Add");
		JHELPER.addPair(jPanel, gbc, addButton);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.warning("Add expense to collector: name:"+jtfName.getText()
				+" value: "+jtfValue.getText()+" type:"+jComboboxType.getSelectedItem());
				try {
					Expense expense = new Expense(jtfName.getText(),
							Double.parseDouble(jtfValue.getText()),
							jtfDate.getText(), 
							ExpenseType.fromString(jComboboxType.getSelectedItem().toString()));
					
					managerInterface.addExpense(expense);
					jtfValue.setText("");
					jtfName.setText("");
					jtfDate.setText("");
					jComboboxType.setSelectedIndex(0);
					JOptionPane.showMessageDialog(jPanel, "The expense has been added.");
					if(managerInterface.isExpenseOverLimit(expense, Collector.MONTH_LIMIT)){
						JOptionPane.showMessageDialog(jPanel, "Monthly budget overrun!");
						LOGGER.warning("Monthly budget overrun after adding expense");
					}
					if(managerInterface.isExpenseOverLimit(expense, Collector.YEAR_LIMIT)){
						JOptionPane.showMessageDialog(jPanel, "Yearly budget overrun!");
						LOGGER.warning("Yearly budget overrun after adding expense");
					}					
				} catch (IllegalArgumentException e1) {
					
					LOGGER.warning("Failed adding expense " + e1.getMessage());
					JOptionPane.showMessageDialog(jPanel, e1.getMessage());
				}
			}
		});
		
		setLayout(new GridLayout(1, 1));
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(jPanel);
		LOGGER.info("End create add expenses panel");
	}
}
