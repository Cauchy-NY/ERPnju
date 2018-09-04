package main.BussinessLogicService.ManifestBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

public interface ManifestBLService extends Remote{
	
	/**
	 * ���ý�����
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setGoodsInList(ManifestVO vo) throws RemoteException;
	
	/**
	 * ���ý����˻���
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setGoodsInReturnList(ManifestVO vo) throws RemoteException;
	
	/**
	 * �������۵�
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSaleList(ManifestVO vo) throws RemoteException;
	
	/**
	 * ���������˻���
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSaleReturnList(ManifestVO vo) throws RemoteException;
	
	/**
	 * �õ���ǰ����Ա������manifests
	 * @param operator
	 * @return ArrayList<ManifestVO>
	 * @throws RemoteException
	 */
	public ArrayList<ManifestVO> getOperatorManifests(String operator) throws RemoteException;
	
	/**
	 * ɾ��manifest
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage deleteManifest(ManifestVO vo) throws RemoteException;
	
	/**
	 * �õ�������ƷVO
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods() throws RemoteException;
	
	/**
	 * ͨ�����͵õ���һ��manifestID
	 * @param type
	 * @return String
	 * @throws RemoteException
	 */
	public String getNextManifestID(String type) throws RemoteException;
	
	/**
	 * ͨ���ͻ����͵õ����пͻ�����
	 * @param type
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getAllCustomerName(String type) throws RemoteException;

	/**
	 * �޸ĵ���
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modifyManifest(ManifestVO vo) throws RemoteException;
}
