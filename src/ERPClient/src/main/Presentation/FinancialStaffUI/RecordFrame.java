package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.RecordUI.SaleDetailFrame;
import main.Presentation.FinancialStaffUI.RecordUI.SaleHistoryFrame;
import main.Presentation.FinancialStaffUI.RecordUI.SaleStateFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

public class RecordFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	/**
	 * 财务人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane recordFrame = FXMLLoader.load(RecordFrame.class.getResource("RecordFrameFXML.fxml"));
			return recordFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();		//方便调试
	}

	
	/**
	 * 主页按钮的监听
	 */
	@FXML
	public void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * 返回按钮的监听
	 */
	@FXML
	public void back(){
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * 查看销售明细表按钮的监听
	 */
	@FXML
	public void checkSaleDetail() {
		Parent saleDetailFrame = SaleDetailFrame.init();
		MainFrame.setSceneRoot(saleDetailFrame);
	}
	
	/**
	 * 查看经营历程表按钮的监听
	 */
	@FXML
	public void checkSaleHistory() {
		Parent saleHistoryFrame = SaleHistoryFrame.init();
		MainFrame.setSceneRoot(saleHistoryFrame);
	}
	
	/**
	 * 查看经营情况表按钮的监听
	 */
	@FXML
	public void checkSaleState() {
		Parent saleStateFrame = SaleStateFrame.init();
		MainFrame.setSceneRoot(saleStateFrame);
	}
}
