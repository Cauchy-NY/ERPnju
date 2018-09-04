package main.Presentation.SalesmanUI.CustomerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerManageFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

/**
 * ����������Ա�ͻ��޸Ľ���Ŀ�����
 * @author ����ΰ
 *
 */
public class CustomerModifyFrame extends MainUIController{
	
	@FXML
	TextField id;
	
	@FXML
	TextField name;
	
	@FXML
	TextField number;
	
	@FXML
	TextField address;
	
	@FXML
	TextField email;
	
	@FXML
	TextField postcode;
	
	@FXML
	TextField receivelimit;
	
	@FXML
	TextField receive;
	
	@FXML
	TextField payment;
	
	@FXML
	TextField defaultsalesman;
	
	@FXML
	TextField category;
	
	@FXML
	ComboBox<String> level;
	
	ArrayList<Integer> list;//����ȯlist
	
	String staffID;
	

	public static int index;
	
	static CustomerVO modifycustomervo = null;
	
	Stage popupWindow = new Stage();
	
	static String sourcescene;
	
	/**
	 * ����������Ա�ͻ�����������ĳ�ʼ������
	 *@return ���ؼ��غõĽ���
	 *@param vo Ԥ�޸Ŀͻ���vo
	 *@param sourcescenekind �򿪸ý����parentscene
	 */
	public void init(CustomerVO vo,String sourcescenekind){
		
		modifycustomervo = vo;
		sourcescene = sourcescenekind;
		System.out.println(modifycustomervo.getID());
		AnchorPane popup = new AnchorPane();
		try {
			popup = FXMLLoader.load(CustomerModifyFrame.class.getResource("CustomerModifyFrameFXML.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene popupscene = new Scene(popup);
		popupscene.setFill(null);
		popupWindow.setScene(popupscene);
		popupWindow.initStyle(StageStyle.UNDECORATED);
		popupWindow.initStyle(StageStyle.TRANSPARENT);
		popupWindow.show();
	}
	
	/**
	 * ��ʼ���޸Ŀͻ�����ʱ��䲿��textfield
	 */
	@FXML
	public void initialize(){
		
		id.setText(modifycustomervo.getID());
		id.setEditable(false);
		name.setText(modifycustomervo.getName());
		name.setEditable(false);
		receive.setText(modifycustomervo.getReceive()+"");
		receive.setEditable(false);
		payment.setText(modifycustomervo.getPayment()+"");
		payment.setEditable(false);
		category.setText(modifycustomervo.getCategory());
		category.setEditable(false);
		level.setValue(modifycustomervo.getLevel());
		defaultsalesman.setText(modifycustomervo.getDefaultsalesman());
		number.setText(modifycustomervo.getNumber());
		address.setText(modifycustomervo.getAddress());
		postcode.setText(modifycustomervo.getPostCode());
		email.setText(modifycustomervo.getEmail());
		receivelimit.setText(modifycustomervo.getReceivelimit()+"");
		if (user.getIsSupremeAuthority()==true) {
			receivelimit.setDisable(false);
		}
	}
	
	/**
	 * ȷ���޸Ŀͻ���ť�ļ�������BL�㽻����
	 */
	@FXML
	protected void customermodifysure(ActionEvent event) {
		if (defaultsalesman.getText().equals("")||address.getText().equals("")||number.getText().equals("")||email.getText().equals("")||postcode.getText().equals("")||receivelimit.getText().equals("")) {
	   //����Ϣδ��д��������
		}else {
		CustomerVO customermodified = modifycustomervo;
		customermodified.setLevel(level.getSelectionModel().getSelectedItem());
		customermodified.setDefaultsalesman(defaultsalesman.getText());
		customermodified.setNumber(number.getText());
		customermodified.setAddress(address.getText());
		customermodified.setPostCode(postcode.getText());
		customermodified.setEmail(email.getText());
		customermodified.setReceivelimit(Double.parseDouble(receivelimit.getText()));
		customermodified.setStaffID(user.getName()); 
		ResultMessage modifytry = null;
		try {
			modifytry = RemoteHelper.getCustomerBLService().customerModify(customermodified);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	    Button temp = (Button)event.getSource();
	    temp.getScene().getWindow().hide();
	    
	    if (sourcescene.equals("CustomerManagescene")) {
	    	MainFrame.setSceneRoot(CustomerManageFrame.init());
		}else if(sourcescene.equals("Searchscene")){
			MainFrame.setSceneRoot(SearchResultFrame.init(SearchResultFrame.keywords.get(SearchResultFrame.keywords.size()-1), SearchResultFrame.keytypes.get(SearchResultFrame.keytypes.size()-1)));
		}
	    
		}
	}
	
	/**
	 * qȡ���޸ĵİ�ť����
	 */
	@FXML
	public void cancelmodify(ActionEvent event) {
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	}
	
	
	
}
