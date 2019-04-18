package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxRequestConfig.Builder;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

public class Main {
	public static final String ACCESS_TOKEN = "lBp-cvErnmAAAAAAAAAAHWXnaRxQb2aEl2mnZ7x4ddUydLxU1fhkbChd0DLHrZ5i";
	public static final String BASE_DIR = (System.getProperty("user.home") + 
			System.getProperty("file.separator") +
			"Desktop" + 
			System.getProperty("file.separator") +
			"Test");
	
	public static void main(String[] args) throws DbxException, IOException {
		System.out.println("radi");
		
			//	povezuje se sa dropboxom
			//DbxRequestConfig config = new DbxRequestConfig.newBuilder("dropbox/softverKomponente").build();
			DbxRequestConfig config = new DbxRequestConfig("dropbox/softverKomponente");
			DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
			
			//	pravi novi profil
			FullAccount profil = client.users().getCurrentAccount();
			System.out.println(profil.getName().getDisplayName());
			
			//	uzima metadatu od foldera i fajlova
			ListFolderResult listFolder = client.files().listFolder("");
			while (true) {
				for(Metadata meta : listFolder.getEntries()) {
					System.out.println(meta.getPathLower());
				}
				
				if (!listFolder.getHasMore()) {
					break;
				}
				
				listFolder = client.files().listFolderContinue(listFolder.getCursor());
			}
		//	upload
		/*try (InputStream input = new FileInputStream(BASE_DIR + "/test.txt")) {
			FileMetadata metadata = client.files().uploadBuilder(BASE_DIR + "/test.txt").uploadAndFinish(input);
		}*/
		
	}

}
