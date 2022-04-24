/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;


import Logica.usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ksand
 */
public class ServiceLogin extends Service {
    private static final String buscarUsuario = "{?=call BUSCAR_USUARIO (?)}";
    
    public ServiceLogin() {
    }
    
    public usuario buscarUsuario(String id) throws GlobalException, NoDataException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        usuario eUsuario = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(buscarUsuario);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                eUsuario = new usuario(rs.getString("USUARIO_ID"),
                        rs.getInt("ROL_ID"),
                        rs.getString("CLAVE"));
                coleccion.add(eUsuario);
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
        return eUsuario;
    }

    public usuario login(usuario u) throws Exception{
        usuario result = null;
        
        result = this.buscarUsuario(u.getUSUARIO_ID());
        if(result == null)
            throw new Exception("Usuario no encontrado");
        if(!(result.getCLAVE()).equals(u.getCLAVE())){
            throw new Exception("Usuario con mala contrasenna");
        }
        
        return result;
    }
}
