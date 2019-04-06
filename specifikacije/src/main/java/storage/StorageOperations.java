package storage;
/**
 * 
 * @author AMD
 *	Specifikacije za osnovne operacije sa skladistem
 */
public interface StorageOperations {
	/**
	 * 
	 * @param oldPath Putanja do stare lokacije skladista
	 * @param newPath Putanja do nove lokacije skladista
	 */
	void moveStorage (String oldPath, String newPath);
}
