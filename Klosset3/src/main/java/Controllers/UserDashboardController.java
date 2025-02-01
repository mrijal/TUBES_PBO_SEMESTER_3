package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import Models.Asset;
import Models.DatabaseConnection;
import com.mycompany.klosset3.App;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.Parent;

public class UserDashboardController {

    @FXML private TableView<Asset> assetsTable;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colCategory;
    @FXML private TableColumn<Asset, String> colStatus;
    @FXML private TableColumn<Asset, String> colDate;
    @FXML private Button logoutButton;

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadAssets();
    }

    public void loadAssets() {
        assetsTable.getItems().clear();
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "SELECT * FROM assets WHERE created_by = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 1); // Ganti dengan ID user yang login
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                assetsTable.getItems().add(new Asset(
                        resultSet.getInt("id_asset"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getString("created_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAddAssetForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add_asset.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Ambil controller dari form tambah aset
            AddAssetController addAssetController = loader.getController();
            addAssetController.setDashboardController(this); // Hubungkan dengan dashboard

            stage.setTitle("Ajukan Aset");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
    try {
        
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.close(); 

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/klosset3/login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(loader.load()));
        loginStage.setTitle("Login");
        loginStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public void logactivity() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/log_activity.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Ambil controller dari form tambah aset
            LogActivityController LogActivityController = loader.getController();
            LogActivityController.setDashboardController(this); // Hubungkan dengan dashboard

            stage.setTitle("Ajukan Aset");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
   
