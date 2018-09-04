package main.Presentation.FinancialStaffUI.AccountUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.Presentation.FinancialStaffUI.AccountFrame;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.AccountVO;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

/**
 * �ڳ����˽����controller
 * @author 49869
 *
 */
public class CreateAccountFrame extends MainUIController{
	private AccountBLService accountBL;
	
	@FXML
	ImageView head;
	
	@FXML
	ComboBox<String> searchTypeBox;
	@FXML
	ComboBox<String> searchConditionBox;
	@FXML
	ComboBox<String> searchBox;
	
	@FXML
	TableView<GoodsInfo> goodsTable;
	@FXML
	TableColumn<GoodsInfo, String> goodsFenLeiColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsNameColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsXingHaoColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsBuyPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsSellPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsRecentBuyPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsRecentSellPriceColumn;

	@FXML
	TableView<CustomerInfo> customerTable;
	@FXML
	TableColumn<CustomerInfo, String> customerCategoryColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerNameColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerContactColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerReceivableColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerPayableColumn;
	
	@FXML
	TableView<BankAccountInfo> bankAccountTable;
	@FXML
	TableColumn<BankAccountInfo, String> bankAccountNameColumn;
	@FXML
	TableColumn<BankAccountInfo, Double> bankAccountAmountColumn;
	
	private static ArrayList<GoodsVO> searchGoodsList;
	private static ArrayList<CustomerVO> searchCustomerList;
	private static ArrayList<BankAccountVO> searchBankAccountList;
	
	private boolean isModify = false;
	
	/**
	 * �ڳ����˽���ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane createAccountFrame = FXMLLoader.load(CreateAccountFrame.class.getResource("CreateAccountFrameFXML.fxml"));
			return createAccountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * ����FXMLʱ��ʼ��table��comboBox��
	 */
	@FXML
	private void initialize() {
		accountBL = RemoteHelper.getAccountBLService();
//		searchBox.getEditor().textProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				// TODO Auto-generated method stub
//				System.out.println("test");
//				searchAndAdd();
//			}
//			
//		});
		
//		searchBox.getEditor().setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent event) {
//				// TODO Auto-generated method stub
//				if(event.getCode() == KeyCode.ENTER) {
//					searchAndAdd();
//				}
//			}
//			
//		});

//		searchBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				// TODO Auto-generated method stub
//				if(oldValue.intValue() == newValue.intValue()) {
//					System.out.println("sss");
//				}
//				
//			}
//			
//		});
		searchConditionBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(newValue != null) {
					isModify = true;
					searchBox.getItems().clear();
					isModify = false;
					searchAndAdd();
				}
			}
			
		});
		
		searchBox.setOnKeyPressed((KeyEvent) -> {
			if(KeyEvent.getCode() == KeyCode.DOWN) {
				searchBox.getSelectionModel().selectNext();
				KeyEvent.consume();
			}
			if(KeyEvent.getCode() == KeyCode.UP) {
				searchBox.getSelectionModel().selectPrevious();
				KeyEvent.consume();
			}
		});

		//��ʼ��ComboBox
		searchTypeBox.setItems(FXCollections.observableArrayList("��Ʒ", "�ͻ�", "�����˻�"));
		
		goodsFenLeiColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("fenLei"));
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("name"));
		goodsXingHaoColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("xingHao"));
		goodsBuyPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("buyPrice"));
		goodsSellPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("sellPrice"));
		goodsRecentBuyPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("recentBuyPrice"));
		goodsRecentSellPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("recentSellPrice"));

//		ObservableList<GoodsInfo> goodsList= FXCollections.observableArrayList(new GoodsInfo("1", "1", "1", "1", "1", "1", "1", "1"));
//		goodsTable.setItems(goodsList);
		goodsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		customerCategoryColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("category"));
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("name"));
		customerContactColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("contact"));
		customerReceivableColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("receivable"));
		customerPayableColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("payable"));

//		ObservableList<CustomerInfo> customerList = FXCollections.observableArrayList(new CustomerInfo("1", "1", "1", 1, 1));
//		customerTable.setItems(customerList);
		customerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		bankAccountNameColumn.setCellValueFactory(new PropertyValueFactory<BankAccountInfo, String>("name"));
		bankAccountAmountColumn.setCellValueFactory(new PropertyValueFactory<BankAccountInfo, Double>("amount"));
		
//		ObservableList<BankAccountInfo> bankAccountList = FXCollections.observableArrayList(new BankAccountInfo("1", 1));
//		bankAccountTable.setItems(bankAccountList);
		bankAccountTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//		oldIndex = searchBox.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	private void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	private void back(){
		Parent back = AccountFrame.init();
		MainFrame.setSceneRoot(back);
	}
	
	/**
	 * �������͵ļ���
	 */
	@FXML
	private void selectSearchType() {
		String type = searchTypeBox.getSelectionModel().getSelectedItem();
//		if(type.equals("��Ʒ")) {
//			searchConditionBox.setItems(FXCollections.observableArrayList("ID", "��Ʒ����"));
//		}
		switch(type) {
		case "��Ʒ":{
			searchConditionBox.setItems(FXCollections.observableArrayList("ID", "��Ʒ����"));
			break;
		}
		case "�ͻ�":{
			searchConditionBox.setItems(FXCollections.observableArrayList("ID", "����"));
			break;
		}
		case "�����˻�":{
			searchConditionBox.setItems(FXCollections.observableArrayList("����"));
			break;
		}
		}
	}
	
	@FXML
	private void input() {
		System.out.println("test");
	}
	
	/**
	 * ������ļ���
	 */
	@FXML
	private void searchAndAdd() {
//		newIndex = searchBox.getSelectionModel().getSelectedIndex();
//		if(oldIndex == newIndex && oldIndex >= 0) {
//			System.out.println("hh");
//			return;
//		}
//		oldIndex = newIndex;
//		addSearchToTable();
//		searchBox.getItems().clear();

//		if(addSearchToTable()) {
////			searchBox.getSelectionModel().clearSelection();
//			return;
//		}
		//���÷�������
		
//		searchBox.show();
		if(isModify) {			//�������һ��action��������޸ģ�����
//			System.out.println("modify");
			return;
		}
		if(searchTypeBox.getSelectionModel().isEmpty() || searchConditionBox.getSelectionModel().isEmpty()) {
			return;
		}
		if(searchBox.getSelectionModel().isEmpty()) {		//���δѡ�У�˵��������
			isModify = true;
			searchBox.getItems().clear();
			isModify = false;
			
			String value = searchBox.getValue();
			if(value == null) {
				value = "";
			}
			
			switch(searchTypeBox.getSelectionModel().getSelectedItem()) {
			case "��Ʒ":{
				String condition = searchConditionBox.getSelectionModel().getSelectedItem();
				if(condition.equals("ID")) {
					try {
	//					ArrayList<GoodsVO> searchList = accountBL.getGoods("ID", searchBox.getValue());
	//					for(int i = 0;i < searchList.size();i++) {
	//						searchBox.getItems().add(searchList.get(i).getID());
	//					}
						searchGoodsList = accountBL.getGoods("ID", value);
						for(int i = 0;i < searchGoodsList.size();i++) {
							searchBox.getItems().add(searchGoodsList.get(i).getID());
						}
	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(condition.equals("��Ʒ����")) {
					try {
	//					ArrayList<GoodsVO> searchList = accountBL.getGoods("name", searchBox.getValue());
	//					for(int i = 0;i < searchList.size();i++) {
	//						searchBox.getItems().add(searchList.get(i).getName());
	//					}
						searchGoodsList = accountBL.getGoods("name", value);
						for(int i = 0;i < searchGoodsList.size();i++) {
							searchBox.getItems().add(searchGoodsList.get(i).getName());
						}
	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			}
			case "�ͻ�":{
				String condition = searchConditionBox.getSelectionModel().getSelectedItem();
				if(condition.equals("ID")) {
					try {
	//					ArrayList<CustomerVO> searchList = accountBL.getCustomer("ID", searchBox.getValue());
	//					for(int i = 0;i < searchList.size();i++) {
	//						searchBox.getItems().add(searchList.get(i).getID());
	//					}
						searchCustomerList = accountBL.getCustomer("ID", value);
						for(int i = 0;i < searchCustomerList.size();i++) {
							searchBox.getItems().add(searchCustomerList.get(i).getID());
						}
	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(condition.equals("����")) {
					try {
	//					ArrayList<CustomerVO> searchList = accountBL.getCustomer("name", searchBox.getValue());
	//					for(int i = 0;i < searchList.size();i++) {
	//						searchBox.getItems().add(searchList.get(i).getName());
	//					}
						searchCustomerList = accountBL.getCustomer("name", value);
						for(int i = 0;i < searchCustomerList.size();i++) {
							searchBox.getItems().add(searchCustomerList.get(i).getName());
						}
	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			}
			case "�����˻�":{
				String condition = searchConditionBox.getSelectionModel().getSelectedItem();
				if(condition.equals("����")) {
					try {
	//					ArrayList<BankAccountVO> searchList = accountBL.getBankAccount(searchBox.getValue());
	//					for(int i = 0;i < searchList.size();i++) {
	//						searchBox.getItems().add(searchList.get(i).getId());
	//					}
						searchBankAccountList = accountBL.getBankAccount(value);
						for(int i = 0;i < searchBankAccountList.size();i++) {
							searchBox.getItems().add(searchBankAccountList.get(i).getId());
						}
	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			}
			}
			searchBox.show();
		}
		else {				//��ѡ�У�˵�������
			switch(searchTypeBox.getSelectionModel().getSelectedItem()) {
			case "��Ʒ":{
				//System.out.println("index:" + searchBox.getSelectionModel().getSelectedIndex() + " size:" + searchGoodsList.size());
				GoodsVO goodsVO = searchGoodsList.get(searchBox.getSelectionModel().getSelectedIndex());
				goodsTable.getItems().add(new GoodsInfo(goodsVO.getCatagory(), goodsVO.getName(), goodsVO.getVersion(), ""+goodsVO.getBid(), ""+goodsVO.getRetailPrice(), "", ""));
				
				break;
			}
			case "�ͻ�":{
				CustomerVO customerVO = searchCustomerList.get(searchBox.getSelectionModel().getSelectedIndex());
				customerTable.getItems().add(new CustomerInfo(customerVO.getID(), customerVO.getCategory(), customerVO.getName(), customerVO.getNumber(), customerVO.getReceive(), customerVO.getPayment()));
				
				break;
			}
			case "�����˻�":{
				BankAccountVO bankAccountVO = searchBankAccountList.get(searchBox.getSelectionModel().getSelectedIndex());
				bankAccountTable.getItems().add(new BankAccountInfo(bankAccountVO.getId(), bankAccountVO.getAmount()));
				break;
			}
			}
//			searchBox.getSelectionModel().clearSelection();
			searchBox.hide();
			isModify = true;		//��Ҫ�����޸ģ�����Ϊ�޸�״̬
			Platform.runLater(()->{
				searchBox.getSelectionModel().clearSelection();
				searchBox.getItems().clear();
				isModify = false;	//�޸����
			});

		}

	}
	
	/**
	 * ɾ����Ʒ��Ϣ�б��а�ť�ļ���
	 */
	@FXML
	private void deleteGoodsRow() {
		goodsTable.getItems().removeAll(goodsTable.getSelectionModel().getSelectedItems());
	}
	
	/**
	 * ɾ���ͻ���Ϣ�б��а�ť�ļ���
	 */
	@FXML
	private void deleteCustomerRow() {
		customerTable.getItems().removeAll(customerTable.getSelectionModel().getSelectedItems());
	}
	
	/**
	 * ɾ�������˻��б�ť�ļ���
	 */
	@FXML
	private void deleteBankAccountRow() {
		customerTable.getItems().removeAll(customerTable.getSelectionModel().getSelectedItems());
	}
	
	/**
	 * ȡ����ť�ļ���
	 */
	@FXML
	private void cancel() {
		
		back();
	}
	
	/**
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	private void sure() {
		//���VO
//		ArrayList<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		ArrayList<ReciptGoodsVO> goodsVOList = new ArrayList<ReciptGoodsVO>();
		ArrayList<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
		ArrayList<BankAccountVO> bankAccountVOList = new ArrayList<BankAccountVO>();
		
		ObservableList<GoodsInfo> goodsInfoList = goodsTable.getItems();
		ObservableList<CustomerInfo> customerInfoList = customerTable.getItems();
		ObservableList<BankAccountInfo> bankAccountInfoList = bankAccountTable.getItems();
		
//		for(int i = 0;i < goodsInfoList.size();i++) {
//			GoodsInfo info = goodsInfoList.get(i);
////			GoodsVO vo = new GoodsVO(info.getName(), "1");		//TODO ȱ�ٲ���
//			GoodsVO vo = new GoodsVO(info.getName(), "", info.getXingHao(), 0, Double.parseDouble(info.getBuyPrice()), Double.parseDouble(info.getSellPrice()), 0, 0, info.getFenLei(), 0, "");
//			goodsVOList.add(vo);
//		}
		for(int i = 0;i < goodsInfoList.size();i++) {
			GoodsInfo info = goodsInfoList.get(i);
			ReciptGoodsVO vo = new ReciptGoodsVO(0, info.getName(), info.getXingHao(), "", 0, Double.parseDouble(info.getBuyPrice()), Double.parseDouble(info.getSellPrice()), info.getFenLei());
			goodsVOList.add(vo);
		}
		for(int i = 0;i < customerInfoList.size();i++) {
			CustomerInfo info = customerInfoList.get(i);
//			CustomerVO vo = new CustomerVO(null, info.getCategory(), null, info.getName(), info.getContact(), null, null, info.getReceivable(), info.getPayable(), 0, null);
//			customerVOList.add(vo);

			try {
				CustomerVO vo = accountBL.getCustomer("ID", info.getID()).get(0);
				customerVOList.add(vo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0;i < bankAccountInfoList.size();i++) {
			BankAccountInfo info = bankAccountInfoList.get(i);
//			BankAccountVO vo = new BankAccountVO(info.getName(), info.getAmount(), null);
//			bankAccountVOList.add(vo);
			
			try {
				BankAccountVO vo = accountBL.getBankAccount(info.getName()).get(0);
				bankAccountVOList.add(vo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		AccountVO vo = new AccountVO(goodsVOList, customerVOList, bankAccountVOList, username);
		
		try {
			ResultMessage result = accountBL.set(vo);
			if(result == ResultMessage.SUCCESS) {
				System.out.println("success");
			}
			else {
				System.out.println("fail");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		back();
	}
	
	
	/**
	 * ������Ʒ��Ϣ�б����ݰ󶨵���
	 * @author ��Ԭ��
	 *
	 */
	public class GoodsInfo{
		private SimpleStringProperty fenLei;
		private SimpleStringProperty name;
		private SimpleStringProperty xingHao;
		private SimpleStringProperty buyPrice;
		private SimpleStringProperty sellPrice;
		private SimpleStringProperty recentBuyPrice;
		private SimpleStringProperty recentSellPrice;
		
		public GoodsInfo(String fenLei, String name, String xingHao, String buyPrice, 
				String sellPrice, String recentBuyPrice, String recentSellPrice) {
			this.fenLei = new SimpleStringProperty(fenLei);
			this.name = new SimpleStringProperty(name);
			this.xingHao = new SimpleStringProperty(xingHao);
			this.buyPrice = new SimpleStringProperty(buyPrice);
			this.sellPrice = new SimpleStringProperty(sellPrice);
			this.recentBuyPrice = new SimpleStringProperty(recentBuyPrice);
			this.recentSellPrice = new SimpleStringProperty(recentSellPrice);
		}
		
		public void setFenLei(String fl) {
			fenLei.set(fl);
		}
		public String getFenLei() {
			return fenLei.get();
		}
		
		public void setName(String n) {
			name.set(n);
		}
		public String getName() {
			return name.get();
		}
		
		public void setXingHao(String xh) {
			xingHao.set(xh);
		}
		public String getXingHao() {
			return xingHao.get();
		}

		public void setBuyPrice(String bp) {
			buyPrice.set(bp);
		}
		public String getBuyPrice() {
			return buyPrice.get();
		}

		public void setSellPrice(String sp) {
			sellPrice.set(sp);
		}
		public String getSellPrice() {
			return sellPrice.get();
		}

		public void setRecentBuyPrice(String rbp) {
			recentBuyPrice.set(rbp);
		}
		public String getRecentBuyPrice() {
			return recentBuyPrice.get();
		}

		public void setRecentSellPrice(String rsp) {
			recentSellPrice.set(rsp);
		}
		public String getRecentSellPrice() {
			return recentSellPrice.get();
		}
	}
	
	/**
	 * �����ͻ���Ϣ�б����ݰ󶨵���
	 * @author ��Ԭ��
	 *
	 */
	public class CustomerInfo{
		private String id;
		private SimpleStringProperty category;
		private SimpleStringProperty name;
		private SimpleStringProperty contact;
		private SimpleDoubleProperty receivable;
		private SimpleDoubleProperty payable;
		
		public CustomerInfo(String id, String category, String name, String contact, double receivable, double payable) {
			this.id = id;
			this.category = new SimpleStringProperty(category);
			this.name = new SimpleStringProperty(name);
			this.contact = new SimpleStringProperty(contact);
			this.receivable = new SimpleDoubleProperty(receivable);
			this.payable = new SimpleDoubleProperty(payable);
		}
		
		public void setID(String id) {
			this.id = id;
		}
		public String getID() {
			return id;
		}
		
		public void setCategory(String fl) {
			category.set(fl);
		}
		public String getCategory() {
			return category.get();
		}
		
		public void setName(String n) {
			name.set(n);
		}
		public String getName() {
			return name.get();
		}
		
		public void setContact(String lxfs) {
			contact.set(lxfs);
		}
		public String getContact() {
			return contact.get();
		}

		public void setReceivable(double ys) {
			receivable.set(ys);
		}
		public double getReceivable() {
			return receivable.get();
		}

		public void setPayable(double yf) {
			payable.set(yf);
		}
		public double getPayable() {
			return payable.get();
		}
	}

	/**
	 * ���������˻����ݰ󶨵���
	 * @author ��Ԭ��
	 */
	public class BankAccountInfo{
		private SimpleStringProperty name;
		private SimpleDoubleProperty amount;
		
		/**
		 * 
		 * @param �����˻�������
		 */
		BankAccountInfo(String name, double amount){
			this.name = new SimpleStringProperty(name);
			this.amount = new SimpleDoubleProperty(amount);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}
		
		public double getAmount() {
			return amount.get();
		}
		public void setAmount(double amount) {
			this.amount.set(amount);
		}
	}

}
