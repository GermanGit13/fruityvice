package com.svalero.fruityvice.api.controller;

import com.svalero.fruityvice.api.model.FruitInformation;
import com.svalero.fruityvice.api.model.Nutritions;
import com.svalero.fruityvice.api.task.FamilyTask;
import com.svalero.fruityvice.api.task.FruitTask;
import com.svalero.fruityvice.api.task.FruitsTask;
import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.List;

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
    private Button btDetailsFruit;
    @FXML
    private Button btAdd;
    @FXML
    private Button btCompress;
    @FXML
    private Button btExport;
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
    private TextField tfDetailId;
    @FXML
    private TextField tfDetailName;
    @FXML
    private TextField tfDetailFamily;
    @FXML
    private TextField tfDetailGenus;
    @FXML
    private TextField tfDetailOrder;
    @FXML
    private TextField tfDetailCarbo;
    @FXML
    private TextField tfDetailProtein;
    @FXML
    private TextField tfDetailFat;
    @FXML
    private TextField tfDetailCalories;
    @FXML
    private TextField tfDetailSugar;
    @FXML
    private TextArea listAllArea;
    @FXML
    private TextArea listFamily;
    @FXML
    private TextArea listDetailArea;
    @FXML
    private ProgressIndicator piChargeData;
    @FXML
    private ProgressBar pgChargeData;

    private FruitsTask fruitsTask;
    private FamilyTask familyTask;
    private FruitTask fruitTask;

    private List<String> fruitInformations; //Para guardar las datos recibidos de la API en este caso son definiciones de palabras
    private ArrayList<String> fruitDetails; //Para guardar el detalle de una fruta

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
            this.fruitInformations.add(fruitInformation.getGenus() + fruitInformation.getName() + fruitInformation.getId() + fruitInformation.getFamily() + fruitInformation.getOrder() + fruitInformation.getNutritions().getCarbohydrates() + fruitInformation.getNutritions().getProtein() + fruitInformation.getNutritions().getFat() + fruitInformation.getNutritions().getCalories() + fruitInformation.getNutritions().getSugar());
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
            this.fruitInformations.add(fruitInformation.getGenus() + fruitInformation.getName() + fruitInformation.getId() + fruitInformation.getFamily() + fruitInformation.getOrder() + fruitInformation.getNutritions().getCarbohydrates() + fruitInformation.getNutritions().getProtein() + fruitInformation.getNutritions().getFat() + fruitInformation.getNutritions().getCalories() + fruitInformation.getNutritions().getSugar());
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
        this.fruitDetails = new ArrayList<String>();

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

//            this.fruitDetails.add(fruitInformation.getGenus() + fruitInformation.getName() + fruitInformation.getId() + fruitInformation.getFamily() + fruitInformation.getOrder() + fruitInformation.getNutritions().getCarbohydrates() + fruitInformation.getNutritions().getProtein() + fruitInformation.getNutritions().getFat() + fruitInformation.getNutritions().getCalories() + fruitInformation.getNutritions().getSugar());
            this.fruitDetails.add(String.valueOf(fruitInformation.getId()));
            this.fruitDetails.add(fruitInformation.getName());
            this.fruitDetails.add(fruitInformation.getGenus());
            this.fruitDetails.add(fruitInformation.getFamily());
            this.fruitDetails.add(fruitInformation.getOrder());
            this.fruitDetails.add(String.valueOf(fruitInformation.getNutritions().getCarbohydrates()));
            this.fruitDetails.add(String.valueOf(fruitInformation.getNutritions().getProtein()));
            this.fruitDetails.add(String.valueOf(fruitInformation.getNutritions().getFat()));
            this.fruitDetails.add(String.valueOf(fruitInformation.getNutritions().getCalories()));
            this.fruitDetails.add(String.valueOf(fruitInformation.getNutritions().getSugar()));
            this.pgChargeData.setVisible(false);
        });

        fruitTask = new FruitTask(id, userId, pgChargeData);
        new Thread(this.fruitTask).start();
    }

    /**
     * Método que se realizara al pulsar el boton en el entorno gráfico
     * esta definido en el onAction
     * Cargara los detalles completos de una fruta desde un listado obtenido al listar todas las frutas
     */
    @FXML
    public void detailsFruit(ActionEvent event) {
//        this.listDetailArea.setText("");

        /**
         * Recogemos los datos de la lista creada al consultar los valores nutricionales para crear un objeto FruitInformation
         */
        int id = Integer.parseInt(fruitDetails.get(0).toString());
        String name = fruitDetails.get(1).toString();
        String genues = fruitDetails.get(2).toString();
        String family = fruitDetails.get(3).toString();
        String order = fruitDetails.get(4).toString();
        float carbohydrates = Float.parseFloat(fruitDetails.get(5).toString());
        float protein = Float.parseFloat(fruitDetails.get(6).toString());
        float fat = Float.parseFloat(fruitDetails.get(7).toString());
        int calories = Integer.parseInt(fruitDetails.get(8).toString());
        float sugar = Float.parseFloat(fruitDetails.get(9).toString());

        Nutritions newNutrition = new Nutritions(carbohydrates, protein, fat, calories, sugar);
        FruitInformation newFruit = new FruitInformation(genues, name, id, family, order, newNutrition);

        tfDetailId.setText(String.valueOf(newFruit.getId()));
        tfDetailName.setText(newFruit.getName());
        tfDetailFamily.setText(newFruit.getFamily());
        tfDetailGenus.setText(newFruit.getGenus());
        tfDetailOrder.setText(newFruit.getOrder());
        tfDetailCarbo.setText(String.valueOf(newFruit.getNutritions().getCarbohydrates()));
        tfDetailProtein.setText(String.valueOf(newFruit.getNutritions().getProtein()));
        tfDetailFat.setText(String.valueOf(newFruit.getNutritions().getFat()));
        tfDetailCalories.setText(String.valueOf(newFruit.getNutritions().getCalories()));
        tfDetailSugar.setText(String.valueOf(newFruit.getNutritions().getSugar()));

//        this.listDetailArea.setText(listDetailArea.getText() + "\n" + "ID: " + newFruit.getId());
//        this.listDetailArea.setText(listDetailArea.getText() + "\n" + "Name:  " + newFruit.getName());
//        for (String fruitDetail : this.fruitDetails) { //recorremos la lista para volver a pintarla en el text area sin la definion que borramos
//            this.listDetailArea.setText(listDetailArea.getText() + "\n" + fruitDetail); //lo mostramos en el text Area
//        }
    }
}
