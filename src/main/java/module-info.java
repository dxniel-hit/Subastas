module co.edu.uniquindio.pr3.subastasUQ {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens co.edu.uniquindio.pr3.subastasUQ.application to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.pr3.subastasUQ.model to javafx.base;
    opens co.edu.uniquindio.pr3.subastasUQ.viewControllers to javafx.fxml;

}