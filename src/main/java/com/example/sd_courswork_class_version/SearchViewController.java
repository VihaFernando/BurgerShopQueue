package com.example.sd_courswork_class_version;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchViewController implements Initializable {
    @FXML
    private Button HomeButton;             //elements IDs
    @FXML
    private TextField SearchBar;
    @FXML
    private ListView SearchList;
    private static FoodQueue[] queues;

    public SearchViewController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void HomeButtonController(ActionEvent event) {
        Stage stage = (Stage)this.HomeButton.getScene().getWindow();   //button for close the window
        stage.close();
    }

    @FXML
    private void SearchBarController(){
        SearchList.getItems().clear();  //clear the previous search results
        String search = SearchBar.getText();  //taking user input
        for (int i =0; i<3; i++){
            for (Customer customer:queues[i].getCustomers()){   //geting information from customer class
                if (customer.getFullName().contains(search)){
                    SearchList.getItems().add(customer.getFullName() + "     Cashier:  " + String.valueOf(i+1)
                            + "    Position:  " + String.valueOf(queues[i].getCustomers().indexOf(customer) +1) + "   Burger count: " + customer.getBurgersRequired() );

                }
            }
        }
    }
}
