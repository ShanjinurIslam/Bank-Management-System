package sample;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oracle.sql.ARRAY;

import java.io.*;
import java.sql.* ;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Spondon on 20/09/2017.
 */
class OracleJDBC {
    protected Connection c = null;
    protected Statement stmt = null;
    String job_id ;
    String[] a ;
    String checkUser(String username,String password){
        a = new String[5] ;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","Bank","bank");
            stmt = c.createStatement() ;
            ResultSet r = stmt.executeQuery("select employee_name,department_id,branch_id,job_id from employees where employee_id= "+username+" and password = '"+password+"'") ;
            if(!r.next()){
                job_id = "No Match Found" ;
            }
            else{
                job_id = r.getString("JOB_ID") ;
                a[0] = "Employee id  : " + username ;
                a[1] = "Employee Name: " + r.getString("EMPLOYEE_NAME");
                a[2] = "Department ID: " +r.getString("DEPARTMENT_ID");
                a[3] = "Branch Id    : " +r.getString("BRANCH_ID");
                a[4] = "Log in time  : " +new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/data/Accountant.txt"))) ;
                int i =0 ;
                while (i<5){
                    writer.write(a[i++]+"\n");
                }
                writer.close();
            }
            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return job_id ;
    }


    public String checkBalance(String account, String branch) {
        String balance = new String();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","Bank","bank");
            stmt = c.createStatement() ;
            String sql = "Select ACC_BALANCE from ACCOUNTS where ACC_ID="+account + "and BRANCH_ID = (select BRANCH_ID from BRANCH where BRANCH_NAME = '"+branch+"')" ;
            ResultSet r = stmt.executeQuery(sql) ;
            if(!r.next()){
                balance = "No Such Account on this Branch" ;
            }
            else{
                balance = r.getString("ACC_BALANCE") ;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return balance;
    }

    public ArrayList<Data> getStat(String account){
        ArrayList<Data> trans = new ArrayList<>() ;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","Bank","bank");
            stmt = c.createStatement();
            String sql = "Select * from TRANSACTIONS where ACC_ID="+account ;
            ResultSet rs = stmt.executeQuery(sql) ;
            while (rs.next()){
                String[] t = new String[4] ;
                t[0] = rs.getString("TRANSACTION_ID") ;
                t[1] = rs.getString("TRANSACTION_TYPE") ;
                t[2] = rs.getString("TRANSACTION_AMOUNT") ;
                t[3] = rs.getString("TRANSACTION_DATE") ;
                Data d = new Data(t[0],t[1],t[2],t[3]) ;
                System.out.println(d.a+d.b+d.c+d.d);
                trans.add(d) ;
            }
            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return trans ;
    }

    public void deleteAccount(String account){
        try{
            String r = new String() ;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","Bank","bank");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select MAX(TRANSACTION_ID)+1 from TRANSACTIONS");
            while (rs.next()){
                r = rs.getString(1) ;
            }
            //System.out.println(r);
            String sql1 = "INSERT INTO TRANSACTIONS VALUES("+ r + ",'WITHDRAW',(SELECT ACC_BALANCE FROM ACCOUNTS WHERE ACC_ID ="+account+" AND ACC_TYPE IN('SAVINGS','STUDENT')),"+ account + ",TO_DATE('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "','YYYY-MM-DD HH24:MI:SS'))";
            //System.out.println(sql1);
            stmt.execute(sql1) ;
            String sql2 = "UPDATE ACCOUNTS SET ACC_BALANCE=0 ,ACC_STATUS='INACTIVE' WHERE ACC_ID="+account+ "AND ACC_TYPE IN('SAVINGS','STUDENT')" ;
            //System.out.println(sql2);
            stmt.execute(sql2) ;
            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
