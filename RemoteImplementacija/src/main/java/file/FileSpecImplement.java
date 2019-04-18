package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import spec.FileSpec;
/**
 * 
 * @author AMD 
 * Implementacija {@link FileSpec} interfejsa koji omogucava rad sa fajlovima. Koristice se dropbox kao remote storage.
 * 
 */
public class FileSpecImplement implements FileSpec {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadFile(String path, String newLocation) throws NoSuchFileException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadMultiFiles(String[] files, String newLocation) throws NoSuchFileException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadFile(String path, String storagePath) throws NoSuchFileException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadMultiFile(String path, String[] storagePath)
			throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMultiFiles(String[] path) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renameFile(String path, String newName) throws NoSuchFileException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createStorage(String path, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createFile(String path, String name) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(String path, FilenameFilter filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excludeExtension(String extension) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zipFile(File file, File newLocation) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zipMultiFiles(File[] sourceFile, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zipDirectory(File file, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unZip(File file, File newLocation) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	
}
