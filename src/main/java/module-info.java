module com.example.bibliotecajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bibliotecajavafx to javafx.fxml;
    exports com.example.bibliotecajavafx;
    opens Model;
}