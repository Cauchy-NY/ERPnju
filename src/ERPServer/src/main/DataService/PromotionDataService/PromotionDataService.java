package main.DataService.PromotionDataService;

import java.util.ArrayList;

import main.PO.PromotionPO;
import main.utility.PromotionType;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface PromotionDataService {
	
	/**
	 * �����µ��Żݲ���
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage insert(PromotionPO po);
	
	/**
	 * ����idɾ���Żݲ���
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage delete(String id);
	
	/**
	 * �����û�������Ϣ
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage update (PromotionPO po);
	
	/**
	 * ����id�����Żݲ���
	 * @param id
	 * @return PromotionPO
	 */
	public PromotionPO find(String id);
	
	/**
	 * ����������ȡ���������б�
	 * @param type
	 * @return ArrayList<PromotionPO>
	 */
	public ArrayList<PromotionPO> getPromotionList(PromotionType type);
	
}
