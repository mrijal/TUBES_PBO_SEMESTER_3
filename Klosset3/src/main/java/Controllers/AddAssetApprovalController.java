package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AddAssetApprovalController {

    @FXML
    private Label assetNameLabel;
    
    @FXML
    private Button approveButton, rejectButton;

    @FXML
    public void approveAsset() {
        assetNameLabel.setText("Aset Disetujui!");
    }

    @FXML
    public void rejectAsset() {
        assetNameLabel.setText("Aset Ditolak!");
    }
}
