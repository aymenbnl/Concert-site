package DAO_MongoDb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {
	private static String server_url="mongodb://localhost:27017";
	
	private static String db_name="db_gestion_concerts";
	
	private static MongoClient mongoClient = null;
	
	private static MongoDatabase mongo_db=null;
	
	public static MongoDatabase getDatabase() throws MongoDAOException {
		try {
			if(mongo_db == null) {
				CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
				CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
				
				ConnectionString connectionString = new ConnectionString(server_url);
				mongoClient = MongoClients.create(connectionString);
				MongoDatabase database = mongoClient.getDatabase(db_name).withCodecRegistry(pojoCodecRegistry);
				System.out.println("Connexion à la bdd établie\n");
				mongo_db = database;
				return database;
			}
			
			return mongo_db;
		} catch (Exception e) {
			throw new MongoDAOException("Impossible de se connecter à la BDD ("+e.getMessage()+")");
		}
	}
	
	public static void close() {
		if(mongoClient!=null) {
			try {
				mongoClient.close();
			} catch (Exception e) {
			}
		}
	}
}
