package main.Presentation.SalesmanUI;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerUI.CustomerAddFrame;
import main.Presentation.SalesmanUI.CustomerUI.CustomerModifyFrame;
import main.Presentation.SalesmanUI.CustomerUI.CustomerdetailInfoFrame;
import main.Presentation.SalesmanUI.CustomerUI.SearchFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

/**
 * ����������Ա�ͻ�����������Ŀ�����
 * @author ����ΰ
 *
 */
public class CustomerManageFrame  extends MainUIController{
	
	
	@FXML 
	ImageView head;
	
	@FXML
	TableView<CustomerAll> table;
	
	@FXML	
	TableColumn<CustomerAll,String> idCol;
	
	@FXML
	TableColumn<CustomerAll,String> nameCol;
	
	@FXML
	TableColumn<CustomerAll,String> categoryCol;
	
	@FXML
	TableColumn<CustomerAll,String> levelCol;
	
	@FXML
	TableColumn<CustomerAll,String> receiveCol;
	
	@FXML
	TableColumn<CustomerAll,String> paymentCol;
	
	@FXML
	TableColumn<CustomerAll,String> numberCol;
	
	ObservableList<CustomerAll> tablelist;
	
	ArrayList<CustomerVO> customerslist = null;
	
	@FXML
	Button deletecustomer;
	
	@FXML
	Button modifycustomer;
	
	/**
	 * ����������Ա�ͻ�����������ĳ�ʼ������
	 *@return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		try {
			GridPane customermanage = FXMLLoader.load(CustomerManageFrame.class.getResource("CustomerManageFXML.fxml"));
			return customermanage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 *����fxmlʱ�Զ����صķ�������ʼ���ͻ�������б�
	 */
	public void initialize() {
			//����table������ӳ���
		  	tablelist = FXCollections.observableArrayList();
           
	        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));//ӳ��          
	        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); 
	        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
			levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));
			receiveCol.setCellValueFactory(new PropertyValueFactory<>("receive"));
			paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
			numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
			
			//�õ����пͻ���Ϣ
	        try {
				customerslist = RemoteHelper.getCustomerBLService().customerFind("","name");
			} catch (RemoteException e) {

				e.printStackTrace();
			}

	        for (int i = 0; i < customerslist.size(); i++) {
				tablelist.add(new CustomerAll(
						customerslist.get(i).getID(),
						customerslist.get(i).getName(),
						customerslist.get(i).getCategory(), 
						customerslist.get(i).getLevel(),
						customerslist.get(i).getReceive()+"",
						customerslist.get(i).getPayment()+"",
						customerslist.get(i).getNumber()));
			}
	        table.setItems(tablelist); //tableview���list
	        
	        //���ͻ�table��Ӽ������ò���ɾ�İ�ť��disble����
	        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount() == 1){
							if (table.getSelectionModel().getSelectedItem()==null) {
								
							}else {
								modifycustomer.setDisable(false);
							if (!table.getSelectionModel().getSelectedItem().getReceive().equals("0")
									&&table.getSelectionModel().getSelectedItem().getCategory().equals("������")) {
								deletecustomer.setDisable(true);
							}
							if (!table.getSelectionModel().getSelectedItem().getPayment().equals("0")
									&&table.getSelectionModel().getSelectedItem().getCategory().equals("������")) {
								deletecustomer.setDisable(true);
							}
							if (table.getSelectionModel().getSelectedItem().getPayment().equals("0.0")
								&&table.getSelectionModel().getSelectedItem().getReceive().equals("0.0")){
								deletecustomer.setDisable(false);
							}
							}
					}

				}
			});
	}
	

	/**
	 * ���ؽ���������Ա�����水ť�ļ���
	 */
	@FXML
	public void returnSalesmanHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(SalesmanHomeFrame.init());
    }
	
	/**
	 * �������������Ա���ҽ��水ť�ļ���
	 */
	@FXML
	public void searchframeHandle(ActionEvent event) {
		SearchFrame searchFrame = new SearchFrame();
		searchFrame.init();
    }
	
	/**
	 * ��ӿͻ��ļ���
	 */
	@FXML
	public void customeraddframeHandle(ActionEvent event) {
		CustomerAddFrame customerAddFrame = new CustomerAddFrame();
		customerAddFrame.init();
        
    }
	
	
	/**
	 * ɾ���ͻ��ļ���
	 */
	@FXML
	public void deletecustomer(ActionEvent event) {
		CustomerVO deletevo = null;
		for (int i = 0; i < customerslist.size(); i++) {
			if (customerslist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				deletevo = customerslist.get(i);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
		}
		//�������ַ������� �ڶ��ּ�
//		customerslist.remove(table.getSelectionModel().getSelectedIndex());
//		tablelist.remove(table.getSelectionModel().getSelectedIndex());
		MainFrame.setSceneRoot(CustomerManageFrame.init());
		}
	}
	
	/**
	 * �޸Ŀͻ��ļ���
	 */
	@FXML
	public void modifycustomer(ActionEvent event) {
		CustomerVO modifyvo = null;
		for (int i = 0; i < customerslist.size(); i++) {
			if (customerslist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				modifyvo = customerslist.get(i);
				break;
			}
		}
		if (table.getSelectionModel().getSelectedIndex()==-1) {
			//��δѡ���۽�����
		}else {
		CustomerModifyFrame customerModifyFrame = new CustomerModifyFrame();
		customerModifyFrame.init(modifyvo,"CustomerManagescene");
		}
    }
	
	/**
	 * ˫���в鿴�û�������Ϣ�ļ���
	 */
	@FXML
	protected void detailinfo(MouseEvent event) {
		CustomerVO detailvo = null;
		for (int i = 0; i < customerslist.size(); i++) {
			if (customerslist.get(i).getID().equals(table.getSelectionModel().getSelectedItem().getId())) {
				detailvo = customerslist.get(i);
				break;
			}
		}
		if (event.getClickCount() == 2) {
				CustomerdetailInfoFrame customerdetailInfoFrame = new CustomerdetailInfoFrame();
				customerdetailInfoFrame.init(detailvo);
		}
	}

	
	/**
	 * ���ڿͻ�tableview�󶨵�������
	 * @author ����ΰ
	 */
	public class CustomerAll{
		
		private  String id;
		private  String name;
		private  String category;
		private  String level;
		private  String receive;
		private  String payment;
		private  String number;
		
		
		public CustomerAll() {
		}
		
		public CustomerAll(String Id,String Name,String Category,String Level,String Receive,String Payment,String Number){
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


