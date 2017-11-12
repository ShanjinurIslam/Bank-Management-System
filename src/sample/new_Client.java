package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Created by Spondon on 11/11/2017.
 */
public class new_Client {
    OracleJDBC oracleJDBC = new OracleJDBC() ;
    @FXML
    TextField client_name ;
    @FXML
    TextField client_address ;
    @FXML
    TextField client_email ;
    @FXML
    TextField client_phone ;
    @FXML
    TextField branch_name ;

    public void createClient(){
        oracleJDBC.newClient(client_name.getText(),client_address.getText(),client_email.getText(),client_phone.getText(),branch_name.getText());
    }
}
