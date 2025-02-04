package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Models.DatabaseConnection;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import Models.BarcodeGenerator;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddAssetController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> categoryBox;
    @FXML private TextArea descriptionField;

    private UserDashboardController dashboardController; // Tambahkan variabel
    

    @FXML
    public void initialize() {
        categoryBox.getItems().addAll("Elektronik", "Kendaraan", "Properti", "Lainnya");
    }

    public void setDashboardController(UserDashboardController controller) {
        this.dashboardController = controller;
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
        String query = "INSERT INTO assets (name, category, description, status, created_by, barcode_path) VALUES (?, ?, ?, 'pending', ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.setString(2, category);
        statement.setString(3, description);
        statement.setInt(4, 2);
        String barcode_path = "src/main/resources/barcodes/asset_" + name + ".png";
        BarcodeGenerator.generateBarcode( name, category, description);
        statement.setString(5, barcode_path);
        
        

        statement.executeUpdate();
        
        

        // Ambil ID aset yang baru saja dibuat
        int assetId = -1;
        var rs = statement.getGeneratedKeys();
        if (rs.next()) {
            assetId = rs.getInt(1);
            BarcodeGenerator.generateBarcode( name, category, description);
        }

        // Tambahkan log aktivitas
        if (assetId != -1) {
            logActivity(2, "Mengajukan aset", assetId);
            
        }

        showAlert("Aset berhasil diajukan!");

        if (dashboardController != null) {
            dashboardController.loadAssets(); // Perbarui tabel setelah submit
        }

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
    private void logActivity(int userId, String action, int assetId) {
    try (Connection connection = DatabaseConnection.connect()) {
        String query = "INSERT INTO activity_logs (id_user, action, id_asset, timestamp) VALUES (?, ?, ?, NOW())";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setString(2, action);
        statement.setInt(3, assetId);
        statement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
