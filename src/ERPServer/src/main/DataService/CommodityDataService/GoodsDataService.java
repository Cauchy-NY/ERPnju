package main.DataService.CommodityDataService;

import java.util.ArrayList;

import com.aliyuncs.exceptions.ClientException;

import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.utility.ResultMessage;

public interface GoodsDataService {
	/**
	 * ������Ʒ
	 * @param po
	 * @return ���ز����Ƿ�ɹ�
	 */
	public boolean add(GoodsPO po);
	
	/**
	 * ɾ����Ʒ
	 * @param po
	 * @return ���ز����Ƿ�ɹ�
	 */
	public boolean delete(GoodsPO po);
	
	/**
	 * �޸���Ʒ
	 * @param po
	 * @return ���ز����Ƿ�ɹ�
	 */
	public boolean modify(GoodsPO po);
	
	/**
	 * ͨ���ؼ��ֺ����Ͳ�����Ʒ
	 * @param keyword
	 * @param type
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> find(String keyword, String type);

	/**
	 * ���¿����Ʒ������������ۼۣ������۵���Ϣ
	 * @param list
	 * @param type ��������
	 * @param lebal ״̬
	 * @return resultmessage
	 */
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal);

	/**
	 * ��һ������Ʒ����id�ĺ�벿�֣����ڸ÷����µ���Ӵ���
	 * @param string
	 * @return ��һ����Ʒid�ĺ�벿��
	 */
	int newID(String string);

	/**
	 * ���;���������Ϣ���ֿ������Ա���ѽ���
	 * @param po
	 * @throws ClientException
	 */
	void sentAlertMessage(GoodsPO po) throws ClientException;
}
