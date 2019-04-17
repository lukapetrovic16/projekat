package file;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import spec.FileSpec;

public class File implements FileSpec{

	public void uploadFile(String file, String path) {
		// TODO Auto-generated method stub
		System.err.println("Ne mozes da uploadujes u lokalnoj implementaciji!");
	}

	public void uploadMultiFiles(String[] files, String path) {
		// TODO Auto-generated method stub
		System.err.println("Ne mozes da uploadujes u lokalnoj implementaciji!");
	}

	public void downloadFile(String path, String storagePath) {
		// TODO Auto-generated method stub
		System.err.println("Ne mozes da downloadujes u lokalnoj implementaciji!");
	}

	public void downloadMultiFile(String path, String[] storagePath) {
		// TODO Auto-generated method stub
		System.err.println("Ne mozes da downloadujes u lokalnoj implementaciji!");
	}

	public void deleteFile(String path) {
		// TODO Auto-generated method stub
		try {
			//Files.createFile(Paths.get("src/test/resources/fileToDelete.txt"));
			Path fileToDeletePath = Paths.get(path);
									//Paths.get("src/test/resources/fileToDelete.txt");
			Files.delete(fileToDeletePath);
		} catch (NoSuchFileException e) { 
			// Izbacuje exception ukoliko fajl koji zelimo da izbrisemo trenutno ne postoji.
			e.printStackTrace();
		} catch (DirectoryNotEmptyException e) {
			// Izbacuje exception ukoliko direkcija koju zelimo da izbrisemo nije prazna.
			e.printStackTrace();
		} catch (IOException e) {
			// Hvata exceptione u vezi I/O.
			e.printStackTrace();
		}
	}

	public void deleteMultiFiles(String[] path) {
		// TODO Auto-generated method stub
		/** 
		 * Imamo listu fajlova koje brisemo, prodjemo po njoj i pobrisemo ih jedan po jedan
		 * Ima iste exceptione kao i deleteFile
		 */
	}

	public void deleteAllFiles(String path) {
		// TODO Auto-generated method stub
		/** 
		 * Procitamo sve fajlove na datoj direkciji i upisemo ih u listu kao paths, onda
		 * imamo listu fajlova koje brisemo, prodjemo po njoj i pobrisemo ih jedan po jedan
		 * Ima iste exceptione kao i deleteFile
		 */
		
	}

	public void moveFile(String path, String newLocation) {
		// TODO Auto-generated method stub
		
	}

	public void renameFile(String path, String newName) {
		// TODO Auto-generated method stub
		
	}

	public void moveStorage(String oldLocation, String newLocation) {
		// TODO Auto-generated method stub
		
	}

	public void createStorage(String path) {
		// TODO Auto-generated method stub
		
	}

	public void deleteStorage(String path) {
		// TODO Auto-generated method stub
		
	}

	public void updateStorage(String path) {
		// TODO Auto-generated method stub
		
	}

	public void renameStorage(String path, String newName) {
		// TODO Auto-generated method stub
		
	}

	public void zipFile(String path, String newLocation) {
		// TODO Auto-generated method stub
		
	}

	public void zipMultiFiles(String path, String[] sourceFile) {
		// TODO Auto-generated method stub
		
	}

}
