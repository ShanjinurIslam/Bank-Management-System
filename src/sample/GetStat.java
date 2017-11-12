package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
/**
 * Created by Spondon on 07/11/2017.
 */
public class GetStat {
    private OracleJDBC oracleJDBC = new OracleJDBC();
    @FXML
    TextField acc_id ;
    @FXML
    Button getStat ;
    @FXML
    Button printToPDF ;
    @FXML
    TableView<Data> stat_table = new TableView<>();

    public void onGetStatClick(){
        String account = acc_id.getText();
        ArrayList<Data> trans = oracleJDBC.getStat(account) ;
        TableColumn C1 = new TableColumn("TRANSACTION_ID") ;
        TableColumn C2 = new TableColumn("TRANSACTION_TYPE") ;
        TableColumn C3 = new TableColumn("TRANSACTION_AMOUNT") ;
        TableColumn C4 = new TableColumn("TRANSACTION_DATE") ;
        C1.setPrefWidth(200);
        C2.setPrefWidth(200);
        C3.setPrefWidth(200);
        C4.setPrefWidth(200);
        C1.setCellValueFactory(new PropertyValueFactory<Data,String>("a"));
        C2.setCellValueFactory(new PropertyValueFactory<Data,String>("b"));
        C3.setCellValueFactory(new PropertyValueFactory<Data,String>("c"));
        C4.setCellValueFactory(new PropertyValueFactory<Data,String>("d"));
        final ObservableList<Data> data = FXCollections.observableArrayList(trans);
        for ( int i = 0; i<stat_table.getItems().size(); i++) {
            stat_table.getItems().clear();
        }
        stat_table.getColumns().clear();
        stat_table.setItems(data);
        stat_table.getColumns().addAll(C1,C2,C3,C4) ;
    }

    public void setPrintToPDF(){
        String account = acc_id.getText();
        ArrayList<Data> trans = oracleJDBC.getStat(account) ;
        PDDocument document = new PDDocument() ;
        PDPage page = new PDPage();
        document.addPage( page );
        PDFont font = PDType1Font.COURIER;
        try{
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            int fontsize = 8 ;
            float leading = 1.5f * fontsize;
            contentStream.beginText();
            contentStream.setFont( font,fontsize);
            contentStream.moveTextPositionByAmount( 100, 700 );
            String title = "TRANC_ID    TRANC_TYPE      TRANC_AMOUNT    TRANC_DATE";
            contentStream.showText(title);
            contentStream.moveTextPositionByAmount(0, -leading);
            for (Data d:trans
                 ) {
                String transaction = d.a + "        " + d.b +"          " + d.c +"          " + d.d ;
                contentStream.showText(transaction);
                contentStream.moveTextPositionByAmount(0, -leading);
            }
            contentStream.endText();
            contentStream.close();
            String filename = "src/data/"+"Statement_"+account+".pdf" ;
            document.save(filename);
            document.close();
            Desktop.getDesktop().open(new File(filename)) ;
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
