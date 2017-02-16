package Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import DAO.NotificacionDAO;
import DTO.Notificacion;


@Path("notificaciones")
public class NotificacionRecursos {


	//------------------------------------POST----------------------------------------------------//
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNotificacion(Notificacion notificacion) {
		return NotificacionDAO.addNotificacion(notificacion);
	}
}
