/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juan.gatosapp;

/**
 *
 * @author juan.leyva.hernandez
 */
public class GatosFav {
    String idFav;
    String image_id;
    String apikey= "b9c9e833-1857-46bc-a4c5-ddf321501902"; 
    Imagex image;        

    public String getIdFav() {
        return idFav;
    }

    public void setId(String id) {
        this.idFav = idFav;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }
}
