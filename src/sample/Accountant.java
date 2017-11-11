package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Spondon on 04/11/2017.
 */
public class Accountant {
    private Stage stage1,stage2,stage3,stage4,stage ;
    private Parent root ;
    @FXML
    Button create_acc ;
    @FXML
    Button get_stat ;
    @FXML
    Button check_bal ;
    @FXML
    Button delete_acc ;
    @FXML
    Button log_out ;
    @FXML
    Button transfer_history ;
    @FXML
    TextArea employee_info = new TextArea();
    void showInfo(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/data/Accountant.txt")) ;
            String r  ;
            String a = new String() ;
            while((r = reader.readLine())!=null){
                a += r+"\n" ;
            }
            employee_info.setText(a);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    @FXML
    public void initialize(){
        showInfo();
    }

    public void setCreate_acc(){
        this.stage1 =  new Stage() ;

        try {
            this.root = FXMLLoader.load(this.getClass().getResource("CreateAccount.fxml"));
            this.stage1.setScene(new Scene(this.root,400,600));
            this.stage1.show();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public void setCheck_bal(){
        this.stage2 =  new Stage() ;
        try {
            this.root = FXMLLoader.load(this.getClass().getResource("CheckBal.fxml"));
            this.stage2.setScene(new Scene(this.root,400,600));
            this.stage2.show();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public void setGet_stat(){
        this.stage3 =  new Stage() ;
        try {
            this.root = FXMLLoader.load(this.getClass().getResource("GetStat.fxml"));
            this.stage3.setScene(new Scene(this.root,800,600));
            this.stage3.show();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public void setDelete_acc(){
        this.stage4 =  new Stage() ;
        try {
            this.root = FXMLLoader.load(this.getClass().getResource("DeleteAcc.fxml"));
            this.stage4.setScene(new Scene(this.root,400,600));
            this.stage4.show();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public void LogOut(){
        if(stage1!=null) stage1.close();
        if(stage2!=null) stage2.close();
        if(stage3!=null) stage3.close();
        if(stage4!=null) stage4.close();
        stage = (Stage) log_out.getScene().getWindow() ;
        try {
            this.root = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
            this.stage.setScene(new Scene(this.root,400,600));
            this.stage.show();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }
}
