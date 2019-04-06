package file;

import java.io.File;

/**
 * 
 * Osnovne operacije sa fajlovima
 *
 */

public interface FileOperations {
	/**
	 * 
	 * @param file Fajl koji zelimo da napravimo
	 * @param path Putanja na koju zelimo da postavimo fajl
	 */
	void upoadFile (File file, String path);
	
	/**
	 * 
	 * @param file Fajl koji zelimo da skinemo
	 * @param path Putanja na kojoj zelimo da se fajl skine
	 */
	void downloadFile (File file, String path);
}
