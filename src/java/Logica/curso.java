/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ksand
 */
public class curso implements Serializable{
    private int codigo;
    private String nombre;
    private int creditos;
    private int horas_semanales;
    private int carrera_id;
    private int ciclo_id;
//    private List<grupo> grupos; 

    public curso(int codigo, String nombre, int creditos, int horas_semanales, int carrera_id, int ciclo_id) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas_semanales = horas_semanales;
        this.carrera_id = carrera_id;
        this.ciclo_id = ciclo_id;

    }

    public curso() {
        codigo=0;
//        grupos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras_semanales() {
        return horas_semanales;
    }

    public void setHoras_semanales(int horas_semanales) {
        this.horas_semanales = horas_semanales;
    }

    public int getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(int carrera_id) {
        this.carrera_id = carrera_id;
    }

    public int getCiclo_id() {
        return ciclo_id;
    }

    public void setCiclo_id(int ciclo_id) {
        this.ciclo_id = ciclo_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.codigo;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + this.creditos;
        hash = 59 * hash + this.horas_semanales;
        hash = 59 * hash + this.carrera_id;
        hash = 59 * hash + this.ciclo_id;
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
        final curso other = (curso) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.creditos != other.creditos) {
            return false;
        }
        if (this.horas_semanales != other.horas_semanales) {
            return false;
        }
        if (this.carrera_id != other.carrera_id) {
            return false;
        }
        if (this.ciclo_id != other.ciclo_id) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "curso{" + "codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + ", horas_semanales=" + horas_semanales + ", carrera_id=" + carrera_id + ", ciclo_id=" + ciclo_id + '}';
    }

   
    
    
    
}
