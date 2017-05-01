package DTO;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Medico")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico implements Iusuario{
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	
	public enum TipoMedico{
		ESPECIALIZADO, GENERAL
	}
	
	private String nombre;
	private String nivel; 
	private ArrayList<String> idPacientes;
	private int edad;
	private Date fechaNacimiento;
	private String direccion;
	private int telefono;
	private int docIdentidad;
	private TipoMedico tipo;
	private String usuario;
	private String contrasenia;
	
	public Medico(){
		
	}

	public Medico(ObjectId id, String nombre, String nivel, ArrayList<String> idPacientes,
			int edad, Date fechaNacimiento, String direccion, int telefono, int docIdentidad,TipoMedico tipo,
			String usuario, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nivel = nivel;
		this.idPacientes = idPacientes;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.docIdentidad = docIdentidad;
		this.tipo=tipo;
		this.usuario= usuario;
		this.contrasenia=contrasenia;
	}
	
	public boolean validarLogin (String usr, String pssw)
	{
		if (usr.equals(usuario)&& pssw.equals(contrasenia)){
			return true;
		}
		return false;
	}



	public ObjectId getId() {
		// TODO Auto-generated method stub
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNivel() {
		return nivel;
	}



	public void setNivel(String nivel) {
		this.nivel = nivel;
	}



	public ArrayList<String> getIdPacientes() {
		return idPacientes;
	}



	public void setIdPacientes(ArrayList<String> idPacientes) {
		this.idPacientes = idPacientes;
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public int getTelefono() {
		return telefono;
	}



	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}



	public int getDocIdentidad() {
		return docIdentidad;
	}



	public void setDocIdentidad(int docIdentidad) {
		this.docIdentidad = docIdentidad;
	}



	public TipoMedico getTipo() {
		return tipo;
	}



	public void setTipo(TipoMedico tipo) {
		this.tipo = tipo;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}
        
        public void addPaciente (String idPaciente){
            
            idPacientes.add(idPaciente);
            
        }
	
	
	
}
