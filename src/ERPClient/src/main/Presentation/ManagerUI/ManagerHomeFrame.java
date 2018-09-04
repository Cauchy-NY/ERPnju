package main.Presentation.ManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

public class ManagerHomeFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	/**
	 * �ܾ���������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(ManagerHomeFrame.class.getResource("ManagerHomeFrameFXML.fxml"));
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
	private void changeUser(){
		//��ʾ�Ƿ�ȷ���л�
		
		MainFrame.setSceneRoot(LoginUI.init());
	}

	/**
	 * �������ݰ�ť�ļ���
	 */
	@FXML
	private void checkRecipt() {
		Parent checkReciptFrame = CheckReciptFrame.init();
		MainFrame.setSceneRoot(checkReciptFrame);
	}
	
	/**
	 * ��Ӫ��¼�鿴��ť�ļ���
	 */
	@FXML
	private void checkRecord() {
		Parent recordFrame = RecordFrame.init();
		MainFrame.setSceneRoot(recordFrame);
	}

	/**
	 * �ƶ��������԰�ť�ļ���
	 */
	@FXML
	private void promotion() {
		Parent promotionFrame = PromotionFrame.init();
		MainFrame.setSceneRoot(promotionFrame);
	}

}
