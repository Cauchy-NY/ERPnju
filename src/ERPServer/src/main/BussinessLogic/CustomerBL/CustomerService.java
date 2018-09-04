package main.BussinessLogic.CustomerBL;

import java.util.ArrayList;

import main.PO.CustomerPO;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public interface CustomerService {
	/**
	 * ͨ���ͻ����ҵ�customer����
	 * @param customerName
	 * @return customerPO
	 */
	public CustomerPO findCustomer(String customerName);
	
	/**
	 * ͨ�����ͺ͹ؼ��ֲ��ҿͻ�
	 * @param keyword �ؼ���
	 * @param type ��������
	 * @return ArrayList<CustomerVO>
	 */
	public ArrayList<CustomerVO> customerFind(String keyword, String type);

	/**
	 * ͨ���������ͺͽ�Ǯ�㶮�ͻ�Ӧ��Ӧ��
	 * @param customerName
	 * @param type
	 * @param money
	 * @return ResultMessage
	 */
	public ResultMessage updateCustomer(String customerName, String type, double money);
	
}
