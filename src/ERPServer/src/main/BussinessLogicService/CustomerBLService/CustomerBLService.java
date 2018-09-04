package main.BussinessLogicService.CustomerBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CustomerVO;
import main.utility.ResultMessage;
/**
 * 
 * @author �����
 *
 */
public interface CustomerBLService extends Remote {
	
	/**
	 * �����ͻ�
	 * @param vo �ͻ�vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerAdd(CustomerVO vo ) throws RemoteException;
	
	/**
	 * ɾ���ͻ�
	 * @param vo �ͻ�vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerDelete(CustomerVO vo ) throws RemoteException;
	
	/**
	 * �޸Ŀͻ�
	 * @param vo �ͻ�vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerModify(CustomerVO vo ) throws RemoteException;
	
	/**
	 * ���ҿͻ�
	 * @param keyword �ؼ���
	 * @param type ����
	 * @return ArrayList<CustomerVO>�ͻ��б�
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> customerFind(String keyword, String type ) throws RemoteException;

	/**
	 * �õ���һ���ͻ�ID
	 * @param type
	 * @return string �ͻ�id
	 * @throws RemoteException
	 */
	public String getNextCustomerID(String type ) throws RemoteException;
}
