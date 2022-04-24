/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Logica.usuario;

/**
 *
 * @author ksand
 */
public class model_usuario {
    usuario current_user ;

    public model_usuario() {
        current_user= null;
    }

    public usuario getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(usuario current_user) {
        this.current_user = current_user;
    }
}
