/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.gatosapp;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author juan.leyva.hernandez
 */
public class Main {
    
    public static void main(String[] args) throws IOException{
        int opcion_menu = -1;
        String[] botones = {" Ver un gato al azar", "Ver favoritos", "Salir"};
        
        do{
            
            //menu principal... JOptionPane nos provee un conjunto de 
            //ventanas de dialogo.
            String opcion = (String) JOptionPane.showInputDialog(null, 
                    "Gatitos java", "Menu principal", 
                    JOptionPane.INFORMATION_MESSAGE,
                    null, botones,botones[0]);
            
            //validamos que opcion selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    opcion_menu = i;
                }
            }
            
            switch(opcion_menu){
                case 0:
                    ServicioGato.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    ServicioGato.verFavorito(gato.getApiKey());
                default:
                    break;
            }     
        }while(opcion_menu != 1);
    }
    
    
    
}
