package main;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App  {

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manajemen Aset");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
