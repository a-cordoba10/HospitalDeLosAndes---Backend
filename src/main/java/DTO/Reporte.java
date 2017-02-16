package DTO;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Reporte")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reporte {
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	
	private Date fecha;
	public enum TipoReporte {
		DECISION, TRATAMIENTO, DIAGNOSTICO, EXAMÉN
	}
	private String descripcion;
	private String idDoctor;
	private TipoReporte tipo;
	
	public Reporte (){
		
	}
	

	public Reporte(ObjectId id, Date fecha, String descripcion, String idDoctor, TipoReporte tipo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idDoctor = idDoctor;
		this.tipo=tipo;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public ObjectId getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public TipoReporte getTipo() {
		return tipo;
	}


	public void setTipo(TipoReporte tipo) {
		this.tipo = tipo;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}

}
