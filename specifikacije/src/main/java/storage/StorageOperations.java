package storage;
/**
 * 
 * @author AMD
 *	Specifikacije za najvaznije operacije sa skladistem
 */
public interface StorageOperations {
	/**
	 * 
	 * @param path Putanja do skladista
	 */
	void createStorage (String path);
	/**
	 * 
	 * @param path Putanja do skladista
	 */
	void deleteStorage (String path);
	/**
	 * 
	 * @param path Putanja do skladista
	 */
	void updateStorage (String path);
}
