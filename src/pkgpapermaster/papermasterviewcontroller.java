/**
 * Sample Skeleton for 'papermasterview.fxml' Controller Class
 */

package pkgpapermaster;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
//import javadatabaseconnection.mysqlconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mysqlconnector.sqlconnector;

public class papermasterviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboid"
    private ComboBox<String> comboid; // Value injected by FXMLLoader

    @FXML // fx:id="txtprice"
    private TextField txtprice; // Value injected by FXMLLoader

  
    
    @FXML
    void dosearch(ActionEvent event) {
    	try {	
    		PreparedStatement pst=con.prepareStatement("select * from papers where paper=?" );
    	pst.setString(1, (comboid.getEditor().getText()));
    	ResultSet table=pst.executeQuery();
    	
    	boolean jasoos=false;
    	while(table.next())
    	{ 
    		jasoos=true;
    	String comboid=table.getString("paper");
    	comboid.toString();
        float price=table.getFloat("price");
        txtprice.setText(price+"");
    	
    	}
    	if(jasoos==false)
    		
    		showMsg("Record Not Found");
    	}
    	catch(Exception exp) {}  
    	
    	
    }
 
 
    @FXML
    void dodelete(ActionEvent event) {
    	try {
        	pst=con.prepareStatement("delete from papers where paper=?" );
        	pst.setString(1, (comboid.getEditor().getText()));
        	int count=pst.executeUpdate();
        	if (count==0)
        			
        		showMsg("Invalid Combo ID");
        	
        	else {
        		showMsg(count+"Records Deleted");
        		 txtprice.setText("");
        		fillpaper();
               }
              }

        	catch(Exception exp) {}
    }

    @FXML
    void donew(ActionEvent event) {
    	
        txtprice.setText("");
		comboid.setValue(null);
       comboid.toString();

    }
  
    PreparedStatement pst;
    @FXML
    void dosave(ActionEvent event) {
    	try {
			  
			pst=con.prepareStatement("insert into papers values(?,?)");
			pst.setString(1, (comboid.getEditor().getText()));
			pst.setFloat(2,Float.parseFloat(txtprice.getText()));
		
			pst.executeUpdate();
			//System.out.println("savveeeddd");
			showMsg("Saved");
			fillpaper();
			txtprice.setText("");
			comboid.setValue(null);
			
			} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//fillpaper();
			}
    	
    }
   
    void fillpaper()
    {
    	comboid.getItems().clear();
    	ArrayList<String> ids=new ArrayList<String>();
    	try {
    		pst=con.prepareStatement("select distinct paper from papers" );
        	ResultSet table=pst.executeQuery();
        
        	while(table.next())// end pf loop
        	{
        		System.out.println(table.getString("paper"));
        		ids.add(table.getString("paper")+"");
        	}
        	comboid.getItems().addAll(ids);
        	
    	}
    	catch (Exception exp) {}
    }
    
    @FXML
    void doupdate(ActionEvent event) {
    	try {
			  
			pst=con.prepareStatement("update papers set price=? where paper=? ");
			pst.setString(2, (comboid.getEditor().getText()));
			pst.setFloat(1,Float.parseFloat(txtprice.getText()));
			int count=pst.executeUpdate();
			if (count==0)
			
				showMsg("Invalid Combo ID");
				else
			showMsg("Record updated");
			txtprice.setText("");
			comboid.setValue(null);
		
		} 
    	
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    }


    void showMsg(String msg)
    {
    	
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information Dialog");
    				//or //alert.setTitle(null);
    			alert.setHeaderText("Response");
    			alert.setContentText(msg);
    			alert.showAndWait();
    }
    
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=sqlconnector.getConnection();
    	 
    	 fillpaper();

    }
}
