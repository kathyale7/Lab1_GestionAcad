/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Logica.carrera;
import Logica.curso;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ksand
 */
public class ServiceCarrera extends Service {

    private static final String insertarCarrera = "{call INSERTAR_CARRERA (?, ?, ?)}";
    private static final String listarCarrera = "{?=call LISTAR_CARRERA ()}";
    private static final String buscarCarrera = "{?=call BUSCAR_CARRERA (?)}";
    private static final String modificarCarrera = "{call MODIFICAR_CARRERA (?, ?, ?)}";
    private static final String eliminarCarrera = "{call ELIMINAR_CARRERA (?)}";

    public ServiceCarrera() {
    }

    public Collection listarCarrera() throws SQLException, NoDataException, GlobalException {
//        try {
//            conectar();
//        } catch (ClassNotFoundException ex){
//            throw new GlobalException("No se ha localizado el Driver");
//        } catch (SQLException e) {
//            throw new NoDataException ("La base de datos no se encuentra disponible");
//        }

        ResultSet rs = null;
        ArrayList collecion = new ArrayList();
        carrera eCarrera = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(listarCarrera);
           // pstmt.registerOutParameter(1, );
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()){
                eCarrera = new carrera (rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getString("titulo"));
                collecion.add(eCarrera);
            } } catch (SQLException e){
                    e.printStackTrace();
                    throw new GlobalException("Sentencia no valida");
                    }
            finally {
                    try {
                    if(rs!=null){
                    rs.close();
                    } if (pstmt !=null){
                    pstmt.close();
                    }
                    desconectar();
                    }
                    catch (SQLException e) {
                    throw new GlobalException("Estatutos invalidos o nulos");
                    }
                    }
            if (collecion == null || collecion.size()==0){
                throw new NoDataException("No hay datos");
            }
            return collecion;
        }

   

    public void insertarCarrera(carrera eCarrera) throws GlobalException, NoDataException{
        try
		{
			conectar();
		}
		catch (ClassNotFoundException e)
		{
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		CallableStatement pstmt = null;

		try
		{
			 
			pstmt = conexion.prepareCall(insertarCarrera);
			pstmt.setInt(1, eCarrera.getCodigo());
			pstmt.setString(2, eCarrera.getNombre());
			pstmt.setString(3, eCarrera.getTitulo());
			
			
			boolean resultado = pstmt.execute();
			if (resultado == true)
			{
				throw new NoDataException("No se realizo la inserción");
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new GlobalException("Llave duplicada");
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
    }
    
}
