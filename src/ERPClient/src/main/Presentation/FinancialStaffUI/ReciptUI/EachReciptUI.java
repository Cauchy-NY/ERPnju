package main.Presentation.FinancialStaffUI.ReciptUI;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.DoubleStringConverter;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.ReciptFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.BankAccountVO;

/**
 * ÿ�����ݽ����controller�����࣬�����ֵ��ݽ���Ĺ�������
 * @author 49869
 *
 */
public class EachReciptUI extends MainUIController{
	protected ReciptBLService reciptBL;
	
	@FXML
	public ImageView head;
	
	@FXML
	protected TextField sumField;
	@FXML
	protected TextField operatorField;

	@FXML
	protected ComboBox<String> searchBox;
	protected ArrayList<BankAccountVO> bankAccountVOList;
	protected boolean isModify = false;

	@FXML
	protected TableView<Info> table;
	@FXML
	protected TableColumn<Info, String> nameColumn;
	@FXML
	protected TableColumn<Info, Double> amountColumn;
	@FXML
	protected TableColumn<Info, String> commentColumn;
	
	@FXML
	protected Button addRowButton;
	@FXML
	protected Button deleteRowButton;
	
	@FXML
	protected Button sureButton;
	@FXML
	protected Button cancelButton;

	/**
	 * ÿ�μ���FXMLʱ����Table���г�ʼ������,�Լ������Զ����ɵ��ı���
	 */
	@FXML
	public void initialize(){
		reciptBL = RemoteHelper.getReciptBLService();
		
		//��ʼ��table
		nameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("name"));
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		nameColumn.setCellFactory(ComboBoxTableCell.forTableColumn("sss", "xxx"));
		nameColumn.setOnEditCommit((CellEditEvent<Info, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
		});
		
		amountColumn.setCellValueFactory(new PropertyValueFactory<Info, Double>("amount"));
		amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		amountColumn.setOnEditCommit((CellEditEvent<Info, Double> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
			sumField.setText(""+calcSum());
		});
		
		commentColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("comment"));
		commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumn.setOnEditCommit((CellEditEvent<Info, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});

//		ObservableList<Info> list = FXCollections.observableArrayList(new Info());
//		table.setItems(list);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//�Զ������ı����ֵ
		operatorField.setText(username);
		
		loadBankAccount();

	}

	protected void loadBankAccount() {
		isModify = true;
		searchBox.getItems().clear();
		isModify = false;
		try {
			String value = searchBox.getValue();
			if(value == null) {
				value = "";
			}
			bankAccountVOList = reciptBL.getBankAccount(value);
			for(int i = 0;i < bankAccountVOList.size();i++){
				BankAccountVO vo = bankAccountVOList.get(i);
				searchBox.getItems().add(vo.getId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ������ļ���
	 */
	@FXML
	protected void searchAndAdd() {
		if(isModify) {
			return;
		}
		if(!searchBox.isShowing()) {
			searchBox.show();
		}
		if(searchBox.getSelectionModel().isEmpty()) {
			isModify = true;
			searchBox.getItems().clear();
			isModify = false;
			
			try {
				bankAccountVOList = reciptBL.getBankAccount(searchBox.getValue());
				for(int i = 0; i < bankAccountVOList.size(); i++) {
					BankAccountVO vo = bankAccountVOList.get(i);
					searchBox.getItems().add(vo.getId());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searchBox.show();
		}
		else {
			BankAccountVO vo = bankAccountVOList.get(searchBox.getSelectionModel().getSelectedIndex());
			table.getItems().add(new Info(vo.getId(), 0, ""));
			
			isModify = true;
			Platform.runLater(()->{
				searchBox.getSelectionModel().clearSelection();
				searchBox.getItems().clear();
				isModify = false;
			});
			
			searchBox.hide();
		}
	}

	/**
	 * ��ҳ��ť�ļ���
	 */
	@FXML
	protected void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * ���ذ�ť�ļ���
	 */
	@FXML
	protected void back(){
		Parent back = ReciptFrame.init();
		MainFrame.setSceneRoot(back);
	}
	
	/**
	 * ����table��һ�а�ť�ļ���
	 */
	@FXML
	protected void addRow() {
		table.getItems().add(new Info());
	}
	
	/**
	 * ɾ��table��һ�а�ť�ļ���
	 */
	@FXML
	protected void deleteRow() {
		table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
	}

	/**
	 * �����ܶ��ʾ
	 */
	protected double calcSum() {
		double sum = 0;
		
		ObservableList<Info> list = table.getItems();
		for(int i = 0;i < list.size();i++) {
			sum += list.get(i).getAmount();
		}
		
		return sum;
	}
	
	/**
	 * ����TableView���ݰ󶨵���
	 * @author ��Ԭ��
	 *
	 */
	public class Info{
		private SimpleStringProperty name;
		private SimpleDoubleProperty amount;
		private SimpleStringProperty comment;
		
		/**
		 * ����һ���յ�Info
		 */
		public Info(){
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
		public Info(String name, double amount, String comment){
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
}
