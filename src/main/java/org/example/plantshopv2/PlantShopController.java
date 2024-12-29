package org.example.plantshopv2;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class PlantShopController {

    @FXML
    private ListView<Plant> plantListView;
    @FXML
    private ImageView plantImageView;
    @FXML
    private Label plantDescriptionLabel;
    @FXML
    private Button addToCartButton;
    @FXML
    private TableView<Plant> cartTable;
    @FXML
    private TableColumn<Plant, String> cartNameColumn;
    @FXML
    private TableColumn<Plant, Double> cartPriceColumn;
    @FXML
    private Label totalPriceLabel;

    private final ObservableList<Plant> plants = FXCollections.observableArrayList();
    private final CartService cartService = new CartService();

    @FXML
    public void initialize() {
        // Add plants to the list
        plants.addAll(
                new Plant("Cactus", 10.99, "A small cactus", new javafx.scene.image.Image(getClass().getResource("images/cactus.jpg").toExternalForm())),
                new Plant("Fern", 15.49, "A lush green fern", new javafx.scene.image.Image(getClass().getResource("images/fern.jpg").toExternalForm())),
                new Plant("Bonsai", 50.00, "A miniature bonsai tree", new javafx.scene.image.Image(getClass().getResource("images/bonsai.jpg").toExternalForm()))
        );

        plantListView.setItems(plants);
        cartTable.setItems(cartService.getCart());

        // Bind columns to Plant properties
        cartNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        cartPriceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());

        // Bind total price label
        totalPriceLabel.textProperty().bind(Bindings.format("Total: $%.2f", Bindings.createDoubleBinding(cartService::getTotalPrice, cartService.getCart())));

        // Add listener for selected plant
        plantListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                plantImageView.setImage(newSelection.getImage());
                plantDescriptionLabel.setText(newSelection.getDescription());
            }
        });
    }

    @FXML
    private void handleAddToCart() {
        Plant selectedPlant = plantListView.getSelectionModel().getSelectedItem();
        if (selectedPlant != null) {
            cartService.addPlantToCart(selectedPlant);
        }
    }
}
