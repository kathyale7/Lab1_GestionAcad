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
public class grupo implements Serializable{
    private int CODIGO;
private int CURSO_ID;
private int CICLO_ID;	
private int NUM_GRUPO;
private String HORARIO;
private int PROFESOR_ID;

    public grupo(int CODIGO, int CURSO_ID, int CICLO_ID, int NUM_GRUPO, String HORARIO, int PROFESOR_ID) {
        this.CODIGO = CODIGO;
        this.CURSO_ID = CURSO_ID;
        this.CICLO_ID = CICLO_ID;
        this.NUM_GRUPO = NUM_GRUPO;
        this.HORARIO = HORARIO;
        this.PROFESOR_ID = PROFESOR_ID;
    }
public grupo() {
        
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(int CODIGO) {
        this.CODIGO = CODIGO;
    }

    public int getCURSO_ID() {
        return CURSO_ID;
    }

    public void setCURSO_ID(int CURSO_ID) {
        this.CURSO_ID = CURSO_ID;
    }

    public int getCICLO_ID() {
        return CICLO_ID;
    }

    public void setCICLO_ID(int CICLO_ID) {
        this.CICLO_ID = CICLO_ID;
    }

    public int getNUM_GRUPO() {
        return NUM_GRUPO;
    }

    public void setNUM_GRUPO(int NUM_GRUPO) {
        this.NUM_GRUPO = NUM_GRUPO;
    }

    public String getHORARIO() {
        return HORARIO;
    }

    public void setHORARIO(String HORARIO) {
        this.HORARIO = HORARIO;
    }

    public int getPROFESOR_ID() {
        return PROFESOR_ID;
    }

    public void setPROFESOR_ID(int PROFESOR_ID) {
        this.PROFESOR_ID = PROFESOR_ID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.CODIGO;
        hash = 29 * hash + this.CURSO_ID;
        hash = 29 * hash + this.CICLO_ID;
        hash = 29 * hash + this.NUM_GRUPO;
        hash = 29 * hash + Objects.hashCode(this.HORARIO);
        hash = 29 * hash + this.PROFESOR_ID;
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
        final grupo other = (grupo) obj;
        if (this.CODIGO != other.CODIGO) {
            return false;
        }
        if (this.CURSO_ID != other.CURSO_ID) {
            return false;
        }
        if (this.CICLO_ID != other.CICLO_ID) {
            return false;
        }
        if (this.NUM_GRUPO != other.NUM_GRUPO) {
            return false;
        }
        if (this.PROFESOR_ID != other.PROFESOR_ID) {
            return false;
        }
        return Objects.equals(this.HORARIO, other.HORARIO);
    }

    @Override
    public String toString() {
        return "grupo{" + "CODIGO=" + CODIGO + ", CURSO_ID=" + CURSO_ID + ", CICLO_ID=" + CICLO_ID + ", NUM_GRUPO=" + NUM_GRUPO + ", HORARIO=" + HORARIO + ", PROFESOR_ID=" + PROFESOR_ID + '}';
    }

}
