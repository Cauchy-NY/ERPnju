package main.Presentation.SalesmanUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * ����������Ա������Ŀ�����
 * @author ����ΰ
 *
 */
public class SalesmanHomeFrame extends MainUIController {
	
	/**
	 * ����������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(SalesmanHomeFrame.class.getResource("SalesManHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * �л��û���ť�ļ���
	 */
	@FXML
	public void changeusers(){
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	/**	
	 * ����ͻ����������水ť�ļ���
	 */
	@FXML
	public void CustomerManageHandle(ActionEvent event) {
		Parent customermanage = CustomerManageFrame.init();
        MainFrame.setSceneRoot(customermanage);
    }
	
	/**
	 * ����������������水ť�ļ���
	 */
	@FXML
	public void ManifestManageHandle(ActionEvent event) {
        MainFrame.setSceneRoot(ManifestFrame.init());
    }
	
}
