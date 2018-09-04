package main.BussinessLogic.ManifestBL;

import java.util.ArrayList;

import main.PO.ManifestPO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

public interface ManifestService {
	/**
	 * �õ�����δ�����ĵ���
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> getUncheckedManifest();
	
	/**
	 * �õ������������ĵ���
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> getCheckedManifest();
	
	/**
	 * ����������������manifs
	 * @param list ��������manifest�б�
	 * @return ResultMessage
	 */
	public ResultMessage saveManifest(ArrayList<ManifestPO> list);
	
	/**
	 * ͨ������ʱ����ҵ���
	 * @param startTime
	 * @param endTime
	 * @return ���ϸ�ʱ��εĵ���
	 */
	public ArrayList<ManifestPO> findManifestByTime(String startTime, String endTime);
	
	/**
	 * ͨ����������жϷ������������۵���Ʒ
	 * @param startTime
	 * @param endTime
	 * @param goodsName ��Ʒ����
	 * @param customerName �ͻ�����
	 * @param operator ����Ա
	 * @return ArrayList<ReciptGoodsVO>
	 */
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator);
	
	/**
	 * ͨ������������ص���PO
	 * @param startTime
	 * @param endTime
	 * @param customerName �ͻ�����
	 * @param operator ����Ա
	 * @param type ��������
	 * @return ArrayList<ManifestVO>
	 */
	public ArrayList<ManifestVO> getSalesHistoryList(String startTime, String endTime,
			String customerName, String operator, DocumentsType type);
	
	/**
	 * ���ú�嵥��
	 * @param type ��嵥������
	 * @param id ��嵥�ݱ��
	 * @return boolean
	 */
	public boolean setRedDashed(String type, String id);
}

