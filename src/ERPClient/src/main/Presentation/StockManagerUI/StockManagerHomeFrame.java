package main.Presentation.StockManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.SalesmanHomeFrame;

/**
 * ��������Ա������Ŀ�����
 * @author ����ΰ
 *
 */
public class StockManagerHomeFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * ��������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(StockManagerHomeFrame.class.getResource("StockManagerHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * ��Ʒ�������������
	 */
	@FXML
	public void categoryManageFrameHandle(){
		MainFrame.setSceneRoot(CategoryManageFrame.init());
	}
	
	/**
	 * ��Ʒ����������
	 */
	@FXML
	public void GoodsManageFrameHandle(){
		MainFrame.setSceneRoot(GoodsManageFrame.init());
	}
	
	/**
	 * ������������
	 */
	@FXML
	public void CommodityFrameHandle(){
		MainFrame.setSceneRoot(CommodityFrame.init());
	}
	
	/**
	 * �л��û���ť�ļ���
	 */
	@FXML
	public void changeusers(){
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	
}
