package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class FruitTask extends Task {

    private Consumer<FruitInformation> userId;
    private int id;

    public FruitTask(int id, Consumer<FruitInformation> userId) {
        this.userId = userId;
        this.id = id;
    }

    @Override
    protected Object call() throws Exception {

        FruitService fruitService = new FruitService();
        fruitService.getId(id).subscribe(userId);

        return null;
    }
}
