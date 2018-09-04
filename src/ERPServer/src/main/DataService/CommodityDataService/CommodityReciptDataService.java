package main.DataService.CommodityDataService;

import java.util.ArrayList;

import main.PO.CommodityReciptPO;
import main.utility.ResultMessage;

public interface CommodityReciptDataService {
	/**
	 * ������浥��
	 * @param po
	 * @return resultmessage �����Ƿ�ɹ�
	 */
	public ResultMessage add(CommodityReciptPO po);

	/**
	 * �޸Ŀ�浥��
	 * @param list ����list
	 * @return �����Ƿ�ɹ�
	 */
	public ResultMessage modify(ArrayList<CommodityReciptPO> list);

	/**
	 * ͨ���ؼ��ֺ����Ͳ��ҿ�浥��
	 * @param keyword
	 * @param type
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> find(String keyword, String type);

	/**
	 * ͨ��ʱ�䡢�ͻ���������Ա��ϲ�ѯ��浥��
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> find(String startTime, String endTime, String customerName, String operator);

}
