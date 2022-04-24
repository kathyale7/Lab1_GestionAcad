/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.ServiceGrupo;
import Logica.grupo;
import java.util.List;

/**
 *
 * @author ksand
 */
public class model_grupo {
    ServiceGrupo s;
    grupo grupo_actual;
    List<grupo> lista_grupos;

    public model_grupo(ServiceGrupo s, grupo grupo_actual, List<grupo> lista_grupos) {
        this.s = s;
        this.grupo_actual = grupo_actual;
        this.lista_grupos = lista_grupos;
    }
    public model_grupo() {
        
    }

    public ServiceGrupo getS() {
        return s;
    }

    public void setS(ServiceGrupo s) {
        this.s = s;
    }

    public grupo getGrupo_actual() {
        return grupo_actual;
    }

    public void setGrupo_actual(grupo grupo_actual) {
        this.grupo_actual = grupo_actual;
    }

    public List<grupo> getLista_grupos() {
        return lista_grupos;
    }

    public void setLista_grupos(List<grupo> lista_grupos) {
        this.lista_grupos = lista_grupos;
    }
    
    
}
