package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.dropbox.core.*;
import com.dropbox.core.util.IOUtil.ProgressListener;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;

import spec.FileSpec;
import spec.FileSpecManager;
/**
 * 
 * @author AMD 
 * Implementacija {@link FileSpec} interfejsa koji omogucava rad sa fajlovima. Koristice se dropbox kao remote storage.
 * 
 */
public class FileRemote implements FileSpec {
	private static void printProgress(long uploaded, long size) {
        System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
	}
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
	 * Omogucava postavljanje zeljenog fajla na remote storage odnosno dropbox.
	 * @param dropPath Na koju putanju u dropboxu se cuva fajl
	 * @param file Fajl koju se uploaduje
	 * @param client Korisnik na ciji se dropbox skladisti fajl
	 * @throws FileNotFoundException Javlja exception ukoliko fajl nije pronadjen ili mu se ne moze pristupiti.
	 * @throws IOException Obavestava ukoliko se desi exception oblika I/O.
	 */
	@Override
	public void uploadFile(String dropPath, File file, DbxClientV2 client) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		try (InputStream in = new FileInputStream(file)) {
            ProgressListener progressListener = l -> printProgress(l, file.length());

            FileMetadata metadata = client.files().uploadBuilder(dropPath)
                .withMode(WriteMode.ADD)
                .withClientModified(new Date(file.lastModified()))
                .uploadAndFinish(in, progressListener);

            System.out.println(metadata.toStringMultiline());
        } catch (UploadErrorException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
            System.exit(1);
        } catch (DbxException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Error reading from file \"" + file + "\": " + ex.getMessage());
            System.exit(1);
        }
		
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
	 * Omogucava skidanje zadatog fajla na zadatu destinaciju. 
	 * 
	 * @param path Putanja na kojoj zelimo da se skine fajl
	 * @param client Korisnik na ciji se dropbox skladisti fajl
	 * @param storagePath Putanja na kojoj se nalazi fajl koji hocemo da skinemo
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 * @throws NoSuchFileException Izbacuje exception ukoliko fajl koji zelimo da koristimo ne postoji.
	 */
	@Override
	public void downloadFile(String path, DbxClientV2 client, String storagePath)
			throws NoSuchFileException, IOException {
		// TODO Auto-generated method stub
		try
        {
            //output file for download --> storage location on local system to download file
            OutputStream downloadFile = new FileOutputStream(path);
            try
            {
            FileMetadata metadata = client.files().downloadBuilder(storagePath).download(downloadFile);
            }
            finally
            {
                downloadFile.close();
            }
        }
        //exception handled
        catch (DbxException e)
        {
            //error downloading file
            JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
        }
        catch (IOException e)
        {
            //error downloading file
            JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
        }
		
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
