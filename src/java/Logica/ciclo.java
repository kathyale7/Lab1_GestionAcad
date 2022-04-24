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
public class ciclo implements Serializable {

    private int codigo;
    private String ANHO;
    private int NUMERO;
    private String FECHA_INICIO;
    private String FECHA_FINAL;
    private int ES_DEFAULT;

    public ciclo(int codigo, String ANHO, int NUMERO, String FECHA_INICIO, String FECHA_FINAL, int ES_DEFAULT) {
        this.codigo = codigo;
        this.ANHO = ANHO;
        this.NUMERO = NUMERO;
        this.FECHA_INICIO = FECHA_INICIO;
        this.FECHA_FINAL = FECHA_FINAL;
        this.ES_DEFAULT = ES_DEFAULT;
    }

    public ciclo() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getANHO() {
        return ANHO;
    }

    public void setANHO(String ANHO) {
        this.ANHO = ANHO;
    }

    public int getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(int NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getFECHA_FINAL() {
        return FECHA_FINAL;
    }

    public void setFECHA_FINAL(String FECHA_FINAL) {
        this.FECHA_FINAL = FECHA_FINAL;
    }

    public int getES_DEFAULT() {
        return ES_DEFAULT;
    }

    public void setES_DEFAULT(int ES_DEFAULT) {
        this.ES_DEFAULT = ES_DEFAULT;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.codigo;
        hash = 29 * hash + Objects.hashCode(this.ANHO);
        hash = 29 * hash + this.NUMERO;
        hash = 29 * hash + Objects.hashCode(this.FECHA_INICIO);
        hash = 29 * hash + Objects.hashCode(this.FECHA_FINAL);
        hash = 29 * hash + this.ES_DEFAULT;
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
        final ciclo other = (ciclo) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.NUMERO != other.NUMERO) {
            return false;
        }
        if (this.ES_DEFAULT != other.ES_DEFAULT) {
            return false;
        }
        if (!Objects.equals(this.ANHO, other.ANHO)) {
            return false;
        }
        if (!Objects.equals(this.FECHA_INICIO, other.FECHA_INICIO)) {
            return false;
        }
        return Objects.equals(this.FECHA_FINAL, other.FECHA_FINAL);
    }

    @Override
    public String toString() {
        return "ciclo{" + "codigo=" + codigo + ", ANHO=" + ANHO + ", NUMERO=" + NUMERO + ", FECHA_INICIO=" + FECHA_INICIO + ", FECHA_FINAL=" + FECHA_FINAL + ", ES_DEFAULT=" + ES_DEFAULT + '}';
    }

}
