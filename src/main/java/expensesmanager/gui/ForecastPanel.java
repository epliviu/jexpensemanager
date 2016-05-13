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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import expensesmanager.ManagerInterface;
/**
 * @author nicolicioiul
 *
 */
@SuppressWarnings("serial")
public class ForecastPanel extends JPanel{
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
	 */
	public ForecastPanel(ManagerInterface managerInterface) {
		super();
		JHELPER = JHelper.getInstance();
		createForecastPanel(managerInterface);
	}
	/**
	 * create jpanel and add it
	 */
	private void createForecastPanel(ManagerInterface managerInterface) {
		LOGGER.info("Start create forecast panel.");
		JPanel jPanel = new JPanel();
		//Add labels
		jPanel.setLayout(new GridBagLayout());
		// Init 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Add labels and inputs
		JHELPER.addPair(jPanel, gbc, "Forecast", "(future expense)");
		JTextField inputDate = JHELPER.addPair(jPanel, gbc, "Date", 8);
		JButton viewButton = new JButton("View");
		JHELPER.addPair(jPanel, gbc, viewButton);
		JLabel forecastLabel = JHELPER.addPair(jPanel, gbc, "");	
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double value = managerInterface.getForecastExpenses(inputDate.getText(), 5);
					forecastLabel.setText("Forecast value:"+JHELPER.prettyTextInput(value));
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(jPanel, e1.getMessage());
				}
			}
		});
		setLayout(new GridLayout(1, 1));
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(jPanel);
		LOGGER.info("End create forecast panel.");
	}
}
