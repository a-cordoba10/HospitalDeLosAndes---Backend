package DTO;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Consejo")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Consejo {
	
//	Definicion del enum
	public enum TipoAlerta {
		DIETA, ACTIVIDADFISICA, TOMAMEDICAMENTO,ASISTENCIACITAMEDICA
	}
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	
	

	private Date fecha;
	private TipoAlerta tipo;
	private String descripcion;
	private String idDoctor;
	
	public Consejo (){
		
	}

	
	public Consejo(ObjectId id, Date fecha, String descripcion, String idDoctor, TipoAlerta tipo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idDoctor = idDoctor;
		this.tipo = tipo;
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
		
		return id;
	}
	public TipoAlerta getTipo() {
		return tipo;
	}

	public void setTipo(TipoAlerta tipo) {
		this.tipo = tipo;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	

}
