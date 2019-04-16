package spec;

import java.io.File;

public interface FileSpec {
	/**
	 * Ubaciti terminate, initialize, save, find(search) 
	 */

	
		/**
		 * Omogucava postavljanje zadatog fajla na zadatu destinaciju. 
		 * Exceptions:
		 * 
		 * @param file Fajl koji zelimo da postavimo
		 * @param path Putanja na koju zelimo da postavimo fajl
		 */
		void uploadFile (String file, String path);
		/**
		 * Omogucava postavljanje zadatih fajlova na zadatu destinaciju. 
		 * Exceptions:
		 * 
		 * @param files Fajlovi koji zelimo da postavimo
		 * @param path Putanja na koju zelimo da postavimo fajlove
		 */
		void uploadMultiFiles (String[] files, String path);
		/**
		 * Omogucava skidanje zadatog fajla na zadatu destinaciju. 
		 * Exceptions:
		 * 
		 * @param path Putanja na kojoj zelimo da se skine fajl
		 * @param storagePath Putanja na kojoj se nalazi fajl koji hocemo da skinemo
		 */
		void downloadFile (String path, String storagePath);
		/**
		 * Omogucava skidanje zadatih fajlova na zadatu destinaciju. 
		 * Exceptions:
		 * 
		 * @param path Putanja na kojoj zelimo da se skinu fajlovi
		 * @param storagePath Putanje na kojima se nalaze fajlovi koje hocemo da skinemo
		 */
		void downloadMultiFile (String path, String[] storagePath);
		/**
		 * Omogucava brisanje zadatog fajla.
		 * Exceptions:
		 * 
		 * @param path Putanja na kojoj se nalazi fajl koji hocemo da izbrisemo
		 */
		void deleteFile (String path);
		/**
		 * Omogucava brisanje zadatih fajlova.
		 * Exceptions:
		 * 
		 * @param path Putanje na kojim se nalaze fajlovi koje hocemo da izbrisemo
		 */
		void deleteMultiFiles (String[] path);
		/**
		 * Omogucava brisanje svih fajlova na nekom direktorijumu.
		 * Exceptions:
		 * 
		 * @param path Putanje na kojim se nalaze fajlovi koje hocemo da izbrisemo
		 */
		void deleteAllFiles (String path);
		/**
		 * Omogucava premestanje fajla na drugu putanju.
		 * Exceptions:
		 * 
		 * @param path Lokacija fajla koji pomeramo
		 * @param newLocation Nova lokacija fajla
		 */
		void moveFile (String path, String newLocation);
		/**
		 * Omogucava promenu naziva fajla.
		 * Exceptions:
		 * 
		 * @param path Putanja do fajla kome menjamo naziv
		 * @param newName Novi naziv fajla
		 * 
		 */
		void renameFile (String path, String newName);
		/**
		 * Omogucava premestanje skladista sa jedne na drugu destinaciju.
		 * Exceptions:
		 * 
		 * @param oldLocation Stara lokacija skladista
		 * @param newLocation Nova lokacija skladista
		 */
		
		void moveStorage (String oldLocation, String newLocation);
		/**
		 * Omogucava kreiranje novog skladista na datom direktorijumu.
		 * Exceptions:
		 * 
		 * @param path Putanja do novog skladista
		 */
		void createStorage (String path);
		/**
		 * Omogucava brisanje postojeceg skladista na datom direktorijumu.
		 * Exceptions: 
		 * 
		 * @param path Putanja do skladista koje zelimo da izbrisemo
		 */
		void deleteStorage (String path);
		/**
		 * Omogucava osvezavanje podataka skladista na datom direktorijumu.
		 * Exceptions:
		 * 
		 * @param path Putanja do skladista kog refreshujemo
		 */
		void updateStorage (String path);
		/**
		 * Omogucava promenu naziva novog skladista na datom direktorijumu.
		 * Exceptions:
		 * 
		 * @param path Putanja do skladista kome menjamo naziv
		 * @param newName Novi naziv skladista
		 */
		void renameStorage (String path, String newName);
		/**
		 * Omogucava arhiviranje fajlova u .zip formatu.
		 * Exceptions:
		 * 
		 * @param path Putanja ka fajlu koji zipujemo
		 * @param newLocation Putanja ka fajlu u koji zipujemo
		 */
		void zipFile (String path, String newLocation);
		/**
		 * Omogucava arhiviranje vise fajlova i foldera u .zip formatu.
		 * Exceptions:
		 * 
		 * @param path Putanja ka fajlu u koji zipujemo
		 * @param sourceFile Svi fajlovi koji se zipuju
		 */
		void zipMultiFiles (String path, String[] sourceFile);


}
