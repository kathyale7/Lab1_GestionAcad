/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import java.sql.ResultSet;
import java.util.Collection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.driver.*;


/**
 *
 * @author ksand
 */
public class ServiceCurso extends Service {
    private static final String insertarCurso = "{call INSERTAR_CURSO (?, ?, ?, ?,?,?)}";
    private static final String listarCurso = "{?=call LISTAR_CURSO ()}";
    private static final String buscarCurso = "{?=call BUSCAR_CURSO (?)}";
    private static final String modificarCurso = "{call MODIFICAR_CURSO (?, ?, ?, ?,?,?)}";
    private static final String eliminarCurso = "{call ELIMINAR_CURSO (?)}";
    
    public ServiceCurso(){
    }
    
    public Collection listarCurso()  {
//        try {
//            conectar();
//        } catch (ClassNotFoundException ex){
//            throw new GlobalException("No se ha localizado el Driver");
//        } catch (SQLException e) {
//            throw new NoDataException ("La base de datos no se encuentra disponible");
//        }
        return null;
        
    }
    
}
