package main.Presentation.ManagerUI.RecordUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.ManagerUI.RecordFrame;

/**
 * �ܾ���ľ�Ӫ���̱�����controller
 * @author yyr
 */
public class SaleHistoryFrame extends main.Presentation.FinancialStaffUI.RecordUI.SaleHistoryFrame {

	/**
	 * ���ز鿴������ϸ�����
	 * @return ���غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane saleHistoryFrame = FXMLLoader.load(SaleHistoryFrame.class.getResource("SaleHistoryFrameFXML.fxml"));
			return saleHistoryFrame;
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
	protected void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	protected void back() {
		Parent back = RecordFrame.init();
		MainFrame.setSceneRoot(back);
	}

}
