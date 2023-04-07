package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class FamilyTask extends Task {

    public Consumer<FruitInformation> userFamily; //Oservador que consume las Familias
    private String family; //Para seleccionar la busqueda de todas as frutas

    public FamilyTask(String family, Consumer<FruitInformation> userFamily) {
        this.family = family;
        this.userFamily = userFamily;
    }

    @Override
    protected Object call() throws Exception {
        FruitService fruitServiceFamily = new FruitService(); //creamos el servicio qeu se conecta con la api
        fruitServiceFamily.getFamily(family).subscribe(userFamily); //nos subscribimos al observador

        return null;
    }
}
