package com.svalero.fruityvice.api.controller;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.task.FruitTask;
import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


import java.util.ArrayList;
import java.util.List;

/**
 * declaramos los componentes que tenemos en el fmxl (scene builder)
 */
public class AppController  {

    @FXML
    private Button btListAll;
    @FXML
    private TextArea listAllArea;
    private FruitTask fruitTask;
    private String all; //Para solicitar todas las frutas en Get all

    private List<String> fruitInformations; //Para guardar las datos recibidos de la API en este caso son definiciones de palabras

    /**
     * Método que se realizara al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     */
    @FXML
    public void showAll(ActionEvent event) {
        this.fruitInformations = new ArrayList<String>(); //creamos una lista nueva cada vez que pulsamos el botón buscar que invoca este método
        this.all = "all";
        listAllArea.setText("");

        Consumer<FruitInformation> user = (fruitInformation -> {
            listAllArea.setText(listAllArea.getText() + "\n" + fruitInformation.getName() + " - Familia:" + fruitInformation.getFamily()); //lo mostramos en el text Area
            this.fruitInformations.add(fruitInformation.getName());
        });

        fruitTask = new FruitTask(user);
        new Thread(fruitTask).start();
    }
 }
