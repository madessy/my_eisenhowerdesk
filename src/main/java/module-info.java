module com.example.myeisenhowerdesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.myeisenhowerdesk to javafx.fxml;
    exports com.example.myeisenhowerdesk;
    exports Code;
}