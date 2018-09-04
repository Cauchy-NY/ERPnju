package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.VO.CommodityInfoVO;
import main.utility.ResultMessage;

public interface CommodityBLService extends Remote{
	
	/**
	 * ���鿴
	 * @param startTime
	 * @param endTime
	 * @return commodityInfoVO �������鿴��4����Ϣ
	 * @throws RemoteException
	 */
	public CommodityInfoVO viewCommodity(String startTime, String endTime) throws RemoteException;
	
	/**
	 * ������̵���Ϣ����Excel
	 * @param fileName
	 * @param storeAdress
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage checkCommodityInfoToExcel(String fileName, String storeAdress) throws RemoteException;
	
}
