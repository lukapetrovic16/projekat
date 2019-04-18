package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.naming.InitialContext;
import org.apache.commons.io.FileUtils;

import com.dropbox.core.v2.DbxClientV2;

import spec.FileSpec;
import spec.FileSpecManager;


/**
 * 
 * @author Djordje
 * Omogucava lokalnu implementaciju, koristi interfejs {@link FileSpec}, koji ima u depencency-ju i koji sluzi sa rad sa fajlovima.
 * 
 *
 */
public class FileLocal implements FileSpec {
	/**
	 * 
	 */
	public static final String BASE_DIR = (System.getProperty("user.home") + 
			System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator") + "Test");
	private static FileLocal init;

	static {
		FileSpecManager.ubaciSpec(new FileLocal());
		/*
		try {
			init = new FileLocal();
			init.initialize();
		} catch (Exception e) {
			// TODO: handle exception
		}	*/	
	}
	
	public void initialize(){
		/*try {
			pokreniTest();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void terminate() {
		/*if(init != null) {
			init = null;
		}*/
	}
	
	@SuppressWarnings("unused")
	private void pokreniTest() throws FileNotFoundException, IOException {
		createFile(BASE_DIR, "t1.txt");
		System.out.println("Uspesan test.");
	}
	/**
	 * Upload file funkcija omogucava da se zeljeni fajl postavi na neku lokaciju u lokalnom skladistu.
	 * @param path Putanja do fajla koji se nalazi u skladistu
	 * @param newLocation Nova putanja na lokalnom repozitorijumu na koju zelimo da postavimo fajl.
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void uploadFile(String path, String newLocation) throws NoSuchFileException, FileNotFoundException, IOException {
		/**
		 *  Pomocu Commons IO prebacujemo fajl na novu destinaciju,
		 *  ukoliko destinacija ne postoji napravice je.
		 */
		String ekstenzija = path.substring(path.lastIndexOf("."));
		System.out.println(ekstenzija);
		if(!bannedExtensions.contains(ekstenzija)) {
			FileUtils.moveFileToDirectory(
					FileUtils.getFile(path), 
					FileUtils.getFile(newLocation),
					true);
		}else 
			throw new IllegalArgumentException();
		System.out.println("Upload file successful.");
	}
	/**
	 * Ova funkcija omogucava da se na zadatu putanju u lokalnom skladistu postavi vise fajlova
	 * @param files Niz fajlova koje zelimo da postavimo na lokalno skladiste
	 * @param newLocation Putanja do lokalnog skladista na kojoj zelimo da postavimo listu fajlova
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void uploadMultiFiles(String[] files, String newLocation) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		for(String file : files) {
			String ekstenzija = file.substring(file.lastIndexOf("."));
			if(!bannedExtensions.contains(ekstenzija)) {
				FileUtils.moveFileToDirectory(
						FileUtils.getFile(file), 
						FileUtils.getFile(newLocation),
						true);
			}else 
				throw new IllegalArgumentException();
		}
		System.out.println("Upload files successful.");
	}
	/**
	 * Ova funkcija omogucava da se sa zadate lokacije na lokalnom skladistu skine fajl na novoj zadatoj lokaciji
	 * @param path Putanja do fajla koji se nalazi na lokalnom skladistu koji zelimo da skinemo
	 * @param storagePath Putanja na koju zelimo da uskladistimo taj isti fajl
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void downloadFile(String path, String storagePath) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		FileUtils.moveFileToDirectory(
				FileUtils.getFile(storagePath), 
				FileUtils.getFile(path),
				true);
		System.out.println("Download file successful.");
	}
	/**
	 * Ova funkcija omogucava da se sa zadatih vise lokacija na lokalnom skladistu skine vise fajlova.
	 * @param path Putanja na koju zelimo da uskladistimo sve fajlove sa skladista
	 * @param storagePath Lista putanja do fajlova koji se nalaze na lok. skladistu koje zelimo da skinemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void downloadMultiFile(String path, String[] storagePath) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		for(String file : storagePath) {
			FileUtils.moveFileToDirectory(
					FileUtils.getFile(file), 
					FileUtils.getFile(path),
					true);
		}
		System.out.println("Download files successful.");
	}
	/**
	 * Ova funkcija omogucava da se izbrise zeljeni fajl sa lokalnog skladista
	 * @param path Putanja do fajla koji zelimo da izbrisemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void deleteFile(String path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		new File(path).delete();
		System.out.println("Delete file successful.");
	}
	/**
	 * Ova funkcija omogucava da se izbrise vise fajlova sa lokalnog skladista
	 * @param path Lista putanja do fajlova koje zelimo da izbrisemo
	 * @throws NoSuchFileException Ovaj exception se javlja kada se pokusa da se postavi fajl koji ne postoji na lokaciji u zadatom skladistu.
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void deleteMultiFiles(String[] path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		/** 
		 * Imamo listu fajlova koje brisemo, prodjemo po njoj i pobrisemo ih jedan po jedan
		 * Ima iste exceptione kao i deleteFile
		 */
		for(String file : path) {
			new File(file).delete();	
		}
		System.out.println("Delete files successful.");
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
	public void renameFile(String path, String newName) throws NoSuchFileException, FileNotFoundException, IOException {

		File file1 = new File(path);
		File file2 = new File(path.substring(0, path.lastIndexOf(System.getProperty("file.separator"))) + 
				System.getProperty("file.separator") + newName);
		
		// System.out.println(file1 + "-" + file2);
		
		boolean success = file1.renameTo(file2);

		if (!success) {
		   // File was not successfully renamed
			System.out.println("Rename file WAS NOT successful.");
		}else {
			System.out.println("Rename file successful.");
		}
	}
	/**
	 * Ova funkcija omogucava lokalno skladiste
	 * @param path Putanja na kojoj zelimo da kreiramo novo skladiste
	 * @param name Naziv koji dajemo zadatom skladistu pre nego sto ga kreiramo
	 * 
	 */
	public void createStorage(String path, String name) {
		// TODO Auto-generated method stub
		// Kreira novi folder sa datim imenom na datoj putanji.
		File newFolder = new File(path, name);
		newFolder.mkdir();  // mkdir ili mkdirs, not sure yet koji zelim
		if(newFolder.isDirectory()) {
			System.out.println("Create storage successful.");
		}
	}
	/**
	 * Ova funkcija omogucava da se kreira novi fajl na zadatom skladistu
	 * @param path Putanja na kojoj cemo kreirati zeljeni fajl u skladistu
	 * @param name Ime koje dodeljujemo fajlu koji kreiramo
	 * @throws IOException Signalizira da se desio neki I/O exception
	 * @throws FileNotFoundException Ovaj exception se javlja ukoliko je pronalazenje fajla na zadatoj lokaciji nemoguce ili ukoliko postoji a ne moze da se pristupi istom.
	 * 
	 */
	public void createFile(String path, String name) throws FileNotFoundException, IOException {
		File newFile = new File(path, name);
		newFile.createNewFile();
		if(newFile.isFile()){
			System.out.println("Create file successful.");
		}
	}
	/**
	 * Ova funkcija omogucava da pronadjemo elemente u lokalnom skladistu
	 * @param path Putanja na kome se pretrazuje fajl
	 * @param filter Filter po kome se pretrazuju fajlovi
	 * 
	 */
	public void find(String path, FilenameFilter filter) {
		File directory = new File(path);
		if(directory.isDirectory()) {
			try {
				directory.listFiles(filter);
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("Nije pronadjen zadati fajl.");
			}
		}
		System.out.println("Find file successful.");
	}
	
	ArrayList<String> bannedExtensions = new ArrayList<String>();
	public void excludeExtension(String extension) {
		if(!bannedExtensions.contains(extension)) {
			bannedExtensions.add(extension);
		}
		System.out.println("Banned extensions successful.");
	}
	/**
	 * Ova funkcija zabranjuje koriscenje odredjenih ekstenzija
	 * @param extension Ekstenzija koju smo naveli kao neprihvatajucu
	 *
	 */
	public void zipFile(File file, File newLocation) throws FileNotFoundException, IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newLocation));
        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        FileInputStream fileInputStream = new FileInputStream(file);
        
        byte[] buffer = new byte[1024];
        
        int bytesRead;
        
        while ((bytesRead = fileInputStream.read(buffer)) > 0)
            zipOutputStream.write(buffer, 0, bytesRead);

        fileInputStream.close();
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        System.out.println("Zip file successful.");
	}
	/**
	 * Ova funkcija omogucava da se zipuje zadati fajl
	 * @param file Fajl koji biramo da bude zipovan
	 * @param newLocation Nova lokacija na koju zelimo da sacuvamo zipovani fajl
	 * @throws IOException Signalizira da se desio neki I/O exception
	 *
	 */
	public void zipMultiFiles(File[] sourceFile, File newLocation) throws FileNotFoundException, IOException{
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newLocation));
        for(File file : sourceFile) {
        	
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
	        FileInputStream fileInputStream = new FileInputStream(file);
	        
	        byte[] buffer = new byte[1024];
	        
	        int bytesRead;
	        
	        while ((bytesRead = fileInputStream.read(buffer)) > 0)
	            zipOutputStream.write(buffer, 0, bytesRead);
	        
	        fileInputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        System.out.println("Zip files successful.");
	}
	/**
	 * Ova funkcija omogucava da se zipuje veci broj fajlova
	 * @param sourceFile Svi fajlovi koji se zipuju
	 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	public void zipDirectory(File file, File newLocation) throws FileNotFoundException, IOException{
		
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newLocation));
		zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        FileInputStream fileInputStream = new FileInputStream(file);
    
        zipFileForDir(file, file.getName(), zipOutputStream);
        
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        fileInputStream.close(); 
        System.out.println("Zip directory successful.");
	}
	/**
	 * Omogucava arhiviranje direktorijuma u .zip format.
	 * 
	 * @param file Direktorijum koji se zipuje
	 * @param newLocation Putanja ka direktorijumu u koji zipujemo sa sve nazivom zipa.
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	private static void zipFileForDir(File fileToZip, String fileName, ZipOutputStream zipOut) throws FileNotFoundException, IOException {
		if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
        	if (fileName.endsWith("/")) {
        		zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFileForDir(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
	}
	/**
	 * Omogucava ekstraktovanje fajlova iz .zip formata.
	 * 
	 * @param file Zip fajl koji se unzipuje.
	 * @param newLocation Putanja ka direktorijumu u koji unzipujemo
	 * @throws IOException Hvata exceptione u vezi I/O.
	 * @throws FileNotFoundException Signalizira da je fajl sa datom adresom nije pronadjen.
	 */
	public void unZip(File file, File newLocation) throws FileNotFoundException, IOException {

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFileForZip(newLocation, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        System.out.println("Unzip file successful.");
	}
	private static File newFileForZip(File destinationDir, ZipEntry zipEntry) throws FileNotFoundException, IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }    
        
        return destFile;
    }

	public void uploadFile(String dropPath, File file, DbxClientV2 client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void downloadFile(String path, DbxClientV2 client, String storagePath)
			throws NoSuchFileException, IOException {
		// TODO Auto-generated method stub
		
	}

}
