package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddAssetController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> categoryBox;
    @FXML private TextArea descriptionField;

    @FXML
    public void initialize() {
        categoryBox.getItems().addAll("Elektronik", "Kendaraan", "Properti", "Lainnya");
    }

    public void submitAsset() {
        String name = nameField.getText();
        String category = categoryBox.getValue();
        String description = descriptionField.getText();

        if (name.isEmpty() || category == null || description.isEmpty()) {
            showAlert("Harap isi semua field!");
            return;
        }

        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO assets (name, category, description, status, created_by) VALUES (?, ?, ?, 'pending', ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setString(3, description);
            statement.setInt(4, 1); // Ganti dengan ID user yang login
            statement.executeUpdate();

            showAlert("Aset berhasil diajukan!");
            closeForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeForm() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }
}
