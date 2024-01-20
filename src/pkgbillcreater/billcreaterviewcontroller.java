
/**
 * Sample Skeleton for 'billcreaterview.fxml' Controller Class
 */

package pkgbillcreater;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mysqlconnector.sqlconnector;

public class billcreaterviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tcom"
    private ListView<String> tcom; // Value injected by FXMLLoader

    @FXML // fx:id="tday"
    private TextField tday; // Value injected by FXMLLoader

    @FXML // fx:id="tprice"
    private TextField tprice; // Value injected by FXMLLoader

    @FXML // fx:id="tbill"
    private TextField tbill; // Value injected by FXMLLoader

    @FXML // fx:id="tdojf"
    private DatePicker tdojf; // Value injected by FXMLLoader

    @FXML // fx:id="tdoj"
    private DatePicker tdoj; // Value injected by FXMLLoader

    @FXML
    void dobill(ActionEvent event) {
    	float bill=Float.parseFloat(tprice.getText())*Float.parseFloat(tday.getText());
    	tbill.setText(bill+"");
    }

    @FXML
    void doday(ActionEvent event) {
    	LocalDate local=tdoj.getValue();
    	Date doj=Date.valueOf(local);
    	LocalDate local1=tdojf.getValue();
    	Date edoj=Date.valueOf(local1);
    	long dif=doj.getTime()-(edoj.getTime());
    	TimeUnit time=TimeUnit.DAYS;
    	long diff = time.convert(dif, TimeUnit.MILLISECONDS);
    	tday.setText(diff+"");
    }
    PreparedStatement pst;
    @FXML
    void dosave(ActionEvent event) {
    	try {
    		pst= con.prepareStatement("insert into bills values(?,?,?,?,0)");
    		pst.setString(1,ls);
    		LocalDate local=tdojf.getValue();
    		Date doj=Date.valueOf(local);
    		pst.setDate(2,doj);
    		LocalDate locall=tdoj.getValue();
    		Date dojj=Date.valueOf(locall);
    		pst.setDate(3, dojj);
    		pst.setFloat(4, Float.parseFloat(tbill.getText()));
    		pst.executeUpdate();
    		pst=con.prepareStatement("update customers set dostart=? where mobile=?");
    		pst.setDate(1, dojj);
    		pst.setString(2, ls);
    		pst.executeUpdate();
    		showmg("Data Saved");
    		} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		}

    }
    String ls;
    @FXML
    void doselected(MouseEvent event) {
    	ls=tcom.getSelectionModel().getSelectedItem();
    	try {
    	pst=(PreparedStatement) con.prepareStatement("select * from customers where mobile=?");
    	pst.setString(1, ls);
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    	Date doj=table.getDate("dostart");
    	float tp=table.getFloat("totalprice");
    	tdojf.setValue(doj.toLocalDate());
    	tprice.setText(tp+"");
    	}
    	} catch (SQLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	}

    }
    void filllis()
    {
    ArrayList<String> ids=new ArrayList<String>();
    try {
    pst= con.prepareStatement("select mobile from customers");
    ResultSet table=pst.executeQuery();
    while(table.next())
    {
    ids.add(table.getString("mobile"));
    }
    tcom.getItems().addAll(ids);
    } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }
    void showmg(String msg)
    {
    Alert alt=new Alert(AlertType.INFORMATION);
    alt.setTitle("Imformation dialog");
    alt.setHeaderText("Response");
    alt.setContentText(msg);
    alt.showAndWait();
    }
    
Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tcom != null : "fx:id=\"tcom\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        assert tday != null : "fx:id=\"tday\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        assert tprice != null : "fx:id=\"tprice\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        assert tbill != null : "fx:id=\"tbill\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        assert tdojf != null : "fx:id=\"tdojf\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        assert tdoj != null : "fx:id=\"tdoj\" was not injected: check your FXML file 'billcreaterview.fxml'.";
        con=sqlconnector.getConnection();
        filllis();
        }
}
