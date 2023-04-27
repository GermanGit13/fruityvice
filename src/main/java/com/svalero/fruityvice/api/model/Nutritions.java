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
public class Nutritions {

    private float carbohydrates; //Carbohydrates of the Fruit (per 100g) in gramm.
    private float protein; // Protein of the Fruit (per 100g) in gramm.
    private float fat; //Fat of the Fruit (per 100g) in gramm.
    private int calories; //Calories of the Fruit (per 100g) in gramm.
    private float sugar; //Sugar of the Fruit (per 100g) in gramm.
}
