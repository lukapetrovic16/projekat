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
	 * @param path Putanja na kojoj zelimo da se fajl skine
	 * @param storagePath Putanja na kojoj se nalazi fajl koji hocemo da skinemo
	 */
	void downloadFile (String path, String storagePath);
}
