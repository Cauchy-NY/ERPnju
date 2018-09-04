package main.BussinessLogicService.AccountBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
import main.VO.CustomerVO;
import main.VO.AccountVO;
import main.VO.BankAccountVO;

/**
 * @author Cauchy������
 */
public interface AccountBLService extends Remote {
	/**
	 * �����ڳ���Ϣ
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage set (AccountVO vo) throws RemoteException;
	
	/**
	 * �����ڳ���Ϣ
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage update (AccountVO vo) throws RemoteException;
	
	/**
	 * ��Ʒģ�����ҷ���
	 * @param condition
	 * @param part
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods (String condition, String part) throws RemoteException;

	/**
	 * �ͻ�ģ�����ұ�
	 * @param condition
	 * @param part
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getCustomer (String condition, String part) throws RemoteException;
	
	/**
	 * �����˻�ģ�����ҷ���
	 * @param part
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccount (String part) throws RemoteException;

	/**
	 * ��ȡ�ڳ���Ϣ
	 * @return AccountVO
	 * @throws RemoteException
	 */
	public AccountVO get () throws RemoteException;
}
