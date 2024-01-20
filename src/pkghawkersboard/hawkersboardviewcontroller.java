/**
 * Sample Skeleton for 'hawkersboardview.fxml' Controller Class
 */

package pkghawkersboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import mysqlconnector.sqlconnector;
import javafx.scene.image.ImageView;

public class hawkersboardviewcontroller {

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboidname"
    private ComboBox<String> comboidname; // Value injected by FXMLLoader

    @FXML // fx:id="addressid"
    private TextField addressid; // Value injected by FXMLLoader

    @FXML // fx:id="acardid"
    private TextField acardid; // Value injected by FXMLLoader

    @FXML // fx:id="mobileid"
    private TextField mobileid; // Value injected by FXMLLoader

    @FXML // fx:id="areaid"
    private TextField areaid; // Value injected by FXMLLoader

    @FXML // fx:id="salaryid"
    private TextField salaryid; // Value injected by FXMLLoader
    
    @FXML // fx:id="imageid"
    private TextField imageid; // Value injected by FXMLLoader

    @FXML // fx:id="dateid"
    private DatePicker dateid; // Value injected by FXMLLoader

    @FXML // fx:id="comboidarea"
    private ComboBox<String> comboidarea; // Value injected by FXMLLoader

    String filepath;
    @FXML
    void dochoosepic(ActionEvent event) {
    	   FileChooser chooser=new FileChooser();
           chooser.setTitle("select profile pic");
           chooser.getExtensionFilters().addAll(
         		  new FileChooser.ExtensionFilter("All Images", "."),
         		  new FileChooser.ExtensionFilter("JPG", "*.jpg"),
         		  new FileChooser.ExtensionFilter("PNG","*.png"),
         		  new FileChooser.ExtensionFilter(".", ".")
         		  );
           File file=chooser.showOpenDialog(null);
           filepath=file.getAbsolutePath();
           try {
 			imageid.setImage(new javafx.scene.image.Image(new FileInputStream(filepath)));
 		} 
           catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

    }

    @FXML
    void dofired(ActionEvent event) {
    	 try {
 			pst=(PreparedStatement) con.prepareStatement("delete from hawkers where hname=?");
 			pst.setString(1, comboidname.getEditor().getText());
 			int count=pst.executeUpdate();
 			fillcombo();
 			if(count==0)
 			{
 				showmg("Invalid name");
 			}
 			else
 			{
 				showmg("Data deleted Successfully");
 				acardid.setText("");
 				addressid.setText("");
 				areaid.setText("");
 				//imageid.setImage();
 				mobileid.setText("");
 				salaryid.setText("");
 			}
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

    }

    @FXML
    void doarea(ActionEvent event) {

        String idd=comboidname.getSelectionModel().getSelectedItem();
        areaid.setText(areaid.getText()+idd+",");
    }

    PreparedStatement pst;
    
    @FXML
    void dohierd(ActionEvent event) {
    	/*try {
			pre=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?,?,?,?)");
			pre.setString(1, comboidname.getEditor().getText());
			pre.setString(2, comboidname.getEditor().getText()); //pic path
			pre.setString(3, addressid.getText());
			pre.setString(4, acardid.getText());
			pre.setString(5, mobileid.getText());
			pre.setInt(4, Integer.parseInt(salaryid.getText()));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	  try {
  			pst=(PreparedStatement) con.prepareStatement("insert into hawkers values(?,?,?,?,?,?,?,?)");
  			pst.setString(1, comboidname.getEditor().getText());
  			pst.setString(2, filepath);
  			pst.setString(3, addressid.getText());
  			pst.setString(4,acardid.getText());
  			pst.setString(5, mobileid.getText());
  			pst.setFloat(6, Float.parseFloat(salaryid.getText()));
  			LocalDate local=dateid.getValue();
  			Date doj=Date.valueOf(local);
  			pst.setDate(7, doj);
  			pst.setString(8,areaid.getText());
  		
  			pst.executeUpdate();
  			showmg("Saved");
  			fillcombo();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	
    }

    @FXML
    void domodify(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("update hawkers set path=?,adresss=?,acard=?,mobile=?,salary=?,dojdate=?,selareas=? where hname=?");
			pst.setString(1, filepath);
			
			pst.setString(2, addressid.getText());
			pst.setString(3,acardid.getText());
			pst.setString(4, mobileid.getText());
			pst.setFloat(5, Float.parseFloat(salaryid.getText()));
			LocalDate local=dateid.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(6, doj);
			pst.setString(7, areaid.getText());
			pst.setString(8, comboidname.getEditor().getText());
			
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("Invalid name");
			}
			else
			{
				showmg("Updated successfully");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void donew(ActionEvent event) {

    }

    @FXML
    void dosearch(ActionEvent event)
    { try
    { 
    	pst=(PreparedStatement) con.prepareStatement("select * from hawkers where hname=?");
		pst.setString(1,comboidname.getEditor().getText());
		ResultSet table= pst.executeQuery();
		Boolean count=false;
		while(table.next())
		{
			count=true;
			String adress=table.getString("adresss");
			String addhaar=table.getString("acard");
			String mobile=table.getString("mobile");
			float sal=table.getFloat("salary");
			java.sql.Date edoj=table.getDate("dojdate");
			String area=table.getString("selareas");
			filepath=table.getString("picpath");
			addressid.setText(adress);
		acardid.setText(addhaar);
			mobileid.setText(mobile);
			salaryid.setText(sal+"");
			dateid.setValue(edoj.toLocalDate());
			areaid.setText(area);
			try {
				imageid.setImage(new javafx.scene.image.Image(new FileInputStream(filepath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(count==true)
		{
			System.out.println("Data search succesfully");
		}
		else
		{
			System.out.println("Invalid id");
		}
		
		
    }
    catch (SQLException exp) 
    {
		// TODO Auto-generated catch block
		exp.printStackTrace();
	}
    	
}

    @FXML
    void doselect(MouseEvent event) {

    }

    void showmg(String msg)
    {
    	Alert alt=new Alert(AlertType.INFORMATION);
    	alt.setTitle("Imformation dialog");
    	alt.setHeaderText("Response");
    	alt.setContentText(msg);
    	alt.showAndWait();
    }
    
    void fillcombo()
    {
    	comboidname.getItems().clear();
    	ArrayList<String> cona= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from hawkers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				cona.add(table.getString("hname"));
			}
			comboidname.getItems().addAll(cona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillcoarea()
    {
    	comboidname.getItems().clear();
    	ArrayList<String> cona= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from selareas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				cona.add(table.getString("selarea"));
			}
			comboidname.getItems().addAll(cona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    Connection con;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
        con=sqlconnector.getConnection();
        fillcombo();
        fillcoarea();
        
    }

}