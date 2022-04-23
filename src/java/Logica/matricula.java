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
public class matricula implements Serializable {

    private int alumno_id;
    private int grupo_num;
    private int curso_id;
    private int estado;
    private int nota;

    public matricula(int alumno_id, int grupo_num, int curso_id, int estado, int nota) {
        this.alumno_id = alumno_id;
        this.grupo_num = grupo_num;
        this.curso_id = curso_id;
        this.estado = estado;
        this.nota = nota;
    }
     public matricula() {
        alumno_id=0;
//        grupos = new ArrayList<>();
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public int getGrupo_num() {
        return grupo_num;
    }

    public void setGrupo_num(int grupo_num) {
        this.grupo_num = grupo_num;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.alumno_id;
        hash = 71 * hash + this.grupo_num;
        hash = 71 * hash + Objects.hashCode(this.curso_id);
        hash = 71 * hash + Objects.hashCode(this.estado);
        hash = 71 * hash + this.nota;
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
        final matricula other = (matricula) obj;
        if (this.alumno_id != other.alumno_id) {
            return false;
        }
        if (this.grupo_num != other.grupo_num) {
            return false;
        }
        if (this.nota != other.nota) {
            return false;
        }
        if (!Objects.equals(this.curso_id, other.curso_id)) {
            return false;
        }
        return Objects.equals(this.estado, other.estado);
    }

    @Override
    public String toString() {
        return "matricula{" + "alumno_id=" + alumno_id + ", grupo_num=" + grupo_num + ", curso_id=" + curso_id + ", estado=" + estado + ", nota=" + nota + '}';
    }
    
    
}


