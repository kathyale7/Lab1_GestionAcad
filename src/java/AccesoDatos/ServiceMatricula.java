/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;


import Logica.matricula;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ksand
 */
public class ServiceMatricula extends Service {

    private static final String insertarMatricula = "{call INSERTAR_MATRICULA (?, ?, ?, ?, ?)}";
    private static final String listarMatricula = "{?=call LISTAR_MATRICULA ()}";
    private static final String buscarMatricula= "{?=call BUSCAR_MATRICULA (?, ?, ?)}";
    private static final String buscarMatricula_ID = "{?=call BUSCAR_MATRICULA_ID (?)}";
    private static final String buscarMatricula_Grupo = "{?=call BUSCAR_MATRICULA_GRUPO (?)}";
    private static final String modificarMatricula = "{call MODIFICAR_MATRICULA (?, ?, ?, ?, ?)}";
    private static final String eliminarMatricula = "{call ELIMINAR_MATRICULA (?, ?, ?)}";

    public ServiceMatricula() {
    }

    public Collection listarMatricula() throws SQLException, NoDataException, GlobalException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collecion = new ArrayList();
        matricula eMatricula = null;
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(listarMatricula);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eMatricula = new matricula(rs.getInt("ALUMNO_ID"),
                        rs.getInt("GRUPO_NUM"),
                        rs.getInt("CURSO_ID"),
                        rs.getInt("ESTADO_ID"),
                rs.getInt("NOTA"));
                collecion.add(eMatricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (collecion == null || collecion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return collecion;
    }

    public void insertarMatricula(matricula eMatricula) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(insertarMatricula);
            pstmt.setInt(1, eMatricula.getAlumno_id());
            pstmt.setInt(2, eMatricula.getGrupo_num());
            pstmt.setInt(3, eMatricula.getCurso_id());
            pstmt.setInt(4, 102);
            pstmt.setInt(5, eMatricula.getNota());


            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public void modificarMatricula(matricula eMatricula) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(modificarMatricula);
           pstmt.setInt(1, eMatricula.getAlumno_id());
            pstmt.setInt(2, eMatricula.getGrupo_num());
            pstmt.setInt(3, eMatricula.getCurso_id());
            pstmt.setInt(4, eMatricula.getEstado());
            pstmt.setInt(5, eMatricula.getNota());
            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public void eliminarMatricula(int id, int grupo, int curso) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(eliminarMatricula);
            pstmt.setInt(1, id);
            pstmt.setInt(2, grupo);
            pstmt.setInt(3, curso);

            int resultado = pstmt.executeUpdate();

            if (resultado != 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public Collection buscarMatricula_id(int id) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        matricula eMatricula = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarMatricula_ID);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eMatricula = new matricula(rs.getInt("ALUMNO_ID"),
                        rs.getInt("GRUPO_NUM"),
                        rs.getInt("CURSO_ID"),
                        rs.getInt("ESTADO_ID"),
                        rs.getInt("NOTA"));
                coleccion.add(eMatricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public Collection buscarMatricula_grupo(int grupo) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        matricula eMatricula = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarMatricula_Grupo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, grupo);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eMatricula = new matricula(rs.getInt("ALUMNO_ID"),
                        rs.getInt("GRUPO_NUM"),
                        rs.getInt("CURSO_ID"),
                        rs.getInt("ESTADO_ID"),
                        rs.getInt("NOTA"));
                coleccion.add(eMatricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    
    
}
