/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DTO.Medico;
import DTO.Paciente;
import DTO.Wrapper;
import Utilities.HospitalLosAlpesDB;
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
                Paciente pBuscado = PacienteDAO.getPacientePorUsr(usr);
                if (pBuscado == null){
                    Wrapper respuesta = new Wrapper("no exitoso", "no existe usuario");
                    return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
                }
                if (!pBuscado.validarLogin(usr, pssw)){
                    Wrapper respuesta = new Wrapper("no exitoso", "password incorrecto");
                    return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
                }
                Wrapper respuesta = new Wrapper("exitoso", "paciente");
                respuesta.setUsuario(pBuscado);
                return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
            }
            if (!buscado.validarLogin(usr, pssw)){
                Wrapper respuesta = new Wrapper("no exitoso", "password incorrecto");
                return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
            }
            Wrapper respuesta = new Wrapper("exitoso", "doctor");
            respuesta.setUsuario(buscado);
            return ResponseHospitalLosAlpes.buildResponse(respuesta, Response.Status.OK);
    }
    
    @GET
    @Path("key")
    public Response guardarId (@QueryParam("key") String key){
        HospitalLosAlpesDB.setDispositivo(key);
        return ResponseHospitalLosAlpes.buildResponse("ok", Response.Status.OK);
    }
    
    
}
