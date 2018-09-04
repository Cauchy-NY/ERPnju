package main.PO;

import main.VO.ReciptVO;

public class ReciptPO {
	//�������Ա���Ϊprivate
	private String id;
	private String state;

	//Ĭ�Ϲ��췽��
	public ReciptPO() {
		this.id = "";
		this.state = "Unckecked";
	}
	
	public ReciptPO(ReciptVO vo) {
		this.id = vo.getId();
		this.state = vo.getState();
	}
	
	public ReciptPO(String id, String state) {
		this.id = id;
		this.state = state;
	}
	
	//���������䱸get��set����
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
