package main.Presentation.ManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.ManagerUI.RecordUI.SaleDetailFrame;
import main.Presentation.ManagerUI.RecordUI.SaleHistoryFrame;
import main.Presentation.ManagerUI.RecordUI.SaleStateFrame;

public class RecordFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	/**
	 * ��Ӫ��¼�鿴����ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane recordFrame = FXMLLoader.load(RecordFrame.class.getResource("RecordFrameFXML.fxml"));
			return recordFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();		//�������
	}

	
	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	public void home(){
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	public void back(){
		Parent back = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * �鿴������ϸ��ť�ļ���
	 */
	@FXML
	public void checkSaleDetail() {
		Parent saleDetailFrame = SaleDetailFrame.init();
		MainFrame.setSceneRoot(saleDetailFrame);
	}
	
	/**
	 * �鿴��Ӫ���̱�ť�ļ���
	 */
	@FXML
	public void checkSaleHistory() {
		Parent saleHistoryFrame = SaleHistoryFrame.init();
		MainFrame.setSceneRoot(saleHistoryFrame);
	}
	
	/**
	 * �鿴��Ӫ�����ť�ļ���
	 */
	@FXML
	public void checkSaleState() {
		Parent saleStateFrame = SaleStateFrame.init();
		MainFrame.setSceneRoot(saleStateFrame);
	}
}
