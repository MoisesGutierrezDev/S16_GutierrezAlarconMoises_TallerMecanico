module vallegrande.edu.pe.tallerpoo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens vallegrande.edu.pe.tallerpoo1 to javafx.fxml;
    opens vallegrande.edu.pe.tallerpoo1.model to javafx.base;
    exports vallegrande.edu.pe.tallerpoo1;
}