package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import models.Asset;
import models.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDashboardController {

    @FXML private TableView<Asset> assetsTable;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colCategory;
    @FXML private TableColumn<Asset, String> colStatus;

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadAssets();
    }

    private void loadAssets() {
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
                        resultSet.getString("status")
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
            stage.setTitle("Ajukan Aset");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
