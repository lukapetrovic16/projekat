package main;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

public class Main {
	public static final String ACCESS_TOKEN = "lBp-cvErnmAAAAAAAAAAHWXnaRxQb2aEl2mnZ7x4ddUydLxU1fhkbChd0DLHrZ5i";
	public static void main(String[] args) {
		System.out.println("radi");
		
		try {
			//	povezuje se sa dropboxom
			DbxRequestConfig config = new DbxRequestConfig("dropbox/softverKomponente");
			DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
			
			//	pravi novi profil
			FullAccount profil = client.users().getCurrentAccount();
			System.out.println(profil.getName().getDisplayName());
			
			//	uzima metadatu od foldera i fajlova
			ListFolderResult listFolder = client.files().listFolder("");
			while (true) {
				for(Metadata meta : listFolder.getEntries()) {
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
