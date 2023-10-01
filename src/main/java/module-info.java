module co.edu.uniquindio.pr3.subastasUQ {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;

    requires org.mapstruct;
    requires java.logging;

    opens co.edu.uniquindio.pr3.subastasUQ.application to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.pr3.subastasUQ.model to javafx.base;
    opens co.edu.uniquindio.pr3.subastasUQ.viewControllers to javafx.fxml;
    exports co.edu.uniquindio.pr3.subastasUQ.mapping.mappers;
}