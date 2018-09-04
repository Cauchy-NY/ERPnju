package main.Presentation.ManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.ManagerUI.PromotionUI.CombineBagPromotionFrame;
import main.Presentation.ManagerUI.PromotionUI.CustomerPromotionFrame;
import main.Presentation.ManagerUI.PromotionUI.TotalPromotionFrame;

public class PromotionFrame extends MainUIController{

	@FXML
	private ImageView head;
	
	/**
	 * ��Ӫ��¼�鿴����ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane promotionFrame = FXMLLoader.load(PromotionFrame.class.getResource("PromotionFrameFXML.fxml"));
			return promotionFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();		//�������
	}


	/**
	 * �ƶ��ͻ��������԰�ť�ļ���
	 */
	@FXML
	public void createCustomerPromotion(){
		Parent customerPromotionFrame = CustomerPromotionFrame.init();
		MainFrame.setSceneRoot(customerPromotionFrame);
	}

	/**
	 * �ƶ���ϰ��������԰�ť�ļ���
	 */
	@FXML
	public void createCombineBagPromotion(){
		Parent combineBagPromotionFrame = CombineBagPromotionFrame.init();
		MainFrame.setSceneRoot(combineBagPromotionFrame);
	}

	/**
	 * �ƶ��ܼ۴������԰�ť�ļ���
	 */
	@FXML
	public void createTotalPromotion(){
		Parent totalPromotionFrame = TotalPromotionFrame.init();
		MainFrame.setSceneRoot(totalPromotionFrame);
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

}
