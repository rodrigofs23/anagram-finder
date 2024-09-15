module com.example.anagramfinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.anagramfinder to javafx.fxml;
    exports com.example.anagramfinder;
}
