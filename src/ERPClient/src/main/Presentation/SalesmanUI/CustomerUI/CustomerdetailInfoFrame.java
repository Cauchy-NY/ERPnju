package main.Presentation.SalesmanUI.CustomerUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainUIController;
import main.VO.CustomerVO;

/**
 * �ͻ���Ϣ����Ŀ�����
 * @author ����ΰ
 *
 */
public class CustomerdetailInfoFrame extends MainUIController{
	
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
	TextField level;
	
	Stage popupWindow = new Stage();
	
	static CustomerVO customerinfo;
	
	/**
	 * �ͻ���Ϣ�����ʼ��
	 * @param vo �ͻ�vo
	 */
	public void init(CustomerVO vo){
		
		customerinfo = vo;
		AnchorPane popup = new AnchorPane();
		try {
			popup = FXMLLoader.load(CustomerdetailInfoFrame.class.getResource("CustomerDetailInfoFrameFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
		id.setText(customerinfo.getID());
		id.setEditable(false);
		name.setText(customerinfo.getName());
		name.setEditable(false);
		receive.setText(customerinfo.getReceive()+"");
		receive.setEditable(false);
		payment.setText(customerinfo.getPayment()+"");
		payment.setEditable(false);
		category.setText(customerinfo.getCategory());
		category.setEditable(false);
		level.setText(customerinfo.getLevel());
		level.setEditable(false);
		defaultsalesman.setText(customerinfo.getDefaultsalesman());
		defaultsalesman.setEditable(false);
		number.setText(customerinfo.getNumber());
		number.setEditable(false);
		address.setText(customerinfo.getAddress());
		address.setEditable(false);
		postcode.setText(customerinfo.getPostCode());
		postcode.setEditable(false);
		email.setText(customerinfo.getEmail());
		email.setEditable(false);
		receivelimit.setText(customerinfo.getReceivelimit()+"");
		receivelimit.setEditable(false);
		
	}
	
	/**
	 * �رվ�����Ϣ���水ť�ļ���
	 */
	@FXML
	private void closedetail(ActionEvent event) {
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	}
	
	
	
}
