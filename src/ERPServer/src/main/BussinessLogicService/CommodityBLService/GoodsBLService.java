package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.GoodsVO;
import main.utility.ResultMessage;

public interface GoodsBLService extends Remote{
	/**
	 * ������Ʒ
	 * @param vo ��Ʒvo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsAdd(GoodsVO vo) throws RemoteException;
	
	/**
	 * ɾ����Ʒ
	 * @param vo ��Ʒvo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsDelete(GoodsVO vo) throws RemoteException;
	
	/**
	 * �޸���Ʒ
	 * @param vo ��Ʒvo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsModify(GoodsVO vo) throws RemoteException;
	

	/**
	 * ������Ʒ
	 * @param keyword �ؼ���
	 * @param type ����
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> goodsFind(String keyword, String type) throws RemoteException;
	
	/**
	 * ������Ʒ����һ��ID
	 * @param category��Ʒ����
	 * @return string ��ƷID
	 * @throws RemoteException
	 */
	public String getNextGoodsId(String category) throws RemoteException;
	
	/**
	 * ��Ʒ�Ƿ��ܱ�ɾ�����Ƿ��н�����Ϣ��
	 * @param goodID ��ƷID
	 * @return boolean ��Ʒ�Ƿ���ɾ��
	 * @throws RemoteException
	 */
	public boolean couldBeDeleted(String goodID) throws RemoteException;
	
}
