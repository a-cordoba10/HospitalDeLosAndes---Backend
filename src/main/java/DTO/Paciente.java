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
@Entity("Paciente")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Paciente {
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	
	private String nombre;
	private int edad;
	private Date fechaNacimiento;
	private String direccion;
	private int telefono;
	private int docIdentidad;
	private double montoPago;
	private Date fechaPago;
	private String idDispositivo;
	private ArrayList<Reporte> historiaClinica;
	private ArrayList<Consejo> consejos;
	private ArrayList<Evento> eventos;
	private double fecuenciaMarcapasos;
	
	public Paciente(){
		
	}



	public Paciente(ObjectId id, String identificador, String nombre, int edad, Date fechaNacimiento, String direccion,
			int telefono, int docIdentidad, double montoPago, Date fechaPago, String idDispositivo,
			ArrayList<Reporte> historiaClinica, ArrayList<Consejo> consejos, ArrayList<Evento> eventos,
			double fecuenciaMarcapasos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.docIdentidad = docIdentidad;
		this.montoPago = montoPago;
		this.fechaPago = fechaPago;
		this.idDispositivo = idDispositivo;
		this.historiaClinica = historiaClinica;
		this.consejos = consejos;
		this.eventos = eventos;
		this.fecuenciaMarcapasos = fecuenciaMarcapasos;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(double montoPago) {
		this.montoPago = montoPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public ArrayList<Reporte> getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(ArrayList<Reporte> historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public ArrayList<Consejo> getConsejos() {
		return consejos;
	}

	public void setConsejos(ArrayList<Consejo> consejos) {
		this.consejos = consejos;
	}

	public double getFecuenciaMarcapasos() {
		return fecuenciaMarcapasos;
	}

	public void setFecuenciaMarcapasos(double fecuenciaMarcapasos) {
		this.fecuenciaMarcapasos = fecuenciaMarcapasos;
	}

	public ObjectId getId() {
		// TODO Auto-generated method stub
		return id;
	}

	

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}



	public void setId(ObjectId id) {
		this.id=id;
		
	}
	
	public boolean addReporte(Reporte reporte){
		return historiaClinica.add(reporte);
	}

	public boolean addConsejo(Consejo consejo){
		return consejos.add(consejo);
	}
	

	public boolean addEvento(Evento evento){
		return eventos.add(evento);
	}
	
}
