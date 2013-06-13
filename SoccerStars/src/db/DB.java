package db;

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DB {

	public static final String DB_FILE;
	
	private static final ObjectContainer db;

	public static String getDBPath(){
		String dbDir = System.getProperty("user.home")+ File.separator + "banco";
		return dbDir;
	}
	
	static{
		
		if (!new File(getDBPath()).exists())
			new File(getDBPath()).mkdirs();
		
		DB_FILE = getDBPath() + File.separator + "soccer.db";
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
//		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "/Users/torquatro/Documents/Desenvolvimento/teste.db");
	}

	public static ObjectContainer getDb() {
		if (db.ext().isClosed())
			db.ext().openSession();
		return db;
	}
	
	
}
