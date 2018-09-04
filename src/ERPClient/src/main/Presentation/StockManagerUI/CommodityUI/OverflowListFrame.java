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
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.Presentation.StockManagerUI.CommodityUI.WarningListFrame.Goods;
import main.RMI.RemoteHelper;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * ��������Ա��д���絥����Ŀ�����
 * @author ����ΰ
 *
 */
public class OverflowListFrame extends MainUIController{
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
	TableColumn<Goods, String> addedamountColumn;
	
	static ArrayList<GoodsVO> overflows;
	
	ObservableList<Goods> overflowslist;
	
	ArrayList<CommodityReciptVO> submitoverflowvo = new ArrayList<>();
	
	/**
	 * ��������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 * @param goods Ԥ��д���ݰ���Ʒvo��list
	 */
	public static Parent init(ArrayList<GoodsVO> goods){
		overflows = goods;
		try {
			GridPane overflowlist = FXMLLoader.load(OverflowListFrame.class.getResource("OverflowListFrameFXML.fxml"));
			return overflowlist;
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
		addedamountColumn.setCellFactory(TextFieldTableCell.<Goods>forTableColumn());
		addedamountColumn.setOnEditCommit(
			    (CellEditEvent<Goods, String> t) -> {
			        ((Goods) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setAddedamount(t.getNewValue());
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
		for (int i = 0; i < overflows.size(); i++) {
			System.out.println(overflows.get(i).getName()+overflows.get(i).getAlertAmounts()+"      2");
		}
		overflowslist= FXCollections.observableArrayList();
		
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));      
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));      
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));      
        addedamountColumn.setCellValueFactory(new PropertyValueFactory<>("addedamount"));
        for (int i = 0; i < overflows.size(); i++) {
			overflowslist.add(new Goods(
					overflows.get(i).getName(),
					overflows.get(i).getID(),
					overflows.get(i).getVersion(),
					overflows.get(i).getAmounts()+"",
					"0"));
		}
        
        goodstable.setItems(overflowslist);
	}
	
	/**
	 * ȷ���ύ���絥��ť�ļ���
	 */
	public void submitoverflowlist(){
		ResultMessage resultMessage = null;
		
		for (int i = 0; i < overflowslist.size(); i++) {
			System.out.println(overflowslist.get(i).getName()+" "+overflowslist.get(i).getAddedamount());
//			resultMessage = RemoteHelper.getCommodityReciptBLService().setWarningList(vo);
			submitoverflowvo.add(new CommodityReciptVO("BY",
					overflowslist.get(i).getName(),
					overflowslist.get(i).getID(),
					Integer.parseInt(overflowslist.get(i).getAddedamount()),
					"�ᱻ����",
					"Unchecked",
					user.getName()));
		}
		for (int i = 0; i < submitoverflowvo.size(); i++) {
			try {
				resultMessage = RemoteHelper.getCommodityReciptBLService().setOverflowList(submitoverflowvo.get(i));
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
		private String addedamount;
		
		

		public Goods(String name, String iD, String version, String amount, String addedamount) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.addedamount = addedamount;
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

		public String getAddedamount() {
			return addedamount;
		}

		public void setAddedamount(String addedamount) {
			this.addedamount = addedamount;
		}



		
		
		
	}
}
