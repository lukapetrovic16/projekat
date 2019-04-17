package spec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public interface FileSpec {
	/**
	 * Ubaciti find(search) , za exclude ekstenzija,  
	 * metode moraju biti univerzalne , da se koriste i za LOKAL i za REMOTE.
	 */
		/**
		 * Omogucava pokretanje programa.
		 */
		void initialize();
		/**
		 * Omogucava gasenje programa.
		 */
		void terminate();
		/**
		 * Omogucava postavljanje zadatog fajla na zadatu destinaciju. 
		 * 
		 * 
		 * @param path Fajl koji zelimo da postavimo
		 * @param newLocation Putanja na koju zelimo da postavimo fajl
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void uploadFile (String path, String newLocation) throws NoSuchFileException, IOException;
		//void uploadFile (String file, String path, String name, String extension);  // polimorfizam   overload, override
		/**
		 * Omogucava postavljanje zadatih fajlova na zadatu destinaciju. 
		 * 
		 * @param files Fajlovi koje zelimo da postavimo
		 * @param newLocation Putanja na koju zelimo da postavimo fajlove
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void uploadMultiFiles (String[] files, String newLocation) throws NoSuchFileException, IOException;
		/**
		 * Omogucava skidanje zadatog fajla na zadatu destinaciju. 
		 * 
		 * @param path Putanja na kojoj zelimo da se skine fajl
		 * @param storagePath Putanja na kojoj se nalazi fajl koji hocemo da skinemo
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void downloadFile (String path, String storagePath) throws NoSuchFileException, IOException;
		/**
		 * Omogucava skidanje zadatih fajlova na zadatu destinaciju. 
		 * 
		 * @param path Putanja na kojoj zelimo da se skinu fajlovi
		 * @param storagePath Putanje na kojima se nalaze fajlovi koje hocemo da skinemo
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void downloadMultiFile (String path, String[] storagePath) throws NoSuchFileException, FileNotFoundException, IOException;
		/**
		 * Omogucava brisanje zadatog fajla.
		 * 
		 * @param path Putanja na kojoj se nalazi fajl koji hocemo da izbrisemo
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void deleteFile (String path) throws NoSuchFileException, FileNotFoundException, IOException;
		/**
		 * Omogucava brisanje zadatih fajlova.
		 * 
		 * @param path Putanje na kojim se nalaze fajlovi koje hocemo da izbrisemo
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 */
		void deleteMultiFiles (String[] path) throws NoSuchFileException, FileNotFoundException, IOException;
		/**
		 * Omogucava promenu naziva fajla.
		 * 
		 * @param path Putanja do fajla kome menjamo naziv
		 * @param newName Novi naziv fajla
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
		 * 
		 */
		void renameFile (String path, String newName) throws NoSuchFileException, FileNotFoundException, IOException;
		/**
		 * Omogucava kreiranje novog skladista sa zadatim imenom na unesenom direktorijumu.
		 * 
		 * @param path Putanja do novog skladista
		 * @param name Naziv novog skladista
		 * 
		 */
		void createStorage (String path, String name);
		/**
		 * Omogucava kreiranje novog fajla sa zadatim imenom na unesenom direktorijumu.
		 * 
		 * @param path Putanja do direktorijuma gde pravimo fajl
		 * @param name Naziv novog fajla
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 */
		void createFile (String path, String name) throws FileNotFoundException, IOException;
		/**
		 * Omogucava pronalazenje fajla sa zadatim imenom.
		 * 
		 * @param path Path na kome ce da pretrazuje
		 * @param filter Fajl koji se trazi
		 */
		void find(String path, FilenameFilter filter);
		/**
		 * Zabranjuje koriscenje fajlova sa zadatom ekstenzijom.
		 * 
		 * @param extension Zadata ekstenzija koja treba biti zabranjena
		 */
		void excludeExtension(String extension);
		/**
		 * Omogucava arhiviranje jednog fajla u .zip format.
		 * 
		 * @param file Putanja ka fajlu koji zipujemo
		 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 */
		void zipFile (File file, File newLocation) throws IOException;
		/**
		 * Omogucava arhiviranje vise fajlova u .zip format.
		 * 
		 * @param sourceFile Svi fajlovi koji se zipuju
		 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 */
		void zipMultiFiles (File[] sourceFile, File newLocation) throws FileNotFoundException, IOException;
		/**
		 * Omogucava arhiviranje direktorijuma u .zip format.
		 * 
		 * @param file Direktorijum koji se zipuje
		 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 */
		void zipDirectory(File file, File newLocation) throws FileNotFoundException, IOException;
		/**
		 * Omogucava ekstraktovanje fajlova iz .zip formata.
		 * 
		 * @param file Zip fajl koji se unzipuje.
		 * @param newLocation Putanja ka direktorijumu u koji unzipujemo
		 * @throws IOException Hvata exceptione u vezi I/O.
		 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
		 */
		void unZip(File file, File newLocation) throws FileNotFoundException, IOException;
}
