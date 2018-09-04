package main.Presentation.FinancialStaffUI.RecordUI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.RecordFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.SaleStateVO;

/**
 * �鿴��Ӫ���������controller
 * @author 49869
 *
 */
public class SaleStateFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;

	@FXML
	TextField saleIncomeField;
	@FXML
	TextField goodsIncomeField;
	@FXML
	TextField goodsDiscountField;
	@FXML
	TextField saleDiscountField;
	@FXML
	TextField saleCostField;
	@FXML
	TextField goodsCostField;
	@FXML
	TextField profitField;
	
	RecordBLService recordBL;
	SaleStateVO saleStateVO;
	
	/**
	 * ���ز鿴��Ӫ��������
	 * @return ���غõĽ���
	 */
	public static Parent init() {
		try {
			GridPane saleStateFrame = FXMLLoader.load(SaleStateFrame.class.getResource("SaleStateFrameFXML.fxml"));
			return saleStateFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}

	public void initialize() {
		recordBL = RemoteHelper.getRecordBLService();
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		
		loadInfo();
	}
	
	void loadInfo() {
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		try {
			saleStateVO = recordBL.getSalesStateList(startTime, endTime);
			saleIncomeField.setText(""+saleStateVO.getSaleIncome());
			goodsIncomeField.setText(""+saleStateVO.getGoodsIncome());
			goodsDiscountField.setText(""+saleStateVO.getGoodsDiscount());
			saleDiscountField.setText("");		//TODO
			saleCostField.setText(""+saleStateVO.getSaleCost());
			goodsCostField.setText(""+saleStateVO.getGoodsCost());
			profitField.setText(""+saleStateVO.getTotal());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * datePicker�ļ���
	 */
	@FXML
	void setTime() {
		loadInfo();
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
	 * ȷ�ϰ�ť�ļ���
	 */
	@FXML
	void sure() {
		back();
	}

	/**
	 * ����ΪExcel��ť�ļ���
	 */
	@FXML
	void outputToExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel �ļ�(*.xls)", "*.xls"));
		fileChooser.setInitialFileName("��Ӫ�����-"+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+".xls");
		File file = fileChooser.showSaveDialog(new Stage());
		if(file == null) {
			return;
		}
		
		String name = file.getName();
		String path = file.getParentFile().getAbsolutePath();

//		System.out.println(name);
//		System.out.println(path);
		try {
			recordBL.saleStateToExcel(saleStateVO, name, path);
			Alert alert = new Alert(AlertType.INFORMATION, "�ɹ������� "+path+name);
			alert.show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
