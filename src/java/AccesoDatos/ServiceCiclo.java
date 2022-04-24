/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;


import Logica.ciclo;
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

public class ServiceCiclo extends Service {


    private static final String listarCiclo = "{?=call LISTAR_CICLO ()}";
    private static final String buscarCiclo = "{?=call BUSCAR_CICLO (?)}";
    private static final String buscarCiclo_Codigo = "{?=call BUSCAR_CICLO_Codigo (?)}";
    private static final String modificarCiclo = "{call MODIFICAR_CICLO (?, ?, ?, ?, ?, ?)}";


    public ServiceCiclo() {
    }

    public Collection listarCiclo() throws SQLException, NoDataException, GlobalException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collecion = new ArrayList();
        ciclo eCiclo = null;
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(listarCiclo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eCiclo = new ciclo(rs.getInt("CODIGO"),
                        rs.getString("ANHO"),
                        rs.getInt("NUMERO"),
                        rs.getString("FECHA_INICIO"),
                        rs.getString("FECHA_FINAL"),
                        rs.getInt("ES_DEFAULT"));
                collecion.add(eCiclo);
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

    public void modificarCiclo(ciclo eCiclo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(modificarCiclo);
            pstmt.setInt(1, eCiclo.getCodigo());
            pstmt.setString(2, eCiclo.getANHO());
            pstmt.setInt(3, eCiclo.getNUMERO());
            pstmt.setString(4, eCiclo.getFECHA_INICIO());
            pstmt.setString(5, eCiclo.getFECHA_FINAL());
            pstmt.setInt(6, eCiclo.getES_DEFAULT());

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

    public Collection buscarCiclo(String anho) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        ciclo eCiclo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarCiclo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, anho);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eCiclo = new ciclo(rs.getInt("CODIGO"),
                        rs.getString("ANHO"),
                        rs.getInt("NUMERO"),
                        rs.getString("FECHA_INICIO"),
                        rs.getString("FECHA_FINAL"),
                        rs.getInt("ES_DEFAULT"));
                coleccion.add(eCiclo);
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
  
   public ciclo buscarCiclo_Codigo(int codigo) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        ciclo eCiclo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarCiclo_Codigo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, codigo);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eCiclo = new ciclo(rs.getInt("CODIGO"),
                        rs.getString("ANHO"),
                        rs.getInt("NUMERO"),
                        rs.getString("FECHA_INICIO"),
                        rs.getString("FECHA_FINAL"),
                        rs.getInt("ES_DEFAULT"));
                coleccion.add(eCiclo);
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
        return eCiclo;
    }
   
}
