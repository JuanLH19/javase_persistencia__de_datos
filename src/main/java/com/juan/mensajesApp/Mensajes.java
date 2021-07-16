/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.mensajesApp;

/**
 *
 * @author juan.leyva.hernandez
 */
public class Mensajes {
    int id_mensaje;
    String mensaje;
    String autor_mensaje;
    String fecha_mensaje;
    
    public Mensajes(){
        
    }

    public Mensajes(String mensaje, String autor_mensaje, String fecha_mensaje) {
        this.mensaje = mensaje;
        this.autor_mensaje = autor_mensaje;
        this.fecha_mensaje = fecha_mensaje;
    }
    
    

    public int getId_Mensaje() {
        return id_mensaje;
    }

    public void setId_Mensaje(int id_Mensaje) {
        this.id_mensaje = id_Mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAutor_Mensaje() {
        return autor_mensaje;
    }

    public void setAutor_Mensaje(String autor_Mensaje) {
        this.autor_mensaje = autor_Mensaje;
    }

    public String getFecha_Mensaje() {
        return fecha_mensaje;
    }

    public void setFecha_Mensaje(String fecha_Mensaje) {
        this.fecha_mensaje = fecha_Mensaje;
    }
    
    
    
}
