package DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import DTO.Consejo;
import DTO.Evento;
import DTO.Medico;
import DTO.Medico.TipoMedico;
import DTO.Paciente;
import DTO.Reporte;
import Utilities.HospitalLosAlpesDB;
import Utilities.ResponseHospitalLosAlpes;

public class PacienteDAO {

	public static JsonParser jsonParser = new JsonParser();
	public static Map<String, String> jsonMap = new HashMap<String, String>();
	public static Gson g = new Gson();



	public static Response getAllPacientes() {	
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		List<Paciente> pacientes = datastore.createQuery(Paciente.class).asList();
		if (pacientes  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Error fetching pacientes");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(pacientes, Response.Status.OK);
		}
	}


	public static Response getPaciente(String idUsuario) {
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		if (paciente  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Paciente not found");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(paciente, Response.Status.OK);
		}
	}


	public static Response getHistoriaClinica(String idUsuario) {
		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		ObjectId objectId = new ObjectId(idUsuario);
		queryPaciente.field("id").equal(objectId);
		Paciente paciente = queryPaciente.get();
		
		if (paciente  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Paciente not found");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(paciente.getHistoriaClinica(), Response.Status.OK);
		}
	}


	public static Response getConsejos(String idUsuario) {
		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
		if (paciente  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Paciente not found");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(paciente.getConsejos(), Response.Status.OK);
		}
	}


	public static Response getEventos(String idUsuario) {
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
		if (paciente  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Paciente not found");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(paciente.getEventos(), Response.Status.OK);
		}
	}


	public static Response getEventosPorFecha(String idUsuario, String fechaInicial, String fechaFinal) {
		
		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		List<Evento> eventos = datastore.createQuery(Evento.class).filter("fecha >=", fechaInicial)
				.filter("fecha <=", fechaFinal)
				.asList();
		if (eventos  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Error fetching pacientes");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(eventos, Response.Status.OK);
		}
	}


	public static Response addPaciente(Paciente paciente) {

		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		datastore.save(paciente);
		return getAllPacientes();


	}


	public static Response addEvento(String idUsuario, Evento evento) {
		
		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
			boolean accion; 
			accion =paciente.addEvento(evento);
			
			if (accion == false ) {
				jsonMap.clear();
				jsonMap.put("Error", "no se agrego el evento");
				String error = g.toJson(jsonMap);
				return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
			} else {
			
			datastore.save(paciente);
			return getAllPacientes();
		}
	}


	public static Response addReporte(String idUsuario, Reporte reporte) {
		

		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
			boolean accion; 
			accion =paciente.addReporte(reporte);
			
			if (accion == false ) {
				jsonMap.clear();
				jsonMap.put("Error", "no se agrego el reporte");
				String error = g.toJson(jsonMap);
				return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
			} else {
			
			datastore.save(paciente);
			return getAllPacientes();
		}
	}


	public static Response addConsejo(String idUsuario, Consejo consejo) {

		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
			boolean accion; 
			accion =paciente.addConsejo(consejo);
			
			if (accion == false ) {
				jsonMap.clear();
				jsonMap.put("Error", "no se agrego el consejo");
				String error = g.toJson(jsonMap);
				return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
			} else {
			
			datastore.save(paciente);
			return getAllPacientes();
		}
	}


	public static Response addReconfiguracion(String idUsuario, String idMedico, double medicion) {
		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		final Query<Medico> queryMedico = datastore.createQuery(Medico.class);
		queryMedico.field("id").equal(new ObjectId(idUsuario));
		Medico medico = queryMedico.get();
		
		final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
		queryPaciente.field("id").equal(new ObjectId(idUsuario));
		Paciente paciente = queryPaciente.get();
		
		if(paciente==null)
		{
			jsonMap.clear();
			jsonMap.put("Error", " el paciente  no existe");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		}
		
		else {
			
		paciente.setFecuenciaMarcapasos(medicion);
		datastore.save(paciente);
		return getPaciente(idUsuario);
	}
		
	}


	public static Response editPaciente(String idUsuario, Paciente paciente) {

		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		Paciente resultPaciente = datastore.get(Paciente.class,paciente.getId());
		if (resultPaciente == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Paciente not found");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			paciente.setId(resultPaciente.getId());

			
			//continuar
			datastore.save(paciente);
			return ResponseHospitalLosAlpes.buildResponse(paciente, Response.Status.OK);
		}

	}






}
