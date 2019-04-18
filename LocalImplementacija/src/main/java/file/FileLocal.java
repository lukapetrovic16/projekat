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
import spec.FileSpec;
import spec.FileSpecManager;


/**
 * 
 * @author Djordje
 * Omogucava lokalnu implementaciju, koristi interfejs {@link FileSpec}, koji ima u depencency-ju i koji sluzi sa rad sa fajlovima.
 * 
 *
 */
public class FileLocal implements FileSpec{
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
	 * Implementacija funkcije za postavljanje zadatih fajlova na zadate lokacije u lokalnom skladistu.
	 * @param path Putanja u lokalnom skladistu na koju zelimo da otpremimo fajl
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

	public void downloadFile(String path, String storagePath) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		FileUtils.moveFileToDirectory(
				FileUtils.getFile(storagePath), 
				FileUtils.getFile(path),
				true);
		System.out.println("Download file successful.");
	}

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
	
	public void deleteFile(String path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		new File(path).delete();
		System.out.println("Delete file successful.");
	}

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
	
	public void createStorage(String path, String name) {
		// TODO Auto-generated method stub
		// Kreira novi folder sa datim imenom na datoj putanji.
		File newFolder = new File(path, name);
		newFolder.mkdir();  // mkdir ili mkdirs, not sure yet koji zelim
		if(newFolder.isDirectory()) {
			System.out.println("Create storage successful.");
		}
	}
	
	public void createFile(String path, String name) throws FileNotFoundException, IOException {
		File newFile = new File(path, name);
		newFile.createNewFile();
		if(newFile.isFile()){
			System.out.println("Create file successful.");
		}
	}
	
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
}
