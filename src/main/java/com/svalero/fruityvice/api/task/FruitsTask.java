package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class FruitsTask extends Task {

    public Consumer<FruitInformation> user; //Oservador que consume las fruits

    public FruitsTask(Consumer<FruitInformation> user) {
        this.user = user;
    }

    @Override
    protected Object call() throws Exception {
        FruitService fruitService = new FruitService(); //creamos el servicio qeu se conecta con la api
        fruitService.getFruits().subscribe(user); //nos subscribimos al observador

        return null;
    }

}
