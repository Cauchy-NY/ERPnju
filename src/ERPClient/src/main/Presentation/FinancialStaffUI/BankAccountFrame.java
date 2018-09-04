package main.Presentation.FinancialStaffUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.BankAccountVO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

/**
 * �����˻��������ĳ�ʼ����������������ת
 * @author ��Ԭ��
 *
 */
public class BankAccountFrame extends MainUIController{
	
	public static BankAccountBLService bankAccountBL;
	
	@FXML
	ImageView head;
	
	@FXML
	TextField searchField;
	
	@FXML
	TableView<Info> table;
	@FXML
	TableColumn<Info, String> nameColumn;
//	@FXML
//	TableColumn<Info, Boolean> multiSelectedColumn;
	
	@FXML
	Button checkButton;
	@FXML
	Button modifyButton;
	@FXML
	Button addButton;
	@FXML
	Button deleteButton;
	
	private ObservableList<Info> list;
	
	/**
	 * ��ʼ�������˻��������
	 * @return
	 */
	public static Parent init(){
		bankAccountBL = RemoteHelper.getBankAccountBLService();
		
		try {
			GridPane bankAccountFrame = FXMLLoader.load(BankAccountFrame.class.getResource("BankAccountFrameFXML.fxml"));
			return bankAccountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new GridPane();
	}
	
	/**
	 * ÿ�μ���FXMLʱ����Table���г�ʼ������
	 */
	@FXML
	public void initialize(){
		nameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("name"));
//		multiSelectedColumn.setCellValueFactory(new PropertyValueFactory<Info, Boolean>("isMultiSelected"));
//		multiSelectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(multiSelectedColumn));
		
		loadInfo();
		table.setItems(list);
		table.setRowFactory(new Callback<TableView<Info>, TableRow<Info>>(){
			@Override
			public TableRow<Info> call(TableView<Info> param) {
				return new TableRowControl();
			}
		});
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}
	
	/**
	 * ���������˻�����Ϣ
	 */
	private void loadInfo(){
		//list = FXCollections.observableArrayList(new Info("test"));
		try {
			ArrayList<BankAccountVO> banklist = bankAccountBL.getBankAccountList();
			ArrayList<Info> infoList = new ArrayList<Info>();
			for(int i = 0;i < banklist.size();i++) {
				BankAccountVO vo = banklist.get(i);
				Info info = new Info(vo.getId());
				infoList.add(info);
			}
			list = FXCollections.observableArrayList(infoList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * ������ļ��̼���
	 */
	@FXML
	private void searchFieldActionHandle(KeyEvent ke){
		if(ke.getCode().equals(KeyCode.ENTER)){
			search();
		}
	}
	
	/**
	 * ����ͼ��ļ���
	 */
	@FXML
	private void search(){
//		list.add(new Info("hh"));
		try {
			ArrayList<BankAccountVO> banklist = bankAccountBL.partFind(searchField.getText());
			ArrayList<Info> infoList = new ArrayList<Info>();
			for(int i = 0;i < banklist.size();i++) {
				BankAccountVO vo = banklist.get(i);
				Info info = new Info(vo.getId());
				infoList.add(info);
			}
			list = FXCollections.observableArrayList(infoList);
			table.setItems(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	/**
	 * ���������˻���ť�ļ���
	 */
	@FXML
	private void add(){
		BankAccountInfoFrame addFrame = new BankAccountInfoFrame();
		addFrame.initInfo("", 0, true);
	}
	
	/**
	 * ɾ�������˻��ļ���
	 */
	@FXML
	private void delete(){
//		System.out.println(list.size());
//		for(int i = 0;i < list.size();i++){
//			Info temp = list.get(i);
//			System.out.println(temp.isMultiSelected());
//			if(temp.isMultiSelected()){
//				deleteBankAccount(temp.getName());
//			}
//		}
//		list.iterator().next().isMultiSelected();
//		multiSelectedColumn.getCellFactory().call(multiSelectedColumn).getItem().booleanValue();
		
//		ObservableList<Info> toDeleteList = table.getSelectionModel().getSelectedItems();
//		for(int i = 0;i < toDeleteList.size();i++){
//			//ɾ������
//			toDeleteList.get(i);
//		}
		ObservableList<Info> toDeleteInfo = table.getSelectionModel().getSelectedItems();
		ObservableList<Info> tempList = table.getItems();
		
		//ɾ��
		for(int i = 0;i < toDeleteInfo.size();i++){
			deleteBankAccount(toDeleteInfo.get(i).getName());
		}

		tempList.removeAll(toDeleteInfo);
		
		table.getSelectionModel().clearSelection();
		table.setItems(tempList);
	}
	
	/**
	 * ɾ��һ�������˻��ķ���
	 * @param name Ҫɾ���������˻�������
	 */
	private void deleteBankAccount(String name){
//		System.out.println("to delete:" + name);
		try {
			bankAccountBL.delete(name, username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �޸������˻��ļ���
	 */
	@FXML
	private void modify(){
		String name = table.getSelectionModel().getSelectedItem().getName();
//		double amount = 100;
		//������Ӧ���û���Ϣ������
		try {
			BankAccountVO vo = bankAccountBL.find(name);
			BankAccountInfoFrame modifyFrame = new BankAccountInfoFrame();
//			modifyFrame.initInfo(name, amount, true);
			modifyFrame.initInfo(name, vo.getAmount(), true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * �鿴�����˻��ļ���
	 */
	@FXML
	private void check(){
		String name = table.getSelectionModel().getSelectedItem().getName();
		//������Ӧ���û���Ϣ������
//		double amount = 100;
//		new BankAccountInfoFrame().initInfo(name, amount, false);

		try {
			BankAccountVO vo = bankAccountBL.find(name);
			new BankAccountInfoFrame().initInfo(name, vo.getAmount(), false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	@FXML
//	public void doubleClickedCheck(MouseEvent me){
//		if(me.getClickCount() == 2){
//		}
//		else{
//			return;
//		}
//	}
	
	/**
	 * ����tableview�����˫���¼�����
	 * @author ��Ԭ��
	 */
	class TableRowControl extends TableRow<Info> {  
		  
		public TableRowControl() {  
			super();  
			this.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 1
							&& TableRowControl.this.getIndex() < table.getItems().size()){
						//���ð�ť���� 
						checkButton.setDisable(false);
						modifyButton.setDisable(false);
						deleteButton.setDisable(false);
						
						if(table.getSelectionModel().getSelectedItems().size() > 1){		//��ѡʱ�����Բ鿴���޸�
							modifyButton.setDisable(true);
							checkButton.setDisable(true);
						}
					}
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 2
							&& TableRowControl.this.getIndex() < table.getItems().size()){
//						System.out.println("test");
						check();
					}
				}
			});
		}
	}

	/**
	 * tableView���������ݰ󶨵���
	 * @author ��Ԭ��
	 */
	public class Info{
		private SimpleStringProperty name;
//		private SimpleBooleanProperty isMultiSelected;
		
		/**
		 * 
		 * @param �����˻�������
		 */
		Info(String name){
			this.name = new SimpleStringProperty(name);
//			this.isMultiSelected = new SimpleBooleanProperty(false);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}
		
//		public boolean isMultiSelected(){
//			return isMultiSelected.get();
//		}
//		
//		public void setMultiSelected(boolean newMultiSelected){
//			isMultiSelected.set(newMultiSelected);
//		}
	}
	
//	private Stage popUpWindow = new Stage();
//	
//	/**
//	 * ����һ������
//	 * @param popUp ��Ҫ���������
//	 */
//	void initPopupWindow(Parent popUp){
//		popUpWindow.initStyle(StageStyle.UNDECORATED);
//		
//		Scene popUpScene = new Scene(popUp);
//		popUpWindow.setScene(popUpScene);
//		popUpWindow.show();
//	}
	
}
