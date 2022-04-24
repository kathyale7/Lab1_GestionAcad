/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Logica.grupo;
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
public class ServiceGrupo extends Service {

    private static final String insertarGrupo = "{call INSERTAR_GRUPO (?, ?, ?, ?,?,?)}";
    private static final String buscarGrupo_Ciclo = "{?=call BUSCAR_GRUPO (?, ?)}";
    private static final String buscarCurso_Profesor = "{?=call BUSCAR_GRUPO_Profesor (?, ?)}";
    private static final String modificarGrupo = "{call MODIFICAR_GRUPO (?, ?, ?, ?,?,?)}";
    

    public ServiceGrupo() {
    }

    
    public void insertarGrupo(grupo eGrupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(insertarGrupo);
            pstmt.setInt(1, eGrupo.getCODIGO());
            pstmt.setInt(2, eGrupo.getCICLO_ID());
            pstmt.setInt(3, eGrupo.getNUM_GRUPO());
            pstmt.setInt(4, eGrupo.getCURSO_ID());
            pstmt.setString(5, eGrupo.getHORARIO());
            pstmt.setInt(6, eGrupo.getPROFESOR_ID());

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

    public void modificarGrupo(grupo eGrupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(modificarGrupo);
             pstmt.setInt(1, eGrupo.getCODIGO());
            pstmt.setInt(2, eGrupo.getCICLO_ID());
            pstmt.setInt(3, eGrupo.getNUM_GRUPO());
            pstmt.setInt(4, eGrupo.getCURSO_ID());
            pstmt.setString(5, eGrupo.getHORARIO());
            pstmt.setInt(6, eGrupo.getPROFESOR_ID());
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

    
    public Collection buscarGrupo_Ciclo(int ciclo, int curso) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        grupo eGrupo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarGrupo_Ciclo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, ciclo);
            pstmt.setInt(3, curso);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eGrupo = new grupo(rs.getInt("CODIGO"),
                        rs.getInt("CICLO_ID"),
                        rs.getInt("NUM_GRUPO"),
                        rs.getInt("CURSO_ID"),
                        rs.getString("HORARIO"),
                        rs.getInt("PROFESOR_ID"));
                coleccion.add(eGrupo);
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

    public Collection buscarCurso_Profesor(int ciclo, int profesor) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        grupo eGrupo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarCurso_Profesor);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, ciclo);
            pstmt.setInt(3, profesor);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eGrupo = new grupo(rs.getInt("CODIGO"),
                        rs.getInt("CICLO_ID"),
                        rs.getInt("NUM_GRUPO"),
                        rs.getInt("CURSO_ID"),
                        rs.getString("HORARIO"),
                        rs.getInt("PROFESOR_ID"));
                coleccion.add(eGrupo);
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
