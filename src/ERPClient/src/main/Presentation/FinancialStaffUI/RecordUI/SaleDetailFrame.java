package main.Presentation.FinancialStaffUI.RecordUI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.RecordFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ReciptGoodsVO;

/**
 * �鿴������ϸ��Ľ����Controller��
 * @author yyr
 *
 */
public class SaleDetailFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;
	@FXML
	ComboBox<String> goodsNameBox;
	@FXML
	ComboBox<String> customerBox;
	@FXML
	ComboBox<String> yewuyuanBox;
	@FXML
	ComboBox<String> stockBox;
	
	@FXML
	TableView<Info> detailTable;
	@FXML
	TableColumn<Info, String> dateColumn;
	@FXML
	TableColumn<Info, String> goodsNameColumn;
	@FXML
	TableColumn<Info, String> xingHaoColumn;
	@FXML
	TableColumn<Info, String> numColumn;
	@FXML
	TableColumn<Info, String> goodsPriceColumn;
	@FXML
	TableColumn<Info, String> sumsumColumn;
	@FXML
	TableColumn<Info, String> sumColumn;

	RecordBLService recordBL;
	ArrayList<ReciptGoodsVO> reciptGoodsList;
	boolean isModify = false;
	
	ArrayList<String> operatorList;
	
	/**
	 * ���ز鿴������ϸ�����
	 * @return ���غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane saleDetailFrame = FXMLLoader.load(SaleDetailFrame.class.getResource("SaleDetailFrameFXML.fxml"));
			return saleDetailFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * ��ʼ�����comboBox��
	 */
	@FXML
	void initialize() {
		recordBL = RemoteHelper.getRecordBLService();
		
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		
		stockBox.setItems(FXCollections.observableArrayList("�ֿ�1"));
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("date"));
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("goodsName"));
		xingHaoColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("xinghao"));
		numColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("num"));
		goodsPriceColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("price"));
		sumColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("sum"));

//		ObservableList<Info> list = FXCollections.observableArrayList(new Info("1", "1", "1", 1, 1, 1));
//		detailTable.setItems(list);
		
		//������Ʒ���ͻ�
		loadGoods();
		loadCustomer();
		
		loadInfo();
	}
	
	/**
	 * ���ؿͻ�ѡ�����б���������
	 */
	void loadGoods() {
		isModify = true;
		goodsNameBox.getItems().clear();
		isModify = false;
		
		String value = goodsNameBox.getValue();
		
		if(value == null) {
			value = "";
		}
		try {
			ArrayList<GoodsVO> voList = recordBL.getGoods("name", value);
			for(int i= 0 ;i < voList.size();i++) {
				GoodsVO vo = voList.get(i);
				goodsNameBox.getItems().add(vo.getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	/**
	 * ��ʼ������ʱ��ѡ��ļ���
	 */
	@FXML
	void setTime() {
		if(startDatePicker.getValue() != null && endDatePicker.getValue() != null) {
//			System.out.println("hh");
			loadInfo();
		}
	}
	
	/**
	 * ��Ʒ��comboBox�ļ���
	 */
	@FXML
	void setGoodsNameBox() {
		if(isModify) {
			return;
		}
		if(goodsNameBox.getSelectionModel().isEmpty()) {
			loadGoods();
			goodsNameBox.show();
		}
		else {
			loadInfo();
		}
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
	 * ҵ��ԱcomboBox�ļ���
	 */
	@FXML
	void setYewuyuanBox() {
		loadInfo();
	}
	
	/**
	 * ���comboBox�ļ���
	 */
	@FXML
	void setStockBox() {
		loadInfo();
	}
	
	/**
	 * �������������õõ�����󣬼���TableView
	 */
	void loadInfo() {
		detailTable.getItems().clear();
		//��ȡʱ��
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		//��ȡ��Ʒ����
		String goodsName = goodsNameBox.getSelectionModel().getSelectedItem();
		//��ȡ�ͻ�
		String customer = customerBox.getSelectionModel().getSelectedItem();
		//��ȡҵ��Ա
		String yewuyuan = yewuyuanBox.getSelectionModel().getSelectedItem();
//		//��ȡ�ֿ�
//		String stock = stockBox.getSelectionModel().getSelectedItem();
		
		if(startTime == null) {
			startTime = "";
		}
		if(endTime == null) {
			startTime = "";
		}
		if(goodsName == null) {
			goodsName = "";
		}
		if(customer == null) {
			customer = "";
		}
		if(yewuyuan == null) {
			yewuyuan = "";
		}

		try {
			reciptGoodsList = recordBL.getSalesDetailList(startTime, endTime, goodsName, customer, yewuyuan);
			
//			if(reciptGoodsList == null) {
//				System.out.println("null");
//				reciptGoodsList = new ArrayList<ReciptGoodsVO>();
//			}
//			else {
//				System.out.println(reciptGoodsList.size());
//			}
			operatorList = new ArrayList<String>();		//��������Щ����Ա
			
			for(int i = 0;i < reciptGoodsList.size();i++) {
				ReciptGoodsVO vo = reciptGoodsList.get(i);
				Info info = new Info(vo.getComment(), vo.getName(), vo.getVersion(), vo.getAmounts(), vo.getBid(), vo.getSum());
				detailTable.getItems().add(info);
				
				//TODO ������Ա�����ڲ���Ա�б���
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		fileChooser.setInitialFileName("������ϸ��-"+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+".xls");
		File file = fileChooser.showSaveDialog(new Stage());
		if(file == null) {
			return;
		}
		
		String name = file.getName();
		String path = file.getParentFile().getAbsolutePath();

//		System.out.println(name);
//		System.out.println(path);
		try {
			recordBL.salesDetailToExcel(reciptGoodsList, name, path);
			Alert alert = new Alert(AlertType.INFORMATION, "�ɹ������� "+path+name);
			alert.show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	void sure() {
		back();
	}

	/**
	 * ����TableView���ݰ󶨵���
	 * @author yyr
	 *
	 */
	public class Info{
		private SimpleStringProperty date;
		private SimpleStringProperty goodsName;
		private SimpleStringProperty xinghao;
		private SimpleStringProperty num;
		private SimpleStringProperty price;
		private SimpleStringProperty sum;
		
		public Info(String d, String gn, String xh, int n, double p, double s) {
			date = new SimpleStringProperty(d);		goodsName = new SimpleStringProperty(gn);
			xinghao = new SimpleStringProperty(xh);	num = new SimpleStringProperty(""+n);
			price = new SimpleStringProperty(""+p);	sum = new SimpleStringProperty(""+s);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

		public String getGoodsName() {
			return goodsName.get();
		}

		public void setGoodsName(String goodsName) {
			this.goodsName.set(goodsName);
		}

		public String getXinghao() {
			return xinghao.get();
		}

		public void setXinghao(String xinghao) {
			this.xinghao.set(xinghao);
		}

		public int getNum() {
			return Integer.parseInt(num.get());
		}

		public void setNum(int num) {
			this.num.set(""+num);
		}

		public double getPrice() {
			return Double.parseDouble(price.get());
		}

		public void setPrice(double price) {
			this.price.set(""+price);
		}

		public double getSum() {
			return Double.parseDouble(sum.get());
		}

		public void setSum(String sum) {
			this.sum.set(sum);
		}
	}
}

