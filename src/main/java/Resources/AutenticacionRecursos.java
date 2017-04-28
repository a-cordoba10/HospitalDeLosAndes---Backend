/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.MedicoDAO;
import DTO.Medico;
import DTO.Wrapper;
import Utilities.ResponseHospitalLosAlpes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.glassfish.jersey.internal.Errors.error;

/**
 *
 * @author JuanEsteban
 */
@Path("auth")
public class AutenticacionRecursos {
    
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedico(@QueryParam("user") String usr,@QueryParam("password") String pssw) {
            Medico buscado = MedicoDAO.getMedicoPorUsr(usr);
            if (buscado==null)
            {
                Wrapper respuesta = new Wrapper("no exitoso", "no existe usuario");
                return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
            }
            if (!buscado.validarLogin(usr, pssw)){
                Wrapper respuesta = new Wrapper("no exitoso", "password incorrecto");
                return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
            }
            Wrapper respuesta = new Wrapper("exitoso", "Medico");
            respuesta.setMedico(buscado);
            return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
    }
    
}
