/**
 * Sample Skeleton for 'areamasterview.fxml' Controller Class
 */

package pkgareamaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import mysqlconnector.sqlconnector;

public class areamasterviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboid"
    private ComboBox<String> comboid; // Value injected by FXMLLoader

    @FXML
    void doremove(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("delete from areas where area=?");
    		pst.setString(1,(comboid.getEditor().getText()));
    		int count=pst.executeUpdate();
    		if(count==0)
    			ShowMsg("Invalid combo.ID");
    		else
    			ShowMsg(count+"Record Deleted");
    		comboid.setValue(null);


    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }
    void ShowMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog Box");
    	alert.setHeaderText("Response");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }

    
    PreparedStatement pst;
    @FXML
    void dosave(ActionEvent event) {
    	try {
		pst=con.prepareStatement("insert into areas value(?)");
		pst.setString(1, (comboid.getEditor().getText()));
		//pst.setFloat(2,Float.parseFloat(txtprice.getText()));
	
		pst.executeUpdate();
		//System.out.println("savveeeddd");
		showMsg("Saved");
		fillareas();
		
		} 
		catch(Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//fillpaper();
		}
    	  
    }
    void fillareas()
    {
    	comboid.getItems().clear();
    	ArrayList<String> ids=new ArrayList<String>();
    	try {
    		pst=con.prepareStatement("select distinct area from areas" );
        	ResultSet table=pst.executeQuery();
        
        	while(table.next())// end pf loop
        	{
        		System.out.println(table.getString("area"));
        		ids.add(table.getString("area")+"");
        	}
        	comboid.getItems().addAll(ids);
        	//txtprice.setText("");//1111111111111111
    		comboid.setValue(null);
    	}
    	catch (Exception exp) {}
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
        assert comboid != null : "fx:id=\"comboid\" was not injected: check your FXML file 'areamasterview.fxml'.";
     	con=sqlconnector.getConnection();
     	fillareas();
    }
}
