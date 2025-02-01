package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Asset;
import Models.DatabaseConnection;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML private TableView<Asset> assetsTable;
    @FXML private TableColumn<Asset, Integer> colId;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colCategory;
    @FXML private TableColumn<Asset, String> colStatus;
    @FXML private TableColumn<Asset, String> colcreated_at;
    @FXML private Button logoutButton;
    @FXML private TextField search;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colcreated_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        loadAssets();
         search.textProperty().addListener(new ChangeListener<String>() {
          
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });
    }

    private void loadAssets() {
        assetsTable.getItems().clear();
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "SELECT * FROM assets";
            PreparedStatement statement = connection.prepareStatement(query);
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

    public void approveAsset() {
        updateAssetStatus("approved");
    }

    public void rejectAsset() {
        updateAssetStatus("rejected");
    }

    public void deleteAsset() {
        Asset selected = assetsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try (Connection connection = DatabaseConnection.connect()) {
                String query = "DELETE FROM assets WHERE id_asset = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, selected.getId());
                statement.executeUpdate();
                loadAssets();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Pilih aset terlebih dahulu!");
        }
    }

    private void updateAssetStatus(String status) {
        Asset selected = assetsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try (Connection connection = DatabaseConnection.connect()) {
                String query = "UPDATE assets SET status = ? WHERE id_asset = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, status);
                statement.setInt(2, selected.getId());
                statement.executeUpdate();
                loadAssets();
                logActivity(1, "Mengubah status aset menjadi " + status, selected.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Pilih aset terlebih dahulu!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
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
    public void search(String keyword) {
        assetsTable.getItems().clear();
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "SELECT * FROM assets WHERE name LIKE ? OR category LIKE ? OR status LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
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
     public void logactivity1() {
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
