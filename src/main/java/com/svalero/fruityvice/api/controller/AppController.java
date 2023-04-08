package com.svalero.fruityvice.api.controller;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.model.Nutritions;
import com.svalero.fruityvice.api.task.FamilyTask;
import com.svalero.fruityvice.api.task.FruitTask;
import com.svalero.fruityvice.api.task.FruitsTask;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * declaramos los componentes que tenemos en el fmxl (scene builder)
 */
public class AppController {

    @FXML
    private Button btListAll;
    @FXML
    private Button btFamily;
    @FXML
    private Button btValorNutrition;
    @FXML
    private TextField tfFamily;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfIdFruit;
    @FXML
    private TextField tfCarbohydrates;
    @FXML
    private TextField tfProtein;
    @FXML
    private TextField tfFat;
    @FXML
    private TextField tfCalories;
    @FXML
    private TextField tfSugar;
    @FXML
    private TextArea listAllArea;
    @FXML
    private TextArea listFamily;
    @FXML
    private TextArea detailsFruit;
    @FXML
    private ProgressIndicator piChargeData;
    @FXML
    private ProgressBar pgChargeData;

    private FruitsTask fruitsTask;
    private FamilyTask familyTask;
    private FruitTask fruitTask;

    private List<String> fruitInformations; //Para guardar las datos recibidos de la API en este caso son definiciones de palabras

//    /**
//     * Implemento Intialize para usarlo con las listas y la barra de proceso
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        piChargeData.setVisible(true);
//    }

    /**
     * Método que se realizara al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     */
    @FXML
    public void showAll(ActionEvent event) {
        this.fruitInformations = new ArrayList<String>(); //creamos una lista nueva cada vez que pulsamos el botón buscar que invoca este método
        listAllArea.setText("");

        Consumer<FruitInformation> user = (fruitInformation -> {
            String previousText;
            previousText = listAllArea.getText() + "\n";
            Thread.sleep(100);
            this.listAllArea.setText(listAllArea.getText() + "\n" + "ID: " + fruitInformation.getId() + " - Name: " + fruitInformation.getName() + " - Family: " + fruitInformation.getFamily()); //lo mostramos en el text Area
            this.fruitInformations.add(fruitInformation.getId() + fruitInformation.getName() + fruitInformation.getFamily());
            this.piChargeData.setVisible(false);
        });

        //Forma directa para evitar error de la API
//        Consumer<FruitInformation> user = (fruitInformation -> {
//            listAllArea.setText(listAllArea.getText() + "\n" + "ID: " + fruitInformation.getId() + "- Name: " + fruitInformation.getName() + " - Family:" + fruitInformation.getFamily()); //lo mostramos en el text Area
//            this.fruitInformations.add(fruitInformation.getName());
//        });

        fruitsTask = new FruitsTask(user, piChargeData);
        new Thread(fruitsTask).start();
    }

    /**
     * Método que se realizará al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     */
    @FXML
    public void showFamily(ActionEvent event) {
        this.fruitInformations = new ArrayList<String>();
        String requestedFamily = tfFamily.getText(); //recogemos el valor de TextField con el nombre de la familia a buscar
        tfFamily.clear();
        tfFamily.requestFocus();
        this.listFamily.setText("");

        Consumer<FruitInformation> userFamily = (fruitInformation -> {
            String previousText;
            previousText = listFamily.getText() + "\n";
            Thread.sleep(100);
            this.listFamily.setText(listFamily.getText() + "\n" + "ID: " + fruitInformation.getId() + " - Name: " + fruitInformation.getName() + " - Family: " + fruitInformation.getFamily() + " - Calorias:" + fruitInformation.getNutritions().getCalories());
            this.fruitInformations.add(fruitInformation.getId() + fruitInformation.getName() + fruitInformation.getGenus()); //Añadimos a la lista para tenerla sin consultar a la API
            this.piChargeData.setVisible(false);
        });

        familyTask = new FamilyTask(requestedFamily, userFamily, piChargeData);
        new Thread(this.familyTask).start();
    }

    /**
     * Método que se realizará al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     */
    @FXML
    public void detailsNutritionFruit(ActionEvent event) {
        String id = tfIdFruit.getText();
        tfIdFruit.clear();
        tfIdFruit.requestFocus();;

        Consumer<FruitInformation> userId = (fruitInformation -> {
            tfName.setText(fruitInformation.getName());
            tfCarbohydrates.setText(String.valueOf(fruitInformation.getNutritions().getCarbohydrates()));
            tfProtein.setText(String.valueOf(fruitInformation.getNutritions().getProtein()));
            tfFat.setText(String.valueOf(fruitInformation.getNutritions().getFat()));
            tfCalories.setText(String.valueOf(fruitInformation.getNutritions().getCalories()));
            tfSugar.setText(String.valueOf(fruitInformation.getNutritions().getSugar()));
            this.pgChargeData.setVisible(false);
        });

        fruitTask = new FruitTask(id, userId, pgChargeData);
        new Thread(this.fruitTask).start();
    }


}
