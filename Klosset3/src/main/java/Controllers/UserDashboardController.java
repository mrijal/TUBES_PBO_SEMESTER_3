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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class UserDashboardController {

    @FXML private TableView<Asset> assetsTable;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colCategory;
    @FXML private TableColumn<Asset, String> colStatus;
    @FXML private TableColumn<Asset, String> colcreated_at;
    @FXML private Button logoutButton;
    @FXML private TextField search;
    @FXML private ImageView barcodeImage;
    @FXML private TableColumn<Asset, String> colBarcode;
    
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colcreated_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barcodePath"));
        loadAssets();
        
        search.textProperty().addListener(new ChangeListener<String>() {
          
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });
    colBarcode.setCellFactory(column -> {
    TableCell<Asset, String> cell = new TableCell<>() {
        private final Button button = new Button("Lihat Barcode");

        {
            button.setOnAction((ActionEvent event) -> {
               Asset asset = getTableView().getItems().get(getIndex());
                showBarcode(asset.getBarcodePath());
            });
        }

        @Override
        protected void updateItem(String barcodePath, boolean empty) {
            super.updateItem(barcodePath, empty);
            if (empty || barcodePath == null) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    };
    return cell;
});


    }
    

    public void loadAssets() {
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
                 assetsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    File file = new File(newSelection.getBarcodePath());
                    if (file.exists()) {
                        barcodeImage.setImage(new Image(file.toURI().toString()));
                    } else {
                        barcodeImage.setImage(null);
                    }
                }
            });

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
    private void showBarcode(String barcodePath) {
    if (barcodePath != null && !barcodePath.isEmpty()) {
        ImageView imageView = new ImageView(new Image("file:" + barcodePath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Barcode");
        alert.setHeaderText("Scan Barcode ini");
        alert.getDialogPane().setContent(imageView);
        alert.showAndWait();
    }
}

}
   
