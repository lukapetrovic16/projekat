package storage;
/**
 * 
 * @author AMD
 *	Specifikacija za osnovne funkcije manipulisanja skladista
 */
public interface StorageManipulation {
	/**
	 * 
	 * @param oldLocation Stara lokacija skladista
	 * @param newLocation Nova lokacija skladista
	 */
	void moveStorage (String oldLocation, String newLocation);

}
