package main.BussinessLogic.CustomerBL;

import java.util.ArrayList;

import main.Data.CustomerData.CustomerData;
import main.DataService.CustomerDataService.CustomerDataService;
import main.PO.CustomerPO;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public class CustomerBL implements CustomerService{

	CustomerDataService service;

	public CustomerBL() {
		service = new CustomerData();
	}

	@Override
	public CustomerPO findCustomer(String customerName) {		
		return findByName(customerName);
	}
	
	@Override
	public ArrayList<CustomerVO> customerFind(String keyword, String type) {
		ArrayList<CustomerPO> list = service.customerFind(keyword, type);
		ArrayList<CustomerVO> retlist = new ArrayList<>();
		for(CustomerPO po : list){
			retlist.add(new CustomerVO(po));
		}
		
		return retlist;
	}
	
	public ResultMessage customerAdd(CustomerVO vo) {
		
		String id = String.format("%06d", service.getCustomerID(vo.getCategory()));
		vo.setID(vo.getCategory()+id);
		
		if(find(vo.getID())!=null||findByName(vo.getName())!=null) 
			return ResultMessage.existedCustomer;
		
		if(service.customerAdd(new CustomerPO(vo)))
			return ResultMessage.SUCCESS;
		
		return ResultMessage.FAIL;
	}

	public ResultMessage customerDelete(CustomerVO vo) {
		
		CustomerPO temp = find(vo.getID());
		
		if(temp==null||temp.getIsDeleted()==true) 
			return ResultMessage.customerNotFound;
		
		if(service.customerDelete(new CustomerPO(vo))) 
			return ResultMessage.SUCCESS;
		
		return ResultMessage.FAIL;
	}

	public ResultMessage customerModify(CustomerVO vo) {
		
		CustomerPO temp = find(vo.getID());
		
		if(temp==null||temp.getIsDeleted()==true)
			return ResultMessage.customerNotFound;
		
		if(service.customerModify(new CustomerPO(vo))) 
			return ResultMessage.SUCCESS;
		
		return ResultMessage.FAIL;
	}
	
	public String getNextCustomerID(String type){
		
		String id = String.format("%06d", service.getCustomerID(type));		
		
		return type+id;	
	}

	//��ID�ľ�ȷ���ҷ��������ܲ鵽��deleted������
	public CustomerPO find(String id) {	
		CustomerPO temp = superFind(id);
		
		if(temp!=null&&temp.getIsDeleted()==false) 
			return temp;
		else 
			return null;
	}
	
	//��ID�ľ�ȷ���ҷ��������Բ鵽��deleted������
	public CustomerPO superFind(String id){
		return service.customerFind(id);
	}
	
	//ͨ���ͻ������ҵ��ͻ�PO
	public CustomerPO findByName(String name) {
		ArrayList<CustomerPO> list = service.customerAll();
		for(CustomerPO temp:list){			
			if(temp.getName().equals(name))
				return temp;
		}
		return null;
	}
	
	@Override
	public ResultMessage updateCustomer(String customerName, String type, double money){
		CustomerPO po = findCustomer(customerName);
		if(type.equals("XSD")||type.equals("SKD")){
			po.setReceive(po.getReceive()+money);
		}
		else if(type.equals("XSTHD")){
			po.setReceive(po.getReceive()-money);
		}
		else if(type.equals("JHD")||type.equals("FKD")){
			po.setPayment(po.getPayment()+money);
		}
		else if(type.equals("JHTHD")){
			po.setPayment(po.getPayment()-money);
		}
		
		service.customerModify(po);
		
		return ResultMessage.SUCCESS;
	}
	
}
