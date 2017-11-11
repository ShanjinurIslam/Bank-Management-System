package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.Buffer;

/**
 * Created by Spondon on 07/11/2017.
 */
public class DeleteAcc {
    Stage stage ;
    OracleJDBC oracleJDBC = new OracleJDBC() ;
    @FXML
    private TextField acc_id ;

    public void setDelete_account(){
        String acc = acc_id.getText() ;
        System.out.println(acc);
        oracleJDBC.deleteAccount(acc);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(100);
        dialogVbox.getChildren().add(new Text("Delete Account Successful"));
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
