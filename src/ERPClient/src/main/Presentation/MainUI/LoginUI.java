package main.Presentation.MainUI;

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.Presentation.AdministerUI.AdminHomeFrame;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.SalesmanUI.SalesmanHomeFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.RMI.RemoteHelper;
import main.VO.UserVO;
import main.utility.UserType;


/**
 * ����LoginUI�ĳ�ʼ��������������������ת
 * @author ��Ԭ������ΰ
 * @version 1.0
 * @date 2017/11/27
 */
public class LoginUI extends MainUIController{
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	/**
	 * ��ʼ��LoginUI
	 * @return ���س�ʼ�����غ�FXML�Ľ���
	 */
	public static Parent init(){
		try {
			GridPane loginUI = FXMLLoader.load(MainFrame.class.getResource("LoginFXML.fxml"));
			return loginUI;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��¼��ť�ļ���
	 */
	@FXML
	public boolean login(){
		if (username.getText().equals("admin")&&password.getText().equals("admin")) {
			MainFrame.setSceneRoot(AdminHomeFrame.init());
		}else {
			
			UserType loginmessage = null;
			try {
				loginmessage = RemoteHelper.getLoginBLService().login(username.getText(), password.getText());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			switch(loginmessage){
			case FINANCIAL_STAFF:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(FinancialStaffHomeFrame.init());
				break;
			}
			case SALES_MAN:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(SalesmanHomeFrame.init());
				break;
			}
			case MANAGER:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(ManagerHomeFrame.init());
				break;
			}
			case STOCK_MANAGER:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(StockManagerHomeFrame.init());
				break;
			}
			case NOT_FOUND:{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("û���ҵ�����");
				alert.showAndWait();
				break;
			}
			case PASSWORD_WRONG:{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("�������");
				alert.showAndWait();
				break;
			}
			}
		
		}
		return true;
	}
	
	/**
	 * ��¼���̵ļ���
	 */
	@FXML
	public void loginenter(KeyEvent event){
		if (event.getCode().equals(KeyCode.ENTER)) {
			login();
		}
	}

	/**
	 * �˳���ť�ļ���
	 */
	@FXML
	public void exit(){
		System.exit(0);
	}
	
	/**
	 * ��С����ť�ļ���
	 */
	@FXML
	public void minimize(){
		MainFrame.minimize();
	}

}
