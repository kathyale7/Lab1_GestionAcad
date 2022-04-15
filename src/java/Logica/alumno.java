/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ksand
 */
public class alumno implements Serializable {
    private int cedula;
    private String usuario_id;
    private String nombre;
    private String telefono;
    private String email;
    private String fecha_nacimiento;
    private int carrera_id; 


    public alumno(int cedula, String usuario_id, String nombre, String telefono, String email, String fecha_nacimiento, int carrera_id) {
        this.cedula = cedula;
        this.usuario_id = usuario_id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.carrera_id = carrera_id;

    }

    public alumno() {
        cedula=0;
//        grupos = new ArrayList<>();
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(int carrera_id) {
        this.carrera_id = carrera_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.cedula;
        hash = 23 * hash + Objects.hashCode(this.usuario_id);
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.telefono);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.fecha_nacimiento);
        hash = 23 * hash + this.carrera_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final alumno other = (alumno) obj;
        if (this.cedula != other.cedula) {
            return false;
        }
        if (this.carrera_id != other.carrera_id) {
            return false;
        }
        if (!Objects.equals(this.usuario_id, other.usuario_id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.fecha_nacimiento, other.fecha_nacimiento);
    }

    @Override
    public String toString() {
        return "alumno{" + "cedula=" + cedula + ", usuario_id=" + usuario_id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", fecha_nacimiento=" + fecha_nacimiento + ", carrera_id=" + carrera_id + '}';
    }
    
}
