/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.gatosapp;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.management.Agent;

/**
 *
 * @author juan.leyva.hernandez
 */
public class ServicioGato {
    public static void verGatos() throws IOException{
        
        //Vamos a traer los datos de la API de gatos
       OkHttpClient client = new OkHttpClient();
       Request request = new Request.Builder()//Realiza la peticion a la API con el metodo GET
       .url("https://api.thecatapi.com/v1/images/search").method("GET", null)
       .build();
       Response response = client.newCall(request).execute();//Ejecutamos la peticion y la guardamos en un objeto response
       
       String elJson = response.body().string();//Almacenamos la respuesta en una variable tipo String
        
        //cortar los corchetes para poder acceder a la API
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);
        
        //crear u objeto de la clase Gson
        /* Enviamos el contenido de nuestro objeto JSON a nuestra clase gato */
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);
        
        //redimensionar en caso de necesitar
        Image image = null;
        /* Obtenemos la imagen llamando la URL recibida desde la API */
        try{
            URL url = new URL(gatos.getUrl());
            
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            
            //image = ImageIO.read(url);
            
                  
            ImageIcon fondoGato = new ImageIcon(bufferedImage);
            
            /* Redimencionamos nuestra imagen usando metodos de la clase abstracta image */
            if(fondoGato.getIconWidth() > 800 || fondoGato.getIconHeight() > 400){
                //redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            
            String menu = "Opciones: \n"
                    + " 1. Ver otra imagen \n"
                    + " 2. Favorito \n"
                    + " 3. Volver \n";
            
            String[] botones = { "Ver otra imagen", "Favorito", "Volver" };
            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato, 
                    JOptionPane.INFORMATION_MESSAGE, fondoGato, botones,botones[0]);
            
            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }
            
            switch (seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
            
            
        }catch(IOException e){
               System.out.println(e);
        }
    }
    
    public static void favoritoGato(Gatos gato){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+gato.getId()+"\"\n}");
            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites")
              .post(body)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", gato.getApiKey())
              .build();
            Response response = client.newCall(request).execute();            
                  
        }catch(IOException e){
            System.out.println(e);
        }
        
        
    }
    
    public static void verFavorito(String apiKey) throws IOException{
    
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
          .url("https://api.thecatapi.com/v1/favourites")
          .get()
          .addHeader("Content-Type", "application/json")
          .addHeader("x-api-key", apiKey)
          .build();

        Response response = client.newCall(request).execute();
        
        // guardamos el string con la respuesta
        String elJson = response.body().string();
        
        //creamos el objeto gson
        Gson gson = new Gson();
        
        GatosFav[] gatosArray = gson.fromJson(elJson,GatosFav[].class);
        
        if(gatosArray.length > 0){
            int min = 1;
            int max  = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max-min)+1)) + min;
            int indice = aleatorio-1;
            
            GatosFav gatofav = gatosArray[indice];
            
            //redimensionar en caso de necesitar
        Image image = null;
        /* Obtenemos la imagen llamando la URL recibida desde la API */
        try{
            URL url = new URL(gatofav.image.getUrl());
            //image = ImageIO.read(url);
            
            
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            
            //image = ImageIO.read(url);
            
                  
            ImageIcon fondoGato = new ImageIcon(bufferedImage);
            
            /* Redimencionamos nuestra imagen usando metodos de la clase abstracta image */
            if(fondoGato.getIconWidth() > 800 || fondoGato.getIconHeight() > 400){
                //redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            
            String menu = "Opciones: \n"
                    + " 1. Ver otra imagen \n"
                    + " 2. Eliminar Favorito \n"
                    + " 3. Volver \n";
            
            String[] botones = { "Ver otra imagen", "Eliminar Favorito", "Volver" };
            String id_gato = gatofav.getIdFav();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato, 
                    JOptionPane.INFORMATION_MESSAGE, fondoGato, botones,botones[0]);
            
            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }
            
            switch (seleccion){
                case 0:
                    verFavorito(apiKey);
                    break;
                case 1:
                    borrarFavorito(gatofav);
                    break;
                default:
                    break;
            }
            
            
        }catch(IOException e){
               System.out.println(e);
        }
            
        }
    
}
   
    public static void borrarFavorito(GatosFav gatofav){
        
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites/"+gatofav.getIdFav()+"")
              .delete(null)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", gatofav.getApikey())
              .build();

            Response response = client.newCall(request).execute();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }

}
