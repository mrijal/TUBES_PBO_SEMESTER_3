module com.mycompany.klosset3 {//klosset3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    
    opens Controllers to javafx.fxml;
    opens Models to javafx.fxml;
    opens com.mycompany.klosset3 to javafx.fxml;

    
    exports Controllers;
    exports Models;
    exports com.mycompany.klosset3;
}
