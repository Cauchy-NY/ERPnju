package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;

public class CollectionOrderPO extends ReciptPO {
	//�������Ա���Ϊprivate
	private String operator;  //����Ա
	private String supplier;  //������
	private String retailer;  //������
	private ArrayList<CollectionItemPO> itemList;
	private double sum;
	
	//Ĭ�Ϲ��췽��
	public CollectionOrderPO() {
		super();
	}
	
	public CollectionOrderPO(CollectionOrderVO vo) {
		super(vo.getId(), vo.getState());
		this.operator = vo.getOperator();
		this.supplier = vo.getSupplier();
		this.retailer = vo.getRetailer();
		
		ArrayList<CollectionItemPO> poList = new ArrayList<CollectionItemPO>();
		ArrayList<CollectionItemVO> voList = vo.getItemList();
		for(int i = 0; i < voList.size(); i++) {
			CollectionItemPO po = new CollectionItemPO(voList.get(i));
			poList.add(po);
		}
		this.itemList = poList;
		this.sum = vo.getSum();
	}

	public CollectionOrderPO(String operator, String supplier, String retailer, ArrayList<CollectionItemPO> list, double sum) {
		super();
		this.operator = operator;
		this.supplier = supplier;
		this.retailer = retailer;
		this.itemList = list;
		this.sum = sum;
	}
	
	//���������䱸get��set����
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public ArrayList<CollectionItemPO> getItemList() {
		return itemList;
	}

	public void setItemList(List<CollectionItemPO> itemList) {
		this.itemList = new ArrayList<CollectionItemPO>(itemList);
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

}

