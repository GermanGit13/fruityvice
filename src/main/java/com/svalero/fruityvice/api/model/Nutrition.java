package com.svalero.fruityvice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Definimos el modelo: coincide con los datos que vamos a recibir de la API por el json
 * Esto lo encontramos en la documentación de la API
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {

    private int carbohydrates; //Carbohydrates of the Fruit (per 100g) in gramm.
    private int protein; // Protein of the Fruit (per 100g) in gramm.
    private int fat; //Fat of the Fruit (per 100g) in gramm.
    private int calories; //Calories of the Fruit (per 100g) in gramm.
    private int sugar; //Sugar of the Fruit (per 100g) in gramm.
}
