package main.DataService.CommodityDataService;

import java.util.ArrayList;

import main.PO.CategoryPO;

public interface CatagoryDataService {
	/**
	 * ������Ʒ����
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean catagoryAdd(CategoryPO po);
	
	/**
	 * ɾ����Ʒ����
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean catagoryDelete(CategoryPO po);
	
	/**
	 * �޸���Ʒ����
	 * @param po
	 * @return �����Ƿ�ɹ�
	 */
	public boolean catagoryModify(CategoryPO po);
	
	/**
	 * ͨ���������ҵ�����PO�����ѯ�������ֱ�ӷ���δ��ɸѡ��
	 * @param catagoryName
	 * @return ArrayList<CategoryPO>
	 */
	public ArrayList<CategoryPO> catagoryFind(String catagoryName);
	
}
