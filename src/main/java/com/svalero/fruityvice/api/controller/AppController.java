package com.svalero.fruityvice.api.controller;

import com.svalero.fruityvice.api.model.FruitInformation;
import io.reactivex.functions.Consumer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * declaramos los componentes que tenemos en el fmxl (scene builder)
 */
public class AppController {

    @FXML
    private Button btListAll;
    @FXML
    private TextArea listAllArea;

    private List<String> fruitInformations; //Para guardar las datos recibidos de la API en este caso son definiciones de palabras

    /**
     * Método que se realizara al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     */
    @FXML
    public void showAll(ActionEvent event) {
        this.fruitInformations = new ArrayList<>(); //creamos una lista nueva cada vez que pulsamos el botón buscar que invoca este método

        Consumer<FruitInformation> user = (fruitInformation -> {
            listAllArea.setText(listAllArea.getText() + "\n" + fruitInformation.getName() + "\n" + fruitInformation.getFamily()); //lo mostramos en el text Area
            this.fruitInformations.add(fruitInformation.getName() + fruitInformation.getFamily());
        });
    }
 }
