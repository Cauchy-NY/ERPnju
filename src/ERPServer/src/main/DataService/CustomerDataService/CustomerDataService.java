package main.DataService.CustomerDataService;

import java.util.ArrayList;

import main.PO.CustomerPO;

public interface CustomerDataService {
	
	/**
	 * ��ӿͻ�
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean customerAdd(CustomerPO po);
	
	/**
	 * ɾ���ͻ�
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean customerDelete(CustomerPO po);
	
	/**
	 * �޸Ŀͻ�
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean customerModify(CustomerPO po);
	
	/**
	 * ���ҿͻ�
	 * @param id
	 * @return ͨ��id���ҵ��Ŀͻ�
	 */
	public CustomerPO customerFind(String id);
	
	/**
	 * �������пͻ�PO
	 * @return ArrayList<CustomerPO>
	 */
	public ArrayList<CustomerPO> customerAll();

	/**
	 * ͨ���ؼ��ֺ����Ͳ��ҿͻ�
	 * @param keyword
	 * @param type
	 * @return ArrayList<CustomerPO> ���������Ŀͻ�
	 */
	public ArrayList<CustomerPO> customerFind(String keyword, String type);

	/**
	 * �õ���һ���û����ڿͻ���ŵĺ�벿��
	 * @param category
	 * @return ��һ���û����ڿͻ���ŵĺ�벿��
	 */
	int getCustomerID(String category); 

}
