package main.Presentation.SalesmanUI.CustomerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerManageFrame;
import main.Presentation.SalesmanUI.SalesmanHomeFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

/**
 * ����������Ա�ͻ������������������Ŀ�����
 * @author ����ΰ
 *
 */
public class SearchResultFrame extends MainUIController{
	
	@FXML Label resulttitle;
	
	@FXML  ImageView head;
	
	@FXML
	TableView<Customer> table;
	
	@FXML
	TableColumn<Customer, String> idCol;

	@FXML
	TableColumn<Customer, String> nameCol;
	
	@FXML
	TableColumn<Customer, String> categoryCol;

	@FXML
	TableColumn<Customer, String> levelCol;

	@FXML
	TableColumn<Customer, String> receiveCol;

	@FXML
	TableColumn<Customer, String> paymentCol;
	
	@FXML
	TableColumn<Customer, String> numberCol;
	
	static String keyword;
	
	static String keytype;
	
	ArrayList<CustomerVO> customervolist = null;
	
	ObservableList<Customer> data;
	
	static ArrayList<String> keywords = new ArrayList<>();
	
	static ArrayList<String> keytypes = new ArrayList<>();

	/**
	 * ����������Ա�ͻ�����������ĳ�ʼ������
	 *@return ���ؼ��غõĽ���
	 *@param keyword1 �����ؼ���
	 *@param keytype1 ��������
	 */
	public static Parent init(String keyword1,String keytype1){
		
		keywords.add(keyword1);
		keytypes.add(keytype1);
		if (keytype1.equals("�ͻ�����")) {
			keytype = "name";
		}else {
			keytype = "ID";
		}
		keyword = keyword1;
		
		try {
			GridPane customersearchresult = FXMLLoader.load(SearchResultFrame.class.getResource("SearchResultFXML.fxml"));
			return customersearchresult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *����fxmlʱ�Զ����صķ���,��ʼ����������ͻ��б�
	 */
    public void initialize() {
		
		resulttitle.setText(keytypes.get(keytypes.size()-1)+"'"+keywords.get(keywords.size()-1)+"'"+"���������");
		data = FXCollections.observableArrayList();

		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));
		receiveCol.setCellValueFactory(new PropertyValueFactory<>("receive"));
		paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

		
		try {
			customervolist = RemoteHelper.getCustomerBLService().customerFind(keyword, keytype);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < customervolist.size(); i++) {
			data.add(new Customer(
					customervolist.get(i).getID(),
					customervolist.get(i).getName(), 
					customervolist.get(i).getCategory(), 
					customervolist.get(i).getLevel(), 
					customervolist.get(i).getReceive()+"", 
					customervolist.get(i).getPayment()+"", 
					customervolist.get(i).getNumber()));
		}
		table.setItems(data);




	}
	
	/**
	 * ���������ͻ���ť�ļ���
	 */
	@FXML
	protected void returnCustomerManageFrame(ActionEvent event) {
		MainFrame.setSceneRoot(CustomerManageFrame.init());
	}
	
	/**
	 * ���ؽ���������Ա�����水ť�ļ���
	 */
	@FXML
	protected void returnSalesmanHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(SalesmanHomeFrame.init());
    }

	/**
	 * ɾ���ͻ���ť�ļ���
	 */
	@FXML
	protected void deletecustomer(ActionEvent event) {
		CustomerVO deletevo = null;
		for (int i = 0; i < customervolist.size(); i++) {
			if (customervolist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				deletevo = customervolist.get(i);
				break;
			}
		}
		if (table.getSelectionModel().getSelectedIndex()==-1) {
			//��δѡ���۽�����
		}else {
		ResultMessage resultMessage = null;
		try {
			resultMessage = RemoteHelper.getCustomerBLService().customerDelete(deletevo);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		MainFrame.setSceneRoot(SearchResultFrame.init(keywords.get(keywords.size()-1), keytypes.get(keytypes.size()-1)));
		}
	}
	
	/**
	 * �޸Ŀͻ��ļ���
	 */
	@FXML
	protected void modifycustomer(ActionEvent event) {
		CustomerVO modifyvo = null;
		for (int i = 0; i < customervolist.size(); i++) {
			if (customervolist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				modifyvo = customervolist.get(i);
				break;
			}
		}
		if (table.getSelectionModel().getSelectedIndex()==-1) {
			//��δѡ���۽�����
		}else {
		CustomerModifyFrame customerModifyFrame = new CustomerModifyFrame();
		customerModifyFrame.init(modifyvo,"Searchscene");
		}
	}
	
	/**
	 * ˫���в鿴�û�������Ϣ�ļ���
	 */
	@FXML
	protected void detailinfo(MouseEvent event) {
		CustomerVO detailvo = null;
		for (int i = 0; i < customervolist.size(); i++) {
			if (customervolist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				detailvo = customervolist.get(i);
				break;
			}
		}
		if (event.getClickCount() == 2) {
//				System.out.println(table.getSelectionModel().getSelectedIndex());
//				CustomerVO detailcustomer = customervolist.get(table.getSelectionModel().getFocusedIndex());
				CustomerdetailInfoFrame customerdetailInfoFrame = new CustomerdetailInfoFrame();
				customerdetailInfoFrame.init(detailvo);
		}
	}
	
	
	/**
	 * ���ڿͻ�tableview�󶨵�������
	 * @author ����ΰ
	 */
	public class Customer{
			
			private  String id;
			private  String name;
			private  String category;
			private  String level;
			private  String receive;
			private  String payment;
			private  String number;
			
			
			public Customer() {
				// TODO Auto-generated constructor stub
			}
			
		public Customer(String Id,String Name,String Category,String Level,String Receive,String Payment,String Number){
				this.setId(Id);
				this.setName(Name);
				this.setCategory(Category);
				this.setLevel(Level);
				this.setReceive(Receive);
				this.setPayment(Payment);
				this.setNumber(Number);
				
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

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getReceive() {
			return receive;
		}

		public void setReceive(String receive) {
			this.receive = receive;
		}

		public String getPayment() {
			return payment;
		}

		public void setPayment(String payment) {
			this.payment = payment;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}


		}

	
	
}
