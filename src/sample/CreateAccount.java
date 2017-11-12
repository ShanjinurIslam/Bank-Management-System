package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by Spondon on 07/11/2017.
 */
public class CreateAccount {
    Stage stage ;
    Parent root ;
    @FXML
    Button new_client ;
    @FXML
    Button existing_client ;
    public void newClient(){
        try {
            stage = (Stage) new_client.getScene().getWindow() ;
            root = FXMLLoader.load(getClass().getResource("new_Client.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Create Client") ;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void existingClient(){

    }
}
