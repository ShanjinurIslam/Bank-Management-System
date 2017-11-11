package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    Stage stage ;
    Parent root ;
    private OracleJDBC oracleJDBC = new OracleJDBC() ;
    @FXML
    TextField username ;
    @FXML
    PasswordField password ;
    @FXML
    Button signIn ;

    public void onButtonClick(){
        String user = username.getText() ;
        String pass = password.getText() ;
        String out = oracleJDBC.checkUser(user,pass) ;
        if(out.equals("ACCOUNTANT")){
            stage = (Stage) signIn.getScene().getWindow() ;
            try {
                root = FXMLLoader.load(getClass().getResource("Accountant.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Welcome Accountant "+ user) ;
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(out.equals("No Match Found")){
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(100);
            dialogVbox.getChildren().add(new Text("Invalid Username or password"));
            Scene dialogScene = new Scene(dialogVbox, 200, 200);
            dialog.setScene(dialogScene);
            dialog.show();

        }
    }
}
