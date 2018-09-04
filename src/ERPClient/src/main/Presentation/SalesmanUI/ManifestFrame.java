package main.Presentation.SalesmanUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.text.TabableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerUI.CustomerdetailInfoFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsInFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsInReturnFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsSaleFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsSaleReturnFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

/**
 * �������������Ա�½���������,���ý���Ĳ��ּ���
 * @author ����ΰ
 *
 */
public class ManifestFrame extends MainUIController{
	
	@FXML
	ImageView head;
	
	//���ύ�����б�UI�ؼ�
	@FXML
	TableView<Mainfests> submittedtable;
	
	@FXML
	TableColumn<Mainfests,String> submittedidCol;	
	
	@FXML 
	TableColumn<Mainfests,String> submittedtypeCol;
	
	@FXML 
	TableColumn<Mainfests,String> submittedcusnameCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedtotalpriceCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedoperatorCol;
	
	@FXML
	TableColumn<Mainfests,String> submitteddateCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedstateCol;
	
	//�ݸ��б�UI�ؼ�
	@FXML 
	TableView<Mainfests> drafttable;
	
	@FXML 
	TableColumn<Mainfests,String> draftidCol;
	
	@FXML
	TableColumn<Mainfests,String> drafttypeCol;
	
	@FXML
	TableColumn<Mainfests,String> draftcusnameCol;
	
	@FXML 
	TableColumn<Mainfests,String> draftdateCol;
	
	@FXML 
	TabPane tabpane;
	
	@FXML 
	Tab listtab;

	@FXML 
	Tab drafttab;
	
	@FXML 
	Tab newtab;
	
	@FXML 
	Label littletitle;
	
	ObservableList<Mainfests> submittedtablelist;//�ύ����table�󶨵�����
	
	ObservableList<Mainfests> drafttablelist;//�ݸ嵥��table�󶨵�����
	
	ArrayList<ManifestVO> allmanifestvo = null;//���еĻ���vo
	
	ArrayList<ManifestVO> submmittedvo = new ArrayList<>();//���ύ�Ļ���vo
	
	ArrayList<ManifestVO> draftvo = new ArrayList<>();//�ݸ�vo
	
	@FXML
	TableView<Gifts> giftgoodstable;
	
	@FXML
	TableColumn<Gifts, String> giftgoodsnameCol;
	
	@FXML
	TableColumn<Gifts, String> giftgoodsamountCol;
 
	@FXML
	AnchorPane giftanchorpane;
	
	/**
	 * ����������Ա��������������ĳ�ʼ������
	 *@return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane manifest = FXMLLoader.load(CustomerManageFrame.class.getResource("ManifestManageFXML.fxml"));
			return manifest;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *����fxmlʱ�Զ����صķ�������ʼ����ػ����б�
	 */
	public void initialize() {

		
		try {
				allmanifestvo = RemoteHelper.getManifestBLService().getOperatorManifests(user.getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for (int i = 0; i < allmanifestvo.size(); i++) {
			if (allmanifestvo.get(i).getState().equals("Draft")) {
				draftvo.add(allmanifestvo.get(i));
			}else {
				submmittedvo.add(allmanifestvo.get(i));
			}
		}	
		
		setsubmmittedtable();
		setdrafttable();
		
	}
	
	
	/**
	 *�����ύ�����б��������
	 */
	public void setsubmmittedtable(){
		
		submittedtablelist =FXCollections.observableArrayList();
		
		submittedidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		submittedtypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		submittedcusnameCol.setCellValueFactory(new PropertyValueFactory<>("customername"));
		submittedtotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		submittedoperatorCol.setCellValueFactory(new PropertyValueFactory<>("operator"));
		submitteddateCol.setCellValueFactory(new PropertyValueFactory<>("subdate"));
		submittedstateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
		
		
		
		for (int i = 0; i < submmittedvo.size(); i++) {
			submittedtablelist.add(new Mainfests(
					submmittedvo.get(i).getID(),
					submmittedvo.get(i).getType(),
					submmittedvo.get(i).getCustomerName(),
					submmittedvo.get(i).getSum()+"",
					submmittedvo.get(i).getOperator(),
					submmittedvo.get(i).getCreateDate(),
					submmittedvo.get(i).getState()));
		}
		submittedtable.setItems(submittedtablelist);
		
		


	}
	
	/**
	 *�󶨲ݸ�����б��������
	 */
	public void setdrafttable(){
		
		drafttablelist =FXCollections.observableArrayList();
		
		
		draftidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		drafttypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		draftcusnameCol.setCellValueFactory(new PropertyValueFactory<>("customername"));
		draftdateCol.setCellValueFactory(new PropertyValueFactory<>("subdate"));
		
		
		for (int i = 0; i < draftvo.size(); i++) {
			drafttablelist.add(new Mainfests(
					draftvo.get(i).getID(),
					draftvo.get(i).getType(),
					draftvo.get(i).getCustomerName(),
					draftvo.get(i).getCreateDate()));
		}
		
		drafttable.setItems(drafttablelist);
		
		

	}
	
	/**
	 * ɾ���ݸ�
	 */
	@FXML
	protected void deletedraft(ActionEvent event) {
		if (drafttable.getSelectionModel().getSelectedIndex()==-1) {
			//��δѡ���۽�����
		}else {
		ResultMessage resultMessage = null;
		try {
			resultMessage = RemoteHelper.getManifestBLService().deleteManifest(draftvo.get(drafttable.getSelectionModel().getSelectedIndex()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			
		}
		}
		MainFrame.setSceneRoot(ManifestFrame.init());
    }
	
	/**
	 * �򿪲ݸ�
	 */
	@FXML
	protected void opendraft(ActionEvent event) {
		ManifestVO vo = null;
		vo = draftvo.get(drafttable.getSelectionModel().getSelectedIndex());
		if (vo.getType().equals("JHD")) {
			MainFrame.setSceneRoot(GoodsInFrame.init("draft", vo));
		}else if (vo.getType().equals("JHTHD")) {
			MainFrame.setSceneRoot(GoodsInReturnFrame.init("draft", vo));
		}else if (vo.getType().equals("XSD")) {
			MainFrame.setSceneRoot(GoodsSaleFrame.init("draft",vo));
		}else if (vo.getType().equals("XSTHD")) {
			MainFrame.setSceneRoot(GoodsSaleReturnFrame.init("draft",vo));
		}

    }
	
	/**
	 * ���ؽ���������Ա�����水ť�ļ���
	 */
	@FXML
	public void returnSalesmanhomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(SalesmanHomeFrame.init());
    }
	
	/**
	 * �����½���������д���水ť�ļ���
	 */
	@FXML
	public void GoodsInListFrameHandle(ActionEvent event ) {
        MainFrame.setSceneRoot(GoodsInFrame.init("",null));
    }
	
	/**
	 * �����½������˻�����д���水ť�ļ���
	 */
	@FXML
	public void GoodsInReturnListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsInReturnFrame.init("",null));
    }

	/**
	 * �����½����۵���д���水ť�ļ���
	 */
	@FXML
	public void GoodsSaleListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsSaleFrame.init("",null));
    }
	

	/**
	 * �����½������˻�����д���水ť�ļ���
	 */
	@FXML
	protected void GoodsSaleReturnListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsSaleReturnFrame.init("",null));
    }
	
	/**
	 * ��������б���水ť�ļ���
	 */
	@FXML
	protected void changelisttab(ActionEvent event) {
		tabpane.getSelectionModel().select(listtab);
		littletitle.setText("�����б�");
    }
	
	/**
	 * ����ݸ��б���水ť�ļ���
	 */
	@FXML
	protected void changedrafttab(ActionEvent event) {
		tabpane.getSelectionModel().select(drafttab);
		littletitle.setText("�ݸ��б�");
    }
	
	/**
	 * �����½��������水ť�ļ���
	 */
	@FXML
	protected void changenewtab(ActionEvent event) {
		tabpane.getSelectionModel().select(newtab);
		littletitle.setText("�½�����");
    }
	
	/**
	 * ˫���в鿴����������������Ʒ�б�ļ���
	 */
	@FXML
	protected void getgifts(MouseEvent event) {
		ManifestVO hasgiftsvo = null;
		for (int i = 0; i < submmittedvo.size(); i++) {
			if (submmittedvo.get(i).getID().equals(submittedtable.getSelectionModel().getSelectedItem().getId())) {
				hasgiftsvo = submmittedvo.get(i);
				break;
			}
		}
		
		if (event.getClickCount() == 2) {
//				CustomerdetailInfoFrame customerdetailInfoFrame = new CustomerdetailInfoFrame();
//				customerdetailInfoFrame.init(detailvo);
			if (hasgiftsvo.getState().equals("Checked")) {
				
				ObservableList<Gifts> giftsdata = FXCollections.observableArrayList();
				for (int i = 0; i < hasgiftsvo.getGiftGoodslist().size(); i++) {
					giftsdata.add(new Gifts(hasgiftsvo.getGiftGoodslist().get(i).getName(),
							hasgiftsvo.getGiftGoodslist().get(i).getAmounts()+""));
				}
				
				giftgoodsnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
				giftgoodsamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
				giftgoodstable.setItems(giftsdata);
				giftanchorpane.setVisible(true);
				
			}
		}
	}
	
	/**
	 * ȷ�ϲ鿴���͵��İ�ť����
	 */
	@FXML
	protected void suregifts() {
		giftanchorpane.setVisible(false);
	}
	
	/**
	 * ���ڻ���tableview�󶨵�������
	 * @author ����ΰ
	 */
	public class Mainfests{
		
		private String id;
		private String type;
		private String customername;
		private String totalprice;
		private String operator;
		private String subdate;
		private String state;
		
		
		
		public Mainfests(String id, String type, String customername, String subdate) {
			super();
			this.id = id;
			this.type = type;
			this.customername = customername;
			this.subdate = subdate;
		}

		public Mainfests(String id, String type, String customername, String totalprice, String operator,
				String subdate, String state) {
			super();
			this.id = id;
			this.type = type;
			this.customername = customername;
			this.totalprice = totalprice;
			this.operator = operator;
			this.subdate = subdate;
			this.state = state;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCustomername() {
			return customername;
		}
		public void setCustomername(String customername) {
			this.customername = customername;
		}
		public String getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(String totalprice) {
			this.totalprice = totalprice;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getSubdate() {
			return subdate;
		}
		public void setSubdate(String subdate) {
			this.subdate = subdate;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	}
	
	/**
	 * ����������Ʒtableview�󶨵�������
	 * @author ����ΰ
	 */
	public class Gifts{
		private String name;
		private String amount;
		
		
		
		public Gifts(String name, String amount) {
			super();
			this.name = name;
			this.amount = amount;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
	}
}
