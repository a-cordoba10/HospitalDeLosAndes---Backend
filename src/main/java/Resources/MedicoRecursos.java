package Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DTO.Consejo;
import DTO.Medico;
import Utilities.ResponseHospitalLosAlpes;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;


@Path("medicos")
public class MedicoRecursos {
    
        @GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMedicos() {
		return MedicoDAO.getAllMedicos();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMedico(Medico medico) {
		return MedicoDAO.addMedico(medico);
	}
        
        @GET
	@Path("{idMedico}/addPaciente/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPaciente(@PathParam("idUsuario") String idUsuario, @PathParam("idMedico") String idMedico) {
		return MedicoDAO.addPaciente(idUsuario,idMedico);
	}
        
  
        @GET
	@Path("{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMedico(@PathParam("idUsuario") String idUsuario) {
		return MedicoDAO.getMedico(idUsuario);
	}

}
