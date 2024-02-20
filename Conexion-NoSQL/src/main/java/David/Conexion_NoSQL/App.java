package David.Conexion_NoSQL;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// URI de conexión a tu cluster remoto MongoDB
    	//String connectionString = "mongodb+srv://usuario:contraseña@tu-cluster.mongodb.net/tu_base_de_datos?retryWrites=true&w=majority";
        //String connectionString = "mongodb+srv://usuario:contraseña@tu-cluster.ankwzts.mongodb.net/?retryWrites=true&w=majority";
        String connectionString = "mongodb://localhost:27017";
        
        //Establecer conexión con el servidor MongoDB
        try (MongoClient mongoClient = MongoClients.create(new ConnectionString(connectionString))){
        	
        	//Establecer conexión con el servidor MongoDB LOCAL
        	// try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
        	
        	//Obtener la base de datos
        	MongoDatabase database = mongoClient.getDatabase("cluster0");
        	
        	//Obtener la colección
        	MongoCollection<Document> collection = database.getCollection("primeraColeccion");
        	
        	//Insertar un documento de ejemplo
        	/*Document document = new Document("nombre", "Ejemplo")
        			.append("edad", 30)
        			.append("ciudad", "EjemploCity");
        	collection.insertOne(document);*/

        	collection.updateOne(Filters.eq("nombre", "Ejemplo"), 
                    			Updates.combine(Updates.set("edad", 20), 
                    					Updates.set("ciudad", "ALBACETE"))); 
        	
        	//Consultar e imprimir todos los documentos en la colección
        	MongoCursor<Document> cursor = collection.find().iterator();
        	try {
        		while (cursor.hasNext()) {
        			System.out.println(cursor.next().toJson());
        		}
        	} finally {
        		cursor.close();
        	}
        }
    }
}
