package main.BussinessLogicService.BankAccountBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.Remote;

import main.VO.BankAccountVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface BankAccountBLService extends Remote {
	/**
	 * ��������˻�
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage add (BankAccountVO vo) throws RemoteException;
	
	/**
	 * ����idɾ�������˻������������Ա
	 * @param id
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage delete (String id, String operator) throws RemoteException;
	
	/**
	 * ���������˻�
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modify (BankAccountVO vo) throws RemoteException;

	/**
	 * ����id���������˻�
	 * @param id
	 * @return BankAccountVO
	 * @throws RemoteException
	 */
	public BankAccountVO find (String id) throws RemoteException;
	
	/**
	 * ����idģ�����������˻�
	 * @param partId
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> partFind(String partId) throws RemoteException;
	
	/**
	 * ��ȡ�����˻��б�
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException;
}
