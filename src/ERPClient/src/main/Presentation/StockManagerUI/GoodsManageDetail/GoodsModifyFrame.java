package main.Presentation.StockManagerUI.GoodsManageDetail;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.RMI.RemoteHelper;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * ��������Ա��Ʒ�޸Ľ���Ŀ�����
 * @author ����ΰ
 *
 */
public class GoodsModifyFrame extends MainUIController{
	@FXML
	TextField id;
	
	@FXML
	TextField name;
	
	@FXML
	TextField version;
	
	@FXML
	TextField amount;
	
	@FXML
	TextField bid;
	
	@FXML
	TextField retailprice;
	
	@FXML
	TextField recentbid;
	
	@FXML
	TextField recentretailprice;
	
	@FXML
	TextField catagory;
	
	static GoodsVO modifyvo;
	
	private Stage popupWindow = new Stage();
	
	/**
	 * ����������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 * @param vo Ԥ�޸���Ʒ��Vo
	 */
	public void init(GoodsVO vo) {
		
		modifyvo = vo;
		AnchorPane popup = new AnchorPane();
		
		try {
			popup = FXMLLoader.load(GoodsModifyFrame.class.getResource("GoodsModifyFrameFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene popupscene = new Scene(popup);
		popupscene.setFill(null);
		popupWindow.setScene(popupscene);
		popupWindow.initStyle(StageStyle.UNDECORATED);
		popupWindow.initStyle(StageStyle.TRANSPARENT);
		popupWindow.show();
	}
	
	/**
	 * ����fxmlʱĬ�ϼ��صķ�����������е���Ʒ�б���г�ʼ��
	 */
	@FXML
	public void initialize(){
		id.setText(modifyvo.getID());
		name.setText(modifyvo.getName());
		version.setText(modifyvo.getVersion());
		amount.setText(modifyvo.getAmounts()+"");
		catagory.setText(modifyvo.getCatagory());
		bid.setText(modifyvo.getBid()+"");
		retailprice.setText(modifyvo.getRetailPrice()+"");
		recentbid.setText(modifyvo.getRecentBid()+"");
		recentretailprice.setText(modifyvo.getRecentRetailPrice()+"");
	}

	/**
	 * ȷ���޸İ�ť�ļ���
	 */
	@FXML
	protected void goodsmodifysure(ActionEvent event) {
		if (name.getText().equals("")||version.getText().equals("")||bid.getText().equals("")||retailprice.getText().equals("")||recentbid.getText().equals("")||recentretailprice.getText().equals("")) {
			
		}else {
			modifyvo.setBid(Double.parseDouble(bid.getText()));
			modifyvo.setRetailPrice(Double.parseDouble(retailprice.getText()));
			modifyvo.setRecentBid(Double.parseDouble(recentbid.getText()));
			modifyvo.setRecentRetailPrice(Double.parseDouble(recentretailprice.getText()));
			modifyvo.setStaffID(username);
			ResultMessage resultMessage;
			try {
				resultMessage =RemoteHelper.getGoodsBLService().goodsModify(modifyvo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Button temp = (Button)event.getSource();
		       temp.getScene().getWindow().hide();
		       MainFrame.setSceneRoot(GoodsManageFrame.init());
		}

	}
	
	/**
	 * ȡ���޸İ�ť�ļ���
	 */
	@FXML
	private void cancelmodify(ActionEvent event) {
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	}
}
