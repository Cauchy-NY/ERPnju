package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CategoryVO;
import main.utility.ResultMessage;

public interface GoodsCatagoryBLService extends Remote{
	/**
	 * ��Ʒ��������
	 * @param vo ����vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryAdd(CategoryVO vo) throws RemoteException;
	
	/**
	 * ��Ʒ����ɾ��
	 * @param vo ����vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryDelete(CategoryVO vo) throws RemoteException;
	
	/**
	 * ��Ʒ�����޸�
	 * @param vo ����vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryModify(CategoryVO vo) throws RemoteException;
	
	/**
	 * �������з���VO
	 * @return ArrayList<CategoryVO> ���з���Vo
	 * @throws RemoteException
	 */
	public ArrayList<CategoryVO> categoryAll() throws RemoteException;
}
