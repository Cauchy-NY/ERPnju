package main.Presentation.SalesmanUI.ManifestUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.ManifestFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

/**
 * ����������Ա�����˻�����д����Ŀ�����
 * @author ����ΰ
 *
 */
public class GoodsInReturnFrame extends MainUIController {
	
	@FXML
	TextField id;
	
	@FXML
	AnchorPane submitmessage;
	
	@FXML
	ComboBox<String> producer;
	
	@FXML
	TextField operator;
	
	
	@FXML
	TextField totalprice;
	
	double totalprize = 0;
	
	@FXML
	TextArea remarks;
	

	@FXML
	ComboBox<String> warehouse;
	
	@FXML 
	TableView<GoodsinReturn> goodstable;
	
	@FXML
	TableColumn<GoodsinReturn, String> idCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> nameCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> modelCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> numberCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> unitpriceCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> amountCol;
	
	@FXML
	TableColumn<GoodsinReturn, String> remarksCol;
	
	@FXML
	ComboBox<String> goodsname;
	
	@FXML
	TextField goodsid;
	
	@FXML
	TextField goodsmodel;
	
	@FXML
	TextField goodsnumber;
	
	@FXML
	TextField goodsunitprice;
	
	@FXML
	TextField goodstotalprice;
	
	@FXML
	TextArea goodsremark;
	
	@FXML
	TabPane tabpane;
	
	@FXML
	Tab listinfo;
	
	@FXML
	Tab goodsinfo;
	
	@FXML
	AnchorPane submitsucccess;
	
	@FXML
	Button successsure;
	
	@FXML 
	AnchorPane goodsaddpane;
	
	@FXML
	AnchorPane reminddraftanchor;
	
	@FXML
	TextField totalprice1;
	
	@FXML
	Label goodsnumlimit;
	
	
	ManifestVO manifestgoodsinReturn;

	ObservableList<GoodsinReturn> inreturnlist = FXCollections.observableArrayList();
	
	ArrayList<GoodsVO> goodslist = new ArrayList<>();
	
	ArrayList<CustomerVO> producerlist = new ArrayList<>();
	
	ArrayList<ReciptGoodsVO> reciptGoodsVOlist = new ArrayList<>();
	
	static ManifestVO draftvo;
	
	static String newsource;
	
	/**
	 * ����������Ա��д�����˻�������ĳ�ʼ������
	 *@return ���ؼ��غõĽ���
	 *@param source �Ƿ�򿪲ݸ�
	 *@param vo �ݸ�vo����null
	 */
	public static Parent init(String source,ManifestVO vo){
		newsource = source;
		draftvo = vo;
		try {
			GridPane goodsinreturnlist = FXMLLoader.load(GoodsInReturnFrame.class.getResource("GoodsInReturnFrameFXML.fxml"));
			return goodsinreturnlist;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *����fxmlʱ�Զ����صķ�������Ʒtable����Ϣ��
	 */
	public void initialize(){
		
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		unitpriceCol.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("totalprices"));
		remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
		
//		int a = 1;
		String ids = "" ;
		if (newsource.equals("")) {//�½�����
			try {
				ids = RemoteHelper.getManifestBLService().getNextManifestID("JHTHD");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("2");	
		}else {//�򿪲ݸ�
			
			ids = draftvo.getID();
			producer.setValue(draftvo.getCustomerName());
			warehouse.setValue(draftvo.getWarehouse());
			operator.setText(draftvo.getOperator());
			remarks.setText(draftvo.getComment());
			totalprice.setText(draftvo.getSum()+"");
			totalprice1.setText(draftvo.getSum()+"");
//			coupon.setValue(draftvo.ge);
//			infototalpricefinal.setText(draftvo.getSum()+"");
			List<ReciptGoodsVO> draftgoodslist = draftvo.getGoodsList();
			for (int i = 0; i < draftgoodslist.size(); i++) {
				inreturnlist.add(new GoodsinReturn(
						draftgoodslist.get(i).getGoodsID(),
						draftgoodslist.get(i).getName(),
						draftgoodslist.get(i).getVersion(),
						draftgoodslist.get(i).getAmounts()+"",
						draftgoodslist.get(i).getBid()+"",
						draftgoodslist.get(i).getSum()+"",
						draftgoodslist.get(i).getComment()));
			}
			totalprize = draftvo.getSum();
		}
		
//		goodsinfo.setDisable(true);
		id.setText(ids);
		operator.setText(user.getName());
		//���ؽ�����
		try {
			producerlist = RemoteHelper.getManifestBLService().getAllCustomerName("������");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ObservableList<String> producersnames = FXCollections.observableArrayList();
		for (int i = 0; i < producerlist.size(); i++) {
			producersnames.add(producerlist.get(i).getName());
		}
		producer.setItems(producersnames);
		
		//������Ʒ�����б�
		try {
			goodslist = RemoteHelper.getManifestBLService().getGoods();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> goodsnamess = FXCollections.observableArrayList();
		for (int i = 0; i < goodslist.size(); i++) {
				 goodsnamess.add(goodslist.get(i).getName()+" "+goodslist.get(i).getVersion());
		}
		goodsname.setItems(goodsnamess);
		
		//ѡ����Ʒ��Ĭ��������Ʒid ���� �ͺŵļ���
		goodsname.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{
							goodsid.setText(goodslist.get(newval.intValue()).getID());
							goodsunitprice.setText(goodslist.get(newval.intValue()).getBid()+"");
							goodsmodel.setText(goodslist.get(newval.intValue()).getVersion());
						});
		
		//��д��Ʒ����ʱ�����ǳ����������
		goodsnumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (goodsnumber.getText().equals("")) {
					//ɾ��Ʒ��������
				}else{	  
					double maxamount = 0;
				    for (int i = 0; i < goodslist.size(); i++) {
						if (goodslist.get(i).getID().equals(goodsid.getText())) {
							maxamount = goodslist.get(i).getAmounts();
							break;
						}
					}
				    if (maxamount>=Double.parseDouble(goodsnumber.getText())) {
						goodsnumlimit.setText("��Ʒ�������Ϲ涨");
				    	goodstotalprice.setText(Double.parseDouble(goodsunitprice.getText()) * Double.parseDouble(goodsnumber.getText())+"");
					}else {
						goodsnumlimit.setText("��Ʒ�����������");
				    	goodstotalprice.setText(Double.parseDouble(goodsunitprice.getText()) * Double.parseDouble(goodsnumber.getText())+"");
					}
				}
            }
        });
		
		goodstable.setItems(inreturnlist);
		
	}
	
	/**
	 * ȷ���ύ�����İ�ť����
	 */
	@FXML
	public void submitsure(){
		ResultMessage resultMessage = null;
		//�ύ����vo����Ϣ����
		for (int i = 0; i < inreturnlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					inreturnlist.get(i).getName(),
					inreturnlist.get(i).getModel(),
					inreturnlist.get(i).getId(),
					Integer.parseInt(inreturnlist.get(i).getNumber()),
					Double.parseDouble(inreturnlist.get(i).getUnitprice()),
					Double.parseDouble(inreturnlist.get(i).getTotalprices()), 
					inreturnlist.get(i).getRemarks()));
		}
					
		if (newsource.equals("")) {  //�½��������Ǳ༭�Ĳݸ�
				manifestgoodsinReturn = new ManifestVO(id.getText()+"",
						producer.getSelectionModel().getSelectedItem()+"",
						warehouse.getSelectionModel().getSelectedItem()+"",
						operator.getText()+"",
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()+""),
						"Unchecked",
						operator.getText());
			try {
				resultMessage = RemoteHelper.getManifestBLService().setGoodsInReturnList(manifestgoodsinReturn);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {  //�༭�Ĳݸ�����½�
				manifestgoodsinReturn = new ManifestVO(id.getText()+"",
						producer.getSelectionModel().getSelectedItem()+"",
						warehouse.getSelectionModel().getSelectedItem()+"",
						operator.getText()+"",
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()+""),
						"Unchecked",
						operator.getText());

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsinReturn);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			submitmessage.setVisible(false);
			submitsucccess.setVisible(true);
	        successsure.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	               MainFrame.setSceneRoot(ManifestFrame.init());
	            }
	        });
		}
		
}
	/**
	 * ��ʾ�Ƿ񱣴�ݸ�
	 */
	@FXML
	public void remindsavedraft(){
		tabpane.getSelectionModel().selectFirst();
		reminddraftanchor.setVisible(true);
	}
	
	/**
	 * ������ݰ�ť�ļ���
	 */
	@FXML
	public void notsavedraft(){
		MainFrame.setSceneRoot(ManifestFrame.init());
	}
	
	/**
	 * ����ݸ尴ť�ļ���
	 */
	@FXML
	public void savedraft(){
		ResultMessage resultMessage = null;
		for (int i = 0; i < inreturnlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					inreturnlist.get(i).getName(),
					inreturnlist.get(i).getModel(),
					inreturnlist.get(i).getId(),
					Integer.parseInt(inreturnlist.get(i).getNumber()),
					Double.parseDouble(inreturnlist.get(i).getUnitprice()),
					Double.parseDouble(inreturnlist.get(i).getTotalprices()), 
					inreturnlist.get(i).getRemarks()));
		}
					
		if (newsource.equals("")) {//�½��������Ǳ༭�Ĳݸ�
				manifestgoodsinReturn = new ManifestVO(id.getText()+"",
						producer.getSelectionModel().getSelectedItem()==null?"":producer.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()),
						"Draft",
						operator.getText());
			
			try {
				resultMessage = RemoteHelper.getManifestBLService().setGoodsInReturnList(manifestgoodsinReturn);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {//�༭�Ĳݸ�����½�
				manifestgoodsinReturn = new ManifestVO(id.getText()+"",
						producer.getSelectionModel().getSelectedItem()==null?"":producer.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()),
						"Draft",
						operator.getText()+"");

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsinReturn);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			MainFrame.setSceneRoot(ManifestFrame.init());
		}
	}
	
	/**
	 * �����Ʒ�б�İ�ť�ļ���
	 */
	@FXML
	public void showgoodsinfo(){
//		goodsinfo.setDisable(false);
		tabpane.getSelectionModel().selectNext();
	}
	
	/**
	 * ��Ҫ��Ϣ��д����İ�ť����
	 */
	@FXML
	public void showlistinfo(){
		tabpane.getSelectionModel().selectFirst();

	}
	
	/**
	 * �����ύ��ť����
	 */
	@FXML
	public void sure(){
		submitmessage.setVisible(true);
	}
	
	/**
	 * �����Ʒ�İ�ť����
	 */
	@FXML
	public void goodsadd(){
		goodsaddpane.setVisible(true);
	}
	
	/**
	 * ȡ�������Ʒ�İ�ť�ļ���
	 */
	@FXML
	public void goodsaddcancel(){
	    goodsid.clear();
	    goodsnumber.setText("0");;
	    goodsmodel.clear();
	    goodsremark.clear();
	    goodstotalprice.setText("0");
	    goodsunitprice.clear();
	    goodsname.setValue("");
		goodsaddpane.setVisible(false);
	}
	
	/**
	 * ȷ�������Ʒ�İ�ť�ļ���
	 */
	@FXML
	public void goodsadddsure(){
		
		if (goodstotalprice.getText().equals("0")||goodsnumlimit.getText().equals("��Ʒ�����������")) {
			//������Ϊ0����Ʒ�����
		}else {
			 inreturnlist.add(new GoodsinReturn(goodsid.getText(),
						goodsname.getSelectionModel().getSelectedItem().split(" ")[0],
						goodsmodel.getText(), 
						goodsnumber.getText(), 
						goodsunitprice.getText(), 
						goodstotalprice.getText(), 
						goodsremark.getText()));
					  
					    totalprize = totalprize + Double.parseDouble(goodstotalprice.getText());
					    totalprice.setText(totalprize+"");
					    totalprice1.setText(totalprize+"");
					 
					    goodsid.clear();
					    goodsnumlimit.setText("");
					    goodsnumber.setText("0");;
					    goodsmodel.clear();
					    goodsremark.clear();
					    goodstotalprice.setText("0");
					    goodsunitprice.clear();
					    goodsname.setValue("");
					    goodsaddpane.setVisible(false);
		}
		
		    
	}
	
	
	/**
	 * ɾ����Ʒ�İ�ť�ļ���
	 */
	@FXML
	public void goodsdelete(){
		for (int i = 0; i <inreturnlist.size(); i++) {
			System.out.println(inreturnlist.get(i).getTotalprices());
		}
	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());

		totalprize = totalprize - Double.parseDouble(inreturnlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
		inreturnlist.remove(goodstable.getSelectionModel().getSelectedIndex());
//		totalprize = totalprize - Double.parseDouble(inlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
//	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());
	    totalprice.setText(totalprize+"");
	    totalprice1.setText(totalprize+"");
//	    infototalprice.setText(totalprize+"");
	}
	
	/**
	 * ȡ���ύ�İ�ť�ļ���
	 */
	@FXML
	public void cancelsubmit(){
		submitmessage.setVisible(false);
	}
	
	/**
	 * ������Ʒtableview�󶨵�������
	 * @author ����ΰ
	 */
	public class GoodsinReturn{
		
		
		private String id;
		private String name;
		private String model;//�ͺ�
		private String number;
		private String unitprice;//����
		private String totalprices;
		private String remarks;
		public GoodsinReturn() {
			// TODO Auto-generated constructor stub
		}
		
		public GoodsinReturn(String id,String name,String model,String number,String unitprice,String amount,String remarks) {
			// TODO Auto-generated constructor stub
			this.id = id;
			this.name = name;
			this.model = model;
			this.number = number;
			this.unitprice = unitprice;
			this.totalprices= amount;
			this.remarks = remarks;
			
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getUnitprice() {
			return unitprice;
		}
		public void setUnitprice(String unitprice) {
			this.unitprice = unitprice;
		}
		public String getTotalprices() {
			return totalprices;
		}
		public void setTotalprices(String amount) {
			this.totalprices = amount;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
	}
	
	
}
