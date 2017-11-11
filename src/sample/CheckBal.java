package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Created by Spondon on 07/11/2017.
 */
public class CheckBal {

    Stage stage ;
    private OracleJDBC oracleJDBC ;
    @FXML
    TextField acc_id ;
    @FXML
    TextField branch_name ;
    @FXML
    Button check ;
    @FXML
    TextField balance ;

    @FXML
    public void initialize(){
        oracleJDBC = new OracleJDBC() ;
    }

    public void setCheck(){
        String account = acc_id.getText() ;
        String branch = branch_name.getText() ;
        String bal = oracleJDBC.checkBalance(account,branch) ;
        if(bal.equals("No Such Account on this Branch")){
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(100);
            dialogVbox.getChildren().add(new Text("No Such Account on this Branch"));
            Scene dialogScene = new Scene(dialogVbox, 200, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
        else {
            balance.setText(bal);
        }
    }
}
