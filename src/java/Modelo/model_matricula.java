/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.ServiceMatricula;
import Logica.matricula;
import java.util.List;

/**
 *
 * @author ksand
 */
public class model_matricula {
     ServiceMatricula s;
    matricula matricula_actual;
    List<matricula> lista_matriculas;

    public model_matricula(ServiceMatricula s, matricula matricula_actual, List<matricula> lista_matriculas) {
        this.s = s;
        this.matricula_actual = matricula_actual;
        this.lista_matriculas = lista_matriculas;
    }
    
    public model_matricula() {
        
    }

    public ServiceMatricula getS() {
        return s;
    }

    public void setS(ServiceMatricula s) {
        this.s = s;
    }

    public matricula getMatricula_actual() {
        return matricula_actual;
    }

    public void setMatricula_actual(matricula matricula_actual) {
        this.matricula_actual = matricula_actual;
    }

    public List<matricula> getLista_matriculas() {
        return lista_matriculas;
    }

    public void setLista_matriculas(List<matricula> lista_matriculas) {
        this.lista_matriculas = lista_matriculas;
    }
    
    
}
