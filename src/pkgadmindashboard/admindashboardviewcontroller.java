/**
 * Sample Skeleton for 'admindashboardview.fxml' Controller Class
 */

package pkgadmindashboard;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mysqlconnector.sqlconnector;

public class admindashboardviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void doareamaster(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgareamaster/areamasterview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void dobillcollector(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgbillcollector/billcollectorview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}

    }

    @FXML
    void dobillcreator(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgbillcreater/billcreaterview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void dobillhistory(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgbillhistory/billhistoryview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void docustomercontrolpanel(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgcustomercontrolpanel/customercontrolpanelview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}

    }

    @FXML
    void docustomergoogler(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgcustomergoogler/customergooglerview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}

    }

    @FXML
    void dohawker(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkghawkersboard/hawkerboardview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }

    @FXML
    void dopaperhistory(ActionEvent event) {
    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/pkgpapermaster/papermasterview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}

    }

    @FXML
    void dotableview(ActionEvent event) {

    	try
    	{
    	Parent root=FXMLLoader.load(getClass().getResource("/hawkershowtable/hawkertableview.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage=new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    }
    
    
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=sqlconnector.getConnection();
    }
}
