package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * ���������Ա������ĳ�ʼ���Լ����Ʋ�����Ա���ֵĽ�����ת
 * @author ��Ԭ��
 * @version 1.0
 * @date 2017/11/27
 */
public class FinancialStaffHomeFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * ������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(FinancialStaffHomeFrame.class.getResource("FinancialStaffHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * �л��û���ť�ļ���
	 */
	@FXML
	public void changeUser(){
		//��ʾ�Ƿ�ȷ���л�
		
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	/**
	 * �˳���ť�ļ���
	 */
//	@FXML
//	public void exit(){
//		//��ʾ�Ƿ�ȷ���˳�
//		
//		System.exit(0);
//	}
//	
//	/**
//	 * ��С����ť�ļ���
//	 */
//	@FXML
//	public void minimize(){
//		MainFrame.minimize();
//	}
	
	/**
	 * �����˻�����ť�ļ�����������ת�������˻��������
	 */
	@FXML
	public void bankAccountManage(){
		Parent bankAccountFrame = BankAccountFrame.init();
		MainFrame.setSceneRoot(bankAccountFrame);
	}
	
	/**
	 * ���񵥾ݰ�ť�ļ�����������ת�����񵥾ݽ���
	 */
	@FXML
	public void recipt(){
		Parent reciptFrame = ReciptFrame.init();
		MainFrame.setSceneRoot(reciptFrame);
	}
	
	/**
	 * �����뾭Ӫ��¼�鿴��ť�ļ�����������ת�������뾭Ӫ��¼�鿴����
	 */
	@FXML
	public void checkRecord(){
		Parent recordFrame = RecordFrame.init();
		MainFrame.setSceneRoot(recordFrame);
	}
	
	/**
	 * �˰�ť�ļ�����������ת���˽���
	 */
	@FXML
	public void account(){
		Parent accountFrame = AccountFrame.init();
		MainFrame.setSceneRoot(accountFrame);
	}
	
	/**
	 * �鿴��־��ť�ļ�����������ת���鿴��־����
	 */
	@FXML
	public void checkLog() {
		
	}
}
