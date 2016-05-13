/**
 * 
 */
package expensesmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


/**
 * @author mco
 *
 */
public class Persistence {

	/**
	 * logger for this class
	 */
	public static final Logger LOGGER = Logger.getGlobal();
	
	private Path file = Paths.get("expenses.data");	

	public void saveCollector(Collector collector) {
		LOGGER.fine("started saving expenses " + collector + " to file " + file);
		try (OutputStream os = Files.newOutputStream(file); 
				ObjectOutputStream oos = new ObjectOutputStream(os)) {
			oos.writeObject(collector);
			LOGGER.info("expense saved: " + collector.getExpenses().size());
		} catch (IOException e) {
			LOGGER.warning("problem saving expense data " + e.getMessage());
		}
	}
	/**
	 * load Collector
	 * @return Collector
	 */
	public Collector loadCollector() {
		LOGGER.fine("started loading expenses from file " + file);
		Collector collector = null;
		if (Files.exists(file)) {
			try (InputStream is = Files.newInputStream(file); 
					ObjectInputStream ois = new ObjectInputStream(is)) {
				collector = (Collector) ois.readObject();
				LOGGER.info("expenses loaded: " + collector.getExpenses().size());
			} catch (IOException e) {
				LOGGER.warning("problem loading expenses data " + e.getMessage());
			} catch (ClassNotFoundException e) {
				LOGGER.warning("problem loading expenses data " + e.getMessage());
			}
		}
		if(collector == null){
			collector = new Collector();
		}
		return collector;
	}

}
