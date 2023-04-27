package com.svalero.fruityvice.api.task;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.model.Nutritions;
import com.svalero.fruityvice.api.service.FruitService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class FruitTask extends Task {

    private Consumer<FruitInformation> userId;
    private Consumer<Nutritions> nutritionConsumer;
    private String id;
    private ProgressBar progressBar;

    public FruitTask(String id, Consumer<FruitInformation> userId, ProgressBar progressBar) {
        this.userId = userId;
        this.id = id;
        this.progressBar = progressBar;
    }

    @Override
    protected Object call() throws Exception {
        this.progressBar.setVisible(true);

        FruitService fruitService = new FruitService();
        fruitService.getId(id).subscribe(userId);

        return null;
    }
}
