package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.ReciptUI.CashBillFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.CheckHistoryFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.CollectionOrderFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.PaymentOrderFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * ���񵥾ݽ���ĳ�ʼ����������������ת
 * @author ��Ԭ��
 *
 */
public class ReciptFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * ���񵥾ݽ���ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane reciptFrame = FXMLLoader.load(ReciptFrame.class.getResource("ReciptFrameFXML.fxml"));
			return reciptFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new GridPane();		//�������
	}

	/**
	 * �˳���ť�ļ���
	 */
//	@FXML
//	public void exit(){
//		//��ʾ�Ƿ�ȷ���˳�
//		
//		System.exit(0);
//	}
//	
//	/**
//	 * ��С����ť�ļ���
//	 */
//	@FXML
//	public void minimize(){
//		MainFrame.minimize();
//	}

	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	public void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	public void back(){
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * �ƶ��տ��ť�ļ���
	 */
	@FXML
	public void collectionOrder(){
		Parent collectionOrderFrame = CollectionOrderFrame.init();
		MainFrame.setSceneRoot(collectionOrderFrame);
	}
	
	/**
	 * �ƶ������ť�ļ���
	 */
	@FXML
	public void paymentOrder(){
		Parent paymentOrderFrame = PaymentOrderFrame.init();
		MainFrame.setSceneRoot(paymentOrderFrame);
	}

	/**
	 * �ƶ��ֽ���õ���ť�ļ���
	 */
	@FXML
	public void cashBill(){
		Parent cashBillFrame = CashBillFrame.init();
		MainFrame.setSceneRoot(cashBillFrame);
	}

	/**
	 * �鿴�������ݰ�ť�ļ���
	 */
	@FXML
	public void checkHistory(){
		Parent checkHistoryFrame = CheckHistoryFrame.init();
		MainFrame.setSceneRoot(checkHistoryFrame);
	}

}
