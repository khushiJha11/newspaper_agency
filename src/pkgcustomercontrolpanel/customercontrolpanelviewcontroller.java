/**
 * Sample Skeleton for 'customercontrolpanelview.fxml' Controller Class
 */

package pkgcustomercontrolpanel;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import mysqlconnector.sqlconnector;

public class customercontrolpanelviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtmobile"
    private TextField txtmobile; // Value injected by FXMLLoader

    @FXML // fx:id="txtaddress"
    private TextField txtaddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader

    @FXML // fx:id="comboidarea"
    private ComboBox<?> comboidarea; // Value injected by FXMLLoader

    @FXML // fx:id="comboidhawkers"
    private ComboBox<?> comboidhawkers; // Value injected by FXMLLoader

    @FXML // fx:id="doj"
    private DatePicker doj; // Value injected by FXMLLoader

    @FXML // fx:id="lblavail"
    private ListView<?> lblavail; // Value injected by FXMLLoader

    @FXML // fx:id="lblsprice"
    private ListView<?> lblsprice; // Value injected by FXMLLoader

    @FXML // fx:id="lblaprice"
    private ListView<?> lblaprice; // Value injected by FXMLLoader

    @FXML // fx:id="lblselect"
    private ListView<?> lblselect; // Value injected by FXMLLoader

    @FXML
    void doclear(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1,mob.getText());
			int count =pst.executeUpdate();
			if(count==0)
			{
				ShowMsg("Invalid mobile number");
			}
			else
			{
				ShowMsg("Data deleted successfullyyy..........");
				mob.setText("");
			    name.setText("");
				comarea.getItems().clear();
				address.setText("");
				selpaper.getItems().clear();
				price.getItems().clear();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
    }

    @FXML
    void dodiscontinue(ActionEvent event) {

    }

    @FXML
    void dosearch(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from customers where mobile=?");
			pst.setString(1,mob.getText());
	    	ResultSet table=pst.executeQuery();
	    	boolean count=false;
	    	while(table.next())
			{
	    		count=true ; 
	    		String nname=table.getString("name");
	    		String area=table.getString("area");
	    		String hhawker=table.getString("hawker");
	    		String aaddress=table.getString("adddress");
	    		String selpaers=table.getString("selpapers");
				float pr=table.getFloat("totalprice");
				Date edoj=table.getDate("dostart");
				name.setText(nname);
				comarea.setValue(area);
				hawker.setValue(hhawker);
				address.setText(aaddress);
				selpaper.getItems().add(selpaers);
				price.getItems().add(pr+"");
				doj.setValue(edoj.toLocalDate());
				
			
			}
	    	if(count==false)
	    	{
	    		ShowMsg("Invalid mob no.");
	    	}
	    	else
	    		ShowMsg("Data searched successfullyy");
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    PreparedStatement pst;
    @FXML
    void dosubmit(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
			pst.setString(1,mob.getText() );
			pst.setString(2,name.getText() );
			pst.setString(3,comarea.getEditor().getText());
			pst.setString(4,hawker.getEditor().getText());
			pst.setString(5, address.getText());
			String ls =selpaper.getItems().toString();
			pst.setString(6, ls);
			int n=price.getItems().size();
			float sum=0;
			Object[] obj=price.getItems().toArray();
			for(int i=0;i<n;i++)
			{
				sum=sum+Float.parseFloat(obj[i].toString());
				
			}
			pst.setFloat(7, sum);
			LocalDate local=doj.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(8,doj);
			pst.executeUpdate();
			ShowMsg("data saved.....");
			fillname();
			
			
		    } 
    	
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("update customers set name=?,area=?,hawker=?,adddress=?,selpapers=?,totalprice=?, dostart=? where mobile=?");
    		pst.setString(8,mob.getText());
    		pst.setString(1,name.getText());
    		pst.setString(2,comarea.getEditor().getText());
    		pst.setString(3,hawker.getEditor().getText());
    		pst.setString(4,address.getText());
    		String ls=selpaper.getItems().toString();
    		pst.setString(5,ls);
    		int n=price.getItems().size();
    		float sum=0;
    		for(int i=0;i<n;i++)
    			
    		{
    			sum=sum+Float.parseFloat(price.getItems().get(i).toString());
    			
    		}
    		pst.setFloat(6, sum);
    		LocalDate local=doj.getValue();
    		Date doj=Date.valueOf(local);
    		pst.setDate(7,doj);
    		int count=pst.executeUpdate();
    		if(count==0)
    		{
    			ShowMsg("invalid mob no.");
    		}
    		else
    		ShowMsg("data updated.....");
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    void fillarea()
    {
    	comarea.getItems().clear();
    	ArrayList<String> ids=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select * from areamaster");
			ResultSet table =pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("area"));
			}
			comarea.getItems().addAll(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void filllist()
    {
    	ArrayList<String> ids=new ArrayList<String>();
    	ArrayList<Float> idm=new ArrayList<Float>();
    	try {
			pst=con.prepareStatement("select * from newspaper");
			ResultSet table =pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("paper"));
				idm.add(table.getFloat("price"));
			}
			paperavail.getItems().addAll(ids);
		     listprice.getItems().addAll(idm);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
    void fillname()
    {
    	ArrayList<String> ids=new ArrayList<String>();
    	//ArrayList<Float> idm=new ArrayList<Float>();
    	try {
			pst=con.prepareStatement("select * from customers");
			ResultSet table =pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("name"));
				//idm.add(table.getFloat("price"));
			}
			hawker.getItems().addAll(ids);
		     //listprice.getItems().addAll(idm);
		} catch (SQLException e) {
			
			e.printStackTrace();
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

    

    Connection con;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        /*assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert comboidarea != null : "fx:id=\"comboidarea\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert comboidhawkers != null : "fx:id=\"comboidhawkers\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert doj != null : "fx:id=\"doj\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert lblavail != null : "fx:id=\"lblavail\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert lblsprice != null : "fx:id=\"lblsprice\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert lblaprice != null : "fx:id=\"lblaprice\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";
        assert lblselect != null : "fx:id=\"lblselect\" was not injected: check your FXML file 'customercontrolpanelview.fxml'.";*/
        con=sqlconnector.getConnection();
        fillarea();
        filllist();
        fillname();
    }
}
