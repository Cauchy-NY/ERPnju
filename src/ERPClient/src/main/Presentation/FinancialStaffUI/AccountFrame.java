package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.AccountUI.CheckAccountFrame;
import main.Presentation.FinancialStaffUI.AccountUI.CreateAccountFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * �˽����controller
 * @author 49869
 *
 */
public class AccountFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	public static Parent init(){
		try {
			GridPane accountFrame = FXMLLoader.load(AccountFrame.class.getResource("AccountFrameFXML.fxml"));
			return accountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new GridPane();
	}
	
	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	private void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	private void back(){
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}
 
	/**
	 * �ڳ����˰�ť�ļ���
	 */
	@FXML
	private void createAccount() {
		Parent createAccountFrame = CreateAccountFrame.init();
		MainFrame.setSceneRoot(createAccountFrame);
	}
	
	/**
	 * �鿴�ڳ���Ϣ��ť�ļ���
	 */
	@FXML
	private void checkAccountInfo() {
		Parent checkAccountFrame = CheckAccountFrame.init();
		MainFrame.setSceneRoot(checkAccountFrame);
	}
}
