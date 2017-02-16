package Resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

import DAO.PacienteDAO;
import DTO.Consejo;
import DTO.Evento;
import DTO.Paciente;
import DTO.Reporte;

@Path("pacientes")
public class PacienteRecursos {

	// ----------------------------------------GET--------------------------------------

	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPacientes() {
		
		return PacienteDAO.getAllPacientes();
	}

	@GET
	@Path("{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPacientes(@PathParam("idUsuario") String idUsuario) {
		return PacienteDAO.getPaciente(idUsuario);
	}

	@GET
	@Path("{idUsuario}/historiasClinicas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHistoriaClinica(@PathParam("idUsuario") String idUsuario) {
		return PacienteDAO.getHistoriaClinica(idUsuario);
	}

	@GET
	@Path("{idUsuario}/consejos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConsejos(@PathParam("idUsuario") String idUsuario) {
		return PacienteDAO.getConsejos(idUsuario);
	}

	@GET
	@Path("{idUsuario}/eventos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventos(@PathParam("idUsuario") String idUsuario) {
		return PacienteDAO.getEventos(idUsuario);
	}

	@GET
	@Path("{idUsuario}/eventos/{fechaInicial}/{fechaFinal}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventosPorFecha(@PathParam("idUsuario") String idUsuario,
			@PathParam("fechaInicial") String fechaInicial, @PathParam("fechaFinal") String fechaFinal) {
		return PacienteDAO.getEventosPorFecha(idUsuario,fechaInicial,fechaFinal);
	}

	// ------------------------------------POST----------------------------------------------------//

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPaciente(Paciente paciente) {
		return PacienteDAO.addPaciente(paciente);
	}

	@POST
	@Path("{idUsuario}/eventos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvento(@PathParam("idUsuario") String idUsuario, Evento evento) {
		return PacienteDAO.addEvento(idUsuario,evento);
	}

	@POST
	@Path("{idUsuario}/historiasClinicas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReporte(@PathParam("idUsuario") String idUsuario, Reporte reporte) {
		return PacienteDAO.addReporte(idUsuario,reporte);
	}

	@POST
	@Path("{idUsuario}/consejos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addConsejo(@PathParam("idUsuario") String idUsuario, Consejo consejo) {
		return PacienteDAO.addConsejo(idUsuario,consejo);
	}


	@POST
	@Path("{idUsuario}/marcapasos/{idMedico}/{medicion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReconfiguracion(@PathParam("idUsuario") String idUsuario,@PathParam("idMedico") String idMedico,@PathParam("medicion") double medicion) {
		return PacienteDAO.addReconfiguracion(idUsuario,idMedico,medicion);
	}
	
	// ------------------------------------UPDATE----------------------------------------------------//

	@PUT
	@Path("{idUsuario}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editPaciente(@PathParam("idUsuario") String idUsuario, Paciente paciente) {
		return PacienteDAO.editPaciente(idUsuario,paciente);
	}

}
