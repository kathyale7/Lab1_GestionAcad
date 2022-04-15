/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Logica.curso;
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
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ksand
 */
public class ServiceCurso extends Service {

    private static final String insertarCurso = "{call INSERTAR_CURSO (?, ?, ?, ?,?,?)}";
    private static final String listarCurso = "{?=call LISTAR_CURSO ()}";
    private static final String buscarCurso = "{?=call BUSCAR_CURSO (?)}";
    private static final String buscarCurso_carrera = "{?=call BUSCAR_CURSO_CARRERA (?)}";
    private static final String modificarCurso = "{call MODIFICAR_CURSO (?, ?, ?, ?,?,?)}";
    private static final String eliminarCurso = "{call ELIMINAR_CURSO (?)}";

    public ServiceCurso() {
    }

    public Collection listarCurso() throws SQLException, NoDataException, GlobalException {
        try {
            conectar();
        } catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException ("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collecion = new ArrayList();
        curso eCurso = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(listarCurso);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()){
                eCurso = new curso (rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getInt("creditos"),
                rs.getInt("horas_semanales"),
                rs.getInt("carrera_id"),
                rs.getInt("ciclo_id"));
                collecion.add(eCurso);
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

   

    public void insertarCurso(curso eCurso) throws GlobalException, NoDataException{
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
			 
			pstmt = conexion.prepareCall(insertarCurso);
			pstmt.setInt(1, eCurso.getCodigo());
			pstmt.setString(2, eCurso.getNombre());
			pstmt.setInt(3, eCurso.getCreditos());
			pstmt.setInt(4, eCurso.getHoras_semanales());
			pstmt.setInt(5, eCurso.getCarrera_id());
                        pstmt.setInt(6, eCurso.getCiclo_id());
			
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
    public void modificarCurso(curso eCurso) throws GlobalException, NoDataException {
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
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareStatement(buscarCurso);
			pstmt.setInt(1, eCurso.getCodigo());
			pstmt.setString(2, eCurso.getNombre());
			pstmt.setInt(3, eCurso.getCreditos());
			pstmt.setInt(4, eCurso.getHoras_semanales());
			pstmt.setInt(5, eCurso.getCarrera_id());
                        pstmt.setInt(6, eCurso.getCiclo_id());
			int resultado = pstmt.executeUpdate();

			//si es diferente de 0 es porq si afecto un registro o mas
			if (resultado != 0)
			{
				throw new NoDataException("No se realizo la actualización");
			}
			else
			{
				System.out.println("\nModificación Satisfactoria!");
			}
		}
		catch (SQLException e)
		{
			throw new GlobalException("Sentencia no valida");
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

	public void eliminarCurso(int id) throws GlobalException, NoDataException {
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
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareStatement(eliminarCurso);
			pstmt.setInt(1, id);

			int resultado = pstmt.executeUpdate();

			if (resultado != 0)
			{
				throw new NoDataException("No se realizo el borrado");
			}
			else
			{
				System.out.println("\nEliminación Satisfactoria!");
			}
		}
		catch (SQLException e)
		{
			throw new GlobalException("Sentencia no valida");
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
	public curso buscarCurso(int id) throws GlobalException, NoDataException {

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
		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
		curso eCurso = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(buscarCurso);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setInt(2, id);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				eCurso = new curso(rs.getInt("codigo"),
									   rs.getString("nombre"),
									   rs.getInt("creditos"),
									   rs.getInt("horas_semanales"),
									   rs.getInt("carrera_id"),
									   rs.getInt("ciclo_id"));
				coleccion.add(eCurso);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
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
		if (coleccion == null || coleccion.size() == 0)
		{
			throw new NoDataException("No hay datos");
		}
		return eCurso;
	}

        public curso buscarCurso_carrera(String carrera_id) throws GlobalException, NoDataException
	{

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
		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
		curso eCurso = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(buscarCurso_carrera);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, carrera_id);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				eCurso = new curso(rs.getInt("codigo"),
									   rs.getString("nombre"),
									   rs.getInt("creditos"),
									   rs.getInt("horas_semanales"),
									   rs.getInt("carrera_id"),
									   rs.getInt("ciclo_id"));
				coleccion.add(eCurso);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
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
		if (coleccion == null || coleccion.size() == 0)
		{
			throw new NoDataException("No hay datos");
		}
		return eCurso;
	}

}
