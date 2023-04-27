package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import org.apache.commons.lang3.time.StopWatch;

import java.time.Instant;

public class FruitsTask extends Task {

    public Consumer<FruitInformation> user; //Oservador que consume las fruits
    private ProgressIndicator progressIndicator; //Para usar el indicador

    public FruitsTask(Consumer<FruitInformation> user, ProgressIndicator progressIndicator) {
        this.user = user;
        this.progressIndicator = progressIndicator;
    }

    @Override
    protected Object call() throws Exception {
        this.progressIndicator.setVisible(true); //Hacemos visible el Indicador para traernos los datos

        FruitService fruitService = new FruitService(); //creamos el servicio qeu se conecta con la api
        fruitService.getFruits().subscribe(user); //nos subscribimos al observador

        return null;
    }

}
