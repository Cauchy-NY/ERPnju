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
 * 总经理的经营历程表界面的controller
 * @author yyr
 */
public class SaleHistoryFrame extends main.Presentation.FinancialStaffUI.RecordUI.SaleHistoryFrame {

	/**
	 * 加载查看销售明细表界面
	 * @return 加载好的界面
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
	 * 主页按钮的监听
	 */
	@FXML
	protected void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	protected void back() {
		Parent back = RecordFrame.init();
		MainFrame.setSceneRoot(back);
	}

}
