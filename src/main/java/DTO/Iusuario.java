/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author JuanEsteban
 */
public interface Iusuario {

    public ObjectId getId();

    public String getNombre();

    public void setNombre(String nombre);

    public int getEdad();

    public void setEdad(int edad);

    public Date getFechaNacimiento();

    public void setFechaNacimiento(Date fechaNacimiento);

    public String getDireccion();

    public void setDireccion(String direccion);

    public int getTelefono();

    public void setTelefono(int telefono);

    public int getDocIdentidad();

    public void setDocIdentidad(int docIdentidad);

}
