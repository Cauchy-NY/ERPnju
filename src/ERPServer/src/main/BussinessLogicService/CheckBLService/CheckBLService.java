package main.BussinessLogicService.CheckBLService;

import java.rmi.RemoteException;
import java.rmi.Remote;

import main.VO.InfoVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface CheckBLService extends Remote {
	
	/**
	 * ��ȡ����������
	 * @return InfoVO
	 * @throws RemoteException
	 */
	public InfoVO getInfo() throws RemoteException;
	
	/**
	 * �ܾ�����ĵ�����Ϣ�󷵻�����
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSuggestion(InfoVO vo, String operator) throws RemoteException;
	
}
