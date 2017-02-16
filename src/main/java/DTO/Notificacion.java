package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Notificacion")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Notificacion {
	
	@Id
	@XmlElement(name = "id")
	private ObjectId id;
	public enum TipoNotificacion {
		EMERGENCIA
	}
	private String idEvento;
	private String idPaciente;
	private String mensaje;
	private TipoNotificacion tipo;
	
	public Notificacion(){
		
	}

	public Notificacion(ObjectId id, String idEvento, String idPaciente, String mensaje, TipoNotificacion tipo) {
		super();
		this.id = id;
		this.idEvento = idEvento;
		this.idPaciente = idPaciente;
		this.mensaje = mensaje;
		this.tipo=tipo;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public ObjectId getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public TipoNotificacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoNotificacion tipo) {
		this.tipo = tipo;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	

}
