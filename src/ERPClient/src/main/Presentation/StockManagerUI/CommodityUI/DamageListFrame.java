package main.Presentation.StockManagerUI.CommodityUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityFrame;
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.Presentation.StockManagerUI.CommodityUI.OverflowListFrame.Goods;
import main.RMI.RemoteHelper;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * ��������Ա��д���𵥽���Ŀ�����
 * @author ����ΰ
 *
 */
public class DamageListFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML 
	TableView<Goods> goodstable;
	
	@FXML
	TableColumn<Goods, String> idColumn;
	
	@FXML
	TableColumn<Goods, String> nameColumn;
	
	@FXML
	TableColumn<Goods, String> versionColumn;
	
	@FXML
	TableColumn<Goods, String> amountColumn;
	
	@FXML
	TableColumn<Goods, String> reduceamountColumn;
	
	static ArrayList<GoodsVO> damages;
	
	ObservableList<Goods> damageslist;
	
	ArrayList<CommodityReciptVO> submitdamagelistvo = new ArrayList<>();
	
	/**
	 * ��������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 * @param goods Ԥ��д���ݰ���Ʒvo��list
	 */
	public static Parent init(ArrayList<GoodsVO> goods){
		damages = goods;
		try {
			GridPane damagelist = FXMLLoader.load(DamageListFrame.class.getResource("DamageListFrameFXML.fxml"));
			return damagelist;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
		
	}
	
	/**
	 * ����fxmlʱĬ�ϼ��صķ�����������е���Ʒ�б���г�ʼ��
	 */
	@FXML
	public void initialize(){
		loadlist();
		goodstable.setEditable(true);
		reduceamountColumn.setCellFactory(TextFieldTableCell.<Goods>forTableColumn());
		reduceamountColumn.setOnEditCommit(
			    (CellEditEvent<Goods, String> t) -> {
			        ((Goods) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setReduceamount(t.getNewValue());
//			        for (int i = 0; i < warnedlist.size(); i++) {
//						if (goodstable.getSelectionModel().getSelectedItem().getName().equals(warnedlist.get(i).getName())) {
//							warnedlist.get(i).setWanringamount(t.getNewValue());
//							System.out.println(warnedlist.get(i).getName()+warnedlist.get(i).getWanringamount());
//						}
//					}
			});
	}
	
	/**
	 * ������Ʒ���б�ķ���
	 */
	public void loadlist(){
		for (int i = 0; i < damages.size(); i++) {
			System.out.println(damages.get(i).getName()+damages.get(i).getAlertAmounts()+"      2");
		}
		damageslist= FXCollections.observableArrayList();
		
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));      
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));      
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));      
        reduceamountColumn.setCellValueFactory(new PropertyValueFactory<>("reduceamount"));
        for (int i = 0; i < damages.size(); i++) {
			damageslist.add(new Goods(
					damages.get(i).getName(),
					damages.get(i).getID(),
					damages.get(i).getVersion(),
					damages.get(i).getAmounts()+"",
					"0"));
		}
        
        goodstable.setItems(damageslist);
	}
	
	/**
	 * ȷ���ύ���𵥰�ť�ļ���
	 */
	public void submitddamagelist(){
		ResultMessage resultMessage = null;
		
		for (int i = 0; i < damageslist.size(); i++) {
			System.out.println(damageslist.get(i).getName()+" "+damageslist.get(i).getReduceamount());
//			resultMessage = RemoteHelper.getCommodityReciptBLService().setWarningList(vo);
			submitdamagelistvo.add(new CommodityReciptVO("BS",
					damageslist.get(i).getName(),
					damageslist.get(i).getID(),
					Integer.parseInt(damageslist.get(i).getReduceamount()),
					"�ᱻ����",
					"Unchecked",
					user.getName()));
		}
		for (int i = 0; i < submitdamagelistvo.size(); i++) {
			try {
				resultMessage = RemoteHelper.getCommodityReciptBLService().setDamageList(submitdamagelistvo.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (resultMessage.equals(ResultMessage.SUCCESS)) {
				System.out.println("�ύ�ɹ�");
				MainFrame.setSceneRoot(GoodsManageFrame.init());
			}
		}
		
	}
	
	
	/**
	 * ���ؿ�������Ա�����水ť�ļ���
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
	
	/**
	 * ���ؿ�������Ա�����水ť�ļ���
	 */
	@FXML
	protected void returnGoodsManageFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsManageFrame.init());
    }
	
	/**
	 * ������Ʒtable�󶨵�������
	 * @author ����ΰ
	 */
	public class Goods{
		
		private String name;
		private String ID;
		private String version;
		private String amount;
		private String reduceamount;
		
		
		

		public Goods(String name, String iD, String version, String amount, String reduceamount) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.reduceamount = reduceamount;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getReduceamount() {
			return reduceamount;
		}

		public void setReduceamount(String reduceamount) {
			this.reduceamount = reduceamount;
		}



		
		
		
	}
}
