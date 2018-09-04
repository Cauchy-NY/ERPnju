package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.VO.CommodityReciptVO;
import main.utility.ResultMessage;

public interface CommodityReciptBLService extends Remote{
	/**
	 * ��ӿ�����͵�
	 * @param vo ��浥vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setGiftList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * ��ӿ�汨�絥
	 * @param vo ��浥vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setOverflowList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * ��ӿ�汨��
	 * @param vo ��浥vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setDamageList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * ��ӿ�汨����
	 * @param vo ��浥vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setWarningList(CommodityReciptVO vo) throws RemoteException;
}
