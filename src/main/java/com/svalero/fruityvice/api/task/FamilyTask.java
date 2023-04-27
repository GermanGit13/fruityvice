package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

public class FamilyTask extends Task {

    public Consumer<FruitInformation> userFamily; //Oservador que consume las Familias
    private String family; //Para seleccionar la busqueda de todas as frutas
    private ProgressIndicator progressIndicator;

    public FamilyTask(String family, Consumer<FruitInformation> userFamily, ProgressIndicator progressIndicator) {
        this.family = family;
        this.userFamily = userFamily;
        this.progressIndicator = progressIndicator;
    }

    @Override
    protected Object call() throws Exception {
        this.progressIndicator.setVisible(true); //Hacemos visible el Indicador para traernos los datos

        FruitService fruitServiceFamily = new FruitService(); //creamos el servicio qeu se conecta con la api
        fruitServiceFamily.getFamily(family).subscribe(userFamily); //nos subscribimos al observador

        return null;
    }
}
