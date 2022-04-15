/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Logica.alumno;
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
public class ServiceAlumno extends Service {
    private static final String insertarAlumno = "{call INSERTAR_ALUMNO (?, ?, ?, ?, ?, ?, ?)}";
    private static final String listarAlumno = "{?=call LISTAR_ALUMNO ()}";
    private static final String buscarAlumno_id = "{?=call BUSCAR_ALUMNO_ID (?)}";
    private static final String buscarAlumno__nombre = "{?=call BUSCAR_ALUMNO_NOMBRE (?)}";
    private static final String buscarAlumno__carrera = "{?=call BUSCAR_ALUMNO_CARRERA (?)}";
    private static final String modificarAlumno = "{call MODIFICAR_ALUMNO (?, ?, ?, ?, ?, ?, ?)}";
    private static final String eliminarAlumno = "{call ELIMINAR_ALUMNO (?)}";

    public ServiceAlumno() {
    }

    public Collection listarAlumno() throws SQLException, NoDataException, GlobalException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collecion = new ArrayList();
        alumno eAlumno = null;
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(listarAlumno);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eAlumno = new alumno(rs.getInt("cedula"),
                        rs.getString("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"), 
                rs.getInt("carrera_id"));
                collecion.add(eAlumno);
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

    public void insertarAlumno(alumno eAlumno) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(insertarAlumno);
            pstmt.setInt(1, eAlumno.getCedula());
            pstmt.setString(2, eAlumno.getUsuario_id());
            pstmt.setString(3, eAlumno.getNombre());
            pstmt.setString(4, eAlumno.getTelefono());
            pstmt.setString(5, eAlumno.getEmail());
            pstmt.setString(6, eAlumno.getFecha_nacimiento());
            pstmt.setInt(7, eAlumno.getCarrera_id());

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

    public void modificarAlumno(alumno eAlumno) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(modificarAlumno);
            pstmt.setInt(1, eAlumno.getCedula());
            pstmt.setString(2, eAlumno.getUsuario_id());
            pstmt.setString(3, eAlumno.getNombre());
            pstmt.setString(4, eAlumno.getTelefono());
            pstmt.setString(5, eAlumno.getEmail());
            pstmt.setString(6, eAlumno.getFecha_nacimiento());
            pstmt.setInt(7, eAlumno.getCarrera_id());
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

    public void eliminarAlumno(int id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(eliminarAlumno);
            pstmt.setInt(1, id);

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

    public alumno buscarAlumno_id(int id) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        alumno eAlumno = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarAlumno_id);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eAlumno = new alumno(rs.getInt("cedula"),
                        rs.getString("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"),
                rs.getInt("carrera_id"));
                coleccion.add(eAlumno);
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
        return eAlumno;
    }

    public alumno buscarAlumno__nombre(String nombre) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
       alumno eAlumno = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarAlumno__nombre);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eAlumno = new alumno(rs.getInt("cedula"),
                        rs.getString("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"),
                rs.getInt("carrera_id"));
                coleccion.add(eAlumno);
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
        return eAlumno;
    }
    
    public alumno buscarAlumno__carrera(String carrera_id) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        alumno eAlumno = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarAlumno__carrera);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, carrera_id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eAlumno = new alumno(rs.getInt("cedula"),
                        rs.getString("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("fecha_nacimiento"),
                rs.getInt("carrera_id"));
                coleccion.add(eAlumno);
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
        return eAlumno;
    }
}
