/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.ServiceCarrera;
import Logica.carrera;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ksand
 */
public class model_carrera {
    ServiceCarrera s;
    carrera carrera_actual;
    List<carrera> lista_carreras;

    public model_carrera(ServiceCarrera s, carrera carrera_actual, List<carrera> lista_carreras) {
        this.s = s;
        this.carrera_actual = carrera_actual;
        this.lista_carreras = lista_carreras;
    }
    public model_carrera() {
        
        lista_carreras = new ArrayList<>();
    }

    public ServiceCarrera getS() {
        return s;
    }

    public void setS(ServiceCarrera s) {
        this.s = s;
    }

    public carrera getCarrera_actual() {
        return carrera_actual;
    }

    public void setCarrera_actual(carrera carrera_actual) {
        this.carrera_actual = carrera_actual;
    }

    public List<carrera> getLista_carreras() {
        return lista_carreras;
    }

    public void setLista_carreras(List<carrera> lista_carreras) {
        this.lista_carreras = lista_carreras;
    }
    
    
}
