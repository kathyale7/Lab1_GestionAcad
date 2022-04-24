/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.ServiceCiclo;
import Logica.ciclo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ksand
 */
public class model_ciclo {
     ServiceCiclo s;
    ciclo ciclo_actual;
    List<ciclo> lista_ciclos;

    public model_ciclo(ServiceCiclo s, ciclo ciclo_actual, List<ciclo> lista_ciclos) {
        this.s = s;
        this.ciclo_actual = ciclo_actual;
        this.lista_ciclos = lista_ciclos;
    }
    public model_ciclo() {
       lista_ciclos = new ArrayList<>();
    }

    public ServiceCiclo getS() {
        return s;
    }

    public void setS(ServiceCiclo s) {
        this.s = s;
    }

    public ciclo getCiclo_actual() {
        return ciclo_actual;
    }

    public void setCiclo_actual(ciclo ciclo_actual) {
        this.ciclo_actual = ciclo_actual;
    }

    public List<ciclo> getLista_ciclos() {
        return lista_ciclos;
    }

    public void setLista_ciclos(List<ciclo> lista_ciclos) {
        this.lista_ciclos = lista_ciclos;
    }
    
}
