package com.svalero.fruityvice.api.service;

import com.svalero.fruityvice.api.model.FruitInformation;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Representa a la API que vamos a consultar
 */
public interface FruityViceAPI {

    /**
     * Creamos un GET con la URL que vamos a pasarle a la API con los datos de nuestro objeto java
     * Esto lo revisamos en la documentación de la API
     */
    @GET("/api/fruit/all")
    Observable<List<FruitInformation>> getInformation(); //Va a ser un observable lo que recibimos de la API necesitamos RxJava.
}
