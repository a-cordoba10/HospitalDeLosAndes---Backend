package Utilities;


import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class HospitalLosAlpesDB {
	

	private static String MONGO_URI = "mongodb://elcrack:masterdb@ds145329.mlab.com:45329/hospitallosaples"; 

	private static String MONGO__DB = "hospitallosaples";
	
	
	
	final static Morphia morphia = new Morphia();
	static Datastore datastore;
	static Boolean c = false;
	static Gson gson;
	
	
	public static Datastore getDatastore() {
		if(datastore == null) {
			morphia.mapPackage("DTO");
			MongoClientURI mouri = new MongoClientURI(MONGO_URI);
			datastore = morphia.createDatastore(new MongoClient(mouri), MONGO_DB);
		}
		
		datastore.ensureCaps();
		datastore.ensureIndexes();
		
		return datastore;
	}
	
	
	






















	//private static String MONGO_URI = "mongodb://elcrack:masterdb@ds145329.mlab.com:45329/hospitallosaples"; 
	private static String MONGO__URI = "mongodb://localhost:27017";
	private static String MONGO_DB = "hospitallosaples";

}
