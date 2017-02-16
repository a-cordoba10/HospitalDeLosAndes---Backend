package DTO;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Evento")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Evento {
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	
	public enum TipoEvento{
		FRECUENCIACARDIACA, PRESIONSANGUINEA, NIVELDEESTRES
	}
	
	public enum TipoNivel{
		VERDE, AMARILLO, ROJO 
		}
	private Date fecha; 
	
	private int medicion;
	
	private String GPS;
	
	private TipoEvento tipo;
	
	private TipoNivel nivel;
	
	
	public Evento(){
		
	}

	public Evento(ObjectId id, Date fecha, int medicion, String gPS , TipoEvento tipo, TipoNivel nivel) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.medicion = medicion;
		this.GPS = gPS;
		this.nivel=nivel;
		this.tipo=tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMedicion() {
		return medicion;
	}

	public void setMedicion(int medicion) {
		this.medicion = medicion;
	}

	public String getGPS() {
		return GPS;
	}

	public void setGPS(String GPS) {
		this.GPS = GPS;
	}

	public ObjectId getId() {
		
		return id;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public TipoNivel getNivel() {
		return nivel;
	}

	public void setNivel(TipoNivel nivel) {
		this.nivel = nivel;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
	
}
