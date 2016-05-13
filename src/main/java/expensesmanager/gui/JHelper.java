/**
 * 
 */
package expensesmanager.gui;

import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author nicolicioiul
 *
 */
public class JHelper {
	private static JHelper instance = null;

	protected JHelper() {
	}

	public static JHelper getInstance() {
		if (instance == null) {
			instance = new JHelper();
		}
		return instance;
	}

	/**
	 * Helper used to add a pair label + input size
	 * 
	 * @param panel
	 * @param gbc
	 * @param label
	 * @param inputSizeTextField
	 */
	public JTextField addPair(JPanel panel, GridBagConstraints gbc, String label, int inputSizeTextField) {
		// Add label
		JLabel JLabel = new JLabel(label, SwingConstants.CENTER);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridy++;
		gbc.gridx = 0;
		panel.add(JLabel, gbc);
		// Add input size
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx++;
		JTextField JTextfield = new JTextField(inputSizeTextField);
		panel.add(JTextfield, gbc);
		return JTextfield;
	}

	/**
	 * Helper used to add a pair of labels
	 * 
	 * @param panel
	 * @param gbc
	 * @param firstLabel
	 * @param secondLabel
	 */
	public void addPair(JPanel panel, GridBagConstraints gbc, String firstLabel, String secondLabel) {
		// Add first label
		JLabel JFirstLabel = new JLabel(firstLabel, SwingConstants.CENTER);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		panel.add(JFirstLabel, gbc);
		// Add second label
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx++;
		JLabel JSecondLabel = new JLabel(secondLabel, SwingConstants.CENTER);
		panel.add(JSecondLabel, gbc);
	}

	/**
	 * Helper used to add a pair of empty space and button
	 * 
	 * @param panel
	 * @param gbc
	 * @param buttonLabel
	 */
	public void addPair(JPanel panel, GridBagConstraints gbc, JButton jButton) {
		// Add button
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridy++;
		panel.add(jButton, gbc);
	}

	/**
	 * Helper used to add a pair of empty space and label
	 * 
	 * @param panel
	 * @param gbc
	 * @param JButton
	 */
	public JLabel addPair(JPanel panel, GridBagConstraints gbc, String label) {
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridy++;
		JLabel JLabel = new JLabel(label, SwingConstants.CENTER);
		panel.add(JLabel, gbc);
		return JLabel;
	}
	/**
	 * Helper used to add a pair label + input size
	 * 
	 * @param panel
	 * @param gbc
	 * @param label
	 * @param JCombobox
	 */
	public void addPair(JPanel panel, GridBagConstraints gbc, String label, JComboBox<?> select) {
		// Add label
		JLabel JLabel = new JLabel(label, SwingConstants.CENTER);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridy++;
		gbc.gridx = 0;
		panel.add(JLabel, gbc);
		// Add input size
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx++;

		panel.add(select, gbc);
	}
	/**
	 * Add table 
	 * @param panel
	 * @param gbc
	 * @param table
	 */
	public void addTable(JPanel panel, GridBagConstraints gbc, JTable table) {
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy++;
		gbc.gridx = 0;
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, gbc);
	}
	/**
	 * Return a string that can be used to input text
	 * @param d
	 * @return string
	 */
	public String prettyTextInput(double d) {
	  if(d == 0){
		  return "";
	  }
	  int i = (int) d;
	  return d == i ? String.valueOf(i) : String.valueOf(d);
	}

	/**
	 * Return a string that can be used to input text
	 * @param d
	 * @return string
	 */
	public String prettyTextInput(Date date) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("FF-mm-yyyy");
		return formatter.format(date);
	}
}
