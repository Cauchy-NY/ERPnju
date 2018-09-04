package main.BussinessLogic.CommodityBL;

import java.util.ArrayList;

import main.PO.CommodityReciptPO;
import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 
 * @author �����
 *
 */
public interface CommodityService {

	/**
	 * �����ͺ͹ؼ��ַ�����Ʒ�б�
	 * @param keyword
	 * @param type
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> getGoodsList(String keyword, String type);
	

	/**
	 * ����������Ʒ�б�
	 * @param all
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> getGoodsList(String all);
	
	/**
	 * ����δ������浥��
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> getUncheckedCommodityRecipt();
	
	/**
	 * ������������浥��
	 * ��浥��û�пͻ����Ͳ���Ա������������Ϊ���� �򲻷��ؿ�浥����
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CommodityReciptVO>
	 */
	public ArrayList<CommodityReciptVO> getCheckedCommodityRecipt(String startTime, String endTime,
			String customerName, String operator);
	
	/**
	 * �����浥List
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCommodityRecipt(ArrayList<CommodityReciptPO> list);
	
	/**
	 * ���¿����Ʒ����
	 * @param list ��Ʒ�б�
	 * @param type ��浥����
	 * @param lebal ����״̬��־
	 * @return ResultMessage
	 */
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal);
	
	/**
	 * �������͵�
	 * @param giftlist ������Ʒ�б�
	 * @return ResultMessage
	 */
	public ResultMessage createGiftCommodityRecipt(ArrayList<ReciptGoodsPO> giftlist);
	
	/**
	 * ͨ��id������Ʒ
	 * @param id
	 * @return GoodsVO
	 */
	public GoodsVO findGoodsByID(String id);

}

