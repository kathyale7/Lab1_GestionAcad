/*
    Programación 4
    I Ciclo - 2021
    Proyecto 1 - Cursos Libres.com
    117440348 - Joaquin Barrientos Monge
    A00144883 - Kathy Sandoval Blandon
 */
package Modelo;


import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceCurso;
import Logica.curso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ksand
 */
public class model_curso {
    ServiceCurso s;
    curso curso_actual;
    List<curso> lista_cursos;
    
     public model_curso() {
        curso_actual = new curso();
        lista_cursos = new ArrayList<>();   
    }

    public curso getCurso_actual() {
        return curso_actual;
    }

    public void setCurso_actual(curso curso_actual) {
        this.curso_actual = curso_actual;
    }

    public List<curso> getLista_cursos() throws SQLException, NoDataException, GlobalException {
        try {
            return (List<curso>) s.listarCurso(); 
        } catch (SQLException | NoDataException | GlobalException ex) {
            Logger.getLogger(model_curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return (List<curso>) s.listarCurso(); 
    }

    public void setLista_cursos(List<curso> lista_cursos) {
        try {
            this.lista_cursos = (List<curso>) s.listarCurso();
        } catch (SQLException | NoDataException | GlobalException ex) {
            Logger.getLogger(model_curso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
}
