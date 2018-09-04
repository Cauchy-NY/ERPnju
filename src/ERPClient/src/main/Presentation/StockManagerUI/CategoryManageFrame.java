package main.Presentation.StockManagerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CategoryVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * ��������Ա��Ʒ�������Ŀ�����
 * @author ����ΰ
 *
 */
public class CategoryManageFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	@FXML
	TreeView<String> treeview;
	
	@FXML
	TextField categoryid;
	
	@FXML
	TextField detailcategory;
	
	@FXML
	TextField hasitem;
	
	@FXML
	TextField addtextfield;
	
	@FXML
	TextField modifytextfield;
	
	@FXML
	Label addmessagelabel;
	
	@FXML
	Label modifymessagelabel;
	
	@FXML 
	Label isdeletedmessage;
	
	@FXML
	AnchorPane addmessageanchor;
	
	@FXML
	AnchorPane deletemessageanchor;
	
	@FXML
	AnchorPane modifymessageanchor;
	
	ArrayList<Goodscategory> allcategorydata = new ArrayList<>();
	
	ArrayList<TreeItem<String>> categorytreeitems = new ArrayList<>();
	
	ArrayList<CategoryVO> allcategory = null;
	
	ArrayList<String> readycheckleafcategory = new ArrayList<>();
		
//	CategoryVO voget = null;
	
	

	/**
	 * ����������Ա������ĳ�ʼ������
	 * @return ���ؼ��غõĽ���
	 */
	public static Parent init(){
		
		try {
			GridPane categorymanage = FXMLLoader.load(CategoryManageFrame.class.getResource("CategoryManageFrameFXML.fxml"));
			return categorymanage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * ����fxmlʱĬ�ϼ��صķ�����������е���Ʒ�б���г�ʼ��
	 */
	@FXML
	public void initialize(){

			try {
				allcategory = RemoteHelper.getGoodsCatagoryBLService().categoryAll();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		for (int i = 0; i < allcategory.size(); i++) {
			allcategorydata.add(new Goodscategory(allcategory.get(i)));

		}
		
		
		System.out.println(allcategorydata.size());
		for (int i = 0; i < allcategorydata.size(); i++) {

			categorytreeitems.add(new TreeItem<String>(allcategorydata.get(i).getMyvalue()));

			categorytreeitems.get(i).setExpanded(true);

		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			if (allcategorydata.get(i).getFatherStr().equals("Nofather")) {
				treeview.setRoot(categorytreeitems.get(i));
			}
		}

		
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			ArrayList<Goodscategory> temp = allcategorydata.get(i).getSonscategory();
			for (int j = 0; j < allcategorydata.size(); j++) {
					for (int j2 = 0; j2 < temp.size(); j2++) {
						if (allcategorydata.get(j).getMyvalue().equals(temp.get(j2).getMyvalue())) {
//							System.out.println(temp.get(j2).equals(allcategorydata.get(j)));
//							System.out.println(allcategorydata.get(j).getMyvalue()+" "+allcategorydata.get(j).getEpanded());
//							System.out.println(temp.get(j2).getMyvalue()+" "+temp.get(j2).getEpanded());
							categorytreeitems.get(i).getChildren().add(categorytreeitems.get(j));

						}
					}			

			}
			temp = null;
		}	

		//�������ѡ��ĳ����� �ļ���
		  treeview.getSelectionModel().selectedItemProperty().addListener(
                  new ChangeListener<TreeItem <String>>() {
                  @Override
                  public void changed(ObservableValue<? extends TreeItem<String>> observableValue,
                  TreeItem<String> oldItem, TreeItem<String> newItem) {
                	  String detail = "";
                	  String categoryidd = "";
//                	  boolean hasit = false;
                          for (int i = 0; i < allcategory.size(); i++) {
      						if (allcategory.get(i).getMyvalue().equals(newItem.getValue())) {
      							categoryidd = allcategory.get(i).getCategoryID();
//      							hasit = allcategory.get(i).getHasGoods();
      	                	  if (!newItem.getValue().equals(treeview.getRoot().getValue())) {
      							CategoryVO vos = null;
      							vos = allcategory.get(i);
      							detail = vos.getMyvalue();
      							
      							while (!vos.getFatherStr().equals("Nofather")) {
      								detail = vos.getFatherStr()+"-"+ detail;
      								for (int j = 0; j < allcategory.size(); j++) {
      									if (allcategory.get(j).getMyvalue().equals(vos.getFatherStr())) {
      										vos = allcategory.get(j);

      										break;
      									}
      								}
      							}
//      							detail = treeview.getRoot().getValue()+"-"+detail;
      	                	  }else {
								detail = treeview.getRoot().getValue();
							}
      							break;
      						}
      					}
                	detailcategory.setText(detail);
                	categoryid.setText(categoryidd);
//                	if (hasit==true) {
//						hasitem.setText("����Ʒ");
//					}else {
//						hasitem.setText("������Ʒ");
//					}
                	Goodscategory seclectcategory = null;
                	ArrayList<GoodsVO> check = null;
                	for (int i = 0; i < allcategorydata.size(); i++) {
						if (allcategorydata.get(i).getMyvalue().equals(newItem.getValue())) {
							seclectcategory = allcategorydata.get(i);
						}
					}
                	if (!seclectcategory.getSonscategory().isEmpty()) {
						hasitem.setText("������Ʒ");
					}else {
						try {
							check = RemoteHelper.getGoodsBLService().goodsFind(seclectcategory.getMyvalue(), "catagory");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (check.isEmpty()) {
							hasitem.setText("������Ʒ");
						}else {
							hasitem.setText("����Ʒ");
						}
					}
                	
                  }
              });
	}
	
	
	/**
	 * ���ӷ���
	 */
	@FXML
	protected void addcategory(ActionEvent event) {
		if (treeview.getSelectionModel().getSelectedItem()!=null) {
		boolean canbeadd;
		Goodscategory selectedgoodscategory = null;
		ArrayList<GoodsVO> sonsgoods = null;
		for (int i = 0; i < allcategorydata.size(); i++) {
			if (treeview.getSelectionModel().getSelectedItem().getValue().equals(allcategorydata.get(i).getMyvalue())) {
				selectedgoodscategory = allcategorydata.get(i);
				break;
			}
		}
		try {
			sonsgoods = RemoteHelper.getGoodsBLService().goodsFind(selectedgoodscategory.getMyvalue(), "catagory");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (sonsgoods.isEmpty()) {
			canbeadd = true;
		}else {
			canbeadd = false;
		}
		
		CategoryVO addvo = null;
			if (addtextfield.getText().equals("")) {
				addmessageanchor.setVisible(true);
				addmessagelabel.setText("!���������ӷ��������");
			}else if(canbeadd==false){
				addmessageanchor.setVisible(true);
				addmessagelabel.setText("!�÷����º�����Ʒ���޷���������");
			}else {			
				addvo = new CategoryVO(new ArrayList<>(), addtextfield.getText(), false, treeview.getSelectionModel().getSelectedItem().getValue(), "", "��������Ա",false);
				ResultMessage resultMessage = null;
				try {
					resultMessage =RemoteHelper.getGoodsCatagoryBLService().catagoryAdd(addvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (resultMessage.equals(ResultMessage.SUCCESS)) {
					addmessageanchor.setVisible(true);
					addmessagelabel.setText("��ӳɹ�");
				}
			}
		
		}else {
			addmessageanchor.setVisible(true);
			addmessagelabel.setText("!��ѡ�������");
		}
		
    }
	
	
	void find(Goodscategory a){  //�õ�ѡ���������Ҷ�ڵ��name
		if (a.getSonscategory().isEmpty()) {
			readycheckleafcategory.add(a.getMyvalue());
		}else {
			for (int i = 0; i < a.getSonscategory().size(); i++) {
				find(a.getSonscategory().get(i));
			}
		}
	}
	
	/**
	 * ɾ������
	 */
	@FXML
	protected void deletecategory(ActionEvent event) {
	   if (treeview.getSelectionModel().getSelectedItem()!=null) {
	   boolean canbedelete = true;
       Goodscategory seclectitem = null;
	   ArrayList<GoodsVO> goodscheck = null;
	   for (int i = 0; i < allcategorydata.size(); i++) {
				if (allcategorydata.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
					seclectitem = allcategorydata.get(i);
				}
			}
	   find(seclectitem);	   	
	   for (int j = 0; j < readycheckleafcategory.size(); j++) {
	   		
				try {
					goodscheck = RemoteHelper.getGoodsBLService().goodsFind(readycheckleafcategory.get(j), "catagory");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!goodscheck.isEmpty()) {
					canbedelete = false;
					break;
				}
				
			}
	   
	   
       CategoryVO deletevo = null;
       if (canbedelete == false) {
    	   deletemessageanchor.setVisible(true);
    	   isdeletedmessage.setText("!�÷�������ӷ����º�����Ʒ�޷�ɾ���÷���");
    	   readycheckleafcategory.clear();
       }else {
    	   for (int i = 0; i < allcategory.size(); i++) {
    			if (allcategory.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
    				deletevo = allcategory.get(i);
    			}
    	       }
    	   ResultMessage resultMessage = null;
           try {
        	   resultMessage = RemoteHelper.getGoodsCatagoryBLService().catagoryDelete(deletevo);
           } catch (RemoteException e) {
        	   // TODO Auto-generated catch block
        	   e.printStackTrace();
           }
           if (resultMessage.equals(ResultMessage.SUCCESS)) {
        	   deletemessageanchor.setVisible(true);
        	   isdeletedmessage.setText("ɾ���ɹ�");
           }
//           updateExpanded();
       }
	   }else {
		   deletemessageanchor.setVisible(true);
    	   isdeletedmessage.setText("!��ѡ��Ԥɾ���ķ���");
	}
    }
	
	/**
	 * �޸ķ���
	 */
	@FXML
	protected void modifycategory(ActionEvent event) {
		CategoryVO modifyvo = null;
		if (treeview.getSelectionModel().getSelectedItem()!=null) {
			for (int i = 0; i < allcategorydata.size(); i++) {
				if (allcategorydata.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
					modifyvo = allcategory.get(i);
				}
			}
			if (modifytextfield.getText().equals("")) {
				modifymessageanchor.setVisible(true);
				modifymessagelabel.setText("!�������޸�����");
			}else {
				
				modifyvo.setMyvalue(modifytextfield.getText());
				ResultMessage resultMessage = null;
				try {
					resultMessage = RemoteHelper.getGoodsCatagoryBLService().catagoryModify(modifyvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				if (resultMessage.equals(ResultMessage.SUCCESS)) {
					modifymessageanchor.setVisible(true);
					modifymessagelabel.setText("�޸ĳɹ�");
				}
//				updateExpanded();
			}
			
		}else {
			modifymessageanchor.setVisible(true);
			modifymessagelabel.setText("!��ѡ��Ԥ�޸ĵķ���");
		}

    }
	
	/**
	 * ���ؿ�������Ա�����水ť�ļ���
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
	
	/**
	 * ��ӽ�����Ϣ��ʾ
	 */
	@FXML
	public void addmessagesure(){
		addmessageanchor.setVisible(false);
		if(addmessagelabel.getText().equals("��ӳɹ�")){
			MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * ɾ��������Ϣ��ʾ
	 */
	@FXML
	public void deletemessagesure(){
		deletemessageanchor.setVisible(false);
        if (isdeletedmessage.getText().equals("ɾ���ɹ�")) {
        	MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * �޸Ľ�����Ϣ��ʾ
	 */
	@FXML
	public void modifymessagesure(){
		modifymessageanchor.setVisible(false);
		if (modifymessagelabel.getText().equals("�޸ĳɹ�")) {
			MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * ���ڷ���treeview�󶨵�������
	 * @author ����ΰ
	 */
	class Goodscategory {

			private ArrayList<Goodscategory> sonscategory = new ArrayList<>();
			private String myvalue;
//			private Boolean epanded;
			private String fatherStr;
			private Boolean hasitems;
			
			
			public Goodscategory(CategoryVO vo) {
				setMyvalue(vo.getMyvalue());
//				setEpanded(vo.getEpanded());
				setFatherStr(vo.getFatherStr());
				setHasitems(vo.getHasGoods());
				ArrayList<CategoryVO> list = vo.getSonscategory();
				ArrayList<Goodscategory> listchange = new ArrayList<>();

					for (int i = 0; i < list.size(); i++) {
						CategoryVO voson= list.get(i);
						Goodscategory changeson = new Goodscategory(voson);
						listchange.add(changeson);
					}
				setSonscategory(listchange);
			}
			
			public String getMyvalue() {
				return myvalue;
			}
			public void setMyvalue(String myvalue) {
				this.myvalue = myvalue;
			}
			public ArrayList<Goodscategory> getSonscategory() {
				return sonscategory;
			}
			public void setSonscategory(ArrayList<Goodscategory> sonscategory) {
				this.sonscategory = sonscategory;
			}
			
			public void addsons(Goodscategory s){
				this.sonscategory.add(s);
			}

			public String getFatherStr() {
				return fatherStr;
			}

			public void setFatherStr(String fatherStr) {
				this.fatherStr = fatherStr;
			}

			public Boolean getHasitems() {
				return hasitems;
			}

			public void setHasitems(Boolean hasitems) {
				this.hasitems = hasitems;
			}
		    	
		
	}
	
	
}
