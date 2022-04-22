/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceProfesor;
import Logica.profesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ksand
 */
public class model_profesor {
    ServiceProfesor s;
    profesor profesor_actual;
    List<profesor> lista_profesores;
    
     public model_profesor() {
        profesor_actual = new profesor();
        lista_profesores = new ArrayList<>();   
    }

    public ServiceProfesor getS() {
        return s;
    }

    public void setS(ServiceProfesor s) {
        this.s = s;
    }

    public profesor getProfesor_actual() {
        return profesor_actual;
    }

    public void setProfesor_actual(profesor profesor_actual) {
        this.profesor_actual = profesor_actual;
    }

    public List<profesor> getLista_profesores() throws SQLException, NoDataException, GlobalException {
        try {
            setLista_profesores((List<profesor>) s.listarProfesor());
             
        } catch (SQLException | NoDataException | GlobalException ex) {
            Logger.getLogger(model_curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return lista_profesores; 
    }

    public void setLista_profesores(List<profesor> lista_profesores) {
        this.lista_profesores = lista_profesores;
    }

    

    
}
