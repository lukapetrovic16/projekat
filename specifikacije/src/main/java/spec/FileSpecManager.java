package spec;

import java.util.ArrayList;

public class FileSpecManager {

	public static ArrayList<FileSpec> specovi = new ArrayList<>();
	
	public static void ubaciSpec(FileSpec fileSpec) {
		specovi.add(fileSpec);
	}
		
	public static FileSpec vracaSpec(String klasa) {
		for(FileSpec fs : specovi) {
			if(fs.getClass().getName().equals(klasa)){
				return fs;
			}
		}
		throw new IllegalArgumentException();
	}
}
