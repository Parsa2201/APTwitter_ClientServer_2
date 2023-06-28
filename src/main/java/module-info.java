module com.twitter.client{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.twitter.client to javafx.fxml;
    exports com.twitter.client;
        }