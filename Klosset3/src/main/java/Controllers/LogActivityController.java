package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.ActivityLog;
import Models.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogActivityController {

    @FXML
    private TableView<ActivityLog> logTable;
    @FXML
    private TableColumn<ActivityLog, String> colUser;
    @FXML
    private TableColumn<ActivityLog, String> colAction;
    @FXML
    private TableColumn<ActivityLog, String> colAsset;
    @FXML
    private TableColumn<ActivityLog, String> colTimestamp;
    private UserDashboardController dashboardController;
    private AdminDashboardController DashboardController;

    @FXML
    public void initialize() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        colAsset.setCellValueFactory(new PropertyValueFactory<>("asset"));
        colTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        loadLogs();
    }

    private void loadLogs() {
        logTable.getItems().clear();
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "SELECT activity_logs.id_log, users.username, activity_logs.action, assets.name, activity_logs.timestamp "
                    +
                    "FROM activity_logs " +
                    "JOIN users ON activity_logs.id_user = users.id_user " +
                    "LEFT JOIN assets ON activity_logs.id_asset = assets.id_asset";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                logTable.getItems().add(new ActivityLog(
                        resultSet.getInt("id_log"),
                        resultSet.getString("username"),
                        resultSet.getString("action"),
                        resultSet.getString("name"),
                        resultSet.getString("timestamp")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDashboardController(UserDashboardController controller) {
        this.dashboardController = controller;
    }

    public void setDashboardController(AdminDashboardController controller) {
        this.DashboardController = controller;
    }
}
