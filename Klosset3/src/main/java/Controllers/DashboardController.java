package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
    
    @FXML
    private Label welcomeMessage;

    public void initialize() {
        welcomeMessage.setText("Selamat Datang di Dashboard!");
    }
}
