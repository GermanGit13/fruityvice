package com.svalero.fruityvice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Definimos el modelo: coincide con los datos que vamos a recibir de la API por el json
 * Esto lo encontramos en la documentación de la API
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitInformation {

    private String name; //Name of the Fruit.
    private int id; //ID of the Fruit.
    private String family; //Family of the Fruit.
    private String genus; //Genus of the Fruit.
    private String order; //Order of the Fruit.
    private ArrayList<Nutrition> nutritions; // List of Nutritions of the Fruit.
}
