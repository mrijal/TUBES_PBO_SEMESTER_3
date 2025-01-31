/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
iimport javafx.event.ActionEvent;
import javafx.fxml.FXML;
mport javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Adminbur
 */
public class AddAssetController implements Initializable {


    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<?> categoryBox;
    @FXML
    private TextArea descriptionField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void submitAsset(ActionEvent event) {
    }

    @FXML
    private void closeForm(ActionEvent event) {
    }

}
