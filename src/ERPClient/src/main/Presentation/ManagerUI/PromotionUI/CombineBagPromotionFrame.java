package main.Presentation.ManagerUI.PromotionUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.ManagerUI.PromotionFrame;
import main.RMI.RemoteHelper;
import main.VO.GoodsVO;
import main.VO.PackagePromotionVO;
import main.utility.ResultMessage;

public class CombineBagPromotionFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;

	@FXML
	ComboBox<String> searchConditionBox;
	@FXML
	ComboBox<String> searchBox;
	
	@FXML
	TableView<Info> table;
	@FXML
	TableColumn<Info, String> goodsIDColumn;
	@FXML
	TableColumn<Info, String> goodsNameColumn;
	@FXML
	TableColumn<Info, Integer> goodsNumColumn;
	@FXML
	TableColumn<Info, Double> goodsPriceColumn;
	
	@FXML
	TextField sumPriceField;
	@FXML
	TextField reducePriceField;
	
	private PromotionBLService promotionBL;
	private boolean isModify = false;
	private ArrayList<GoodsVO> goodsVOList;
	
	/**
	 * �����ƶ��û��������Ե��ݽ���
	 * @return ���غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane combingBagPromotionFrame = FXMLLoader.load(CombineBagPromotionFrame.class.getResource("CombineBagPromotionFrameFXML.fxml"));
			return combingBagPromotionFrame;
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
		promotionBL = RemoteHelper.getPromotionBLService();
		
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		
		searchConditionBox.setItems(FXCollections.observableArrayList("ID", "��Ʒ����"));
		searchConditionBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				isModify = true;
				searchBox.getItems().clear();
				isModify = false;
				searchAndAdd();
			}
			
		});
		
		goodsIDColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("id"));
//		goodsIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		goodsIDColumn.setOnEditCommit((CellEditEvent<Info, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setId(t.getNewValue());
//		});
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("name"));
//		goodsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		goodsNameColumn.setOnEditCommit((CellEditEvent<Info, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
//		});
		goodsNumColumn.setCellValueFactory(new PropertyValueFactory<Info, Integer>("num"));
		goodsNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		goodsNumColumn.setOnEditCommit((CellEditEvent<Info, Integer> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNum(t.getNewValue());
			calcSumPrice();
		});
		goodsPriceColumn.setCellValueFactory(new PropertyValueFactory<Info, Double>("price"));
//		goodsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		goodsPriceColumn.setOnEditCommit((CellEditEvent<Info, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrice(t.getNewValue());
//		});

		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
	
	/**
	 * ������ļ���
	 */
	@FXML
	private void searchAndAdd() {
		if(isModify) {
			return;
		}
		if(searchBox.getSelectionModel().isEmpty()) {		//������
			isModify = true;
			searchBox.getItems().clear();
			isModify = false;

			String value = searchBox.getValue();
			if(value == null) {
				value = "";
			}
			switch(searchConditionBox.getValue()) {
			case "ID":{
				try {
					goodsVOList = promotionBL.getGoods("ID", value);
					for(int i = 0; i < goodsVOList.size();i++) {
						GoodsVO vo = goodsVOList.get(i);
						searchBox.getItems().add(vo.getID());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "��Ʒ����":{
				try {
					goodsVOList = promotionBL.getGoods("name", value);
					for(int i = 0; i < goodsVOList.size();i++) {
						GoodsVO vo = goodsVOList.get(i);
						searchBox.getItems().add(vo.getName());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			}
			searchBox.show();
		}
		else {			//��ѡ�����
			int index = searchBox.getSelectionModel().getSelectedIndex();
			GoodsVO vo = goodsVOList.get(index);
			Info info = new Info(vo.getID(), vo.getName(), vo.getRetailPrice());
			table.getItems().add(info);
			
			isModify = true;
			Platform.runLater(()->{
				searchBox.getItems().clear();
				searchBox.getSelectionModel().clearSelection();
				isModify = false;
			});
			
			searchBox.hide();
			
			//�����ܼ�
			calcSumPrice();
		}
	}
	
	/**
	 * ������ϰ��ܼ۵ķ���
	 */
	private void calcSumPrice() {
		ObservableList<Info> list = table.getItems();
		double sumPrice = 0;
		for(int i = 0;i < list.size();i++) {
			Info info = list.get(i);
			sumPrice += info.getNum()*info.getPrice();
		}
		sumPriceField.setText(""+sumPrice);
	}
	
	/**
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	private void sure() {
		//���VO
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		double reduce = Double.parseDouble(reducePriceField.getText());
		ArrayList<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		ObservableList<Info> infoList = table.getItems();
		for(int i = 0;i < infoList.size();i++) {
			Info info = infoList.get(i);
            goodsVOList.add(new GoodsVO(info.getName(), info.getId(), "", info.getNum(), 0, 0, 0, 0, "", 0, ""));
		}
		
		PackagePromotionVO vo = new PackagePromotionVO(startTime, endTime, reduce, goodsVOList);
		
		//���÷���
		try {
			ResultMessage result = promotionBL.setPromotion(vo, username);
			System.out.println(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		back();
	}

	/**
	 * ȡ����ť�ļ���
	 */
	@FXML
	private void cancel() {
		back();
	}

	/**
	 * ɾ������ȯ�б��а�ť�ļ���
	 */
	@FXML
	private void deleteRow() {
		table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
	}

	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	private void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	private void back() {
		Parent back = PromotionFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * ������Ʒ�б�giftTable���ݰ󶨵���
	 * @author yyr
	 *
	 */
	public class Info {
		private SimpleStringProperty id;
		private SimpleStringProperty name;
		private SimpleIntegerProperty num;
		private SimpleDoubleProperty price;
		
		public Info(String id, String name, double price) {
			this.id = new SimpleStringProperty(id);
			this.name = new SimpleStringProperty(name);
			this.num = new SimpleIntegerProperty(1);
			this.price = new SimpleDoubleProperty(price);
		}

		public void setId(String id) {
			this.id.set(id);
		}
		public String getId() {
			return id.get();
		}
		
		public void setName(String name) {
			this.name.set(name);
		}
		public String getName() {
			return name.get();
		}
		
		public void setNum(int num) {
			this.num.set(num);
		}
		public int getNum() {
			return num.get();
		}
		
		public void setPrice(double price) {
			this.price.set(price);
		}
		public double getPrice() {
			return price.get();
		}
	}

}
