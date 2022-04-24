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
public class usuario implements Serializable {

    private String USUARIO_ID;
    private int ROL_ID;
    private String CLAVE;

    public usuario(String USUARIO_ID, int ROL_ID, String CLAVE) {
        this.USUARIO_ID = USUARIO_ID;
        this.ROL_ID = ROL_ID;
        this.CLAVE = CLAVE;
    }

    public String getUSUARIO_ID() {
        return USUARIO_ID;
    }

    public void setUSUARIO_ID(String USUARIO_ID) {
        this.USUARIO_ID = USUARIO_ID;
    }

    public int getROL_ID() {
        return ROL_ID;
    }

    public void setROL_ID(int ROL_ID) {
        this.ROL_ID = ROL_ID;
    }

    public String getCLAVE() {
        return CLAVE;
    }

    public void setCLAVE(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.USUARIO_ID);
        hash = 83 * hash + this.ROL_ID;
        hash = 83 * hash + Objects.hashCode(this.CLAVE);
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
        final usuario other = (usuario) obj;
        if (this.ROL_ID != other.ROL_ID) {
            return false;
        }
        if (!Objects.equals(this.USUARIO_ID, other.USUARIO_ID)) {
            return false;
        }
        return Objects.equals(this.CLAVE, other.CLAVE);
    }

    @Override
    public String toString() {
        return "usuario{" + "USUARIO_ID=" + USUARIO_ID + ", ROL_ID=" + ROL_ID + ", CLAVE=" + CLAVE + '}';
    }
    
    
}
