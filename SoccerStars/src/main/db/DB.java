package main.db;

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DB {

	private static final String DB_FILE;
	
	private static final ObjectContainer db;

	static{
		String dbDir = System.getProperty("user.home")+ File.separator + "banco";
		if (!new File(dbDir).exists())
			new File(dbDir).mkdirs();
		
		DB_FILE = dbDir + File.separator + "soccer.db";
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
//		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "/Users/torquatro/Documents/Desenvolvimento/teste.db");
	}

	public static ObjectContainer getDb() {
		if (db.ext().isClosed())
			db.ext().openSession();
		return db;
	}
	
	
}
