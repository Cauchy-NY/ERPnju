package main.Presentation.AdministerUI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.UserVO;
import main.utility.UserType;

/**
 * ϵͳ������Ա����û�����Ŀ�����
 * @author ����ΰ
 *
 */
public class UserAddFrame extends MainUIController{
	
	@FXML
	TextField name;
	
	@FXML
	TextField jobnumhead;
	
	@FXML
	TextField jobnumtail;
	
	@FXML
	TextField password;
	
	@FXML
	TextField age;
	
	@FXML
	ComboBox<String> sex;
	
	@FXML
	ComboBox<String> type;
	
	@FXML
	RadioButton supre;
	
	@FXML
	Button surebutton;
	
	private Stage popupWindow = new Stage();
	
	/**
	 * ϵͳ����Ա������ĳ�ʼ������
	 */
	public void init(){
		
		AnchorPane popup = new AnchorPane();
		try {
			popup = FXMLLoader.load(UserAddFrame.class.getResource("UserAddFrameFXML.fxml"));
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
	 * ����fxmlʱĬ�ϼ��صķ�����������е���Ʒ�б���г�ʼ��
	 */
	@FXML
	public void initialize(){
		type.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{
						if (type.getSelectionModel().getSelectedItem().equals("StockManager")) {
							jobnumhead.setText("stm");
						}
						if (type.getSelectionModel().getSelectedItem().equals("SalesMan")) {
							jobnumhead.setText("sam");
						}
						if (type.getSelectionModel().getSelectedItem().equals("FinancialStaff")) {
							jobnumhead.setText("fs");
						}
						if (type.getSelectionModel().getSelectedItem().equals("Manager")) {
							jobnumhead.setText("ma");
						}
						
						
		});
	}
	
	/**
	 * ȷ������û��İ�ť�ļ���
	 */
	@FXML
	public void useraddsure(ActionEvent event){
		if (name.getText().equals("")||age.getText().equals("")||jobnumhead.equals("")||jobnumtail.equals("")||password.getText().equals("")||sex.getValue().equals("")||sex.getValue().equals("")) {
			System.out.println("��Ϣδ�����޷����");
		}else {
			UserVO adddvo = new UserVO(name.getText(),
					sex.getValue(),
					Integer.parseInt(age.getText()),
					jobnumhead.getText()+jobnumtail.getText(),
					password.getText(),
					type.getValue(),
					supre.isSelected());
			UserType result = null;
			try {
				result = RemoteHelper.getLoginBLService().register(adddvo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result == UserType.ALREADY_EXIT) {
				System.out.println("�޷����");
			}else if (result == UserType.REGISTER_SUCCESS) {
				 Button temp = (Button)event.getSource();
			     temp.getScene().getWindow().hide();
				MainFrame.setSceneRoot(AdminHomeFrame.init());
			}
		}
	}
	
	/**
	 * ȡ������û���ť�ļ���
	 */
	@FXML
	public void canceladd(ActionEvent event){
		 Button temp = (Button)event.getSource();
	     temp.getScene().getWindow().hide();
	}
}
