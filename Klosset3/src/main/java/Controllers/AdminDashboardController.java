package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Asset;
import models.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDashboardController {

    @FXML private TableView<Asset> assetsTable;
    @FXML private TableColumn<Asset, Integer> colId;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colCategory;
    @FXML private TableColumn<Asset, String> colStatus;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadAssets();
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
                        resultSet.getString("status")
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
}
