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

import org.apache.commons.io.FileUtils;
import spec.FileSpec;


/**
 * Omogucava lokalnu implementaciju, koristi interfejs iz ko-projekta specifikacije, kog ima u depencency.
 * ???????????? moram jos da ispisem ovde..
 * 
 * @author Djordje
 *
 */
public class FileLocal implements FileSpec{


	public void initialize() {
		
	}
	
	public void terminate() {
		
	}
	
	public void uploadFile(String path, String newLocation) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		/**
		 *  Pomocu Commons IO prebacujemo fajl na novu destinaciju,
		 *  ukoliko destinacija ne postoji napravice je.
		 */
		String ekstenzija = path.substring(path.lastIndexOf("."));
		if(!bannedExtensions.contains(ekstenzija)) {
			FileUtils.moveFileToDirectory(
					FileUtils.getFile(path), 
					FileUtils.getFile(newLocation),
					true);
		}else 
			throw new IllegalArgumentException();
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
	}

	public void downloadFile(String path, String storagePath) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		FileUtils.moveFileToDirectory(
				FileUtils.getFile(storagePath), 
				FileUtils.getFile(path),
				true);
	}

	public void downloadMultiFile(String path, String[] storagePath) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		for(String file : storagePath) {
			FileUtils.moveFileToDirectory(
					FileUtils.getFile(file), 
					FileUtils.getFile(path),
					true);
		}
	}
	
	public void deleteFile(String path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
			new File(path).delete();
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
	}
	
	public void renameFile(String path, String newName) throws NoSuchFileException, FileNotFoundException, IOException {
		FileUtils.moveFileToDirectory(
				FileUtils.getFile(path), 
				FileUtils.getFile(path.substring(0, path.lastIndexOf("/")) + newName),
				true);
	}
	
	public void createStorage(String path, String name) {
		// TODO Auto-generated method stub
		// Kreira novi folder sa datim imenom na datoj putanji.
		File newFolder = new File(path, name);
		newFolder.mkdir();  // mkdir ili mkdirs, not sure yet koji zelim
	}
	
	public void createFile(String path, String name) throws FileNotFoundException, IOException {
		File newFile = new File(path, name);
		newFile.createNewFile();
		if(newFile.isFile()){
			System.out.println("uspelo");
		}
	}
	
	public void find(String path, FilenameFilter filter) {
		File directory = new File(path);
		if(directory.isDirectory()) {
			try {
				directory.list(filter);
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("Nije pronadjen zadati fajl.");
			}
		}
	}
	
	ArrayList<String> bannedExtensions = new ArrayList<String>();
	public void excludeExtension(String extension) {
		if(!bannedExtensions.contains(extension)) {
			bannedExtensions.add(extension);
		}
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
	}
	
	public void zipDirectory(File file, File newLocation) throws FileNotFoundException, IOException{
		
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newLocation));
		zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        FileInputStream fileInputStream = new FileInputStream(file);
    
        zipFileForDir(file, file.getName(), zipOutputStream);
        
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        fileInputStream.close(); 
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
