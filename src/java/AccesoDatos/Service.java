/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Service {
//    private peliculasDAO peliculasDAO;

    
    private static Service theInstance;
    
    
    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    public Service() {
//        this.peliculasDAO = new peliculasDAO();
        
    }
    
    
    /*usuarios*/
    
//    public usuarios crearUsuario(usuarios u){
//        usuarios result = null;
//        
//        try {
//            result = usuariosDAO.create(u);
//        } catch (Exception ex) {
//            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            return result;
//    }
//    
//    public usuarios login(usuarios u){
//        usuarios result = null;
//
//        try {
//            result = usuariosDAO.readbyId(u.getId());
//            
//            
//            
//        } catch (Exception ex) {
//            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        
//        if (result == null ||
//                (!result.getContrasenna().equals(u.getContrasenna())))
//        {
//            return null;
//        }
//        return result;
//        
//    }
    
    
    
//    public usuarios readbyidU(String id) throws Exception{
//        return usuariosDAO.readbyId(id);
//    }
    
    
  
      
}
