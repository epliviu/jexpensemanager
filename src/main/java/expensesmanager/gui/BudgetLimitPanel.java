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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import expensesmanager.ManagerInterface;

/**
 * @author nicolicioiul
 *
 */
@SuppressWarnings("serial")
public class BudgetLimitPanel extends JPanel{
	/***
	 * UI Helper
	 */
	public static JHelper JHELPER = null;
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();

	public BudgetLimitPanel(ManagerInterface managerInterface) {
		super();
		JHELPER = JHelper.getInstance();
		createBudgetLimitPanel(managerInterface);
	}
	/**
	 * create jpanel and add it
	 */
	private void createBudgetLimitPanel(ManagerInterface managerInterface) {
		LOGGER.info("Start create budget limit panel");
		JPanel jPanel = new JPanel();
		//Add label:  budget and limit
		jPanel.setLayout(new GridBagLayout());
		// Init 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Add labels and inputs
		JHELPER.addPair(jPanel, gbc, "Budget", "(see budget limit)");
		JTextField monthLimit = JHELPER.addPair(jPanel, gbc, "Month limit value", 10);
		monthLimit.setText(JHELPER.prettyTextInput(managerInterface.getExpensesLimit("month")));

		JTextField yearLimit = JHELPER.addPair(jPanel, gbc, "Year limit value", 10);
		yearLimit.setText(JHELPER.prettyTextInput(managerInterface.getExpensesLimit("year")));
		
		//Add button
		JButton addButton = new JButton("Set");
		JHELPER.addPair(jPanel, gbc,  addButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					Float monthLimitValue = Float.parseFloat(monthLimit.getText());
					managerInterface.setExpensesLimit(monthLimitValue, "month");				
				}catch(Exception e1){
					LOGGER.warning("Error set month budget limit: "+e1.getMessage());
					JOptionPane.showMessageDialog(jPanel, e1.getMessage());
				}
				try{
					Float yearLimitValue = Float.parseFloat(yearLimit.getText());
					managerInterface.setExpensesLimit(yearLimitValue, "year");
				}catch(Exception e2){
					LOGGER.warning("Error set year budget limit: "+e2.getMessage());
					JOptionPane.showMessageDialog(jPanel, e2.getMessage());
				}
			}
		});
		
		setLayout(new GridLayout(1, 1));
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(jPanel);
		LOGGER.info("End create budget limit panel");
	}
}
