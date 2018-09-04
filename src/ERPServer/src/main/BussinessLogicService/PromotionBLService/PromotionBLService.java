package main.BussinessLogicService.PromotionBLService;

import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import main.VO.*;
import main.utility.PromotionType;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface PromotionBLService extends Remote {
	
	/**
	 * ���ô�������
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setPromotion(PromotionVO vo, String operator) throws RemoteException;
	
	/**
	 * ����id��ȡ��������
	 * @param promotionID
	 * @return PromotionVO
	 * @throws RemoteException
	 */
	public PromotionVO getPromotion(String promotionID) throws RemoteException;
	
	/**
	 * �������Ͳ��Ҵ����б�
	 * @param type
	 * @return ArrayList<PromotionVO>
	 * @throws RemoteException
	 */
	public ArrayList<PromotionVO> getPromotionList(PromotionType type) throws RemoteException;
	
	/**
	 * ����ɾ���������Է���
	 * @param promotionIDs
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage deletePromotions(ArrayList<String> promotionIDs, String operator) throws RemoteException;
	
	/**
	 * �޸Ĵ�������
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modifyPromotion(PromotionVO vo, String operator) throws RemoteException;
	
	/**
	 * ��Ʒģ�����ҷ���
	 * @param condition
	 * @param part
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods (String condition, String part) throws RemoteException;
	
}
