package DAO;

import static DAO.PacienteDAO.g;
import static DAO.PacienteDAO.jsonMap;
import DTO.Evento;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import DTO.Medico;
import DTO.Paciente;
import Utilities.HospitalLosAlpesDB;
import Utilities.ResponseHospitalLosAlpes;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

public class MedicoDAO {
	
	public static JsonParser jsonParser = new JsonParser();
	public static Map<String, String> jsonMap = new HashMap<String, String>();
	public static Gson g = new Gson();


	public static Response getAllMedicos() {	
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		List<Medico> medicos = datastore.createQuery(Medico.class).asList();
		if (medicos  == null) {
			jsonMap.clear();
			jsonMap.put("Error", "Error fetching medicos");
			String error = g.toJson(jsonMap);
			return ResponseHospitalLosAlpes.buildResponse(error, Response.Status.NOT_FOUND);
		} else {
			return ResponseHospitalLosAlpes.buildResponse(medicos, Response.Status.OK);
		}
	}

	public static Response addMedico(Medico medico) {

		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		datastore.save(medico);
		return getAllMedicos();
	}
        
        public static Medico getMedicoPorUsr(String usr) {		
		Datastore datastore = HospitalLosAlpesDB.getDatastore();
		List<Medico> medicos = datastore.createQuery(Medico.class).filter("usuario =", usr)
				.asList();
		if (medicos.isEmpty()) {
			return null;
		} else {
			return medicos.get(0);
		}
	}

    public static Response addPaciente(String idUsuario, String idMedico) {
        
        Datastore datastore = HospitalLosAlpesDB.getDatastore();
        final Query<Medico> queryMedico = datastore.createQuery(Medico.class);
        queryMedico.field("id").equal(new ObjectId(idMedico));
        Medico medico = queryMedico.get();
        final Query<Paciente> queryPaciente = datastore.createQuery(Paciente.class);
        queryPaciente.field("id").equal(new ObjectId(idUsuario));
        Paciente paciente = queryPaciente.get();
        if (medico==null || paciente == null) {
                return ResponseHospitalLosAlpes.buildResponse("error fetching", Response.Status.NOT_FOUND);
        }
        medico.addPaciente(paciente.getId().toString());
        datastore.save(medico);
        return ResponseHospitalLosAlpes.buildResponse(medico, Response.Status.OK);
    }

}
