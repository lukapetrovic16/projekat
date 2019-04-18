package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import spec.FileSpec;
import spec.FileSpecManager;
/**
 * 
 * @author AMD 
 * Implementacija {@link FileSpec} interfejsa koji omogucava rad sa fajlovima. Koristice se dropbox kao remote storage.
 * 
 */
public class FileRemote implements FileSpec {
	
	static {
		FileSpecManager.ubaciSpec(new FileRemote());
		
	}
	

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Upload file funkcija omogucava da se zeljeni fajl postavi na dropbox skladiste odnosno remote storage.
	 * @param path Putanja do fajla koji se nalazi u skladistu
	 * @param newLocation Putanja do lokacije na dropboxu gde zelimo da postavimo fajl
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void uploadFile(String path, String newLocation) throws NoSuchFileException, IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se na zadatu putanju u dropboxu postavi vise fajlova
	 * @param files Niz fajlova koje zelimo da postavimo na dropbox
	 * @param newLocation Putanja do dropbox skladista na kojoj zelimo da postavimo listu fajlova
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void uploadMultiFiles(String[] files, String newLocation) throws NoSuchFileException, IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se sa zadate lokacije na dropboxu skine fajl na novoj zadatoj lokaciji
	 * @param path Putanja do fajla koji se nalazi na dropboxu koji zelimo da skinemo
	 * @param storagePath Putanja na koju zelimo da uskladistimo taj isti fajl
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void downloadFile(String path, String storagePath) throws NoSuchFileException, IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se sa zadatih vise lokacija na dropboxu skine vise fajlova.
	 * @param path Putanja na koju zelimo da uskladistimo sve fajlove sa skladista
	 * @param storagePath Lista putanja do fajlova koji se nalaze na dropboxu koje zelimo da skinemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void downloadMultiFile(String path, String[] storagePath)
			throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se izbrise zeljeni fajl sa dropboxa
	 * @param path Putanja do fajla koji zelimo da izbrisemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void deleteFile(String path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se izbrise vise fajlova sa dropboxa
	 * @param path Lista putanja do fajlova koje zelimo da izbrisemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void deleteMultiFiles(String[] path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se izmeni ime fajla u skladistu
	 * @param path Putanja do fajla koji zelimo da preimenujemo
	 * @param newName Novo ime koje dajemo fajlu
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void renameFile(String path, String newName) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava kreira skladiste na dropboxu
	 * @param path Putanja na kojoj zelimo da kreiramo novo skladiste
	 * @param name Naziv koji dajemo zadatom skladistu pre nego sto ga kreiramo
	 * 
	 */
	@Override
	public void createStorage(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se kreira novi fajl na zadatom skladistu
	 * @param path Putanja na kojoj cemo kreirati zeljeni fajl u skladistu
	 * @param name Ime koje dodeljujemo fajlu koji kreiramo
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	@Override
	public void createFile(String path, String name) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da pronadjemo elemente iz skladista
	 * @param path Putanja na kome se pretrazuje fajl
	 * @param filter Filter po kome se pretrazuju fajlovi
	 * 
	 */
	@Override
	public void find(String path, FilenameFilter filter) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija zabranjuje koriscenje odredjenih ekstenzija
	 * @param extension Ekstenzija koju smo naveli kao neprihvatajucu
	 *
	 */
	@Override
	public void excludeExtension(String extension) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se zipuje zadati fajl
	 * @param file Fajl koji biramo da bude zipovan
	 * @param newLocation Nova lokacija na koju zelimo da sacuvamo zipovani fajl
	 * @throws IOException Signalizira da se desio neki I/O exception
	 *
	 */
	@Override
	public void zipFile(File file, File newLocation) throws IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Ova funkcija omogucava da se zipuje veci broj fajlova
	 * @param sourceFile Svi fajlovi koji se zipuju
	 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	@Override
	public void zipMultiFiles(File[] sourceFile, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Omogucava arhiviranje direktorijuma u .zip format.
	 * 
	 * @param file Direktorijum koji se zipuje
	 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	@Override
	public void zipDirectory(File file, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Omogucava ekstraktovanje fajlova iz .zip formata.
	 * 
	 * @param file Zip fajl koji se unzipuje.
	 * @param newLocation Putanja ka direktorijumu u koji unzipujemo
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	@Override
	public void unZip(File file, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	
}
