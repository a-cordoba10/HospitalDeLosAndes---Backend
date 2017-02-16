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


public class NotificacionDAO {

	public static JsonParser jsonParser = new JsonParser();
	public static Map<String, String> jsonMap = new HashMap<String, String>();
	public static Gson g = new Gson();


	public static Response addNotificacion(Notificacion notificacion) {

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
