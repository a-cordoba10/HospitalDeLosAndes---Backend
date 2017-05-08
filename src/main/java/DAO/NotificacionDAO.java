package DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.mongodb.morphia.Datastore;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import DTO.Notificacion;
import Utilities.HospitalLosAlpesDB;
import Utilities.ResponseHospitalLosAlpes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;


public class NotificacionDAO {

	public static JsonParser jsonParser = new JsonParser();
	public static Map<String, String> jsonMap = new HashMap<String, String>();
	public static Gson g = new Gson();


	public static Response addNotificacion(Notificacion notificacion) {
            
            try {
                String httpsURL = "https://fcm.googleapis.com/fcm/send";
                
                String query = "{\n" +
                    "\"notification\":{\"body\": \""+ notificacion.getTipo()+"\",\n" +
                    "\"title\": \""+ notificacion.getMensaje()+" del paciente "+notificacion.getIdPaciente()+"\",\n" +
                    "	\"mound\":\"default\",\n" +
                    "\"priority\":\"high\"\n" +
                    "},\n" +
                    "\"data\": {\n" +
                    "\"id\":1\n" +
                    "},\n" +
                    "	\"to\":\""+HospitalLosAlpesDB.getDispositivo()+"\"\n" +"}";               
                
                URL myurl = new URL(httpsURL);
                HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
                con.setRequestMethod("POST");               
                con.setRequestProperty("Content-Type","application/json");
                con.setRequestProperty("Authorization", "key=AAAAd2Q6qoo:APA91bG1F1g9TCwXl_7FIRmdvLWBdHFL3MLuBl9qK6WMqB0ZMjKv9uCKPm8LTQKcWtC7bAqF5GJesxR4Ycbeed-IvEbVpaA0h41BOVbULT3giBwjOe7i7L5lXb8AJ6vukR3_SxgQq6fz");
                con.setDoOutput(true);
                con.setDoInput(true);
                
                DataOutputStream output = new DataOutputStream(con.getOutputStream());
                
                
                output.writeBytes(query);
                
                output.close();
                
                DataInputStream input = new DataInputStream( con.getInputStream() );
                
                for( int c = input.read(); c != -1; c = input.read() )
                    System.out.print( (char)c );
                input.close();
                
                System.out.println("Resp Code:"+con .getResponseCode());
                System.out.println("Resp Message:"+ con .getResponseMessage());
                
                
            } catch (Exception ex) {
                Logger.getLogger(NotificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Datastore datastore = HospitalLosAlpesDB.getDatastore();
                datastore.save(notificacion);
                return getAllNotificaciones();

	}


	private static Response getAllNotificaciones() {



		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		List<Notificacion> notificaciones = datastore.createQuery(Notificacion.class).asList();
		if (notificaciones  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Error fetching notificaciones");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(notificaciones, Response.Status.OK);
		}
	}








}
