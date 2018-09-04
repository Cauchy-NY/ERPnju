package main.PO;

import main.VO.BankAccountVO;

public class BankAccountPO {
	//�������Ա���Ϊprivate
	private String id;  //�����˻�id
	private double amount;  //�����˻����
	
	//Ĭ�Ϲ��췽��
	public BankAccountPO() {}
	
	public BankAccountPO(BankAccountVO vo) {
		this.id = vo.getId();
		this.amount = vo.getAmount();
	}
	
	public BankAccountPO (String id, double amount) {
		this.id = id;
		this.amount = amount;
	}
	
	//���������䱸get��set����
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
