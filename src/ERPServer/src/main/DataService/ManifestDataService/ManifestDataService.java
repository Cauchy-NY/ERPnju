package main.DataService.ManifestDataService;

import java.util.ArrayList;

import main.PO.ManifestPO;
import main.utility.ResultMessage;
/**
 * 
 * @author �����
 *
 */
public interface ManifestDataService {
	/**
	 * ����Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean setManifest(ManifestPO po);
	
	/**
	 * ����Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean updateManifest(ManifestPO po);
	
	/**
	 * ��һ���������ڵ��յڼ����Ĳ���
	 * @param string
	 * @param date
	 * @return ��һ�����ݵ�intֵ
	 */
	public int newID(String string, String date);
	
	/**
	 * ͨ���ؼ��ֺ����Ͳ���Manifest
	 * @param keyword
	 * @param type
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> find(String keyword, String type);
	
	/**
	 * �޸�manifests
	 * @param list
	 * @return resultmessage
	 */
	public ResultMessage modify(ArrayList<ManifestPO> list);
	
	/**
	 * ɾ��Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean deleteManifest(ManifestPO po);
	
	/**
	 * ͨ����������õ�manifest
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return 
	 */
	public ArrayList<ManifestPO> getSalesHistoryList(String startTime, String endTime,
			String customerName,String operator);

	/**
	 * �޸�manifestPO
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage modifyManifest(ManifestPO po);
	
	/**
	 * ͨ��ʱ����ҵ���
	 * @param startTime
	 * @param endTime
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> findByTime(String startTime, String endTime);

}
