package main.BussinessLogicService.ReciptBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.BankAccountVO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CustomerVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface ReciptBLService extends Remote {
	/**
	 * �����տ
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setCollection (CollectionOrderVO vo) throws RemoteException;
	
	/**
	 * ���ø��
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setPaymentOrder (PaymentOrderVO vo) throws RemoteException;
	
	/**
	 * �����ֽ��õ�
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setCashBill (CashBillVO vo) throws RemoteException;	
	
	/**
	 * ����id���ҵ���
	 * @param id
	 * @return ReciptVO
	 * @throws RemoteException
	 */
	public ReciptVO findOne (String id) throws RemoteException;
	
	/**
	 * �����˻�ģ�����ҷ���
	 * @param part
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException;
	
	/**
	 * �ͻ�ģ�����ҷ���
	 * @param keyword
	 * @param customerName
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getCustomerList(String keyword, String customerName) throws RemoteException;
	
	/**
	 * ��ȡ���е����б�
	 * @return ArrayList<ReciptVO>
	 * @throws RemoteException
	 */
	public ArrayList<ReciptVO> getAllRecipts() throws RemoteException;
	
	/**
	 * ��ȡ���е��տ
	 * @return ArrayList<CollectionOrderVO>
	 * @throws RemoteException
	 */
	public ArrayList<CollectionOrderVO> getAllCollectionOrderList() throws RemoteException;
	
	/**
	 * ��ȡ���еĸ��
	 * @return ArrayList<PaymentOrderVO>
	 * @throws RemoteException
	 */
	public ArrayList<PaymentOrderVO> getAllPaymentOrderList() throws RemoteException;
	
	/**
	 * ��ȡ���е��ֽ��õ�
	 * @return ArrayList<CashBillVO>
	 * @throws RemoteException
	 */
	public ArrayList<CashBillVO> getAllCashBillList() throws RemoteException;
}
