package com.svalero.fruityvice.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.model.Nutritions;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FruitService {

    /**
     * Clase de Servicio donde creamos toda la infraesctructura que RetroFit necesita para crear conectarse a dicha API
     */

    private String BASE_URL = "https://fruityvice.com"; //URL de la API a la que nos conectamos
    private FruityViceAPI fruityViceAPI; //Llamamos a la interface creada

    /**
     * Constructor para crear un cliente con Retrofit segun su documentacion:
     *              Creamos el Parseador de Gson
     *              Creamos el cliente de Retrofit
     *              Creamos el adaptador RxJAva para hacerlo Observable
     *              Conectamos a la API definida
     */
    public FruitService() {
        /**
         * Configuramos el log
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC); //definimos un nivel de debugeo
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder()
                .setLenient() //Lo configuramos
                .create(); //Se crea

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //Le pasamos la URL base de la API
                .client(client) //pasamos el cliente del log
                .addConverterFactory(GsonConverterFactory.create(gsonParser)) //Le añadimos el parseados Gson creado
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Usamos el adaptador de RxJava para crear el observable
                .build(); //Lo creamos

        this.fruityViceAPI = retrofit.create(FruityViceAPI.class); //Lo conectamos a la API que hemos definido
    }

    /**
     * Consulta de un Observable: Devolvemos el listado completo sin los valores nutricionales
     * Observable de FruitInformation
     */
    public Observable<FruitInformation> getFruits() {
        return this.fruityViceAPI.getInformation().flatMapIterable(fruitInformation -> fruitInformation); //Aplanamos la lista que recibimos a un objeto fruitInformation
    }

    /**
     * Consulta de un Observable: Devolvemos el listado detallado de las frutas por familias
     * Observable de FruitInformation
     */
    public Observable<FruitInformation> getFamily(String family) {
        return this.fruityViceAPI.getFamily(family).flatMapIterable(fruitInformation -> fruitInformation); //Aplanamos la lista que recibimos a un objeto fruitInformation
    }

    /**
     * Consulta de un Observable: Devolvemos el listado detallado de las frutas por familias
     * Observable de FruitInformation
     */
    public Observable<FruitInformation> getId(String id) {
        return this.fruityViceAPI.getId(id);
    }
//    public Observable<Nutrition> getId(String id) {
//        return this.fruityViceAPI.getId(id).flatMapIterable(fruitInformation -> fruitInformation)
//                .map(fruitInformation -> fruitInformation.getNutrition()).flatMapIterable(nutritions -> nutritions); //Aplanamos la lista que recibimos a un objeto fruitInformation
//    }
}
