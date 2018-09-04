package main.Presentation.FinancialStaffUI;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

/**
 * ���������˻���Ϣ�����ĳ�ʼ�����¼�����
 * @author ��Ԭ��
 *
 */
public class BankAccountInfoFrame extends MainUIController{
	
	public static BankAccountBLService bankAccountBL = RemoteHelper.getBankAccountBLService();
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField amountField;
	
	@FXML
	private Button sureButton;
	@FXML
	private Button modifyButton;
	
	@FXML
	private Label titleLabel;
		
	private Stage popUpWindow = new Stage();
	
	private static String initName = "";		//���������ݳ�ʼֵ������������;
	private static double initAmount = 0;
	private static boolean editable = false;	//�����������Ƿ��Ǳ༭ģʽ������������;
	
	/**
	 * ��ʼ����Ϣ����
	 * @param name Ҫ��ʾ�����֣������������nameΪ""
	 * @param amount Ҫ��ʾ�Ľ��
	 * @param isModify �Ƿ����޸�ģʽ
	 */
	public void initInfo(String name, double amount, boolean isModify){
		initName = name;
		initAmount = amount;
		editable = isModify;
		
		GridPane popUp = new GridPane();
		try {
			popUp = FXMLLoader.load(BankAccountInfoFrame.class.getResource("BankAccountInfoFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene popUpScene = new Scene(popUp);
		popUpWindow.setScene(popUpScene);
		popUpWindow.initStyle(StageStyle.UNDECORATED);
		popUpWindow.show();
	}
	
	/**
	 * ����FXMLʱ�Զ����õķ����������ı�����ʾ��ֵ�������ݳ�ʼֵ���趨���������
	 */
	@FXML
	public void initialize(){
		if(editable){				//����Ǳ༭ģʽ
			modifyInInfo();
		}
		if(initName.equals("")){	//���������ģʽ
			titleLabel.setText("���������˻�");
			modifyButton.setVisible(false);
		}
		nameField.setText(initName);
		amountField.setText(""+initAmount);
		
		editable = false;
	}
	
	/**
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	private void sureInInfo(Event e){
		if(nameField.isEditable()){		//�༭ģʽ���޸Ļ���������
			//���ýӿڣ�����
			BankAccountVO vo = new BankAccountVO(nameField.getText(), Double.parseDouble(amountField.getText()), username);
			try {
				ResultMessage rm = null;
//				System.out.println(bankAccountBL.add(vo));
				if(titleLabel.getText().equals("���������˻�")) {
					rm = bankAccountBL.add(vo);
				}
				if(titleLabel.getText().equals("�޸������˻���Ϣ")) {
					rm = bankAccountBL.modify(vo);
				}
				if(rm == ResultMessage.FAIL) {
					System.out.println("fail");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			BankAccountFrame.updateTable();
			MainFrame.setSceneRoot(BankAccountFrame.init());
		}
		cancelInInfo(e);
	}
	
	/**
	 * �޸İ�ť�ļ���
	 */
	@FXML
	public void modifyInInfo(){
		titleLabel.setText("�޸������˻���Ϣ");
		modifyButton.setVisible(false);
		nameField.setEditable(true);
		amountField.setEditable(true);
	}
	
	/**
	 * ȡ����ť�ļ���
	 * @param e Event
	 */
	@FXML
	private void cancelInInfo(Event e){
		Button temp = (Button)e.getSource();
		temp.getScene().getWindow().hide();
	}
}
