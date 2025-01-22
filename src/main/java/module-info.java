module mains {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.media;

    opens mains to javafx.fxml;
    exports mains;
    exports mains.main1;
    opens mains.main1 to javafx.fxml;
    exports mains.main2;
    opens mains.main2 to javafx.fxml;
    exports mains.main3;
    opens mains.main3 to javafx.fxml;
    exports mains.main4;
    opens mains.main4 to javafx.fxml;
}