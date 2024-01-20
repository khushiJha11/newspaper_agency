/**
 * Sample Skeleton for 'hawkertableview.fxml' Controller Class
 */

package hawkershowtable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mysqlconnector.sqlconnector;

public class hawkertableviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tblview"
    private TableView<userbean> tblview; // Value injected by FXMLLoader
     PreparedStatement  pst;
    @FXML
    void doshowall(ActionEvent event) {
 ObservableList<userbean> records=getAllRecords();
    	
    	TableColumn<userbean, String> hname=new TableColumn<userbean,String>("hname");//Dikhava Title
    	hname.setCellValueFactory(new PropertyValueFactory<userbean,String>("hname"));//bean field name
    	
    	TableColumn<userbean, String> address=new TableColumn<userbean,String>("address");//Dikhava Title
    	address.setCellValueFactory(new PropertyValueFactory<userbean,String>("address"));//bean field name
    	
    	TableColumn<userbean, String> acard=new TableColumn<userbean,String>("acard");//Dikhava Title
    	acard.setCellValueFactory(new PropertyValueFactory<userbean,String>("acard"));//bean field name
    	
    	TableColumn<userbean, String> mobile=new TableColumn<userbean,String>("mobile");//Dikhava Title
    	 mobile.setCellValueFactory(new PropertyValueFactory<userbean,String>("mobile"));//bean field name
    	 
     	TableColumn<userbean, Float> salary=new TableColumn<userbean,Float>("salary");//Dikhava Title
     	salary.setCellValueFactory(new PropertyValueFactory<userbean,Float>("salary"));//bean field name
    	
    	TableColumn<userbean, String> dojdate=new TableColumn<userbean, String>("dojdate");//Dikhava Title
    	dojdate.setCellValueFactory(new PropertyValueFactory<userbean,String>("dojdate"));//bean field name
    	
    	TableColumn<userbean, String> selareas=new TableColumn<userbean,String>("selareas");//Dikhava Title
    	selareas.setCellValueFactory(new PropertyValueFactory<userbean,String>("selareas"));//bean field name
    	
     	tblview.getColumns().addAll(hname,address,acard,mobile,salary,dojdate,selareas);
    	
    	tblview.setItems(records);//sending records in table
    	
    	
    	

    }
    ObservableList<userbean> getAllRecords()
    {
    	ObservableList<userbean> list; 
    	list=FXCollections.observableArrayList();
    	 try{
			  pst=con.prepareStatement("select * from hwakers");
			 ResultSet table= pst.executeQuery();
			 
			 while(table.next())//like eof of c++
			 {
				 String hname=table.getString("hanme");
				 String address=table.getString("address=");
				 String acard=table.getString("acard");
				 String mobile=table.getString("mobile");
			     Float salary=table.getFloat("salary");
				 String dojdate= table.getString("dojdate");//use string for getting date
				 String selareas=table.getString("selareas");
				 
				 System.out.println(hname+"\t"+address+"\t"+acard+"\t"+mobile+"\t"+salary+"\t"+dojdate+"\t"+selareas);
				 userbean user=new userbean(hname,address,acard,mobile,salary,dojdate,selareas);
				 list.add(user);
						 
			 }
		  }
		  catch(Exception exp)
		  {
			 exp.printStackTrace(); 
		  }
    	 return list;
    	
    }
  
  Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tblview != null : "fx:id=\"tblview\" was not injected: check your FXML file 'hawkertableview.fxml'.";
        con=sqlconnector.getConnection(); 
    }
}
