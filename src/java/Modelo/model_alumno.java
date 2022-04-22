/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceAlumno;
import Logica.alumno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ksand
 */
public class model_alumno {
    ServiceAlumno s;
    alumno alumno_actual;
    List<alumno> lista_alumnos;
    
     public model_alumno() {
        alumno_actual = new alumno();
        lista_alumnos = new ArrayList<>();   
    }

    public ServiceAlumno getS() {
        return s;
    }

    public void setS(ServiceAlumno s) {
        this.s = s;
    }

    public alumno getAlumno_actual() {
        return alumno_actual;
    }

    public void setAlumno_actual(alumno alumno_actual) {
        this.alumno_actual = alumno_actual;
    }

    public List<alumno> getLista_Alumnos() throws SQLException, NoDataException, GlobalException {
        try {
            setLista_alumnos((List<alumno>) s.listarAlumno());
             
        } catch (SQLException | NoDataException | GlobalException ex) {
            Logger.getLogger(model_curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return lista_alumnos; 
    }

    public void setLista_alumnos(List<alumno> lista_alumnos) {
        this.lista_alumnos = lista_alumnos;
    }
}
