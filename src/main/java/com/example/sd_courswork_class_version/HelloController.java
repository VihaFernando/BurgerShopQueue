package com.example.sd_courswork_class_version;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label burgerCountLabel;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    @FXML
    private VBox vBox3;
    @FXML
    private Label customerLable;
    @FXML
    private Label customerBurgerCount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int burgerCount = Main.remainingBurgers;
        FoodQueue[] queues = Main.queues;

        burgerCountLabel.setText(String.valueOf(burgerCount));

        for (int i=0; i<3;i++){
            int count = 0;
            for (Customer customer:queues[i].getCustomers()){
                if (customer != null){
                    if (i==0){
                        vBox1.getChildren().get(count).setVisible(true);

                        vBox1.getChildren().get(count).setOnMouseClicked(mouseEvent -> {    //show customer details when click the customer footprint
                            customerLable.setText(customer.getFullName());
                            customerBurgerCount.setText(String.valueOf(customer.getBurgersRequired()));
                        });
                    }
                    else if (i==1){
                        vBox2.getChildren().get(count).setVisible(true);         //show customer details when click the customer footprint
                        vBox2.getChildren().get(count).setOnMouseClicked(mouseEvent -> {
                            customerLable.setText(customer.getFullName());
                            customerBurgerCount.setText(String.valueOf(customer.getBurgersRequired()));
                        });
                    }
                    else if (i==2){
                        vBox3.getChildren().get(count).setVisible(true);        //show customer details when click the customer footprint
                        vBox3.getChildren().get(count).setOnMouseClicked(mouseEvent -> {
                            customerLable.setText(customer.getFullName());
                            customerBurgerCount.setText(String.valueOf(customer.getBurgersRequired()));
                        });
                    }
                    count++;
                }
            }
        }

    }

    @FXML
    private void SearchController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void WaitingController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("waiting_queue.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}