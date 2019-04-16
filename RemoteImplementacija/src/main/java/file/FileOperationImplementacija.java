package file;

import java.io.File;
/**
 * 
 * @author AMD Implementacija {@link FileOperations} interfejsa. Koristice se dropbox kao remote storage.
 * 
 */
public class FileOperationImplementacija implements FileOperations {
	/** Funkcija za postavljanje fajla na dropbox
	 * @param file Naziv fajla koji postavljamo na dropbox
	 * @param path Putanja do fajla koji se nalazi u dropbox-u
	 */
	public void upoadFile(File file, String path) {
		
	}
	/**
	 * Funkcija za skidanje fajlova sa dropboxa
	 * @param path Putanja do fajla koji zelimo da skinemo sa skladista
	 * @param storagePath Putanja na kojoj se nalazi fajl na dropbox-u
	 */
	public void downloadFile(String path, String storagePath) {
		
	}
	
}
