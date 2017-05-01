/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author JuanEsteban
 */
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("respuesta")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Wrapper {
    @Id
    @XmlElement(name = "id")
    private ObjectId id;
    private String estado;
    private String elemento;
    private Iusuario usuario;



    public Wrapper(String estado, String elemento) {
        this.estado = estado;
        this.elemento = elemento;
    }
    
    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
    
    public void setUsuario(Iusuario usuario) {
        this.usuario = usuario;
    }
    
    public Iusuario getUsuario() {
        return usuario;
    }
}
