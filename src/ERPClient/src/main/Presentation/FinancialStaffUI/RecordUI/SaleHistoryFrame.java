package main.Presentation.FinancialStaffUI.RecordUI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.RecordFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CashBillVO;
import main.VO.CashItemVO;
import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;
import main.VO.CommodityReciptVO;
import main.VO.CustomerVO;
import main.VO.InfoVO;
import main.VO.ManifestVO;
import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptGoodsVO;
import main.utility.DocumentsType;

/**
 * �鿴��Ӫ���̱�����controller
 * @author 49869
 *
 */
public class SaleHistoryFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;
	
	@FXML
	ComboBox<String> reciptTypeBox;
	@FXML
	ComboBox<String> customerBox;
	@FXML
	ComboBox<String> operatorBox;
	@FXML
	ComboBox<String> stockBox;

	@FXML
	TableView<Info> reciptTable;
	@FXML
	TableColumn<Info, String> reciptTypeColumn;
	@FXML
	TableColumn<Info, String> reciptIdColumn;
	@FXML
	TableColumn<Info, String> operatorColumn;
	@FXML
	TableColumn<Info, String> dateColumn;
	
	@FXML
	Button checkButton;
	@FXML
	Button modifyButton;
	@FXML
	Button deleteButton;
	
	@FXML
	TabPane tabPane;
	
	//�տ����
	@FXML
	TextField idInCollection;
	@FXML
	TextField supplierInCollection;
	@FXML
	TextField sellerInCollection;
	@FXML
	TextField sumInCollection;
	@FXML
	TextField operatorInCollection;
	@FXML
	TableView<InfoInFinancialRecipt> tableInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInCollection;
	
	//�������
	@FXML
	TextField idInPayment;
	@FXML
	TextField supplierInPayment;
	@FXML
	TextField sellerInPayment;
	@FXML
	TextField sumInPayment;
	@FXML
	TextField operatorInPayment;
	@FXML
	TableView<InfoInFinancialRecipt> tableInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInPayment;

	//�ֽ���õ�����
	@FXML
	TextField idInCashBill;
	@FXML
	TextField bankAccountInCashBill;
	@FXML
	TextField sumInCashBill;
	@FXML
	TextField operatorInCashBill;
	@FXML
	TableView<InfoInFinancialRecipt> tableInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInCashBill;
	
	//����������
	@FXML
	TextField idInGoodsInList;
	@FXML
	TextField producerInGoodsInList;
	@FXML
	TextField totalpriceInGoodsInList;
	@FXML
	TextField operatorInGoodsInList;
	@FXML
	TextField commentInGoodsInList;
	@FXML
	TextField warehouseInGoodsInList;
	@FXML
	TableView<ListGoods> tableInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> idColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInGoodsInList;
	
	//�����˻�������
	@FXML
	TextField idInGoodsInReturnList;
	@FXML
	TextField producerInGoodsInReturnList;
	@FXML
	TextField totalpriceInGoodsInReturnList;
	@FXML
	TextField operatorInGoodsInReturnList;
	@FXML
	TextField commentInGoodsInReturnList;
	@FXML
	TextField warehouseInGoodsInReturnList;
	@FXML
	TableView<ListGoods> tableInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> idColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInGoodsInReturnList;
	
	//���۵�����
	@FXML
	TextField idInSaleList;
	@FXML
	TextField sellerInSaleList;
	@FXML
	TextField totalpriceInSaleList;
	@FXML
	TextField operatorInSaleList;
	@FXML
	TextField commentInSaleList;
	@FXML
	TextField warehouseInSaleList;
	@FXML
	TableView<ListGoods> tableInSaleList;
	@FXML
	TableColumn<ListGoods, String> idColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInSaleList;
	
	//�����˻�������
	@FXML
	TextField idInSaleReturnList;
	@FXML
	TextField sellerInSaleReturnList;
	@FXML
	TextField totalpriceInSaleReturnList;
	@FXML
	TextField operatorInSaleReturnList;
	@FXML
	TextField commentInSaleReturnList;
	@FXML
	TextField warehouseInSaleReturnList;
	@FXML
	TableView<ListGoods> tableInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> idColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInSaleReturnList;
	
	//��汨�絥
	@FXML
	TextField idInOverflowList;
	@FXML
	TextField goodsnameInOverflowList;
	@FXML
	TextField goodsidInOverflowList;
	@FXML
	TextField amountInOverflowList;
	
	//��汨��
	@FXML
	TextField idInDamageList;
	@FXML
	TextField goodsnameInDamageList;
	@FXML
	TextField goodsidInDamageList;
	@FXML
	TextField amountInDamageList;
	
	//��汨����
	@FXML
	TextField idInWarningList;
	@FXML
	TextField goodsnameInWarningList;
	@FXML
	TextField goodsidInWarningList;
	@FXML
	TextField amountInWarningList;
	
	//������͵�
	@FXML
	TextField idIngiftList;
	@FXML
	TextField goodsnameIngiftList;
	@FXML
	TextField goodsidIngiftList;
	@FXML
	TextField amountIngiftList;

	RecordBLService recordBL;
	InfoVO infoVO;
	
	boolean isModify = false;
	ArrayList<String> operatorList;
	boolean isOperatorSelected = false;
	
	/**
	 * ���ز鿴������ϸ�����
	 * @return ���غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane saleHistoryFrame = FXMLLoader.load(SaleHistoryFrame.class.getResource("SaleHistoryFrameFXML.fxml"));
			return saleHistoryFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}

	/**
	 * ��ʼ�����
	 */
	public void initialize() {
		recordBL = RemoteHelper.getRecordBLService();
		
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		reciptTypeBox.setItems(FXCollections.observableArrayList("ȫ��","�����൥��", "�����൥��", "�����൥��", "����൥��"));
		stockBox.setItems(FXCollections.observableArrayList("�ֿ�1"));
		
		reciptTypeColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptType"));
		reciptIdColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptId"));
		operatorColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("operator"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("date"));
		
		reciptTable.setRowFactory(new Callback<TableView<Info>, TableRow<Info>>(){
			@Override
			public TableRow<Info> call(TableView<Info> param) {
				return new TableRowControl();
			}
		});
		reciptTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//������Ʒ���ͻ�
		loadCustomer();
		
		loadInfo();
		
		//�տ����
		initCollectionOrder();
		//�������
		initPaymentOrder();
		//�ֽ���õ�
		initCashBill();
		//����������
		initGoodsInTable();
		//�����˻���
		initGoodsInReturnTable();
		//���۵�
		initSaleTable();
		//�����˻���
		initSaleReturnTable();

	}
		
	/**
	 * ����tableview�����˫���¼�����
	 * @author ��Ԭ��
	 */
	public class TableRowControl extends TableRow<Info> {  
		
		public TableRowControl() {  
			super();  
			this.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 1
							&& TableRowControl.this.getIndex() < reciptTable.getItems().size()){
						//���ð�ť���� 
						checkButton.setDisable(false);
						modifyButton.setDisable(false);
						deleteButton.setDisable(false);
						
						if(reciptTable.getSelectionModel().getSelectedItems().size() > 1){		//��ѡʱ�����Բ鿴���޸�
							modifyButton.setDisable(true);
							checkButton.setDisable(true);
						}
					}
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 2
							&& TableRowControl.this.getIndex() < reciptTable.getItems().size()){
//						System.out.println("test");
						//check();
						showRecipt();
					}
				}
			});
		}
	}
	
	/**
	 * ���ؿͻ�ѡ�������ݣ���������
	 */
	void loadCustomer() {
		isModify = true;
		customerBox.getItems().clear();
		isModify = false;
		
		String value = customerBox.getValue();
		if(value == null) {
			value = "";
		}

		try {
			ArrayList<CustomerVO> voList = recordBL.getCustomer("name", value);
			for(int i = 0;i < voList.size();i++) {
				CustomerVO vo = voList.get(i);
				customerBox.getItems().add(vo.getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadInfo() {
		reciptTable.getItems().clear();
		//��ȡʱ��
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		//��ȡ��������
		String reciptType = reciptTypeBox.getValue();
		//��ȡ�ͻ�
		String customer = customerBox.getSelectionModel().getSelectedItem();
		//��ȡ����Ա
		String operator = operatorBox.getSelectionModel().getSelectedItem();

		if(startTime == null) {
			startTime = "";
		}
		if(endTime == null) {
			startTime = "";
		}
		if(reciptType == null) {
			reciptType = "";
		}
		if(customer == null) {
			customer = "";
		}
		if(operator == null) {
			operator = "";
		}

		DocumentsType type = DocumentsType.SALES_DOCUMENTS;
		switch(reciptType) {
		case "�����൥��":{
			type = DocumentsType.SALES_DOCUMENTS;
			break;
		}
		case "�����൥��":{
			type = DocumentsType.GOODSIN_DOCUMENTS;
			break;
		}
		case "�����൥��":{
			type = DocumentsType.FINANCIAL_DOCUMENTS;
			break;
		}
		case "����൥��":{
			type = DocumentsType.INVENTORY_DOCUMENTS;
			break;
		}
		}
		if(!isOperatorSelected) {
			operatorList = new ArrayList<String>();
		}
		if(reciptType.equals("") || reciptType.equals("ȫ��")) {
//			forEachLoadInfo(startTime, endTime, DocumentsType.SALES_DOCUMENTS, customer, operator);
//			forEachLoadInfo(startTime, endTime, DocumentsType.GOODSIN_DOCUMENTS, customer, operator);
//			forEachLoadInfo(startTime, endTime, DocumentsType.FINANCIAL_DOCUMENTS, customer, operator);
//			forEachLoadInfo(startTime, endTime, DocumentsType.INVENTORY_DOCUMENTS, customer, operator);
			forEachLoadInfo(startTime, endTime, DocumentsType.ALL, customer, operator);
		}
		else {
			forEachLoadInfo(startTime, endTime, type, customer, operator);
		}
		
		if(!isOperatorSelected) {
			operatorBox.setItems(FXCollections.observableArrayList(operatorList));
		}
	}
	
	void forEachLoadInfo(String startTime, String endTime, DocumentsType type, String customer, String yewuyuan) {
		try {
			infoVO = recordBL.getSalesHistoryList(startTime, endTime, type, customer, yewuyuan);
			
			ObservableList<Info> list = reciptTable.getItems();
			ArrayList<ManifestVO> manifestList = infoVO.getManifestList();
			for(int i = 0;i < manifestList.size();i++) {
				ManifestVO vo = manifestList.get(i);
				String voType = vo.getType();
				switch(voType) {
				case "XSD":{
					voType = "���۵�";
					break;
				}
				case "XSTHD":{
					voType = "�����˻���";
					break;
				}
				case "JHD":{
					voType = "������";
					break;
				}
				case "JHTHD":{
					voType = "�����˻���";
					break;
				}
				}
				list.add(new Info(voType, vo.getID(), vo.getOperator(), vo.getCreateDate()));
				
				if(!isOperatorSelected && !operatorList.contains(vo.getOperator())) {
					operatorList.add(vo.getOperator());
				}
			}
			ArrayList<CommodityReciptVO> commodityReciptList = infoVO.getCommodityReciptList();
			for(int i = 0;i < commodityReciptList.size();i++){
				CommodityReciptVO vo = commodityReciptList.get(i);
				String voType = vo.getType();
				switch(voType) {
				case "BJ":{
					voType = "��汨����";
					break;
				}
				case "ZS":{
					voType = "������͵�";
					break;
				}
				case "BY":{
					voType = "��汨�絥";
					break;
				}
				case "BS":{
					voType = "��汨��";
					break;
				}
				}
				list.add(new Info(voType, ""+vo.getID(), vo.getStaffID(), vo.getCreateDate()));
			}
//			ArrayList<ReciptVO> reciptList = infoVO.getReciptList();
//			for(int i = 0;i < reciptList.size();i++) {
//				ReciptVO reciptVO = reciptList.get(i);
//				String id = reciptVO.getId();
//				String reciptType = id.substring(0, id.indexOf("-"));
//				String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
//				
//				switch(reciptType) {
//				case "SKD":{
//					CollectionOrderVO collectionOrderVO = (CollectionOrderVO)reciptVO;
//					list.add(new Info("�տ", id, collectionOrderVO.getOperator(), date));
//				if(!isOperatorSelected && !operatorList.contains(collectionOrderVO.getOperator())) {
//					operatorList.add(collectionOrderVO.getOperator());
//				}
//					break;
//				}
//				case "FKD":{
//					PaymentOrderVO paymentOrderVO = (PaymentOrderVO)reciptVO;
//					list.add(new Info("���", id, paymentOrderVO.getOperator(), date));
//					if(!isOperatorSelected && !operatorList.contains(paymentOrderVO.getOperator())) {
//						operatorList.add(paymentOrderVO.getOperator());
//					}
//					break;
//				}
//				case "XJFYD":{
//					CashBillVO cashBillVO = (CashBillVO)reciptVO;
//					list.add(new Info("�ֽ���õ�", id, cashBillVO.getOperator(), date));
//					if(!isOperatorSelected && !operatorList.contains(cashBillVO.getOperator())) {
//						operatorList.add(cashBillVO.getOperator());
//					}
//					break;
//				}
//				}
//			}
			
			ArrayList<CollectionOrderVO> collectionOrderList = infoVO.getCollectionOrderList();
			for(int i = 0; i < collectionOrderList.size(); i++) {
				CollectionOrderVO vo = collectionOrderList.get(i);
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
				list.add(new Info("�տ", id, vo.getOperator(), date));
				
				if(!isOperatorSelected && !operatorList.contains(vo.getOperator())) {
					operatorList.add(vo.getOperator());
				}
			}
			ArrayList<PaymentOrderVO> paymentOrderList = infoVO.getPaymentOrderList();
			for(int i = 0; i < paymentOrderList.size(); i++) {
				PaymentOrderVO vo = paymentOrderList.get(i);
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
				list.add(new Info("���", id, vo.getOperator(), date));
				
				if(!isOperatorSelected && !operatorList.contains(vo.getOperator())) {
					operatorList.add(vo.getOperator());
				}
			}
			ArrayList<CashBillVO> cashBillList = infoVO.getCashBillList();
			for(int i = 0; i < cashBillList.size(); i++) {
				CashBillVO vo = cashBillList.get(i);
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
				list.add(new Info("�ֽ���õ�", id, vo.getOperator(), date));
				
				if(!isOperatorSelected && !operatorList.contains(vo.getOperator())) {
					operatorList.add(vo.getOperator());
				}
			}
			
			list.sort(new Comparator<Info>() {
				@Override
				public int compare(Info o1, Info o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});
//			System.out.println(list.size());
//			
//			reciptTable.setItems(list);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ѡ��ʱ��ļ���
	 */
	@FXML
	void setTime() {
		loadInfo();
	}

	/**
	 * ��������ComboBox�ļ���
	 */
	@FXML
	void setReciptTypeBox() {
		loadInfo();
	}
	
	/**
	 * �ͻ�comboBox�ļ���
	 */
	@FXML
	void setCustomerBox() {
		if(isModify) {
			return;
		}
		if(customerBox.getSelectionModel().isEmpty()) {
			loadCustomer();
			customerBox.show();
		}
		else {
			loadInfo();
		}
	}

	/**
	 * ҵ��ԱComboBox�ļ���
	 */
	@FXML
	void setYewuyuanBox() {
		isOperatorSelected = true;
		loadInfo();
		isOperatorSelected = false;
//		onlyOperator();
	}

//	/**
//	 * ������Աɸѡ��û���뵽�õķ������޸���ǰ�ķ���,����ǰ�Ĵ������ظ�
//	 */
//	void onlyOperator() {
//		
//	}
	
	/**
	 * �ֿ�ComboBox�ļ���
	 */
	@FXML
	void setStockBox() {
//		loadInfo();
	}

	/**
	 * �鿴��ť�ļ���
	 */
	@FXML
	void check() {
		
	}

	/**
	 * ���޸ġ�������岢���ƣ���ť�ļ���
	 */
	@FXML
	void modify() {
		ObservableList<Info> toRed = reciptTable.getSelectionModel().getSelectedItems();;
		
		for(Info info : toRed) {
			try {
				System.out.println(recordBL.setRedDashed(info.getReciptType(), info.getReciptId(), username));
				System.out.println("��壺"+info.getReciptId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION, "");
		alert.setHeaderText("��岢���Ƴɹ�");
		alert.showAndWait();
	}

	/**
	 * "ɾ��"������壬��ť�ļ���
	 */
	@FXML
	void delete() {
		ObservableList<Info> toRed = reciptTable.getSelectionModel().getSelectedItems();;
		
		for(Info info : toRed) {
			try {
				System.out.println(recordBL.setRedDashed(info.getReciptType(), info.getReciptId(), username));
				System.out.println("��壺"+info.getReciptId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION, "");
		alert.setHeaderText("���ɹ�");
		alert.showAndWait();
	}

	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	protected void home() {
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	protected void back() {
		Parent back = RecordFrame.init();
		MainFrame.setSceneRoot(back);
	}
	
	/**
	 * ����ΪExcel��ť�ļ���
	 */
	@FXML
	void outputToExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel �ļ�(*.xls)", "*.xls"));
		fileChooser.setInitialFileName("��Ӫ���̱�-"+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+".xls");
		File file = fileChooser.showSaveDialog(new Stage());
		if(file == null) {
			return;
		}
		
		String name = file.getName();
		String path = file.getParentFile().getAbsolutePath();

//		System.out.println(name);
//		System.out.println(path);
		try {
			recordBL.salesHistoryToExcel(infoVO, name, path);
			Alert alert = new Alert(AlertType.INFORMATION, "�ɹ������� "+path+name);
			alert.show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ���տ����
	 * @author yyr
	 */
	void initCollectionOrder() {
		//��ʼ��table
		nameColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		//�ܾ�������ʱӦ���ܸ�������
		
		amountColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
		amountColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		amountColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
			calcSumInCollection();
		});
		
		commentColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
		commentColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});
	}
	/**
	 * ��ʼ���������
	 */
	void initPaymentOrder() {
		//��ʼ��table
		nameColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
		amountColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		amountColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
			calcSumInCollection();
		});
		
		commentColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
		commentColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});
	}
	/**
	 * ��ʼ���ֽ���õ�����
	 */
	void initCashBill() {
		//��ʼ��table
		nameColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
		amountColumnInCashBill.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		amountColumnInCashBill.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
			calcSumInCollection();
		});
		
		commentColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
		commentColumnInCashBill.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumnInCashBill.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});
	}

	/**
	 * �󶨽�����������
	 * @author ����ΰ
	 */
	void initGoodsInTable(){
		
		idColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	}
	
	/**
	 * �󶨽����˻���������
	 * @author ����ΰ
	 */
	void initGoodsInReturnTable(){
		
		idColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	
	}
	
	/**
	 * �����۵�������
	 * @author ����ΰ
	 */
	void initSaleTable(){
		
		idColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	
	}
	
	/**
	 * �������˻���������
	 * @author ����ΰ
	 */
	void initSaleReturnTable(){
		
		idColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	}

	/**
	 * ��ʾһ�ŵ��ݵķ���
	 */
	void showRecipt() {
		Info info = reciptTable.getSelectionModel().getSelectedItem();
		String type = info.getReciptType();
		switch(type) {
		case "�տ":{
			for(CollectionOrderVO vo : infoVO.getCollectionOrderList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showCollectionOrder(vo);
				}
			}
			break;
		}
		case "���":{
			for(PaymentOrderVO vo : infoVO.getPaymentOrderList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showPaymentOrder(vo);
				}
			}
			break;
		}
		case "�ֽ���õ�":{
			for(CashBillVO vo : infoVO.getCashBillList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showCashBill(vo);
				}
			}
			break;
		}
		case "������":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showGoodsIn(vo);
				}
			}
			break;
		}
		case "�����˻���":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showGoodsInReturn(vo);
				}
			}
			break;
		}
		case "���۵�":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showSaleList(vo);
				}
			}
			break;
		}
		case "�����˻���":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showSaleReturnList(vo);
				}
			}
			break;
		}
		case "��汨�絥":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showOverflowList(vo);
				}
			}
			break;
		}
		case "��汨��":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showDamageList(vo);
				}
			}
			break;
		}
		case "��汨����":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showWarningList(vo);
				}
			}
			break;
		}
		case "������͵�":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showGiftList(vo);
				}
			}
			break;
		}
		}
	}
	
	/**
	 * ��ʾһ���տ
	 * @param vo Ҫ��ʾ���տVO
	 */
	void showCollectionOrder(CollectionOrderVO vo) {
		idInCollection.setText(vo.getId());
//		System.out.println(vo.getSupplier());
//		System.out.println(vo.getRetailer());
		supplierInCollection.setText(vo.getSupplier());
		sellerInCollection.setText(vo.getRetailer());
		ArrayList<CollectionItemVO> itemList = vo.getItemList();
		for(CollectionItemVO item : itemList) {
			tableInCollection.getItems().add(new InfoInFinancialRecipt(
					item.getBankAccountID(), item.getAmount(), item.getComment()));
		}
		calcSumInCollection();
		operatorInCollection.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(1);
	}
	/**
	 * ��ʾһ�Ÿ��
	 * @param vo Ҫ��ʾ�ĸ��VO
	 */
	void showPaymentOrder(PaymentOrderVO vo) {
		idInPayment.setText(vo.getId());
		supplierInPayment.setText(vo.getSupplier());
		sellerInPayment.setText(vo.getRetailer());
		ArrayList<PaymentItemVO> itemList = vo.getItemList();
		
		tableInPayment.getItems().clear();
		for(PaymentItemVO item : itemList) {
			tableInPayment.getItems().add(new InfoInFinancialRecipt(
					item.getBankAccountID(), item.getAmount(), item.getComment()));
		}
		calcSumInPayment();
		operatorInPayment.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(2);
	}

	/**
	 * ��ʾһ���ֽ���õ�
	 * @param vo Ҫ��ʾ�ĸ��VO
	 */
	void showCashBill(CashBillVO vo) {
		idInCashBill.setText(vo.getId());
		bankAccountInCashBill.setText(vo.getBankAccountID());
		ArrayList<CashItemVO> itemList = vo.getItemList();
		
		tableInCashBill.getItems().clear();
		for(CashItemVO item : itemList) {
			tableInCashBill.getItems().add(new InfoInFinancialRecipt(
					item.getName(), item.getAmount(), item.getComment()));
		}
		calcSumInCashBill();
		operatorInCashBill.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(3);
	}

	/**
	 * �����տ����ܼ�
	 */
	void calcSumInCollection() {
		ObservableList<InfoInFinancialRecipt> list = tableInCollection.getItems();
		double sum = 0;
		for(InfoInFinancialRecipt info : list) {
			sum += info.getAmount();
		}
		sumInCollection.setText(""+sum);
	}
	/**
	 * ���㸶�����ܼ�
	 */
	void calcSumInPayment() {
		ObservableList<InfoInFinancialRecipt> list = tableInPayment.getItems();
		double sum = 0;
		for(InfoInFinancialRecipt info : list) {
			sum += info.getAmount();
		}
		sumInPayment.setText(""+sum);
	}
	/**
	 * �����ֽ���õ�����ܼ�
	 */
	void calcSumInCashBill() {
		ObservableList<InfoInFinancialRecipt> list = tableInPayment.getItems();
		double sum = 0;
		for(InfoInFinancialRecipt info : list) {
			sum += info.getAmount();
		}
		sumInCashBill.setText(""+sum);
	}

	/**
	 * ��ʾһ��������
	 */
	void showGoodsIn(ManifestVO manifestVO) {
		idInGoodsInList.setText(manifestVO.getID());
		producerInGoodsInList.setText(manifestVO.getCustomerName());
		warehouseInGoodsInList.setText(manifestVO.getWarehouse());
		operatorInGoodsInList.setText(manifestVO.getOperator());
		commentInGoodsInList.setText(manifestVO.getComment());
		totalpriceInGoodsInList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInGoodsInList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInGoodsInList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(4);
	}
	
	/**
	 * ��ʾһ�������˻���
	 */
	void showGoodsInReturn(ManifestVO manifestVO) {
		idInGoodsInReturnList.setText(manifestVO.getID());
		producerInGoodsInReturnList.setText(manifestVO.getCustomerName());
		warehouseInGoodsInReturnList.setText(manifestVO.getWarehouse());
		operatorInGoodsInReturnList.setText(manifestVO.getOperator());
		commentInGoodsInReturnList.setText(manifestVO.getComment());
		totalpriceInGoodsInReturnList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInGoodsInReturnList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInGoodsInReturnList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(5);
	}

	/**
	 * ��ʾһ�����۵�
	 */
	void showSaleList(ManifestVO manifestVO) {
		idInSaleList.setText(manifestVO.getID());
		sellerInSaleList.setText(manifestVO.getCustomerName());
		warehouseInSaleList.setText(manifestVO.getWarehouse());
		operatorInSaleList.setText(manifestVO.getOperator());
		commentInSaleList.setText(manifestVO.getComment());
		totalpriceInSaleList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInSaleList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInSaleList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(6);
	}
	
	/**
	 * ��ʾһ�������˻���
	 */
	void showSaleReturnList(ManifestVO manifestVO) {
		idInSaleReturnList.setText(manifestVO.getID());
		sellerInSaleReturnList.setText(manifestVO.getCustomerName());
		warehouseInSaleReturnList.setText(manifestVO.getWarehouse());
		operatorInSaleReturnList.setText(manifestVO.getOperator());
		commentInSaleReturnList.setText(manifestVO.getComment());
		totalpriceInSaleReturnList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInSaleReturnList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInSaleReturnList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(7);
	}

	/**
	 * ��ʾһ�����絥
	 */
	void showOverflowList(CommodityReciptVO reciptVO) {
		idInOverflowList.setText(""+reciptVO.getID());
		goodsnameInOverflowList.setText(reciptVO.getGoodsName());
		goodsidInOverflowList.setText(reciptVO.getGoodsID());
		amountInOverflowList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(8);
	}
	
	/**
	 * ��ʾһ������
	 */
	void showDamageList(CommodityReciptVO reciptVO) {
		idInDamageList.setText(""+reciptVO.getID());
		goodsnameInDamageList.setText(reciptVO.getGoodsName());
		goodsidInDamageList.setText(reciptVO.getGoodsID());
		amountInDamageList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(9);
	}
	
	/**
	 * ��ʾһ������
	 */
	void showWarningList(CommodityReciptVO reciptVO) {
		idInWarningList.setText(""+reciptVO.getID());
		goodsnameInWarningList.setText(reciptVO.getGoodsName());
		goodsidInWarningList.setText(reciptVO.getGoodsID());
		amountInWarningList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(10);
	}

	/**
	 * ��ʾһ������
	 */
	void showGiftList(CommodityReciptVO reciptVO) {
		idIngiftList.setText(""+reciptVO.getID());
		goodsnameIngiftList.setText(reciptVO.getGoodsName());
		goodsidIngiftList.setText(reciptVO.getGoodsID());
		amountIngiftList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(11);
	}

	@FXML
	void cancel() {
		tabPane.getSelectionModel().select(0);
	}
	
	@FXML
	void sure() {
		//TODO
		
		cancel();
	}

	@FXML
	void sureInSaleState() {
		back();
	}
	
	/**
	 * ����TableView���ݰ󶨵���
	 * @author yyr
	 *
	 */
	public class Info{
		private SimpleStringProperty reciptType;
		private SimpleStringProperty reciptId;
		private SimpleStringProperty operator;
		private SimpleStringProperty date;
		
		public Info(String rt, String id, String o, String d) {
			reciptType = new SimpleStringProperty(rt);
			reciptId = new SimpleStringProperty(id);
			operator = new SimpleStringProperty(o);
			date = new SimpleStringProperty(d);
		}

		public String getReciptType() {
			return reciptType.get();
		}

		public void setReciptType(String type) {
			this.reciptType.set(type);
		}

		public String getReciptId() {
			return reciptId.get();
		}

		public void setReciptId(String id) {
			this.reciptId.set(id);
		}

		public String getOperator() {
			return operator.get();
		}

		public void setOperator(String op) {
			this.operator.set(op);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

	}

	/**
	 * ����TableView���ݰ󶨵���
	 * @author ��Ԭ��
	 *
	 */
	public class InfoInFinancialRecipt{
		private SimpleStringProperty name;
		private SimpleDoubleProperty amount;
		private SimpleStringProperty comment;
		
		/**
		 * ����һ���յ�Info
		 */
		public InfoInFinancialRecipt(){
			name = new SimpleStringProperty("");
			amount = new SimpleDoubleProperty(0);
			comment = new SimpleStringProperty("");
		}
		
		/**
		 * ����һ��������Ϣ��Info
		 * @param name �˻�����
		 * @param amount ת�˽��
		 * @param comment ��ע
		 */
		public InfoInFinancialRecipt(String name, double amount, String comment){
			this.name = new SimpleStringProperty(name);
			this.amount = new SimpleDoubleProperty(amount);
			this.comment = new SimpleStringProperty(comment);
		}
		
		public void setName(String n) {
			name.set(n);
		}
		
		public String getName() {
			return name.get();
		}
		
		public void setAmount(double a) {
			amount.set(a);
		}
		
		public double getAmount() {
			return amount.get();
		}
		
		public void setComment(String c) {
			comment.set(c);
		}
		
		public String getComment() {
			return comment.get();
		}
	}

	/**
	 * ����TableView���ݰ󶨵���
	 * @author ����ΰ
	 *
	 */
	public class ListGoods{
		private String id;
		private String name;
		private String version;
		private String amount;
		private String bid;
		private String comment;
		
		public ListGoods(String id, String name, String version, String amount, String bid, String comment) {
			super();
			this.id = id;
			this.name = name;
			this.version = version;
			this.amount = amount;
			this.bid = bid;
			this.comment = comment;
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
		public String getBid() {
			return bid;
		}
		public void setBid(String bid) {
			this.bid = bid;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

	}

}
