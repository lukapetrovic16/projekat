package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import file.FileLocal;
import spec.FileSpec;
import spec.FileSpecManager;

public class App{
		
	public static final String BASE_DIR = (System.getProperty("user.home") + 
			System.getProperty("file.separator") +
			"Desktop" + 
			System.getProperty("file.separator") +
			"Test");
	public static final String TEST_DIR = "src/test/java";
		
	public static void main(String[] args) {
		
		System.out.println(FileLocal.class.getName());
		
		try {
			Class.forName(FileLocal.class.getName());
			FileSpec fs = FileSpecManager.vracaSpec(FileLocal.class.getName());
			
			try {
				
				fs.createFile(BASE_DIR, "primer.txt");
				fs.createStorage(BASE_DIR, "Dzo");
				String storage = BASE_DIR + System.getProperty("file.separator") + "Dzo";
				fs.createStorage(storage, "t1");
				fs.createStorage(storage, "t2");
				fs.createFile(storage + "/t1", "t1.txt");
				fs.createFile(storage + "/t2", "t2.txt");
				fs.renameFile(BASE_DIR + System.getProperty("file.separator") + "primer.txt",
						"novi_primer.txt");
				fs.uploadFile(BASE_DIR + System.getProperty("file.separator") + "novi_primer.txt",
						BASE_DIR + System.getProperty("file.separator") + "Dzo");
				fs.deleteFile(storage + "/t1/" + "t1.txt");
				fs.excludeExtension("csv");
				fs.createFile(BASE_DIR, "banned.csv");
				//fs.uploadFile(BASE_DIR + , newLocation);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		 FileLocal fl = new FileLocal();
		try {
			
			fl.createStorage(BASE_DIR, "Dzo");
			String storage = BASE_DIR + System.getProperty("file.separator") + "Dzo";
			fl.createStorage(storage, "t1");
			fl.createStorage(storage, "t2");
			fl.createFile(storage + "/t1", "t1.txt");
			fl.createFile(storage + "/t2", "t2.txt");
			fl.createFile(BASE_DIR, "t1.txt");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*System.out.println(System.getProperty("user.home") + 
				System.getProperty("file.separator") +
				"Desktop" + 
				System.getProperty("file.separator") +
				"Test");
		*/
	}
}
