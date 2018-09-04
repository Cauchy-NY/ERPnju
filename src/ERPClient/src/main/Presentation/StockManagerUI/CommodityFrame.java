package main.Presentation.StockManagerUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityUI.CheckCommodityFrame;
import main.Presentation.StockManagerUI.CommodityUI.DamageListFrame;
import main.Presentation.StockManagerUI.CommodityUI.GiftListFrame;
import main.Presentation.StockManagerUI.CommodityUI.OverflowListFrame;
import main.Presentation.StockManagerUI.CommodityUI.ViewCommodityFrame;
import main.Presentation.StockManagerUI.CommodityUI.WarningListFrame;

/**
 * ��������Ա���������Ŀ�����
 * @author ����ΰ
 *
 */
public class CommodityFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * ����������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		
		try {
			GridPane commoditymanage = FXMLLoader.load(CommodityFrame.class.getResource("CommodityFrameFXML.fxml"));
			return commoditymanage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
		
	}
	
	/**
	 * ������鿴����ļ���
	 */
	@FXML
	protected void viewCommodityframe(ActionEvent event) {
        MainFrame.setSceneRoot(ViewCommodityFrame.init());
    }
	
	/**
	 * �������̵����ļ���
	 */
	@FXML
	protected void checkCommodityFrame(ActionEvent event) {
        MainFrame.setSceneRoot(CheckCommodityFrame.init());
    }
	
	
	
	/**
	 * ���ؿ�������Ա�����水ť�ļ���
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
}
