module com.example.doctorsurgery {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.doctorsurgery to javafx.fxml;
    exports com.example.doctorsurgery;
}