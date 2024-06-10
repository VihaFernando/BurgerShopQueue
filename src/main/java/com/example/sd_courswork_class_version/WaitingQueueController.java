package com.example.sd_courswork_class_version;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class WaitingQueueController implements Initializable {
    @FXML
    private ListView WaitingList;
    @FXML
    private Button HomeButton;

    public WaitingQueueController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        WaitingCircularQueue queues = Main.waitingQueue;
        queues.showQueue(this.WaitingList);
    }

    @FXML
    private void HomeButtonController(ActionEvent event) {
        Stage stage = (Stage)this.HomeButton.getScene().getWindow();
        stage.close();
    }
}
