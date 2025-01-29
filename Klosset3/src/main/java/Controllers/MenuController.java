package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button dashboardButton, reportButton, approvalButton;

    @FXML
    public void openDashboard() {
        System.out.println("Membuka Dashboard");
    }

    @FXML
    public void openReport() {
        System.out.println("Membuka Laporan");
    }

    @FXML
    public void openApproval() {
        System.out.println("Membuka Persetujuan Aset");
    }
}
