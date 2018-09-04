package main.Presentation.FinancialStaffUI.ReciptUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;
import main.VO.CustomerVO;

/**
 * �տ�����controller
 * @author 49869
 *
 */
public class CollectionOrderFrame extends EachReciptUI{
	
//	@FXML
//	public ImageView head;
	
//	@FXML
//	private TextField supplierField;
//	@FXML
//	private TextField sellerField;
	
	@FXML
	private ComboBox<String> supplierBox;
	@FXML
	private ComboBox<String> sellerBox;
	
//	@FXML
//	private ComboBox<String> searchBox;
	
//	private ArrayList<BankAccountVO> bankAccountVOList;
//	private boolean isModify = false;
	private ArrayList<CustomerVO> customerVOList;
	
//	@FXML
//	private TextField sumField;
//	@FXML
//	private TextField operatorField;
	
//	@FXML
//	private TableView<Info> transferTable;
//	@FXML
//	private TableColumn<Info, String> nameColumn;
//	@FXML
//	private TableColumn<Info, Double> amountColumn;
//	@FXML
//	private TableColumn<Info, String> commentColumn;
//	
//	@FXML
//	private Button addRowButton;
//	@FXML
//	private Button deleteRowButton;
//	
//	@FXML
//	private Button sureButton;
//	@FXML
//	private Button cancelButton;
	
	/**
	 * �ƶ��տ����ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane collectionOrderFrame = FXMLLoader.load(CollectionOrderFrame.class.getResource("CollectionOrderFrameFXML.fxml"));
			return collectionOrderFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new GridPane();		//�������
	}

	public void initialize() {
		super.initialize();
		
//		loadBankAccount();
		loadSupplier();
		loadSeller();
	}
		
	private void loadSupplier() {
		isModify = true;
		supplierBox.getSelectionModel().clearSelection();
		supplierBox.getItems().clear();
		isModify = false;
		try {
			String value = supplierBox.getValue();
			if(value == null) {
				value = "";
			}
			customerVOList = reciptBL.getCustomerList(value, "name");
//			customerVOList = accountBL.getCustomer("name", value);

			for(int i = 0;i < customerVOList.size();i++) {
				CustomerVO vo = customerVOList.get(i);
				if(vo.getCategory().equals("������")) {
					supplierBox.getItems().add(vo.getName());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadSeller() {
		isModify = true;
		sellerBox.getSelectionModel().clearSelection();
		sellerBox.getItems().clear();
		isModify = false;
		try {
			String value = sellerBox.getValue();
			if(value == null) {
				value = "";
			}
			customerVOList = reciptBL.getCustomerList(value, "name");
//			customerVOList = accountBL.getCustomer("name", value);

			for(int i = 0;i < customerVOList.size();i++) {
				CustomerVO vo = customerVOList.get(i);
				if(vo.getCategory().equals("������")) {
					sellerBox.getItems().add(vo.getName());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@FXML
	private void searchSupplier() {
		if(isModify) {
			return;
		}
		if(supplierBox.getSelectionModel().isEmpty()) {
			loadSupplier();
			supplierBox.show();
		}
	}
	
	@FXML
	private void searchSeller() {
		if(isModify) {
			return;
		}
		if(sellerBox.getSelectionModel().isEmpty()) {
			loadSeller();
			sellerBox.show();
		}
	}
	
	
	/**
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	private void sure() {
		//�������ΪVO
//		String supplier = supplierField.getText();
//		String seller = sellerField.getText();
		String supplier = supplierBox.getValue();
		String seller = sellerBox.getValue();
		
		ArrayList<CollectionItemVO> itemVOList = new ArrayList<CollectionItemVO>();
		ObservableList<Info> infoList = table.getItems();
		for(int i = 0; i < infoList.size();i++) {
			Info info = infoList.get(i);
			CollectionItemVO vo = new CollectionItemVO(info.getName(), info.getAmount(), info.getComment());
			itemVOList.add(vo);
		}
		double sum = Double.parseDouble(sumField.getText());
		CollectionOrderVO vo = new CollectionOrderVO(username, supplier, seller, itemVOList, sum);
//		
//		try {
//			System.out.println(reciptBL.setCollection(vo));
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		back();
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK, new ButtonType("����Ϊ�ݸ�"), ButtonType.CANCEL);
		alert.setHeaderText("�Ƿ��ύ��");
		Optional<ButtonType> buttonType = alert.showAndWait();
		if(buttonType.get().getText().equals("ȷ��")) {
			//System.out.println("ȷ��");
			try {
				reciptBL.setCollection(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		else if(buttonType.get().getText().equals("����Ϊ�ݸ�")) {
			//System.out.println("�ݸ�");
			vo.setState("Draft");
			try {
				reciptBL.setCollection(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ȡ����ť�ļ���
	 */
	@FXML
	private void cancel() {
		//�Ƿ񱣴�Ϊ�ݸ�
		
		back();
	}
}
