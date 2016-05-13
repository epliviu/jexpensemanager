package expensesmanager.gui;


import java.util.logging.Logger;

import expensesmanager.Collector;
import expensesmanager.Persistence;

public class GUIApp {
	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	public static void main(String[] args) {
		// create the GUI and handle events in EDT
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Collector collector = new Persistence().loadCollector();
				ManagerFrame ManagerFrame = new ManagerFrame(collector);
				ManagerFrame.setVisible(true);
				LOGGER.info("GUI visible");
			}
		});
		LOGGER.info("GUIApp started");
	}

}
